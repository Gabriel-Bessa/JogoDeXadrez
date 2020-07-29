
package Xadrez.Peças;

import CamadaTabuleiro.Posicao;
import CamadaTabuleiro.Tabuleiro;
import Xadrez.Color;
import Xadrez.PecaDeXadrez;

public class Queen extends PecaDeXadrez{

    public Queen(Tabuleiro tabuleiro, Color cor) {
        super(tabuleiro, cor);
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
                // Acimaz
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Esqueda
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Direita
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Abaixo
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// NE
		p.setValores(posicao.getLinha() - 1, posicao.getColuna()-1);
		while (getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1, p.getColuna() -1);
		}
		if (getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// SE
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() +1, p.getColuna() -1);
		}
		if (getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// NO
		p.setValores(posicao.getLinha() -1, posicao.getColuna() + 1);
		while (getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1, p.getColuna() + 1);
		}
		if (getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// SO
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() +1);
		while (getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() +1, p.getColuna() +1);
		}
		if (getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
    }
    
    public String toString(){
        return "Q";
    }
}
