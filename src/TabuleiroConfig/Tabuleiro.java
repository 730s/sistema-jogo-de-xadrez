package TabuleiroConfig;

public class Tabuleiro {

    private Integer linhas;
    private Integer colunas;
    private Peca[][] pecas;

    public Tabuleiro(int linhas, int colunas) {
        if (linhas < 1 || colunas < 1) {
            throw new ExcecoesTabuleiro("Erro ao criar tabuleiro, e necessario que haja pelo menos uma linha e uma coluna");
        }
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas];
    }
    public int getLinhas() {
        return linhas;
    }
    public int getColunas() {
        return colunas;
    }
    public Peca peca(int linha, int coluna) {
        if (!existenciaPosicao(linha, coluna)) {
            throw new ExcecoesTabuleiro("Posicao nao existente no tabuleiro");
        }
        return pecas[linha][coluna];
    }
    public Peca peca(Posicao posicao) {
        if (!existenciaPosicao(posicao)) {
            throw new ExcecoesTabuleiro("Posicao nao existente no tabuleiro");
        }
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }
    public void inserirPeca(Peca peca, Posicao posicao) {
        if (existenciaPeca(posicao)) {
            throw new ExcecoesTabuleiro("Ja existe uma peca na posicao");
        }
        pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.posicao = posicao;
    }
    public Peca removerPeca(Posicao posicao) {
        if (!existenciaPosicao(posicao)) {
            throw new ExcecoesTabuleiro("Posicao nao existente no tabuleiro");
        }
        if (peca(posicao) == null) {
            return null;
        }
        Peca pecaRetirada = peca(posicao);
        pecaRetirada.posicao = null;
        pecas[posicao.getLinha()][posicao.getColuna()] = null;
        return pecaRetirada;
    }
    private boolean existenciaPosicao(int linha, int coluna) {
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }

    public boolean existenciaPosicao(Posicao posicao) {
        return existenciaPosicao(posicao.getLinha(), posicao.getColuna());
    }
    public boolean existenciaPeca(Posicao posicao) {
        if (!existenciaPosicao(posicao)) {
            throw new ExcecoesTabuleiro("Posicao nao existente no tabuleiro");
        }
        return peca(posicao) != null;
    }
}
