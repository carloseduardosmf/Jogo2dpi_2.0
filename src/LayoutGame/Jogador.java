package LayoutGame;

import javax.swing.*;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Jogador{
	private int x,y;
	private int dx, dy;
	private Image imagem;
	private int altura, largura;
	private List <Tiro> tiros;
	private boolean teclaAtivada = false;
	private List<Especial> especial;
	private boolean isVisivel;
	private boolean controlesInvertidos = false;
	private long tempoInverso =0;
	private static final long duracaoInversao = 3000;
	
	
	public Jogador() {
		this.x = 100;
		this.y = 100;
		isVisivel=true;
		
		tiros = new ArrayList<Tiro>();
		
		especial = new ArrayList<Especial>();
		
		
	}
	
	public void ultimate() {
		this.especial.add(new Especial(x+largura,y+(altura/2)-90));
	}
	
	public void load() {
		ImageIcon ref = new ImageIcon("./image/spaceship_animation-ezgif.com-resize.gif");
		
		
		imagem = ref.getImage();
		
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
	}
	
	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public void setImagem(Image imagem) {
		this.imagem = imagem;
	}

	public void update() {
		x +=dx;
		y +=dy;
		
		if(controlesInvertidos) {
			long agora = System.currentTimeMillis();
			if(agora - tempoInverso >=duracaoInversao) {
				controlesInvertidos = false;
			}
		}
		
	}
	
	public void tiroSimples() {
		this.tiros.add(new Tiro(x+largura, y +(altura/2)));
		//tiro sair do meio da nave
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,largura,altura);
		
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

	public void keyPressed(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		
		if (codigo ==KeyEvent.VK_E && !teclaAtivada) {
			ultimate();
			teclaAtivada = true;
			
		}
		
		if (codigo ==KeyEvent.VK_Q && !teclaAtivada) {
			tiroSimples();
			teclaAtivada = true;
		    }
		
		if (codigo ==KeyEvent.VK_UP) {
			if(controlesInvertidos) {
				dy=3;
			}else {
				dy=-3;
			}
	    }
		if (codigo ==KeyEvent.VK_DOWN) {
			if(controlesInvertidos) {
				dy=-3;
			}else { 
				dy=3;
			}
		}
		if (codigo ==KeyEvent.VK_LEFT) {
			if(controlesInvertidos) {
				 dx=3;
			}else {
				dx=-3;
			}
		}
		if (codigo ==KeyEvent.VK_RIGHT) {
			if(controlesInvertidos) {
				 dx=-3;
			}else {
				dx=3;
			} 
		
		}
	}	
	public void relase(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		
		if (codigo ==KeyEvent.VK_E) {
			 
			            teclaAtivada = false;
			        }
			   
			
		    
		
		if (codigo ==KeyEvent.VK_Q) {
			teclaAtivada = false;
		    }
		
		
		if (codigo ==KeyEvent.VK_UP) {
		 dy=-0;
	    }
		if (codigo ==KeyEvent.VK_DOWN) {
			 dy=0;
		}
		if (codigo ==KeyEvent.VK_LEFT) {
			 dx=-0;
		}
		if (codigo ==KeyEvent.VK_RIGHT) {
			 dx=0;
		}
	}

	
	
	

	public List<Tiro> getTiros() {
		return tiros;
	}

	public List<Especial> getEspecial() {
		return especial;
	}	
	
	public void ativarControlesInvertidos() {
		controlesInvertidos = true;
		tempoInverso = System.currentTimeMillis();
	}
	
}