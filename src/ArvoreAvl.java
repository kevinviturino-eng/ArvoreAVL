/**
 * Árvore AVL de números inteiros (implementação própria, sem bibliotecas prontas).
 */
public class ArvoreAvl {

    private No raiz;
    private boolean operacaoRealizada; // sinaliza se inserir/remover alterou a árvore

    // ---------------------------------------------------------------
    // Funções auxiliares: altura e fator de balanceamento
    // ---------------------------------------------------------------
    private int altura(No no) {
        return no == null ? 0 : no.altura;
    }

    private void atualizarAltura(No no) {
        no.altura = 1 + Math.max(altura(no.esquerdo), altura(no.direito));
    }

    /** Fator de balanceamento = altura(esquerda) - altura(direita). */
    private int fator(No no) {
        return no == null ? 0 : altura(no.esquerdo) - altura(no.direito);
    }

    public int alturaArvore() {
        return altura(raiz);
    }

    public boolean estaVazia() {
        return raiz == null;
    }

    // ---------------------------------------------------------------
    // Rotações
    // ---------------------------------------------------------------
    private No rotacaoDireita(No y) {
        No x = y.esquerdo;
        No temp = x.direito;

        x.direito = y;
        y.esquerdo = temp;

        atualizarAltura(y);
        atualizarAltura(x);
        return x;
    }

    private No rotacaoEsquerda(No x) {
        No y = x.direito;
        No temp = y.esquerdo;

        y.esquerdo = x;
        x.direito = temp;

        atualizarAltura(x);
        atualizarAltura(y);
        return y;
    }

    /** Atualiza a altura do nó e aplica a rotação necessária (LL, RR, LR ou RL). */
    private No balancear(No no) {
        atualizarAltura(no);
        int fb = fator(no);

        if (fb > 1) {
            if (fator(no.esquerdo) >= 0) {
                System.out.println("  >> Rotação LL (simples à direita) no nó " + no.valor);
                return rotacaoDireita(no);
            }
            System.out.println("  >> Rotação LR (dupla esquerda-direita) no nó " + no.valor);
            no.esquerdo = rotacaoEsquerda(no.esquerdo);
            return rotacaoDireita(no);
        }

        if (fb < -1) {
            if (fator(no.direito) <= 0) {
                System.out.println("  >> Rotação RR (simples à esquerda) no nó " + no.valor);
                return rotacaoEsquerda(no);
            }
            System.out.println("  >> Rotação RL (dupla direita-esquerda) no nó " + no.valor);
            no.direito = rotacaoDireita(no.direito);
            return rotacaoEsquerda(no);
        }

        return no;
    }

    // ---------------------------------------------------------------
    // Inserção
    // ---------------------------------------------------------------
    /** @return true se inseriu; false se o valor já existia (duplicado). */
    public boolean inserir(int valor) {
        operacaoRealizada = false;
        raiz = inserir(raiz, valor);
        return operacaoRealizada;
    }

    private No inserir(No no, int valor) {
        if (no == null) {
            operacaoRealizada = true;
            return new No(valor);
        }

        if (valor < no.valor) {
            no.esquerdo = inserir(no.esquerdo, valor);
        } else if (valor > no.valor) {
            no.direito = inserir(no.direito, valor);
        } else {
            return no; // duplicado: não insere
        }

        return balancear(no);
    }

    // ---------------------------------------------------------------
    // Busca
    // ---------------------------------------------------------------
    public boolean buscar(int valor) {
        No atual = raiz;
        while (atual != null) {
            if (valor == atual.valor) return true;
            atual = valor < atual.valor ? atual.esquerdo : atual.direito;
        }
        return false;
    }

    // ---------------------------------------------------------------
    // Remoção
    // ---------------------------------------------------------------
    /** @return true se removeu; false se o valor não existe. */
    public boolean remover(int valor) {
        operacaoRealizada = false;
        raiz = remover(raiz, valor);
        return operacaoRealizada;
    }

    private No remover(No no, int valor) {
        if (no == null) return null;

        if (valor < no.valor) {
            no.esquerdo = remover(no.esquerdo, valor);
        } else if (valor > no.valor) {
            no.direito = remover(no.direito, valor);
        } else {
            operacaoRealizada = true;

            // Sem filhos ou com apenas um filho
            if (no.esquerdo == null) return no.direito;
            if (no.direito == null) return no.esquerdo;

            // Dois filhos: substitui pelo sucessor (menor da subárvore direita)
            No sucessor = menorNo(no.direito);
            no.valor = sucessor.valor;
            no.direito = remover(no.direito, sucessor.valor);
        }

        return balancear(no);
    }

    private No menorNo(No no) {
        while (no.esquerdo != null) no = no.esquerdo;
        return no;
    }

    // ---------------------------------------------------------------
    // Percursos (mostram valor, altura e fator de balanceamento)
    // ---------------------------------------------------------------
    private String info(No no) {
        return no.valor + "(h=" + no.altura + ", fb=" + fator(no) + ")";
    }

    public void preOrdem() {
        preOrdem(raiz);
        System.out.println();
    }

    private void preOrdem(No no) {
        if (no == null) return;
        System.out.print(info(no) + " ");
        preOrdem(no.esquerdo);
        preOrdem(no.direito);
    }

    public void emOrdem() {
        emOrdem(raiz);
        System.out.println();
    }

    private void emOrdem(No no) {
        if (no == null) return;
        emOrdem(no.esquerdo);
        System.out.print(info(no) + " ");
        emOrdem(no.direito);
    }

    public void posOrdem() {
        posOrdem(raiz);
        System.out.println();
    }

    private void posOrdem(No no) {
        if (no == null) return;
        posOrdem(no.esquerdo);
        posOrdem(no.direito);
        System.out.print(info(no) + " ");
    }

    // ---------------------------------------------------------------
    // Visualização da estrutura (árvore "deitada": direita em cima)
    // ---------------------------------------------------------------
    public void exibirArvore() {
        if (raiz == null) {
            System.out.println("(árvore vazia)");
            return;
        }
        exibir(raiz, 0);
    }

    private void exibir(No no, int nivel) {
        if (no == null) return;
        exibir(no.direito, nivel + 1);
        System.out.println("    ".repeat(nivel) + info(no));
        exibir(no.esquerdo, nivel + 1);
    }
}
