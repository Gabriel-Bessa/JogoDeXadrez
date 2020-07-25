package CamadaTabuleiro;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {
		if (linhas < 1 || colunas < 1) {
			throw new ExcecoesDoTabuleiro("ERROR - na criação do tabuleiro: Deve haver pelo menos 1 linha e 1 coluna!");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	public Peca Peca(int linha, int coluna) {
		if (!ExistePosicao(linha, coluna)) {
			throw new ExcecoesDoTabuleiro("ERROR - Posição não se encontra no  tabuleiro!");
		}
		return pecas[linha][coluna];
	}
	
	public Peca Peca(Posicao posicao) {
		if (!ExistePosicao(posicao)) {
			throw new ExcecoesDoTabuleiro("ERROR - Posição não se encontra no  tabuleiro!");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void ColocarPeca(Peca peca, Posicao posicao) {
		if (EUmaPeca(posicao)) {
			throw new ExcecoesDoTabuleiro("ERROR - Já possui uma peça na posição: " + posicao);
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	public Peca RemoverPeca(Posicao posicao) {
		if (!ExistePosicao(posicao)) {
			throw new ExcecoesDoTabuleiro("ERROR - Esta posição não esta no tabuleiro!");
		}
		if (Peca(posicao) == null) {
			return null;
		}
		Peca aux = Peca(posicao);
		aux.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;
	}
	
	private boolean ExistePosicao(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean ExistePosicao(Posicao posicao) {
		return ExistePosicao(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean EUmaPeca(Posicao posicao) {
		if (!ExistePosicao(posicao)) {
			throw new ExcecoesDoTabuleiro("ERROR - Posição não está no tabuleiro!");
		}
		return Peca(posicao) != null;
	}
}
