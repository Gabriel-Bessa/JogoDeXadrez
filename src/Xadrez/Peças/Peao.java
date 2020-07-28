package Xadrez.Peças;

import CamadaTabuleiro.Posicao;
import CamadaTabuleiro.Tabuleiro;
import Xadrez.Color;
import Xadrez.PartidaDeXadrez;
import Xadrez.PecaDeXadrez;

public class Peao extends PecaDeXadrez {
    private PartidaDeXadrez partida;
    public Peao(Tabuleiro tabuleiro, Color cor, PartidaDeXadrez partida) {
        super(tabuleiro, cor);
        this.partida = partida;
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        
        boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        
        Posicao p = new Posicao(0, 0);

        if (getCor() == Color.BRANCO) {

            p.setValores(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            p.setValores(posicao.getLinha() - 2, posicao.getColuna());
            if (getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p) && getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p) && getContadorDeMovimentos() == 0) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            
            //EN PASSANT
            if(posicao.getLinha() == 3){
                Posicao esquerda = new Posicao(posicao.getLinha(),posicao.getColuna()-1);
                if(getTabuleiro().ExistePosicao(esquerda) && eUmaPecaDoOponente(esquerda) && getTabuleiro().Peca(esquerda) == partida.getEnPassanteVulneravel()){
                    mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
                }
                Posicao direita = new Posicao(posicao.getLinha(),posicao.getColuna() + 1);
                if(getTabuleiro().ExistePosicao(direita) && eUmaPecaDoOponente(direita) && getTabuleiro().Peca(direita) == partida.getEnPassanteVulneravel()){
                    mat[direita.getLinha() - 1][direita.getColuna()] = true;
                }
            }

        } else {
            p.setValores(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            p.setValores(posicao.getLinha() + 2, posicao.getColuna());
            if (getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p) && getTabuleiro().ExistePosicao(p) && !getTabuleiro().EUmaPeca(p) && getContadorDeMovimentos() == 0) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }

            p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().ExistePosicao(p) && eUmaPecaDoOponente(p)) {
                mat[p.getLinha()][p.getColuna()] = true;
            }
            
            //EN PASSANT
            if(posicao.getLinha() == 4){
                Posicao esquerda = new Posicao(posicao.getLinha(),posicao.getColuna()-1);
                if(getTabuleiro().ExistePosicao(esquerda) && eUmaPecaDoOponente(esquerda) && getTabuleiro().Peca(esquerda) == partida.getEnPassanteVulneravel()){
                    mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
                }
                Posicao direita = new Posicao(posicao.getLinha(),posicao.getColuna() + 1);
                if(getTabuleiro().ExistePosicao(direita) && eUmaPecaDoOponente(direita) && getTabuleiro().Peca(direita) == partida.getEnPassanteVulneravel()){
                    mat[direita.getLinha() + 1][direita.getColuna()] = true;
                }
            }
        }
        return mat;
    }

    @Override
    public String toString() {
        return "P";
    }
}
