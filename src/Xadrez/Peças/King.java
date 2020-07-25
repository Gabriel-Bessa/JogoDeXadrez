package Xadrez.Peças;

import CamadaTabuleiro.Posicao;
import CamadaTabuleiro.Tabuleiro;
import Xadrez.PecaDeXadrez;
import Xadrez.Color;

public class King extends PecaDeXadrez {

	public King(Tabuleiro tabuleiro, Color cor) {
		super(tabuleiro, cor);
	}
        
        public boolean podeMover(Posicao posicao){
            PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().Peca(posicao);
            return p == null || p.getCor() != getCor();
        }
        
	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
                
                Posicao p = new Posicao(0, 0);
                
                // Acima
                p.setValores(posicao.getLinha() -1, posicao.getColuna());
                if(getTabuleiro().ExistePosicao(p)&& podeMover(p)){
                    mat[p.getLinha()][p.getColuna()] = true;
                }
                
                // Abaixo
                p.setValores(posicao.getLinha() +1, posicao.getColuna());
                if(getTabuleiro().ExistePosicao(p)&& podeMover(p)){
                    mat[p.getLinha()][p.getColuna()] = true;
                }
                
                // Esquerda
                p.setValores(posicao.getLinha(), posicao.getColuna()-1);
                if(getTabuleiro().ExistePosicao(p)&& podeMover(p)){
                    mat[p.getLinha()][p.getColuna()] = true;
                }
                
                // Direita
                p.setValores(posicao.getLinha(), posicao.getColuna()+1);
                if(getTabuleiro().ExistePosicao(p)&& podeMover(p)){
                    mat[p.getLinha()][p.getColuna()] = true;
                }
                
                // Diagonal NO
                p.setValores(posicao.getLinha() -1, posicao.getColuna()-1);
                if(getTabuleiro().ExistePosicao(p)&& podeMover(p)){
                    mat[p.getLinha()][p.getColuna()] = true;
                }
                
                // Diagonal NE
                p.setValores(posicao.getLinha() -1, posicao.getColuna()+1);
                if(getTabuleiro().ExistePosicao(p)&& podeMover(p)){
                    mat[p.getLinha()][p.getColuna()] = true;
                }
                
                // Diagonal SE
                p.setValores(posicao.getLinha() +1, posicao.getColuna()+1);
                if(getTabuleiro().ExistePosicao(p)&& podeMover(p)){
                    mat[p.getLinha()][p.getColuna()] = true;
                }
                
                // Diagnal SO
                p.setValores(posicao.getLinha() +1, posicao.getColuna()-1);
                if(getTabuleiro().ExistePosicao(p)&& podeMover(p)){
                    mat[p.getLinha()][p.getColuna()] = true;
                }
                
                return mat;
	}
}
