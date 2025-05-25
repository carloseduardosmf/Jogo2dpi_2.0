package LayoutGame;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class TiroEnemy1 {
	private Image imagem;
	private int x,y;
	private int largura, altura;
	private boolean isVisivel = true;
	
	  private static final int LIMITE = 0; 
	  private static int VELOCIDADE = 16;
	  
	  public TiroEnemy1(int x, int y){
		  this.x =x;
		  this.y = y;
	  }
	  
	  public void load() {
		  ImageIcon tiro = new ImageIcon("./image/cosmic_shot_animation-ezgif.com-resize.GIF");
		  imagem = tiro.getImage();
		  this.largura = imagem.getWidth(null);
	      this.altura = imagem.getHeight(null);
		  }
	  
	  public void uptade() {
		  this.x -= VELOCIDADE;
		  if (this.x < LIMITE) {
			  isVisivel = false;
		  }
	  }
	  
	  public Rectangle getBounds() {
	        return new Rectangle(x, y, largura, altura);
	    }

	    public boolean isVisivel() {
	        return isVisivel;
	    }

	    public void setVisivel(boolean isVisivel) {
	        this.isVisivel = isVisivel;
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
	}
	  

