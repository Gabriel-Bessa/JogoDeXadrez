package jogodexadrez;

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
            UI.printTabuleiro(partidaXadrez.getPecas());
            System.out.println();
            System.out.print("Posição de Origem: ");
            PosicaoXadrez inicial = UI.leitorPosicaoXadrez(sc);
            System.out.println("");
            System.out.print("Posição de destino ");
            PosicaoXadrez alvo = UI.leitorPosicaoXadrez(sc);
            PecaXadrez pecaCapturada = partidaXadrez.movimentacaoDePeca(inicial, alvo);
            System.out.println("Peças Capturadas: "+pecaCapturada);
        }
    }
    
}
