/**
 * Nó da árvore AVL: guarda o valor inteiro, a altura e as referências
 * para os filhos esquerdo e direito.
 */
public class No {
    int valor;
    int altura;   // folha = 1, subárvore vazia = 0
    No esquerdo;
    No direito;

    No(int valor) {
        this.valor = valor;
        this.altura = 1;
    }
}
