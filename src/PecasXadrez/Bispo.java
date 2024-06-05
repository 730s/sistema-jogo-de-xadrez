package PecasXadrez;

import TabuleiroConfig.Posicao;
import TabuleiroConfig.Tabuleiro;
import Xadrez.Cores;
import Xadrez.PecaXadrez;

public class Bispo extends PecaXadrez {
    public Bispo(Tabuleiro tabuleiro, Cores cor) {
        super(tabuleiro, cor);
    }
    @Override
    public String toString() {
        return "B";
    }
    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        Posicao p = new Posicao(0, 0);
        // Diag Esq. Superior
        p.setMovimentos(posicao.getLinha() - 1, posicao.getColuna() - 1);
        while (getTabuleiro().existenciaPosicao(p) && !getTabuleiro().existenciaPeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setMovimentos(p.getLinha() - 1, p.getColuna() - 1);
        }
        if (getTabuleiro().existenciaPosicao(p) && existemPecasAdversarias(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        // Diag Dir. Superior
        p.setMovimentos(posicao.getLinha() - 1, posicao.getColuna() + 1);
        while (getTabuleiro().existenciaPosicao(p) && !getTabuleiro().existenciaPeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setMovimentos(p.getLinha() - 1, p.getColuna() + 1);
        }
        if (getTabuleiro().existenciaPosicao(p) && existemPecasAdversarias(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        // Diag Dir. Inferior
        p.setMovimentos(posicao.getLinha() + 1, posicao.getColuna() + 1);
        while (getTabuleiro().existenciaPosicao(p) && !getTabuleiro().existenciaPeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setMovimentos(p.getLinha() + 1, p.getColuna() + 1);
        }
        if (getTabuleiro().existenciaPosicao(p) && existemPecasAdversarias(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        // Diag Esq. Inferior
        p.setMovimentos(posicao.getLinha() + 1, posicao.getColuna() - 1);
        while (getTabuleiro().existenciaPosicao(p) && !getTabuleiro().existenciaPeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setMovimentos(p.getLinha() + 1, p.getColuna() - 1);
        }
        if (getTabuleiro().existenciaPosicao(p) && existemPecasAdversarias(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        return matriz;
    }
}
