package meujogo.Modelo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Chefe {
	private Image imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;

	
	private static int VELOCIDADE = 1;

	public Chefe(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
		load();

	}

	public void load() {
		ImageIcon referencia = new ImageIcon("res\\Chefe300.png");
		imagem = referencia.getImage();

		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}

	public void update() {
		this.x -= VELOCIDADE;

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

}
