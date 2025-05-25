package LayoutGame;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Enemy1 {
	
	private Image imagem;
	private int x,y;
	private int largura,altura;
	boolean isVisivel;
	private int larguraColisao = 15;
	private int alturaColisao = 80;
	private List<TiroEnemy1> tiros;
	
	//private static final int LARGURA = 938;
	private static int VELOCIDADE = 4 ;
	
	
	public Enemy1(int x, int y) { 
		this.x = x;
		this.y = y;
		isVisivel = true;
		
		tiros = new ArrayList<TiroEnemy1>();
	}
	
	public void load() {
		ImageIcon tiro = new ImageIcon("./image/vilão.gif");
		imagem = tiro.getImage();		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}
	public void update() {
		this.x -= VELOCIDADE;
		
			//if(this.x > LARGURA) {
				//isVisivel=false;
			//}
	}
	
	public void atirar() {
		this.tiros.add(new TiroEnemy1(x,y+altura/2));
	}
	
	
	public Rectangle getBounds() {
		
		return new Rectangle(x+100,y,larguraColisao,alturaColisao);
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
	
	public List<TiroEnemy1> getTiros() {
	    return tiros;
	}
	
	
}
