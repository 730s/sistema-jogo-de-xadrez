package Xadrez;

import PecasXadrez.Rei;
import PecasXadrez.Torre;
import TabuleiroConfig.Tabuleiro;

public class PartidaDeXadrez {
    private Tabuleiro tabuleiro;

    public PartidaDeXadrez(){
        this.tabuleiro = new Tabuleiro(8, 8);
        setupInicial();
    }
    public PecaXadrez[][] pecas(){
        PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for(int i=0; i< tabuleiro.getLinhas(); i++){
            for (int j=0; j< tabuleiro.getColunas(); j++){
                matriz[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
            }
        }
        return matriz;
    }
    public void inserirNovaPeca(char coluna, int linha, PecaXadrez peca){
        tabuleiro.inserirPeca(peca, new PosicaoXadrez(coluna, linha).posicionar());

    }
    private void setupInicial(){
        inserirNovaPeca('a', 8, new Torre(tabuleiro, Cores.BRANCO));
        inserirNovaPeca('e', 8, new Rei(tabuleiro, Cores.PRETO));
    }
}
