package Aplicação;

import java.util.InputMismatchException;
import java.util.Scanner;

import Xadrez.ExcecoesDoXadrez;
import Xadrez.PartidaDeXadrez;
import Xadrez.PecaDeXadrez;
import Xadrez.PosicaoDoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez chessMatch = new PartidaDeXadrez();
		
		while (true) {
			try {
				InterfaceComUsuário.clearScreen();
				InterfaceComUsuário.EscreveTabuleiro(chessMatch.getPecas());
				System.out.println();
				System.out.print("Posição Inicial: ");
				PosicaoDoXadrez source = InterfaceComUsuário.LeitorDePosicaoDoXadrez(sc);
				
				System.out.println();
				System.out.print("Posição Alvo: ");
				PosicaoDoXadrez target = InterfaceComUsuário.LeitorDePosicaoDoXadrez(sc);
				
				PecaDeXadrez capturedPiece = chessMatch.movimentacaoDePecas(source, target);
			}
			catch (ExcecoesDoXadrez | InputMismatchException e) {
				System.out.println(e.getMessage());
                                System.out.println("ERROR - Precione <ENTER> para prosseguir...");
				sc.nextLine();
			}
		}
	}
}
