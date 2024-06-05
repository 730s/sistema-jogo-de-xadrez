package PecasXadrez;

import TabuleiroConfig.Posicao;
import TabuleiroConfig.Tabuleiro;
import Xadrez.Cores;
import Xadrez.PecaXadrez;

public class Torre extends PecaXadrez {
    public Torre(Tabuleiro tabuleiro, Cores cor) {
        super(tabuleiro, cor);
    }
    @Override
    public String toString() {
        return "R";
    }
    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        Posicao p = new Posicao(0, 0);
        // Acima
        p.setMovimentos(posicao.getLinha() - 1, posicao.getColuna());
        while (getTabuleiro().existenciaPosicao(p) && !getTabuleiro().existenciaPeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() - 1);
        }
        if (getTabuleiro().existenciaPosicao(p) && existemPecasAdversarias(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        // Esquerda
        p.setMovimentos(posicao.getLinha(), posicao.getColuna() - 1);
        while (getTabuleiro().existenciaPosicao(p) && !getTabuleiro().existenciaPeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() - 1);
        }
        if (getTabuleiro().existenciaPosicao(p) && existemPecasAdversarias(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        // Direita
        p.setMovimentos(posicao.getLinha(), posicao.getColuna() + 1);
        while (getTabuleiro().existenciaPosicao(p) && !getTabuleiro().existenciaPeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setColuna(p.getColuna() + 1);
        }
        if (getTabuleiro().existenciaPosicao(p) && existemPecasAdversarias(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        // Abaixo
        p.setMovimentos(posicao.getLinha() + 1, posicao.getColuna());
        while (getTabuleiro().existenciaPosicao(p) && !getTabuleiro().existenciaPeca(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
            p.setLinha(p.getLinha() + 1);
        }
        if (getTabuleiro().existenciaPosicao(p) && existemPecasAdversarias(p)) {
            matriz[p.getLinha()][p.getColuna()] = true;
        }
        return matriz;
    }
}
