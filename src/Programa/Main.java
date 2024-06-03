package Programa;

import Xadrez.PartidaDeXadrez;
import Xadrez.PecaXadrez;
import Xadrez.PosicaoXadrez;

import java.rmi.server.UID;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
        while (true){
            Interface.mostrarTabuleiro(partidaDeXadrez.pecas());
            System.out.println();
            System.out.print("Posição de Origem: ");
            PosicaoXadrez posicaDeOrigem = Interface.lerPosicaoXadrez(sc);
            System.out.println();
            System.out.print("Posição de Destino: ");
            PosicaoXadrez posicaoDeDestino = Interface.lerPosicaoXadrez(sc);

            PecaXadrez pecaCapturada = partidaDeXadrez.logicaMovimento(posicaDeOrigem, posicaoDeDestino);

        }
    }
}
