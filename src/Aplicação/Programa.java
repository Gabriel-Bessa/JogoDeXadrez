package Aplicação;

import java.util.InputMismatchException;
import java.util.Scanner;

import Xadrez.ExcecoesDoXadrez;
import Xadrez.PartidaDeXadrez;
import Xadrez.PecaDeXadrez;
import Xadrez.PosicaoDoXadrez;
import java.util.ArrayList;
import java.util.List;

public class Programa {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
        List<PecaDeXadrez> capturadas = new ArrayList<>();

        while (!partidaDeXadrez.getCheckMate()) {
            try {
                InterfaceComUsuário.clearScreen();
                InterfaceComUsuário.escrevendoPartida(partidaDeXadrez, capturadas);
                System.out.println();
                System.out.print("Posição Inicial: ");
                PosicaoDoXadrez inicial = InterfaceComUsuário.LeitorDePosicaoDoXadrez(sc);

                boolean[][] movimentosPossiveis = partidaDeXadrez.movimentosPosiveis(inicial);
                InterfaceComUsuário.clearScreen();
                InterfaceComUsuário.EscreveTabuleiro(partidaDeXadrez.getPecas(), movimentosPossiveis);

                System.out.println();
                System.out.print("Posição Alvo: ");
                PosicaoDoXadrez alvo = InterfaceComUsuário.LeitorDePosicaoDoXadrez(sc);

                PecaDeXadrez pecaCapturada = partidaDeXadrez.movimentacaoDePecas(inicial, alvo);

                if (pecaCapturada != null) {
                    capturadas.add(pecaCapturada);
                }
                
                if(partidaDeXadrez.getPromocao() != null){
                    System.out.println("Entre ccom a nova peça (B/N/R/Q): ");
                    String tipo = sc.nextLine().toUpperCase();
                    while(!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("Q") && !tipo.equals("T")){
                       System.out.println("ERROR - Entre ccom a nova peça (B/N/R/Q): ");
                       tipo = sc.nextLine().toUpperCase(); 
                    }
                    partidaDeXadrez.recolocarPecaPromocao(tipo);
                }
            } 
            catch (ExcecoesDoXadrez | InputMismatchException e) {
                System.out.println(e.getMessage());
                System.out.println("ERROR - Precione <ENTER> para prosseguir...");
                sc.nextLine();
            }
        }
        InterfaceComUsuário.clearScreen();
        InterfaceComUsuário.escrevendoPartida(partidaDeXadrez, capturadas);
    }
}
