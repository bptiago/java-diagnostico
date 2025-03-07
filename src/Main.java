import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Comida> cardapio = new ArrayList<>();
        cardapio.add(new Comida("Porção de batata-frita (200 gramas)", 20.12));
        cardapio.add(new Comida("Executivo de Frango", 24.99));

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Menu Inicial\n1 - Fazer pedido\n2 - Sair");
            try {
                int input = sc.nextInt();
                if (input != 1) {
                    System.out.println("Saindo...");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Inserir somente números inteiros. Reiniciando...");
                continue;
            }

            try {
                System.out.println("\nQual o seu nome?");
                sc.nextLine();
                String nome = sc.nextLine();
                if (nome.isBlank()) {
                    throw new IllegalArgumentException("String vazia");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Não utilizar nomes vazios. Reiniciando...");
                continue;
            }

            int count = 1;
            ArrayList<Comida> fatura = new ArrayList<>();
            System.out.println("\nExibindo cardápio...");
            for (Comida item : cardapio) {
                System.out.printf("%o: %s - R$ %.2f\n", count, item.getNome(), item.getPreco());
                count++;
            }
            while (true) {
                System.out.println("Digite o número do item a pedir ou digite '0' para finalizar o pedido:");
                try {
                    int itemEscolhido = sc.nextInt();
                    if (itemEscolhido == 0) break;
                    fatura.add(cardapio.get(itemEscolhido - 1));
                    System.out.println("Item registrado.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Tente digitando o número de um item existente...");
                } catch (InputMismatchException e) {
                    System.out.println("Digite uma entrada válida...");
                    sc.nextLine(); //limpar input prévio do scanner
                }
            }

            double preco = 0;
            for (Comida item : fatura) {
                preco += item.getPreco();
            }
            double taxa = preco * 0.1;
            System.out.println("\nPedido realizado:");
            for (Comida item : fatura) {
                System.out.printf("%s - R$ %.2f\n", item.getNome(), item.getPreco());
            }
            System.out.printf("Preço dos itens: R$ %.2f\n", preco);
            System.out.printf("Taxa de serviço: R$ %.2f\n", taxa);
            System.out.printf("Preço total: R$ %.2f\n", preco + taxa);

            System.out.println("\nQuanto dinheiro foi dado pelo cliente?");
            double dinheiroRecebido = sc.nextDouble();
            if (dinheiroRecebido < (preco + taxa)) {
                System.out.println("Você não tem dinheiro suficiente, vai ter que lavar louça lá na cozinha!");
                break;
            }
            System.out.printf("Troco de %.1f\n", dinheiroRecebido - preco);
            System.out.println("Finalizando pedido e voltando ao menu...\n");
        }

    }
}