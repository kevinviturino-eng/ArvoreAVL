import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ArvoreAvl arvore = new ArvoreAvl();
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            System.out.println();

            switch (opcao) {
                case 1 -> {
                    int v = lerInteiro("Valor a inserir: ");
                    if (arvore.inserir(v)) {
                        System.out.println("Valor " + v + " inserido com sucesso.");
                    } else {
                        System.out.println("Valor " + v + " já existe na árvore (duplicado). Nada foi inserido.");
                    }
                }
                case 2 -> {
                    int v = lerInteiro("Valor a remover: ");
                    if (arvore.remover(v)) {
                        System.out.println("Valor " + v + " removido com sucesso.");
                    } else {
                        System.out.println("Valor " + v + " não existe na árvore.");
                    }
                }
                case 3 -> {
                    int v = lerInteiro("Valor a buscar: ");
                    System.out.println(arvore.buscar(v)
                            ? "Valor " + v + " ENCONTRADO na árvore."
                            : "Valor " + v + " NÃO encontrado na árvore.");
                }
                case 4 -> {
                    System.out.println("Pré-ordem:");
                    arvore.preOrdem();
                }
                case 5 -> {
                    System.out.println("Em ordem:");
                    arvore.emOrdem();
                }
                case 6 -> {
                    System.out.println("Pós-ordem:");
                    arvore.posOrdem();
                }
                case 7 -> {
                    System.out.println("Altura da árvore: " + arvore.alturaArvore());
                    System.out.println("Estrutura (direita no topo, esquerda embaixo):");
                    arvore.exibirArvore();
                }
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n========== ÁRVORE AVL ==========");
        System.out.println("1 - Inserir valor");
        System.out.println("2 - Remover valor");
        System.out.println("3 - Buscar valor");
        System.out.println("4 - Percurso pré-ordem");
        System.out.println("5 - Percurso em ordem");
        System.out.println("6 - Percurso pós-ordem");
        System.out.println("7 - Exibir árvore (altura e fatores de balanceamento)");
        System.out.println("0 - Sair");
        System.out.println("================================");
    }

    /** Lê um inteiro do teclado, repetindo até o usuário digitar um valor válido. */
    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String linha = sc.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }
}
