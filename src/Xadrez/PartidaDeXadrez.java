package Xadrez;

import CamadaTabuleiro.Tabuleiro;
import CamadaTabuleiro.Peca;
import CamadaTabuleiro.Posicao;
import Xadrez.Peças.Bispo;
import Xadrez.Peças.Cavalo;
import Xadrez.Peças.King;
import Xadrez.Peças.Peao;
import Xadrez.Peças.Queen;
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
	private PecaDeXadrez enPassantVulneravel;        
	private List<Peca> pecaNoTabuleiro = new ArrayList<>();
	private List<Peca>pecasCapturadas = new ArrayList<>();
        private PecaDeXadrez promocao;
	
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
	
        public PecaDeXadrez getEnPassanteVulneravel(){
            return enPassantVulneravel;
        }
        
        public PecaDeXadrez getPromocao(){
            return promocao;
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
		
                PecaDeXadrez pecaMovida = (PecaDeXadrez)tabuleiro.Peca(alvo);
                
                // PROMOÇÃo
                promocao = null;
                if(pecaMovida instanceof Peao){
                    if((pecaMovida.getCor() == Color.BRANCO && alvo.getLinha() == 0)||(pecaMovida.getCor() == Color.PRETO && alvo.getLinha() == 7)){
                        promocao = (PecaDeXadrez)tabuleiro.Peca(alvo);
                        promocao = recolocarPecaPromocao("Q");
                    }
                }
                
		check = (testCheck(oponente(jogador)));

		if (testCheckMate(oponente(jogador))) {
			checkMate = true;
		}
		else {
			proximoTurno();
		}
		
                // EN PASSANT
                if(pecaMovida instanceof Peao && (alvo.getLinha() == inicial.getLinha()-2 ||alvo.getLinha() == inicial.getLinha()+2 )){
                 enPassantVulneravel = pecaMovida;   
                }
                else{
                    enPassantVulneravel = null;
                }           
                
                
		return (PecaDeXadrez)pecaCapturada;
	}
	
        public PecaDeXadrez recolocarPecaPromocao(String tipo){
            if(promocao == null){
            throw new IllegalStateException("ERRO - Não há peça a ser promovida!");
            }
            if(!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("Q") && !tipo.equals("T")){
                return promocao;
            }
            
           Posicao posi = promocao.getPosicaoXadrez().toPosition();
           Peca p = tabuleiro.RemoverPeca(posi);
           pecaNoTabuleiro.remove(p);
           
           PecaDeXadrez novaPeca = novaPeca(tipo, promocao.getCor());
           tabuleiro.ColocarPeca(novaPeca, posi);
           pecaNoTabuleiro.add(novaPeca);
           return novaPeca;
        }
        
        private PecaDeXadrez novaPeca(String tipo, Color cor){
            if(tipo.equals("B")) return new Bispo(tabuleiro, cor);
            if(tipo.equals("C")) return new Cavalo(tabuleiro, cor);
            if(tipo.equals("Q")) return new Queen(tabuleiro, cor);
            return new Torre(tabuleiro, cor);
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
		// ROQUE PEQUENO
                if(p instanceof King && alvo.getColuna() == inicial.getColuna()+2){
                    Posicao inicialT = new Posicao(inicial.getLinha(), inicial.getColuna() +3);
                    Posicao alvoT = new Posicao(inicial.getLinha(), inicial.getColuna() +1);
                    PecaDeXadrez torre = (PecaDeXadrez)tabuleiro.RemoverPeca(inicialT);
                    tabuleiro.ColocarPeca(torre, alvoT);
                    torre.incrementadorDeMovimentos();
                }
                
                // ROQUE GRANDE
                if(p instanceof King && alvo.getColuna() == inicial.getColuna() -2){
                    Posicao inicialT = new Posicao(inicial.getLinha(), inicial.getColuna() -4);
                    Posicao alvoT = new Posicao(inicial.getLinha(), inicial.getColuna() -1);
                    PecaDeXadrez torre = (PecaDeXadrez)tabuleiro.RemoverPeca(inicialT);
                    tabuleiro.ColocarPeca(torre, alvoT);
                    torre.incrementadorDeMovimentos();
                }
                
                // EN PASSANT
                if(p instanceof Peao){
                    if(inicial.getColuna() != alvo.getColuna() && pecaCapturada == null){
                        Posicao posicaoPeao;
                        if(p.getCor() == Color.BRANCO){
                            posicaoPeao = new Posicao(alvo.getLinha() +1, alvo.getColuna());
                        }
                        else{
                            posicaoPeao = new Posicao(alvo.getLinha() -1, alvo.getColuna());
                        }
                        
                        pecaCapturada = tabuleiro.RemoverPeca(posicaoPeao);
                        pecasCapturadas.add(pecaCapturada);
                        pecaNoTabuleiro.remove(pecaCapturada);
                    }
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
                // ROQUE PEQUENO
                if(p instanceof King && alvo.getColuna() == inicial.getColuna()+2){
                    Posicao inicialT = new Posicao(inicial.getLinha(), inicial.getColuna() +3);
                    Posicao alvoT = new Posicao(inicial.getLinha(), inicial.getColuna() +1);
                    PecaDeXadrez torre = (PecaDeXadrez)tabuleiro.RemoverPeca(alvoT);
                    tabuleiro.ColocarPeca(torre, inicialT);
                    torre.decrementadorDeMovimentos();
                }
                
                // ROQUE GRANDE
                if(p instanceof King && alvo.getColuna() == inicial.getColuna() -2){
                    Posicao inicialT = new Posicao(inicial.getLinha(), inicial.getColuna() -4);
                    Posicao alvoT = new Posicao(inicial.getLinha(), inicial.getColuna() -1);
                    PecaDeXadrez torre = (PecaDeXadrez)tabuleiro.RemoverPeca(alvoT);
                    tabuleiro.ColocarPeca(torre, inicialT);
                    torre.decrementadorDeMovimentos();
                }
                
                // EN PASSANT
                if(p instanceof Peao){
                    if(inicial.getColuna() != alvo.getColuna() && pecaCapturada == enPassantVulneravel){
                        PecaDeXadrez peao = (PecaDeXadrez)tabuleiro.RemoverPeca(alvo);
                        Posicao posicaoPeao;
                        if(p.getCor() == Color.BRANCO){
                            posicaoPeao = new Posicao(3, alvo.getColuna());
                        }
                        else{
                            posicaoPeao = new Posicao(4, alvo.getColuna());
                        }
                        tabuleiro.ColocarPeca(peao, posicaoPeao);
                        
                    }
                }
                
                
	}
	
	private void validacaoDaPosicaoInicial(Posicao posicao) {
		if (!tabuleiro.EUmaPeca(posicao)) {
			throw new ExcecoesDoXadrez("ERROR - Não possui peça nesta posição!");
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
        colocarPecaNova('a', 8, new Torre(tabuleiro, Color.PRETO));
        colocarPecaNova('h', 8, new Torre(tabuleiro, Color.PRETO)); 
        colocarPecaNova('c', 8, new Bispo(tabuleiro, Color.PRETO));         
        colocarPecaNova('f', 8, new Bispo(tabuleiro, Color.PRETO));
        colocarPecaNova('b', 8, new Cavalo(tabuleiro, Color.PRETO));
        colocarPecaNova('g', 8, new Cavalo(tabuleiro, Color.PRETO));
        colocarPecaNova('e', 8, new King(tabuleiro, Color.PRETO, this)); 
        colocarPecaNova('d', 8, new Queen(tabuleiro, Color.PRETO));
        colocarPecaNova('a', 7, new Peao(tabuleiro, Color.PRETO, this));
        colocarPecaNova('b', 7, new Peao(tabuleiro, Color.PRETO,this));
        colocarPecaNova('c', 7, new Peao(tabuleiro, Color.PRETO,this));
        colocarPecaNova('d', 7, new Peao(tabuleiro, Color.PRETO,this));
        colocarPecaNova('e', 7, new Peao(tabuleiro, Color.PRETO,this));
        colocarPecaNova('f', 7, new Peao(tabuleiro, Color.PRETO,this));
        colocarPecaNova('g', 7, new Peao(tabuleiro, Color.PRETO,this));
        colocarPecaNova('h', 7, new Peao(tabuleiro, Color.PRETO,this));
        
        colocarPecaNova('a', 2, new Peao(tabuleiro, Color.BRANCO,this));
        colocarPecaNova('b', 2, new Peao(tabuleiro, Color.BRANCO,this));
        colocarPecaNova('c', 2, new Peao(tabuleiro, Color.BRANCO,this));
        colocarPecaNova('d', 2, new Peao(tabuleiro, Color.BRANCO,this));
        colocarPecaNova('e', 2, new Peao(tabuleiro, Color.BRANCO,this));
        colocarPecaNova('f', 2, new Peao(tabuleiro, Color.BRANCO,this));
        colocarPecaNova('g', 2, new Peao(tabuleiro, Color.BRANCO,this));
        colocarPecaNova('h', 2, new Peao(tabuleiro, Color.BRANCO,this));
        colocarPecaNova('a', 1, new Torre(tabuleiro, Color.BRANCO));
        colocarPecaNova('h', 1, new Torre(tabuleiro, Color.BRANCO));
        colocarPecaNova('b', 1, new Cavalo(tabuleiro, Color.BRANCO));
        colocarPecaNova('f', 1, new Cavalo(tabuleiro, Color.BRANCO));
        colocarPecaNova('g', 1, new Bispo(tabuleiro, Color.BRANCO)); 
        colocarPecaNova('c', 1, new Bispo(tabuleiro, Color.BRANCO)); 
        colocarPecaNova('e', 1, new King(tabuleiro, Color.BRANCO, this));
        colocarPecaNova('d', 1, new Queen(tabuleiro, Color.BRANCO));
	}
}


