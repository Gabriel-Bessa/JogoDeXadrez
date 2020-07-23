package jogodexadrez;

import CamadaTabuleiro.Tabuleiro;
import xadrez.PartidaXadrez;

public class JogoDeXadrez {
    // Jogo De Xadrez Desenvolvido por GABRIEL BESSA segundo as aulas da UDEMY - JAVA
    public static void main(String[] args) throws Exception {
       Tabuleiro tab = new Tabuleiro(8, 8);
        PartidaXadrez partidaXadrez = new PartidaXadrez();
        UI.printTabuleiro(partidaXadrez.getPecas());
    }
    
}
