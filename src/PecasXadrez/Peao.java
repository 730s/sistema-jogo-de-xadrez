package PecasXadrez;

import TabuleiroConfig.Posicao;
import TabuleiroConfig.Tabuleiro;
import Xadrez.Cores;
import Xadrez.PecaXadrez;

public class Peao extends PecaXadrez {
    public Peao(Tabuleiro tabuleiro, Cores cor) {
        super(tabuleiro, cor);
    }
    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        Posicao p = new Posicao(0, 0);
        if (getCor() == Cores.BRANCO){
            p.setMovimentos(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().existenciaPosicao(p) && !getTabuleiro().existenciaPeca(p)){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setMovimentos(posicao.getLinha() - 2, posicao.getColuna());
            Posicao p2  = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().existenciaPosicao(p) && !getTabuleiro().existenciaPeca(p) && getTabuleiro().existenciaPosicao(p2) && !getTabuleiro().existenciaPeca(p2) && getContadorDeMovimento() == 0){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setMovimentos(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().existenciaPosicao(p) && existemPecasAdversarias(p)){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setMovimentos(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().existenciaPosicao(p) && existemPecasAdversarias(p)){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
        }
        else {
            p.setMovimentos(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().existenciaPosicao(p) && !getTabuleiro().existenciaPeca(p)){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setMovimentos(posicao.getLinha() + 2, posicao.getColuna());
            Posicao p2  = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().existenciaPosicao(p) && !getTabuleiro().existenciaPeca(p) && getTabuleiro().existenciaPosicao(p2) && !getTabuleiro().existenciaPeca(p2)&& getContadorDeMovimento() == 0){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setMovimentos(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().existenciaPosicao(p) && existemPecasAdversarias(p)){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setMovimentos(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().existenciaPosicao(p) && existemPecasAdversarias(p)){
                matriz[p.getLinha()][p.getColuna()] = true;
            }
        }
        return matriz;
    }
    @Override
    public String toString() {
        return "P";
    }
}
