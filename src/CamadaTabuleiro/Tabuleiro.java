package CamadaTabuleiro;

public class Tabuleiro {
    private int linhas;
    private int colunas;
    private Peca[][] pecas;

    public Tabuleiro(int linhas, int colunas) throws Exception {
        if (linhas < 1  || colunas < 1){
            throw new Exception("ERROR - Criação de Tabuleiro invalida!");
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
    
    public Peca peca(int linha, int coluna) throws Exception{
        if(!existePosicao(linha, coluna)){
            throw new Exception("ERRO - Posição inexistente!");
        }
        return pecas[linha][coluna];
    }
    
    public Peca peca(Posicao posicao){
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }    
       
    public void colocarPeca(Peca peca, Posicao posicao) throws Exception{
        if(existeUmaPecaNestaPosicao(posicao)){
            throw new Exception("ERROR - Já existe um peça na posição: "+posicao);
        }
        pecas[posicao.getLinha()][posicao.getColuna()] = peca;
        peca.posicao = posicao;
    }
    private boolean existePosicao(int linha, int coluna){
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }
    public boolean existePosicao(Posicao posicao){
        return existePosicao(posicao.getLinha(), posicao.getColuna());
    }
    public boolean existeUmaPecaNestaPosicao(Posicao posicao) throws Exception{
        if(!existePosicao(posicao)){
            throw new Exception("ERRO - Posição inexistente!");
        }
        return peca(posicao) != null;
    }
}
