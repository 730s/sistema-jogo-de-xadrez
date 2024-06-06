package Xadrez;

import PecasXadrez.*;
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
    private PecaXadrez enPassantVuneravel;
    private PecaXadrez promovido;
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
    public PecaXadrez getEnPassantVuneravel(){
        return enPassantVuneravel;
    }
    public PecaXadrez getPromovido(){
        return promovido;
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
        PecaXadrez pecaMovida = (PecaXadrez)tabuleiro.peca(destino);
        promovido = null;
        if (pecaMovida instanceof Peao){
            if ((pecaMovida.getCor()==Cores.BRANCO && destino.getLinha() == 0) || (pecaMovida.getCor()==Cores.PRETO && destino.getLinha() == 7)) {
                promovido = (PecaXadrez) tabuleiro.peca(destino);
                promovido = trocaPecaPromovida("Q");
            }
        }
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
        // En Passant
        if (pecaMovida instanceof Peao && (posicaoDeDestino.getLinha() == posicaoDeOrigem.getLinha() - 2 || posicaoDeDestino.getLinha() == posicaoDeOrigem.getLinha() + 2 )){
        enPassantVuneravel = pecaMovida;
        }
        else {
            enPassantVuneravel = null;
        }
        return (PecaXadrez) pecaCapturada;
    }
    private Peca executarMovimento(Posicao origem, Posicao destino) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(origem);
        p.acrescentarContadorDeMovimento();
        Peca pecaCapturada = tabuleiro.removerPeca(destino);
        tabuleiro.inserirPeca(p, destino);
        if (pecaCapturada != null){
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2){
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(origemTorre);
            tabuleiro.inserirPeca(torre, destinoTorre);
            torre.acrescentarContadorDeMovimento();
        }
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2){
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(origemTorre);
            tabuleiro.inserirPeca(torre, destinoTorre);
            torre.acrescentarContadorDeMovimento();
        }
        // En Passant
        if (p instanceof  Peao){
            if (origem. getColuna() != destino.getColuna() && pecaCapturada == null){
                Posicao posicaoPeao;
                if (p.getCor() == Cores.BRANCO){
                    posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
                }
                else {
                    posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
                }
                pecaCapturada = tabuleiro.removerPeca(posicaoPeao);
                pecasCapturadas.add(pecaCapturada);
                pecasNoTabuleiro.remove(pecaCapturada);
            }
        }
        return pecaCapturada;
    }
    public PecaXadrez trocaPecaPromovida(String tipo){
        if (promovido == null){
            throw new IllegalStateException("Nao a peca para ser promovida");
        }
        if (!tipo.equals("B") && !tipo.equals("N") && !tipo.equals("R") && !tipo.equals("Q")){
            return promovido;
        }
        Posicao posicao = promovido.getPosicaoXadrez().posicionar();
        Peca p = tabuleiro.removerPeca(posicao);
        pecasNoTabuleiro.remove(p);
        PecaXadrez novaPeca = novaPeca(tipo, promovido.getCor());
        tabuleiro.inserirPeca(novaPeca, posicao);
        pecasNoTabuleiro.add(novaPeca);
        return novaPeca;
    }
    private PecaXadrez novaPeca(String tipo, Cores cor){
        if (tipo.equals("B")) return new Bispo(tabuleiro, cor);
        if (tipo.equals("N")) return new Cavalo(tabuleiro, cor);
        if (tipo.equals("R")) return new Torre(tabuleiro, cor);
        return new Rainha(tabuleiro, cor);
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
        PecaXadrez p = (PecaXadrez) tabuleiro.removerPeca(destino);
        p.subtrairContadorDeMovimento();
        tabuleiro.inserirPeca(p, origem);
        if (pecaCapturada != null){
            tabuleiro.inserirPeca(pecaCapturada, destino);
            pecasCapturadas.remove(pecaCapturada);
            pecasNoTabuleiro.add(pecaCapturada);
        }
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2){
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(destinoTorre);
            tabuleiro.inserirPeca(torre, origemTorre);
            torre.subtrairContadorDeMovimento();
        }
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2){
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(destinoTorre);
            tabuleiro.inserirPeca(torre, origemTorre);
            torre.subtrairContadorDeMovimento();
        }
        if (p instanceof  Peao){
            if (origem. getColuna() != destino.getColuna() && pecaCapturada == enPassantVuneravel){
                PecaXadrez peao = (PecaXadrez)tabuleiro.removerPeca(destino);

                Posicao posicaoPeao;
                if (p.getCor() == Cores.BRANCO){
                    posicaoPeao = new Posicao(3, destino.getColuna());
                }
                else {
                    posicaoPeao = new Posicao(4, destino.getColuna());
                }
                tabuleiro.inserirPeca(peao, posicaoPeao);
            }
        }
    }
    private void setupInicial(){
        inserirNovaPeca('e', 1, new Rei(tabuleiro, Cores.BRANCO, this));
        inserirNovaPeca('d', 1, new Rainha(tabuleiro, Cores.BRANCO));
        inserirNovaPeca('a', 1, new Torre(tabuleiro, Cores.BRANCO));
        inserirNovaPeca('h', 1, new Torre(tabuleiro, Cores.BRANCO));
        inserirNovaPeca('c', 1, new Bispo(tabuleiro, Cores.BRANCO));
        inserirNovaPeca('f', 1, new Bispo(tabuleiro, Cores.BRANCO));
        inserirNovaPeca('b', 1, new Cavalo(tabuleiro, Cores.BRANCO));
        inserirNovaPeca('g', 1, new Cavalo(tabuleiro, Cores.BRANCO));
        inserirNovaPeca('a', 2, new Peao(tabuleiro, Cores.BRANCO, this));
        inserirNovaPeca('b', 2, new Peao(tabuleiro, Cores.BRANCO, this));
        inserirNovaPeca('c', 2, new Peao(tabuleiro, Cores.BRANCO, this));
        inserirNovaPeca('d', 2, new Peao(tabuleiro, Cores.BRANCO, this));
        inserirNovaPeca('e', 2, new Peao(tabuleiro, Cores.BRANCO, this));
        inserirNovaPeca('f', 2, new Peao(tabuleiro, Cores.BRANCO, this));
        inserirNovaPeca('g', 2, new Peao(tabuleiro, Cores.BRANCO, this));
        inserirNovaPeca('h', 2, new Peao(tabuleiro, Cores.BRANCO, this));

        inserirNovaPeca('e', 8, new Rei(tabuleiro, Cores.PRETO, this));
        inserirNovaPeca('d', 8, new Rainha(tabuleiro, Cores.PRETO));
        inserirNovaPeca('a', 8, new Torre(tabuleiro, Cores.PRETO));
        inserirNovaPeca('h', 8, new Torre(tabuleiro, Cores.PRETO));
        inserirNovaPeca('c', 8, new Bispo(tabuleiro, Cores.PRETO));
        inserirNovaPeca('f', 8, new Bispo(tabuleiro, Cores.PRETO));
        inserirNovaPeca('b', 8, new Cavalo(tabuleiro, Cores.PRETO));
        inserirNovaPeca('g', 8, new Cavalo(tabuleiro, Cores.PRETO));
        inserirNovaPeca('a', 7, new Peao(tabuleiro, Cores.PRETO, this));
        inserirNovaPeca('b', 7, new Peao(tabuleiro, Cores.PRETO, this));
        inserirNovaPeca('c', 7, new Peao(tabuleiro, Cores.PRETO, this));
        inserirNovaPeca('d', 7, new Peao(tabuleiro, Cores.PRETO, this));
        inserirNovaPeca('e', 7, new Peao(tabuleiro, Cores.PRETO, this));
        inserirNovaPeca('f', 7, new Peao(tabuleiro, Cores.PRETO, this));
        inserirNovaPeca('g', 7, new Peao(tabuleiro, Cores.PRETO, this));
        inserirNovaPeca('h', 7, new Peao(tabuleiro, Cores.PRETO, this));
    }
}
