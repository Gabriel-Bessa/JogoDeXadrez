package Xadrez;

import CamadaTabuleiro.Tabuleiro;
import CamadaTabuleiro.Peca;
import CamadaTabuleiro.Posicao;

public abstract class PecaDeXadrez extends Peca {

	private Color cor;
        private int contadorDeMovimentos;

	public PecaDeXadrez(Tabuleiro tabuleiro, Color cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Color getCor() {
		return cor;
	}
	
        public int getContadorDeMovimentos(){
            return contadorDeMovimentos;
        }
        
        public void incrementadorDeMovimentos(){
            contadorDeMovimentos++;
        }
        
        public void decrementadorDeMovimentos(){
            contadorDeMovimentos--;
        }
        
        public PosicaoDoXadrez getPosicaoXadrez(){
            return PosicaoDoXadrez.daPosicao(posicao);
        }        
        
	protected boolean eUmaPecaDoOponente(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().Peca(posicao);
		return p != null && p.getCor() != cor;
	}
}
