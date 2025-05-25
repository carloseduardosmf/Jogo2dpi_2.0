package LayoutGame;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.ImageIcon;

public class TiroBoss2 {
	private Image imagem;
	private int x,y;
	private int largura, altura;
	private boolean isVisivel = true;
	
	
	  private static final int LIMITE = 0; 
	  private static int VELOCIDADE = 13;
	  
	  public TiroBoss2(int x, int y){
		  this.x =x;
		  this.y = y;
		  
	  }
	  
	  public void load() {
		  ImageIcon tiro2 = new ImageIcon("./image/green_reverse_shot_nodup1-ezgif.com-rotate.gif");
		  imagem = tiro2.getImage();
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
	  

