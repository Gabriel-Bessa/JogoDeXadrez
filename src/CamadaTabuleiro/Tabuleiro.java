package CamadaTabuleiro;

public class Tabuleiro {
    private int linhas;
    private int colunas;
    private Peca[][] pecas;

    public Tabuleiro(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        pecas = new Peca[linhas][colunas];
    }
    
    public Peca peca(int linha, int coluna){
        return pecas[linha][coluna];
    }
    
    public Peca peca(Posicao posicao){
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }    

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }
    
}
