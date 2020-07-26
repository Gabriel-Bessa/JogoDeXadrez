
package Xadrez.Peças;

import CamadaTabuleiro.Posicao;
import CamadaTabuleiro.Tabuleiro;
import Xadrez.Color;
import Xadrez.PecaDeXadrez;

public class Peao extends PecaDeXadrez{
    
    public Peao(Tabuleiro tabuleiro, Color cor) {
        super(tabuleiro, cor);
    }
    
    @Override
    public boolean[][] movimentosPossiveis() {
        
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
	
        Posicao p = new Posicao(0, 0);
        
        if(getCor() == Color.BRANCO){
            
            p.setValores(posicao.getLinha() - 1, posicao.getColuna());
            if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
            
            p.setValores(posicao.getLinha() - 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p) && getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p) && getContadorDeMovimentos() == 0){
                mat[p.getLinha()][p.getColuna()] = true;
            }
            
            p.setValores(posicao.getLinha() - 1, posicao.getColuna()-1);
            if(getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
            
            
            p.setValores(posicao.getLinha() - 1, posicao.getColuna()+1);
            if(getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
        }
        else{
            p.setValores(posicao.getLinha() + 1, posicao.getColuna());
            if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
            
            p.setValores(posicao.getLinha() + 2, posicao.getColuna());
            Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
            if(getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p) && getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p) && getContadorDeMovimentos() == 0){
                mat[p.getLinha()][p.getColuna()] = true;
            }
            
            p.setValores(posicao.getLinha() + 1, posicao.getColuna()-1);
            if(getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
            
            
            p.setValores(posicao.getLinha() + 1, posicao.getColuna()+1);
            if(getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)){
                mat[p.getLinha()][p.getColuna()] = true;
            }
            
        }
        return mat;
    }    
    @Override
	public String toString() {
		return "P";
	}
}
