package jogodexadrez;

import java.util.InputMismatchException;
import java.util.Scanner;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class JogoDeXadrez {
    // Jogo De Xadrez Desenvolvido por GABRIEL BESSA segundo as aulas da UDEMY - JAVA
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        PartidaXadrez partidaXadrez = new PartidaXadrez();
        while(true){
            try{                
                UI.printTabuleiro(partidaXadrez.getPecas());
                System.out.println();
                System.out.print("Posição de Origem: ");
                PosicaoXadrez inicial = UI.leitorPosicaoXadrez(sc);
                System.out.println("");
                System.out.print("Posição de destino ");
                PosicaoXadrez alvo = UI.leitorPosicaoXadrez(sc);
                PecaXadrez pecaCapturada = partidaXadrez.movimentacaoDePeca(inicial, alvo);
                System.out.println("Peças Capturadas: "+pecaCapturada);
                UI.clearScreen();
            }
            catch (xadrez.xadrezException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }
    
}
