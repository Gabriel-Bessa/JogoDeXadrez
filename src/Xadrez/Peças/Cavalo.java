package Xadrez.Peças;

import CamadaTabuleiro.Posicao;
import CamadaTabuleiro.Tabuleiro;
import Xadrez.Color;
import Xadrez.PecaDeXadrez;

public class Cavalo extends PecaDeXadrez {

    public Cavalo(Tabuleiro tabuleiro, Color cor) {
        super(tabuleiro, cor);
    }

    public boolean podeMover(Posicao posicao) {
        PecaDeXadrez p = (PecaDeXadrez) getTabuleiro().Peca(posicao);
        return p == null || p.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao p = new Posicao(0, 0);
        p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
        if (getTabuleiro().ExistePosicao(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        p.setValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
        if (getTabuleiro().ExistePosicao(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 2);
        if (getTabuleiro().ExistePosicao(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }

        p.setValores(posicao.getLinha() - 2, posicao.getColuna() + 1);
        if (getTabuleiro().ExistePosicao(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        p.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
        if (getTabuleiro().ExistePosicao(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
        if (getTabuleiro().ExistePosicao(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        p.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
        if (getTabuleiro().ExistePosicao(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
        if (getTabuleiro().ExistePosicao(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        return mat;
    }

    @Override
    public String toString() {
        return "C";
    }
}
