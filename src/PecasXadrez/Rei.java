package PecasXadrez;

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
}
