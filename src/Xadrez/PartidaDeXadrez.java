package Xadrez;

import CamadaTabuleiro.Tabuleiro;
import CamadaTabuleiro.Peca;
import CamadaTabuleiro.Posicao;
import Xadrez.Peças.King;
import Xadrez.Peças.Torre;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;
        private int turno;
	private Color jogador;
        
        public int getTurno(){
            return turno;
        }
        public Color getJogador(){
            return jogador;
        }
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
                turno = 1;
                jogador = Color.BRANCO;
		setupInicial();
	}
	
	public PecaDeXadrez[][] getPecas() {
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaDeXadrez) tabuleiro.Peca(i, j);
			}
		}
		return mat;
	}
        
	public boolean[][] movimentosPosiveis(PosicaoDoXadrez posicaoInicial){
            Posicao posicao = posicaoInicial.toPosition();
            validacaoPosicaoInicial(posicao);
            return tabuleiro.Peca(posicao).movimentosPossiveis();
        }
        
	public PecaDeXadrez movimentacaoDePecas(PosicaoDoXadrez posicaoInicial, PosicaoDoXadrez posicaoAlvo) {
		Posicao inicial = posicaoInicial.toPosition();
		Posicao alvo = posicaoAlvo.toPosition();
		validacaoPosicaoInicial(inicial);
		validacaoPosicaoAlvo(inicial, alvo);
		Peca pecaCapturado = Movimentacao(inicial, alvo);
                proximoTurno();
		return (PecaDeXadrez)pecaCapturado;
	}
	
	private Peca Movimentacao(Posicao inicial, Posicao alvo) {
		Peca p = tabuleiro.RemoverPeca(inicial);
		Peca pecaCapturado = tabuleiro.RemoverPeca(alvo);
		tabuleiro.ColocarPeca(p, alvo);
		return pecaCapturado;
	}
	
	private void validacaoPosicaoInicial(Posicao posicao) {
		if (!tabuleiro.EUmaPeca(posicao)) {
			throw new ExcecoesDoXadrez("ERRO - Não há peça nesta possição!");
		}
                if (jogador != ((PecaDeXadrez)tabuleiro.Peca(posicao)).getCor()){
                    throw new ExcecoesDoXadrez("ERROR - A peça selecionada não é sua!");
                }
		if (!tabuleiro.Peca(posicao).EstaPossuiMovimentosPossiveis()) {
			throw new ExcecoesDoXadrez("ERROR - Essa peça Não possui movimentos possiveis!");
		}
	}
	
	private void validacaoPosicaoAlvo(Posicao inicial, Posicao alvo) {
		if (!tabuleiro.Peca(inicial).MovimentoPossivel(alvo)) {
			throw new ExcecoesDoXadrez("ERROR - A peça escolhida não pode ir a posição de destino!");
		}
	}
	
        private void proximoTurno(){
            turno++;
            jogador = (jogador == Color.BRANCO) ? Color.PRETO : Color.BRANCO;
        }
        
	private void ColocarPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.ColocarPeca(peca, new PosicaoDoXadrez(coluna, linha).toPosition());
	}
	
	private void setupInicial() {
	ColocarPeca('c', 1, new Torre(tabuleiro, Color.BRANCO));
        ColocarPeca('c', 2, new Torre(tabuleiro, Color.BRANCO));
        ColocarPeca('d', 2, new Torre(tabuleiro, Color.BRANCO));
        ColocarPeca('e', 2, new Torre(tabuleiro, Color.BRANCO));
        ColocarPeca('e', 1, new Torre(tabuleiro, Color.BRANCO));
        ColocarPeca('d', 1, new King(tabuleiro, Color.BRANCO));

        ColocarPeca('c', 7, new Torre(tabuleiro, Color.PRETO));
        ColocarPeca('c', 8, new Torre(tabuleiro, Color.PRETO));
        ColocarPeca('d', 7, new Torre(tabuleiro, Color.PRETO));
        ColocarPeca('e', 7, new Torre(tabuleiro, Color.PRETO));
        ColocarPeca('e', 8, new Torre(tabuleiro, Color.PRETO));
        ColocarPeca('d', 8, new King(tabuleiro, Color.PRETO));
	}
}