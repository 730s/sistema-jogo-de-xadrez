package PecasXadrez;

import TabuleiroConfig.Posicao;
import TabuleiroConfig.Tabuleiro;
import Xadrez.Cores;
import Xadrez.PartidaDeXadrez;
import Xadrez.PecaXadrez;

public class Peao extends PecaXadrez {
    private PartidaDeXadrez partidaDeXadrez;
    public Peao(Tabuleiro tabuleiro, Cores cor, PartidaDeXadrez partidaDeXadrez) {
        super(tabuleiro, cor);
        this.partidaDeXadrez = partidaDeXadrez;
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
            //En Passant
            if (posicao.getLinha() == 3){
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (getTabuleiro().existenciaPosicao(esquerda) && existemPecasAdversarias(esquerda) && getTabuleiro().peca(esquerda) == partidaDeXadrez.getEnPassantVuneravel()){
                    matriz[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
                }
                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (getTabuleiro().existenciaPosicao(direita) && existemPecasAdversarias(direita) && getTabuleiro().peca(direita) == partidaDeXadrez.getEnPassantVuneravel()){
                    matriz[direita.getLinha() - 1][direita.getColuna()] = true;
                }
            }
        }
        else {
            p.setMovimentos(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().existenciaPosicao(p) && !getTabuleiro().existenciaPeca(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setMovimentos(posicao.getLinha() + 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().existenciaPosicao(p) && !getTabuleiro().existenciaPeca(p) && getTabuleiro().existenciaPosicao(p2) && !getTabuleiro().existenciaPeca(p2) && getContadorDeMovimento() == 0) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setMovimentos(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().existenciaPosicao(p) && existemPecasAdversarias(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            p.setMovimentos(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().existenciaPosicao(p) && existemPecasAdversarias(p)) {
                matriz[p.getLinha()][p.getColuna()] = true;
            }
            //En Passant
            if (posicao.getLinha() == 4) {
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (getTabuleiro().existenciaPosicao(esquerda) && existemPecasAdversarias(esquerda) && getTabuleiro().peca(esquerda) == partidaDeXadrez.getEnPassantVuneravel()) {
                    matriz[esquerda.getLinha()][esquerda.getColuna()] = true;
                }
            }
            Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
            if (getTabuleiro().existenciaPosicao(direita) && existemPecasAdversarias(direita) && getTabuleiro().peca(direita) == partidaDeXadrez.getEnPassantVuneravel()) {
                matriz[direita.getLinha()][direita.getColuna()] = true;
            }
        }
        return matriz;
    }
    @Override
    public String toString() {
        return "P";
    }
}
