package Xadrez;

import CamadaTabuleiro.Tabuleiro;
import CamadaTabuleiro.Peca;
import CamadaTabuleiro.Posicao;
import Xadrez.Peças.King;
import Xadrez.Peças.Torre;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PartidaDeXadrez {
        private int turno;
	private Color jogador;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	
	private List<Peca> pecaNoTabuleiro = new ArrayList<>();
	private List<Peca>pecasCapturadas = new ArrayList<>();
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogador = Color.BRANCO;
		initialSetup();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Color getJogador() {
		return jogador;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
	
	public boolean[][] movimentosPosiveis(PosicaoDoXadrez posicaoInicial) {
		Posicao inicial = posicaoInicial.toPosition();
		validacaoDaPosicaoInicial(inicial);
		return tabuleiro.Peca(inicial).movimentosPossiveis();
	}
	
	public PecaDeXadrez movimentacaoDePecas(PosicaoDoXadrez posicaoInicial, PosicaoDoXadrez posicaoAlvo) {
		Posicao inicial = posicaoInicial.toPosition();
		Posicao alvo = posicaoAlvo.toPosition();
		validacaoDaPosicaoInicial(inicial);
		validacaoDaPosicaoFinal(inicial, alvo);
		Peca pecaCapturada = movimentacao(inicial, alvo);
		
		if (testCheck(jogador)) {
			desfazerMovimentacao(inicial, alvo, pecaCapturada);
			throw new ExcecoesDoXadrez("ERROR - Não pode colocar a si mesmo em check!");
		}
		
		check = (testCheck(oponente(jogador)));

		if (testCheckMate(oponente(jogador))) {
			checkMate = true;
		}
		else {
			proximoTurno();
		}
		
		return (PecaDeXadrez)pecaCapturada;
	}
	
	private Peca movimentacao(Posicao inicial, Posicao alvo) {
		PecaDeXadrez p = (PecaDeXadrez)tabuleiro.RemoverPeca(inicial);
                p.incrementadorDeMovimentos();
		Peca pecaCapturada = tabuleiro.RemoverPeca(alvo);
		tabuleiro.ColocarPeca(p, alvo);
		
		if (pecaCapturada != null) {
			pecaNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		return pecaCapturada;
	}
	
	private void desfazerMovimentacao(Posicao inicial, Posicao alvo, Peca pecaCapturada) {
		PecaDeXadrez p = (PecaDeXadrez)tabuleiro.RemoverPeca(alvo);
		p.decrementadorDeMovimentos();
                tabuleiro.ColocarPeca(p, inicial);
		
		if (pecaCapturada != null) {
			tabuleiro.ColocarPeca(pecaCapturada, alvo);
			pecasCapturadas.remove(pecaCapturada);
			pecaNoTabuleiro.add(pecaCapturada);
		}
	}
	
	private void validacaoDaPosicaoInicial(Posicao posicao) {
		if (!tabuleiro.EUmaPeca(posicao)) {
			throw new ExcecoesDoXadrez("ERRO - Não possui peça nesta posição!");
		}
		if (jogador != ((PecaDeXadrez)tabuleiro.Peca(posicao)).getCor()) {
			throw new ExcecoesDoXadrez("ERROR - Essa peça não é sua!");
		}
		if (!tabuleiro.Peca(posicao).EstaPossuiMovimentosPossiveis()) {
			throw new ExcecoesDoXadrez("ERROR - A peça escolhida não possui movimentos possíveis!");
		}
	}
	
	private void validacaoDaPosicaoFinal(Posicao inicial, Posicao alvo) {
		if (!tabuleiro.Peca(inicial).MovimentoPossivel(alvo)) {
			throw new ExcecoesDoXadrez("ERROR - A peça escolhida não pode se mover para a posição alvo!");
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogador = (jogador == Color.BRANCO) ? Color.PRETO : Color.BRANCO;
	}
	
	private Color oponente(Color color) {
		return (color == Color.BRANCO) ? Color.PRETO : Color.BRANCO;
	}
	
	private PecaDeXadrez king(Color color) {
		List<Peca> list = pecaNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor()== color).collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof King) {
				return (PecaDeXadrez)p;
			}
		}
		throw new IllegalStateException("Não possui um rei " + color + " no tabuleiro!");
	}
	
	private boolean testCheck(Color cor) {
		Posicao kingPosition = king(cor).getPosicaoXadrez().toPosition();
		List<Peca> opponentPieces = pecaNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor()== oponente(cor)).collect(Collectors.toList());
		for (Peca p : opponentPieces) {
			boolean[][] mat = p.movimentosPossiveis();
			if (mat[kingPosition.getLinha()][kingPosition.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Color cor) {
		if (!testCheck(cor)) {
			return false;
		}
		List<Peca> list = pecaNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor()== cor).collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] mat = p.movimentosPossiveis();
			for (int i=0; i<tabuleiro.getLinhas(); i++) {
				for (int j=0; j<tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao inicial = ((PecaDeXadrez)p).getPosicaoXadrez().toPosition();
						Posicao alvo = new Posicao(i, j);
						Peca pecaCapturada = movimentacao(inicial, alvo);
						boolean testCheck = testCheck(cor);
						desfazerMovimentacao(inicial, alvo, pecaCapturada);
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}	
	
	private void colocarPecaNova(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.ColocarPeca(peca, new PosicaoDoXadrez(coluna, linha).toPosition());
		pecaNoTabuleiro.add(peca);
	}
	
	private void initialSetup() {
        colocarPecaNova('h', 7, new Torre(tabuleiro, Color.BRANCO));
        colocarPecaNova('d', 1, new Torre(tabuleiro, Color.BRANCO));
        colocarPecaNova('e', 1, new King(tabuleiro, Color.BRANCO));

        colocarPecaNova('b', 8, new Torre(tabuleiro, Color.PRETO));
        colocarPecaNova('a', 8, new King(tabuleiro, Color.PRETO));
	}
}
