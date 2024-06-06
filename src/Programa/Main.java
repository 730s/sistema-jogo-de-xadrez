package Programa;

import Xadrez.ExcecoesXadrez;
import Xadrez.PartidaDeXadrez;
import Xadrez.PecaXadrez;
import Xadrez.PosicaoXadrez;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
        List<PecaXadrez> pecasCapturadas = new ArrayList<>();
        while (!partidaDeXadrez.getCheckMate()) {
            try {
                Interface.limparTela();
                Interface.mostrarPartida(partidaDeXadrez, pecasCapturadas);
                System.out.println();
                System.out.print("Origem: ");
                PosicaoXadrez origem = Interface.lerPosicaoXadrez(sc);
                boolean[][] movimentosPossiveis = partidaDeXadrez.movimentosPossiveis(origem);
                Interface.limparTela();
                Interface.mostrarTabuleiro(partidaDeXadrez.Pecas(), movimentosPossiveis);
                System.out.println();
                System.out.print("Destino: ");
                PosicaoXadrez destino = Interface.lerPosicaoXadrez(sc);
                PecaXadrez pecaCapturada = partidaDeXadrez.logicaMovimento(origem, destino);
                if (pecaCapturada != null){
                    pecasCapturadas.add(pecaCapturada);
                }
                if (partidaDeXadrez.getPromovido() != null){
                    System.out.print("Escolha a peça para substituir o Peao (B, N, R, Q): ");
                    String tipo = sc.nextLine().toUpperCase();
                    while (!tipo.equals("B") && !tipo.equals("N") && !tipo.equals("R") && !tipo.equals("Q")){
                        System.out.print("Valor invalido, escolha a peca para substituir o Peao (B, N, R, Q): ");
                        tipo = sc.nextLine().toUpperCase();
                    }
                    partidaDeXadrez.trocaPecaPromovida(tipo);
                }
            }
            catch (ExcecoesXadrez excecao ){
                System.out.print(excecao.getMessage());
                sc.nextLine();
            }
            catch (InputMismatchException excecao ){
                System.out.print(excecao.getMessage());
                sc.nextLine();
            }
        }
        Interface.limparTela();
        Interface.mostrarPartida(partidaDeXadrez, pecasCapturadas);
    }
}
