package ifpr.paranavai.jogo.modelo;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.swing.ImageIcon;
import javax.persistence.Column;
import javax.persistence.OneToMany;


@Entity
@Table(name = "tb_personagem")
public class Personagem extends ElementoGrafico {

    private static final int DESLOCAMENTO = 3;

    @Column(name = "posicao_inicial_x")
    private static final int POSICAO_INICIAL_EM_X = 100;

    @Column(name = "posicao_inicial_y")
    private static final int POSICAO_INICIAL_EM_Y = 100;

    @Column(name = "deslocamento_x")
    private int deslocamentoEmX;

    @Column(name = "deslocamento_y")
    private int deslocamentoEmY;

    @Column(name = "pontuacao")
    private int pontuacao;

    @Column(name = "vidas")
    protected int vida = 3;

    @OneToMany(mappedBy = "personagem")
    private ArrayList<Tiro> tiros;

    @OneToMany(mappedBy = "personagem")
    protected List<TiroSuper> tiroSuper;

    public Personagem() {
        this.carregar();
        super.setPosicaoEmX(POSICAO_INICIAL_EM_X);
        super.setPosicaoEmY(POSICAO_INICIAL_EM_Y);
        this.tiros = new ArrayList<Tiro>();
        this.tiroSuper = new ArrayList<TiroSuper>();

    }

    public void carregar() {
        ImageIcon carregando = new ImageIcon(getClass().getResource("/espaconave.png"));
        super.setImagem(carregando.getImage());
    }

    public void atualizar() {

        if (deslocamentoEmX < 1) {
            this.deslocamentoEmX = deslocamentoEmX + 3;
        } else if (deslocamentoEmX > 440) {
            this.deslocamentoEmX = deslocamentoEmX - 3;
        } else if (deslocamentoEmY < 1) {
            this.deslocamentoEmY = deslocamentoEmY + 3;
        } else if (deslocamentoEmY > 240) {
            this.deslocamentoEmY = deslocamentoEmY - 3;
        } else {
            super.setPosicaoEmX(super.getPosicaoEmX() + this.deslocamentoEmX);
            super.setPosicaoEmY(super.getPosicaoEmY() + this.deslocamentoEmY);
        }

    }

    public void atirar() {
        int frenteDaNave = super.getPosicaoEmX() + super.getLarguraImagem();
        int meioDaNave = super.getPosicaoEmY() + (super.getAlturaImagem() / 2);
        Tiro tiro = new Tiro(frenteDaNave, meioDaNave);
        this.tiros.add(tiro);
    }

    public void superAtirar() {
        int frenteDaNave = super.getPosicaoEmY() + super.getLarguraImagem(); 
        int meioDaNave = super.getPosicaoEmX() + (super.getAlturaImagem() / 2);
        TiroSuper superTiro = new TiroSuper(frenteDaNave + -62, meioDaNave + -750);
        this.tiroSuper.add(superTiro);
    }

    public void mover(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_UP:
                this.deslocamentoEmY = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_DOWN:
                this.deslocamentoEmY = DESLOCAMENTO;
                break;
            case KeyEvent.VK_LEFT:
                this.deslocamentoEmX = -DESLOCAMENTO;
                break;
            case KeyEvent.VK_RIGHT:
                this.deslocamentoEmX = DESLOCAMENTO;
                break;
            default:
                break;
        }
    }

    public void parar(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        switch (codigo) {
            case KeyEvent.VK_UP:
                deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_DOWN:
                deslocamentoEmY = 0;
                break;
            case KeyEvent.VK_LEFT:
                deslocamentoEmX = 0;
                break;
            case KeyEvent.VK_RIGHT:
                deslocamentoEmX = 0;
                break;
            default:
                break;
        }
    }

    public int getDeslocamentoEmX() {
        return this.deslocamentoEmX;
    }

    public void setDeslocamentoEmX(int deslocamentoEmX) {
        this.deslocamentoEmX = deslocamentoEmX;
    }

    public int getDeslocamentoEmY() {
        return this.deslocamentoEmY;
    }

    public ArrayList<Tiro> getTiros() {
        return this.tiros;
    }

    public void setTiros(ArrayList<Tiro> tiros) {
        this.tiros = tiros;
    }

    public void setDeslocamentoEmY(int deslocamentoEmY) {
        this.deslocamentoEmY = deslocamentoEmY;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }


    public List<TiroSuper> getTiroSuper() {
        return this.tiroSuper;
    }

    public void setTiroSuper(List<TiroSuper> tiroSuper) {
        this.tiroSuper = tiroSuper;
    }

    public int getVida() {
        return this.vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }


    
}