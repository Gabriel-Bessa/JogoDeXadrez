package Aplicação;

import Xadrez.Color;
import Xadrez.PartidaDeXadrez;
import java.util.InputMismatchException;
import java.util.Scanner;

import Xadrez.PecaDeXadrez;
import Xadrez.PosicaoDoXadrez;

public class InterfaceComUsuário {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

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
	
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}	
	
	public static PosicaoDoXadrez LeitorDePosicaoDoXadrez(Scanner sc) {
		try {
			String s = sc.nextLine();
			char coluna = s.charAt(0);
			int linha = Integer.parseInt(s.substring(1));
			return new PosicaoDoXadrez(coluna, linha);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("ERROR - Valor Invalido! Tente valores entre a1 e h8!");
		}
	}
	
        public static void escrevendoPartida(PartidaDeXadrez partida){
            EscreveTabuleiro(partida.getPecas());
            System.out.println();
            System.out.println("Turno: "+partida.getTurno());
            System.out.println("Esperando jogador: "+partida.getJogador());
        }
        
	public static void EscreveTabuleiro(PecaDeXadrez[][] pieces) {            
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				EscrevendoPeca(pieces[i][j],false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
        public static void EscreveTabuleiro(PecaDeXadrez[][] pieces, boolean[][] movimentosPossiveis) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				EscrevendoPeca(pieces[i][j], movimentosPossiveis[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	private static void EscrevendoPeca(PecaDeXadrez piece, boolean backgroundColor) {
        if(backgroundColor){
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
    	if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (piece.getCor() == Color.BRANCO) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
}
