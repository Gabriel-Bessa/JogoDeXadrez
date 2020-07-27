package Xadrez.Peças;

import CamadaTabuleiro.Posicao;
import CamadaTabuleiro.Tabuleiro;
import Xadrez.PecaDeXadrez;
import Xadrez.Color;
import Xadrez.PartidaDeXadrez;

public class King extends PecaDeXadrez {

        private PartidaDeXadrez  partida;
    
	public King(Tabuleiro tabuleiro, Color cor, PartidaDeXadrez partida) {
		super(tabuleiro, cor);
                this.partida = partida;
	}
        
        public boolean podeMover(Posicao posicao){
            PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().Peca(posicao);
            return p == null || p.getCor() != getCor();
        }
        
        private boolean testeTorreRoque(Posicao posicao){
            PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().Peca(posicao);
            return p != null && p instanceof Torre&& p.getCor() == getCor() && p.getContadorDeMovimentos() == 0 ;
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
                
                // ROQUE
                if(getContadorDeMovimentos() == 0 && !partida.getCheck()){
                    // ROQUE PEQUENO
                    Posicao posicaoT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
                    if(testeTorreRoque(posicaoT1)){
                        Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                        Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
                        if(getTabuleiro().Peca(p1) == null && getTabuleiro().Peca(p2) == null){
                            mat[posicao.getLinha()][posicao.getColuna()+2] = true;
                        }                    
                    }
                    // ROQUE Grande
                    Posicao posicaoT2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
                    if(testeTorreRoque(posicaoT2)){
                        Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                        Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
                        Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
                        if(getTabuleiro().Peca(p1) == null && getTabuleiro().Peca(p2) == null && getTabuleiro().Peca(p3) == null){
                            mat[posicao.getLinha()][posicao.getColuna()-2] = true;
                        }                    
                    }
                }
                return mat;
	}
}
