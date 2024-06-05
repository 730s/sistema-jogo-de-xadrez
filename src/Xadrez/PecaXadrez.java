package Xadrez;

import TabuleiroConfig.Posicao;
import TabuleiroConfig.Tabuleiro;
import TabuleiroConfig.Peca;

public abstract class PecaXadrez extends Peca {
    private Cores cor;
    private int contadorDeMovimento;
    public PecaXadrez(Tabuleiro tabuleiro, Cores cor) {
        super(tabuleiro);
        this.cor = cor;
    }
    public Cores getCor() {
        return cor;
    }
    public void acrescentarContadorDeMovimento(){
        contadorDeMovimento++;
    }
    public void subtrairContadorDeMovimento(){
        contadorDeMovimento--;
    }
    public int getContadorDeMovimento(){
        return contadorDeMovimento;
    }
    protected boolean existemPecasAdversarias(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().peca(posicao);
        return p != null && p.getCor() != cor;
    }
    public PosicaoXadrez getPosicaoXadrez(){
        return PosicaoXadrez.daPosicao(posicao);
    }
}

