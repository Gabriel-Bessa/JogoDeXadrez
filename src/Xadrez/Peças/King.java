package Xadrez.Peças;

import CamadaTabuleiro.Tabuleiro;
import Xadrez.PecaDeXadrez;
import Xadrez.Color;

public class King extends PecaDeXadrez {

	public King(Tabuleiro tabuleiro, Color cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		return mat;
	}
}
