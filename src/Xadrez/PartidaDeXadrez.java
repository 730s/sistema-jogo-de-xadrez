package Xadrez;

import PecasXadrez.Rei;
import PecasXadrez.Torre;
import TabuleiroConfig.Peca;
import TabuleiroConfig.Posicao;
import TabuleiroConfig.Tabuleiro;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartidaDeXadrez {
    private int turno;
    private Cores jogadorAtual;
    private boolean check;
    private boolean checkMate;
    private List<Peca> pecasNoTabuleiro = new ArrayList<>();
    private List<Peca> pecasCapturadas = new ArrayList<>();
    public int getTurno(){
        return turno;
    }
    public Cores getJogadorAtual(){
        return jogadorAtual;
    }
    public boolean getCheck(){
        return check;
    }
    public boolean getCheckMate(){
        return checkMate;
    }
    private Tabuleiro tabuleiro;
    public PartidaDeXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        turno = 1;
        jogadorAtual = Cores.BRANCO;
        check = false;
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
        if (testeCheck(jogadorAtual)){
            desfazerMovimento(origem, destino, pecaCapturada);
            throw new ExcecoesXadrez("Você não pode executar este movimento pois ira ficar ou está em Check");
        }
        check = (testeCheck(oponente(jogadorAtual))) ? true : false;
        if (testeCheckMate(oponente(jogadorAtual))){
            checkMate = true;
        }
        else {
            proximoTurno();
        }
        return (PecaXadrez) pecaCapturada;
    }
    private Peca executarMovimento(Posicao origem, Posicao destino) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(origem);
        Peca pecaCapturada = tabuleiro.removerPeca(destino);
        tabuleiro.inserirPeca(p, destino);
        if (pecaCapturada != null){
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }
        return pecaCapturada;
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
    private Cores oponente(Cores cor){
        return (cor == Cores.BRANCO) ? Cores.PRETO : Cores.BRANCO;
    }
    private PecaXadrez rei(Cores cor){
        List<Peca> lista = pecasNoTabuleiro.stream().filter(x-> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
        for (Peca p : lista){
            if (p instanceof Rei){
                return (PecaXadrez)p;
            }
        }
        throw new IllegalStateException("Não existe o rei da cor " + cor);
    }
    private boolean testeCheck(Cores cor){
        Posicao posicaoDoRei = rei(cor).getPosicaoXadrez().posicionar();
        List<Peca> pecasDoOponente = pecasNoTabuleiro.stream().filter(x-> ((PecaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
        for (Peca p : pecasDoOponente){
            boolean[][] matriz = p.movimentosPossiveis();
            if (matriz[posicaoDoRei.getLinha()][posicaoDoRei.getColuna()]){
                return true;
            }
        }
        return false;
    }
    private boolean testeCheckMate(Cores cor){
        if (!testeCheck(cor)){
            return false;
        }
        List<Peca> lista = pecasNoTabuleiro.stream().filter(x-> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
        for (Peca p : lista){
            boolean[][] matriz = p.movimentosPossiveis();
            for (int i=0; i<tabuleiro.getLinhas(); i++){
                for (int j=0; j<tabuleiro.getColunas(); j++){
                    if (matriz[i][j]){
                        Posicao origem = ((PecaXadrez)p).getPosicaoXadrez().posicionar();
                        Posicao destino = new Posicao(i, j);
                        Peca pecaCapturada = executarMovimento(origem, destino);
                        boolean testeCheck = testeCheck(cor);
                        desfazerMovimento(origem, destino, pecaCapturada);
                        if (!testeCheck){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    private void proximoTurno(){
        turno++;
        jogadorAtual = (jogadorAtual == Cores.BRANCO) ? Cores.PRETO : Cores.BRANCO;
    }
    private void inserirNovaPeca(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.inserirPeca(peca, new PosicaoXadrez(coluna, linha).posicionar());
        pecasNoTabuleiro.add(peca);
    }
    private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada){
        Peca p = tabuleiro.removerPeca(destino);
        tabuleiro.inserirPeca(p, origem);
        if (pecaCapturada != null){
            tabuleiro.inserirPeca(pecaCapturada, destino);
            pecasCapturadas.remove(pecaCapturada);
            pecasNoTabuleiro.add(pecaCapturada);
        }
    }
    private void setupInicial(){
        inserirNovaPeca('h', 7, new Torre(tabuleiro, Cores.BRANCO));
        inserirNovaPeca('d', 1, new Torre(tabuleiro, Cores.BRANCO));
        inserirNovaPeca('e', 1, new Rei(tabuleiro, Cores.BRANCO));

        inserirNovaPeca('b', 8, new Torre(tabuleiro, Cores.PRETO));;
        inserirNovaPeca('a', 8, new Rei(tabuleiro, Cores.PRETO));
    }
}
