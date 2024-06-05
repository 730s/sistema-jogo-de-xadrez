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
        while (true) {
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
    }
}
