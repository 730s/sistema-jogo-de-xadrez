package Programa;

import Xadrez.ExcecoesXadrez;
import Xadrez.PartidaDeXadrez;
import Xadrez.PecaXadrez;
import Xadrez.PosicaoXadrez;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
        while (true){
            try {
                Interface.limparTela();
                Interface.mostrarTabuleiro(partidaDeXadrez.pecas());
                System.out.println();
                System.out.print("Posição de Origem: ");
                PosicaoXadrez posicaDeOrigem = Interface.lerPosicaoXadrez(sc);
                System.out.println();
                System.out.print("Posição de Destino: ");
                PosicaoXadrez posicaoDeDestino = Interface.lerPosicaoXadrez(sc);
                PecaXadrez pecaCapturada = partidaDeXadrez.logicaMovimento(posicaDeOrigem, posicaoDeDestino);
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
