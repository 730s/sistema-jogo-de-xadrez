package Xadrez;

import PecasXadrez.Rei;
import PecasXadrez.Torre;
import TabuleiroConfig.Peca;
import TabuleiroConfig.Posicao;
import TabuleiroConfig.Tabuleiro;

public class PartidaDeXadrez {
    private int turno;
    private Cores jogadorAtual;
    public int getTurno(){
        return turno;
    }
    public Cores getJogadorAtual(){
        return jogadorAtual;
    }
    private Tabuleiro tabuleiro;
    public PartidaDeXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        turno = 1;
        jogadorAtual = Cores.BRANCO;
        setupInicial();
    }
    public PecaXadrez[][] Pecas() {
        PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i=0; i<tabuleiro.getLinhas(); i++) {
            for (int j=0; j<tabuleiro.getColunas(); j++) {
                matriz[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
            }
        }
        return matriz;
    }
    public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
        Posicao posicao = posicaoOrigem.posicionar();
        validarPosicaoDeOrigem(posicao);
        return tabuleiro.peca(posicao).movimentosPossiveis();
    }
    public PecaXadrez logicaMovimento(PosicaoXadrez posicaoDeOrigem, PosicaoXadrez posicaoDeDestino) {
        Posicao origem = posicaoDeOrigem.posicionar();
        Posicao destino = posicaoDeDestino.posicionar();
        validarPosicaoDeOrigem(origem);
        validarPosicaoDeDestino(origem, destino);
        Peca pecaCapturada = executarMovimento(origem, destino);
        proximoTurno();
        return (PecaXadrez) pecaCapturada;
    }
    private Peca executarMovimento(Posicao origem, Posicao destino) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(origem);
        Peca PecaCapturada = tabuleiro.removerPeca(destino);
        tabuleiro.inserirPeca(p, destino);
        return PecaCapturada;
    }
    private void validarPosicaoDeOrigem(Posicao posicao) {
        if (!tabuleiro.existenciaPosicao(posicao)) {
            throw new ExcecoesXadrez("Nao existe peca na posicao de origem");
        }
        if (jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()){
            throw new ExcecoesXadrez("A peça escolhida não é sua");
        }
        if (!tabuleiro.peca(posicao).existeMovimentosPossiveis()) {
            throw new ExcecoesXadrez("Nao ha movimentos possiveis para a peca");
        }
    }
    private void validarPosicaoDeDestino(Posicao origem, Posicao destino) {
        if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {
            throw new ExcecoesXadrez("A peca escolhida nao pode se mover para a posicao de destino");
        }
    }
    private void proximoTurno(){
        turno++;
        jogadorAtual = (jogadorAtual == Cores.BRANCO) ? Cores.PRETO : Cores.BRANCO;
    }
    private void inserirNovaPeca(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.inserirPeca(peca, new PosicaoXadrez(coluna, linha).posicionar());
    }
    private void setupInicial(){
        inserirNovaPeca('c', 1, new Torre(tabuleiro, Cores.BRANCO));
        inserirNovaPeca('c', 2, new Torre(tabuleiro, Cores.BRANCO));
        inserirNovaPeca('d', 2, new Torre(tabuleiro, Cores.BRANCO));
        inserirNovaPeca('e', 2, new Torre(tabuleiro, Cores.BRANCO));
        inserirNovaPeca('e', 1, new Torre(tabuleiro, Cores.BRANCO));
        inserirNovaPeca('d', 1, new Rei(tabuleiro, Cores.BRANCO));

        inserirNovaPeca('c', 7, new Torre(tabuleiro, Cores.PRETO));
        inserirNovaPeca('c', 8, new Torre(tabuleiro, Cores.PRETO));
        inserirNovaPeca('d', 7, new Torre(tabuleiro, Cores.PRETO));
        inserirNovaPeca('e', 7, new Torre(tabuleiro, Cores.PRETO));
        inserirNovaPeca('e', 8, new Torre(tabuleiro, Cores.PRETO));
        inserirNovaPeca('d', 8, new Rei(tabuleiro, Cores.PRETO));
    }
}
