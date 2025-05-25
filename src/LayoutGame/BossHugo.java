package LayoutGame;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class BossHugo {
	
	private static final int PADDING = -200;
	private Image imagem;
	private int x,y;
	private int largura,altura;
	private boolean isVisivel;
	private double direcaoY= 1;
	private List<TiroBoss1> tiros;
	private List<TiroBoss2> tiros2;
	
	int hbX = 600;
	int hbY = 600 ;
	
	//private static final int LARGURA = 938;
	private static int VELOCIDADE = 4 ;
	
	
	public BossHugo(int x, int y) { 
		this.x = x;
		this.y = y;
		isVisivel = true;
		
		tiros = new ArrayList<TiroBoss1>();
		tiros2 = new ArrayList<TiroBoss2>();
	}
	
	public void load() {
		ImageIcon boss = new ImageIcon("./image/eye_monster.gif");
		imagem = boss.getImage();		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		
		
	   
	}
	public void update() {
		this.x -= VELOCIDADE;
		if(this.x < 500) {
			VELOCIDADE = 0;
			
			this.y += direcaoY ;
				if(this.y >= 80) {
					direcaoY= -2;
				}else if(this.y <=-120){
					direcaoY= 1.50;
				}
			}
			
		}
	
		public void atirar() {
			int alturaTiro = (int) (Math.random() * altura);
			this.tiros.add(new TiroBoss1(x+400,y+alturaTiro/2));
		}
		
		public void atiraReverso(){
			int alturaTiro = (int) (Math.random() * altura);
			this.tiros2.add(new TiroBoss2(x+400,y+alturaTiro/2));
		}
	
			//if(this.x > LARGURA) {
				//isVisivel=false;
			//}
	
	
	public Rectangle getBounds() {
		int hbLargura = largura - 2 * PADDING;
	    int hbAltura = altura - 2 * PADDING;

	    if (hbLargura <= 0) hbLargura = largura;
	    if (hbAltura <= 0) hbAltura = altura;

		return new Rectangle(this.x + 200 ,y, largura,hbAltura);
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}
	
	public List<TiroBoss1> getTiros() {
	    return tiros;
	}
	public List<TiroBoss2> getTiros2() {
	    return tiros2;
	}
	
}
