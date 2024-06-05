package PecasXadrez;

import TabuleiroConfig.Posicao;
import TabuleiroConfig.Tabuleiro;
import Xadrez.Cores;
import Xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {
    public Cavalo(Tabuleiro tabuleiro, Cores cor) {
        super(tabuleiro, cor);

    }
    @Override
    public String toString() {
        return "N";
    }
    private boolean podeMover(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p == null || p.getCor() != getCor();
    }
    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        Posicao p = new Posicao(0, 0);
        p.setMovimentos(posicao.getLinha() - 1, posicao.getColuna() - 2);
        if (getTabuleiro().existenciaPosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        p.setMovimentos(posicao.getLinha() -2, posicao.getColuna() - 1);
        if (getTabuleiro().existenciaPosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        p.setMovimentos(posicao.getLinha() - 2, posicao.getColuna() + 1);
        if (getTabuleiro().existenciaPosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        p.setMovimentos(posicao.getLinha() - 1, posicao.getColuna() + 2);
        if (getTabuleiro().existenciaPosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        p.setMovimentos(posicao.getLinha() + 1, posicao.getColuna() + 2);
        if (getTabuleiro().existenciaPosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        p.setMovimentos(posicao.getLinha() + 2, posicao.getColuna() + 1);
        if (getTabuleiro().existenciaPosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        p.setMovimentos(posicao.getLinha() + 2, posicao.getColuna() - 1);
        if (getTabuleiro().existenciaPosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        p.setMovimentos(posicao.getLinha() + 1, posicao.getColuna() - 2);
        if (getTabuleiro().existenciaPosicao(p) && podeMover(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        return matriz;
    }
}
