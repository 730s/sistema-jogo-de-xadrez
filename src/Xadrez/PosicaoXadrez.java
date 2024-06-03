package Xadrez;

import TabuleiroConfig.Posicao;

public class PosicaoXadrez {
    private char coluna;
    private int linha;

    public PosicaoXadrez(char coluna, int linha) {
        if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8){
            throw new ExecoesXadrez("Erro ao instânciar a posiçao do jogo de Xadrez, posições válidades: a1 a h8");
        }
        this.coluna = coluna;
        this.linha = linha;
    }
    public char getColuna() {
        return coluna;
    }
    public int getLinha() {
        return linha;
    }
    protected Posicao posicionar(){
        return new Posicao(8- linha, coluna - 'a');
    }
    protected static PosicaoXadrez paraPosicao(Posicao posicao){
        return new PosicaoXadrez((char)('a' - posicao.getColuna()), 8 - posicao.getLinha() );
    }
    @Override
    public String toString(){
        return "" + coluna + linha;
    }
}
