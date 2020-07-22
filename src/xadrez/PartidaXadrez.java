package xadrez;

import CamadaTabuleiro.Tabuleiro;

public class PartidaXadrez {
    private Tabuleiro tabuleiro;
    
    public PartidaXadrez(){
        tabuleiro = new Tabuleiro(8, 8);
    }
    
    public PecaXadrez[][] getPecas(){
        PecaXadrez[][] part = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas();i++){
            for(int j = 0; j < tabuleiro.getColunas();j++){
                part[i][j] =(PecaXadrez) tabuleiro.peca(i, j);
            }
        }
        return part;
    }
}
