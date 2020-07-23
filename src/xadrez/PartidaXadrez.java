package xadrez;

import CamadaTabuleiro.Posicao;
import CamadaTabuleiro.Tabuleiro;
import xadrez.pecas.King;
import xadrez.pecas.Torre;

public class PartidaXadrez {
    private Tabuleiro tabuleiro;
    
    public PartidaXadrez() throws Exception, java.lang.Exception{
        tabuleiro = new Tabuleiro(8, 8);
        setupInicial();
    }
    
    public PecaXadrez[][] getPecas() throws java.lang.Exception{
        PecaXadrez[][] part = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas();i++){
            for(int j = 0; j < tabuleiro.getColunas();j++){
                part[i][j] =(PecaXadrez) tabuleiro.peca(i, j);
            }
        }
        return part;
    }
    private void setupInicial() throws java.lang.Exception{
        tabuleiro.colocarPeca(new Torre(Cor.BRANCO, tabuleiro), new Posicao(0, 0));
        tabuleiro.colocarPeca(new Torre(Cor.BRANCO, tabuleiro), new Posicao(0, 7));
        tabuleiro.colocarPeca(new King(Cor.BRANCO, tabuleiro), new Posicao(0, 4));
    }
}
