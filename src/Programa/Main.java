package Programa;

import Tabuleiro.Tabuleiro;
import Xadrez.PartidaDeXadrez;

public class Main {
    public static void main(String[] args) {
        PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
        Interface.mostrarTabuleiro(partidaDeXadrez.pecas());
    }
}
