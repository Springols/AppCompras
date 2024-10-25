import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Compra {
    String descricao;
    double valor;

    public Compra(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Descrição: " + descricao + ", Valor: R$" + valor;
    }
}

public class AppCompras {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Compra> listaDeCompras = new ArrayList<>();

        // Define o limite de crédito e saldo
        System.out.print("Digite o limite de crédito: R$");
        double limiteCredito = scanner.nextDouble();
        double saldoRestante = limiteCredito;
        scanner.nextLine(); // Limpar buffer

        while (true) {
            System.out.println("\nMenu de Lançamento de Compras");
            System.out.println("1. Adicionar nova compra");
            System.out.println("2. Listar compras ordenadas por valor");
            System.out.println("3. Exibir saldo restante");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Erro: Por favor, insira um número válido para selecionar uma opção.");
                scanner.nextLine(); // Limpar buffer
                continue;
            }

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            if (opcao == 1) {
                System.out.print("Digite a descrição da compra: ");
                String descricao = scanner.nextLine();
                System.out.print("Digite o valor da compra: R$");

                if (!scanner.hasNextDouble()) {
                    System.out.println("Erro: Por favor, insira um valor numérico.");
                    scanner.nextLine(); // Limpar buffer
                    continue;
                }

                double valor = scanner.nextDouble();
                scanner.nextLine(); // Limpar buffer

                if (valor > saldoRestante) {
                    System.out.println("Compra não autorizada! Saldo insuficiente. Saldo disponível: R$" + saldoRestante);
                } else {
                    listaDeCompras.add(new Compra(descricao, valor));
                    saldoRestante -= valor;
                    System.out.println("Compra adicionada com sucesso!");
                }

                System.out.print("Deseja realizar outra operação? (s/n): ");
                String resposta = scanner.nextLine().trim().toLowerCase();

                if (!resposta.equals("s")) {
                    System.out.println("Saindo...");
                    break;
                }

            } else if (opcao == 2) {
                Collections.sort(listaDeCompras, Comparator.comparingDouble(compra -> compra.valor));
                System.out.println("\nLista de Compras (ordenada por valor):");
                for (Compra compra : listaDeCompras) {
                    System.out.println(compra);
                }
            } else if (opcao == 3) {
                System.out.println("Saldo restante: R$" + saldoRestante);
            } else if (opcao == 4) {
                System.out.println("Saindo...");
                break;
            } else {
                System.out.println("Opção inválida, tente novamente.");
            }
        }
        scanner.close();
    }
}
