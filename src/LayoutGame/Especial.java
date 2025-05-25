package LayoutGame;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Especial {
	 private Image imagem;
	 private int x,y;
	 private int largura, altura;
	 private boolean isVisivel;
	 
	 
	 private static final int LARGURAE =936;
	 private static int VELOCIDADEE = 4;
	 
	 
	 public Especial (int x, int y) {
		 this.x = x;
		 this.y = y;
		 isVisivel = true;	 
	}
	 public void load() {
		 ImageIcon especial = new ImageIcon("./image/animated_sprite_grid_fixed-ezgif.com-resize (1).gif");
		 imagem = especial.getImage();
		 this.largura = imagem.getWidth(null);
		 this.altura = imagem.getHeight(null);
		 
	 }
	 
	 public void update() {
		 this.x += VELOCIDADEE;
		 	if(this.x > LARGURAE) {
		 		isVisivel = false;
		 	}}
	 
	 		public Rectangle getBounds() {
			return new Rectangle(x,y,largura,altura);
	 		}
	 
		 	public boolean isVisivelE() {
		 		return isVisivel;
		 	}
		 	
		 	public void setVisivelE(boolean isVisivel) {
		 		this.isVisivel = isVisivel;
		 	}
		 	
		 	public static int getVELOCIDADEE() {
				return VELOCIDADEE;
			}

			public static void setVELOCIDADEE(int VELOCIDADE) {
				VELOCIDADEE = VELOCIDADE;
			}

			public int getXE() {
				return x;
			}

			public int getYE() {
				return y;
			}

			public Image getImagemE() {
				return imagem;
			}

	
	
}
