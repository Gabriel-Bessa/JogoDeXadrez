
package xadrez;

import CamadaTabuleiro.Peca;
import CamadaTabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca{
    private Cor cor;

    public PecaXadrez(Cor cor, Tabuleiro tabuleiro) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }
    
    
}
