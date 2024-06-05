package Programa;

import Xadrez.Cores;
import Xadrez.PartidaDeXadrez;
import Xadrez.PecaXadrez;
import Xadrez.PosicaoXadrez;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Interface {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static PosicaoXadrez lerPosicaoXadrez(Scanner sc) {
        try {
            String interacao = sc.nextLine();
            char coluna = interacao.charAt(0);
            int linha = Integer.parseInt(interacao.substring(1));
            return new PosicaoXadrez(coluna, linha);
        }
        catch (RuntimeException e) {
            throw new InputMismatchException("Erro ao ler posicao de xadrez. Posicoes validas sao de a1 a h8");
        }
    }
    public static void mostrarTabuleiro(PecaXadrez[][] pecas) {
        System.out.println("  a b c d e f g h");
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++) {
                mostrarPeca(pecas[i][j], false);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
    public static void mostrarTabuleiro(PecaXadrez[][] pecas, boolean[][] movimentosPossiveis) {
        System.out.println("  a b c d e f g h");
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++) {
                mostrarPeca(pecas[i][j], movimentosPossiveis[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
    private static void mostrarPeca(PecaXadrez peca, boolean telaDeFundo) {
        if (telaDeFundo) {
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (peca == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (peca.getCor() == Cores.BRANCO) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }
    public static void mostrarPartida(PartidaDeXadrez partidaDeXadrez, List<PecaXadrez> capturadas){
        mostrarTabuleiro(partidaDeXadrez.Pecas());
        System.out.println();
        mostrarPecasCapturadas(capturadas);
        System.out.println();
        System.out.println("Turno: " +partidaDeXadrez.getTurno());
        System.out.println("Aguardando o jogador: " +partidaDeXadrez.getJogadorAtual());
    }
    private static void mostrarPecasCapturadas(List<PecaXadrez> pecasCapturadas){
        List<PecaXadrez> brancas = pecasCapturadas.stream().filter(x-> x.getCor() == Cores.BRANCO).collect(Collectors.toList());
        List<PecaXadrez> pretas = pecasCapturadas.stream().filter(x-> x.getCor() == Cores.PRETO).collect(Collectors.toList());
        System.out.println("Pecas capturadas: ");
        System.out.print("Brancas: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(brancas.toArray()));
        System.out.print(ANSI_RESET);
        System.out.print("Pretas: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(pretas.toArray()));
        System.out.print(ANSI_RESET);
    }
}