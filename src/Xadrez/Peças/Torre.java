package Xadrez.Peças;

import CamadaTabuleiro.Tabuleiro;
import CamadaTabuleiro.Posicao;
import Xadrez.PecaDeXadrez;
import Xadrez.Color;

public class Torre extends PecaDeXadrez {

	public Torre(Tabuleiro tabuleiro, Color cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "T";
	}
	
	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		// above
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// left
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// right
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// below
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
}
