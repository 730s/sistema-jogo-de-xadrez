package PecasXadrez;

import TabuleiroConfig.Posicao;
import TabuleiroConfig.Tabuleiro;
import Xadrez.Cores;
import Xadrez.PartidaDeXadrez;
import Xadrez.PecaXadrez;

public class Rei extends PecaXadrez {
    private PartidaDeXadrez partidaDeXadrez;
    public Rei(Tabuleiro tabuleiro, Cores cor, PartidaDeXadrez partidaDeXadrez) {
        super(tabuleiro, cor);
        this.partidaDeXadrez = partidaDeXadrez;

    }
    @Override
    public String toString() {
        return "K";
    }
    private boolean testeRook(Posicao posicao){
        PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
        return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContadorDeMovimento() == 0;

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
        // Roque
        if (getContadorDeMovimento() == 0 && !partidaDeXadrez.getCheck()){
            Posicao posicaoT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
            if (testeRook(posicaoT1)){
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
                if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null){
                    matriz[posicao.getLinha()][posicao.getColuna() + 2] = true;
                }
            }
            Posicao posicaoT2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
            if (testeRook(posicaoT2)){
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
                Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
                if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null){
                    matriz[posicao.getLinha()][posicao.getColuna() - 2] = true;
                }
            }
        }
        return matriz;
    }
}
