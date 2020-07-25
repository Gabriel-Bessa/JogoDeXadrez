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
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		while (true) {
			try {
				InterfaceComUsuário.clearScreen();
				InterfaceComUsuário.escrevendoPartida(partidaDeXadrez);
				System.out.println();
				System.out.print("Posição Inicial: ");
				PosicaoDoXadrez inicial = InterfaceComUsuário.LeitorDePosicaoDoXadrez(sc);
				
                                boolean[][] movimentosPossiveis = partidaDeXadrez.movimentosPosiveis(inicial);
                                InterfaceComUsuário.clearScreen();
                                InterfaceComUsuário.EscreveTabuleiro(partidaDeXadrez.getPecas(), movimentosPossiveis);
                                
				System.out.println();
				System.out.print("Posição Alvo: ");
				PosicaoDoXadrez alvo = InterfaceComUsuário.LeitorDePosicaoDoXadrez(sc);
				
				PecaDeXadrez capturedPiece = partidaDeXadrez.movimentacaoDePecas(inicial, alvo);
			}
			catch (ExcecoesDoXadrez | InputMismatchException e) {
				System.out.println(e.getMessage());
                                System.out.println("ERROR - Precione <ENTER> para prosseguir...");
				sc.nextLine();
			}
		}
	}
}
