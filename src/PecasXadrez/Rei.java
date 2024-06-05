package PecasXadrez;

import TabuleiroConfig.Posicao;
import TabuleiroConfig.Tabuleiro;
import Xadrez.Cores;
import Xadrez.PecaXadrez;

public class Rei extends PecaXadrez {
    public Rei(Tabuleiro tabuleiro, Cores cor) {
        super(tabuleiro, cor);

    }
    @Override
    public String toString() {
        return "K";
    }

    private boolean podeMover(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p == null || p.getCor() != getCor();
    }
    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);

        // ACIMA
        p.setMovimentos(posicao.getLinha() - 1, posicao.getColuna());
        if (getTabuleiro().existenciaPosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        // ABAIXO
        p.setMovimentos(posicao.getLinha() + 1, posicao.getColuna());
        if (getTabuleiro().existenciaPosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        // ESQUERDA
        p.setMovimentos(posicao.getLinha(), posicao.getColuna() - 1);
        if (getTabuleiro().existenciaPosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        // DIREITA
        p.setMovimentos(posicao.getLinha(), posicao.getColuna() + 1);
        if (getTabuleiro().existenciaPosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        // Diag Esq. Superior
        p.setMovimentos(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if (getTabuleiro().existenciaPosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        // Diag Dir. Superior
        p.setMovimentos(posicao.getLinha() - 1, posicao.getColuna() + 1);
        if (getTabuleiro().existenciaPosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        // Diag Esq. Inferior
        p.setMovimentos(posicao.getLinha() + 1, posicao.getColuna() - 1);
        if (getTabuleiro().existenciaPosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        // Diag Dir. Inferior
        p.setMovimentos(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if (getTabuleiro().existenciaPosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        return matriz;
    }
}
