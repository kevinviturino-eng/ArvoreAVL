# Árvore AVL em Java

Implementação de uma Árvore AVL de números inteiros, com menu textual.

## Funcionalidades
- Inserção, remoção (0, 1 ou 2 filhos) e busca
- Rotações LL, RR, LR e RL (o tipo é informado no terminal)
- Tratamento de valores duplicados e de busca/remoção de valores inexistentes
- Percursos pré-ordem, em ordem e pós-ordem
- Exibição da altura da árvore e do fator de balanceamento (fb) de cada nó

Convenção: altura de folha = 1 e de subárvore vazia = 0.
Fator de balanceamento = altura(esquerda) - altura(direita).

## Requisitos
- Java 20

## Como executar

### Pelo IntelliJ
1. `File > Open` e selecione a pasta `Arvores_AVL`.
2. Marque a pasta `src` como *Sources Root* (botão direito > Mark Directory as > Sources Root).
3. Em `File > Project Structure`, defina o SDK como Java 20.
4. Execute a classe `Main`.

### Pelo terminal
```
cd src
javac *.java
java Main
```

## Estrutura
- `src/No.java` – nó (valor, altura, filhos esquerdo e direito)
- `src/ArvoreAvl.java` – operações da AVL
- `src/Main.java` – menu textual
