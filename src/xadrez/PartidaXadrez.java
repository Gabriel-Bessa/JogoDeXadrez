package xadrez;

import CamadaTabuleiro.Exception;
import CamadaTabuleiro.Peca;
import CamadaTabuleiro.Posicao;
import CamadaTabuleiro.Tabuleiro;
import xadrez.pecas.King;
import xadrez.pecas.Torre;

public class PartidaXadrez {
    private Tabuleiro tabuleiro;
    
    public PartidaXadrez() throws Exception, java.lang.Exception{
        tabuleiro = new Tabuleiro(8, 8);
        setupInicial();
    }
    
    public PecaXadrez[][] getPecas() throws java.lang.Exception{
        PecaXadrez[][] part = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas();i++){
            for(int j = 0; j < tabuleiro.getColunas();j++){
                part[i][j] =(PecaXadrez) tabuleiro.peca(i, j);
            }
        }
        return part;
    }
    public PecaXadrez movimentacaoDePeca(PosicaoXadrez posicaoDeInicio, PosicaoXadrez posicaoDeDestino) throws xadrezException{
        Posicao inicial = posicaoDeInicio.toPosicao();
        Posicao alvo = posicaoDeDestino.toPosicao();
        validaPosicaoInicial(inicial);
        Peca pecaCapturada = movimentacao(alvo, inicial);
        return (PecaXadrez) pecaCapturada;
    }
    private Peca movimentacao(Posicao alvo, Posicao inicial){
        Peca p = tabuleiro.removePeca(inicial);
        Peca capturada = tabuleiro.removePeca(alvo);
        tabuleiro.colocarPeca(p, alvo);
        return capturada;
    } 
    private void validaPosicaoInicial(Posicao inicial) throws xadrezException{
        if(!tabuleiro.existeUmaPecaNestaPosicao(inicial)){
            throw new xadrezException("Não existe uma peça neste posição! ("+inicial+")");
        }
    }
    public void colocarNovaPeca(char coluna, int linha, PecaXadrez peca) throws java.lang.Exception{
        tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
    }
    private void setupInicial() throws java.lang.Exception{
        colocarNovaPeca('a', 1, new Torre(Cor.PRETO, tabuleiro));
        colocarNovaPeca('h', 1, new Torre(Cor.PRETO, tabuleiro));
        colocarNovaPeca('e', 1, new King(Cor.PRETO, tabuleiro));
        colocarNovaPeca('a', 8, new Torre(Cor.BRANCO, tabuleiro));
        colocarNovaPeca('h', 8, new Torre(Cor.BRANCO, tabuleiro));
        colocarNovaPeca('e', 8, new King(Cor.BRANCO, tabuleiro));
    }
}
