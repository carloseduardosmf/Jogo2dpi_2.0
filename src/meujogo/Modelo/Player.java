package meujogo.Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Player {

	private int x, y;
	private int dx, dy;
	private Image imagem;
	private int largura, altura;
	private List <Tiro> tiros;
	private boolean isVisvel = true;
	private final int LARGURA_TELA  = 1920;
	private final int ALTURA_TELA = 1200;
	
	//VARIAVEIS DO PARA COOL DOWN
	private int quantidadeDeTiros = 0;
	private boolean podeAtirar = true;
	private final int MAX_TIROS = 20;
	long tempoCoolDown = 0;
	
	
	public Player () {
		this.x = 100;
		this.y = 100;
		tiros = new ArrayList<Tiro>();
		load();
	
	}
	public void load() {
		ImageIcon nave = new ImageIcon("res\\chatgptnave-removebg-preview.png");
		imagem = nave.getImage();
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
	}
	public void update() {
		x += dx;
		y += dy;
		if(x<0) {
		   x =0;
		}
		if(x > LARGURA_TELA -largura) {
			x = LARGURA_TELA -largura;
		}
		if(y < 0) {
			y = 0;
		}
		if(y > ALTURA_TELA - altura) {
			y = ALTURA_TELA - altura;
		}
		
	}
	
	public Rectangle getBounds() {
		
    	return new Rectangle(x,y,largura,altura);
    }	
	
	public void tiroSimples() {
		this.tiros.add(new Tiro(x + largura, y + (altura/3)));
	}
	
	public void keyPressed(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_C) {
			//CONTA OS MILISEGUNDOS E ADICIONA A VARIAVEL AGORA
			long agora = System.currentTimeMillis();
			
			/*
			 * verifica se ja deu os 3 tiros, se não ele
			 * continua o programa, se sim ele continua o timer até dar 2000 milisegundos
			 */
			
			if(!podeAtirar) {
				
				if(agora >= tempoCoolDown + 2000) {
					quantidadeDeTiros = 0;
					podeAtirar = true;
					
				}
			}
			//executa o tiro
			if(podeAtirar) {
				
			tiroSimples();
			quantidadeDeTiros++;
			
			
		    //começa o coolDown
			if(quantidadeDeTiros >= MAX_TIROS) {
				podeAtirar = false;
				tempoCoolDown = agora;
			}
			
			
			}
			
		}
			
		if(codigo == KeyEvent.VK_S) {
			dy = 5;
		}
		if(codigo == KeyEvent.VK_W) {
			dy = -5;
		}
		
		if(codigo == KeyEvent.VK_A) {
			dx = -5;
		}
		if(codigo == KeyEvent.VK_D) {
			dx = 5;
		}
		
	} public void keyReleased(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_S) {
			dy = 0;
		}
		if(codigo == KeyEvent.VK_W) {
			dy = 0;
		}
		
		if(codigo == KeyEvent.VK_A) {
			dx = 0;
		}
		if(codigo == KeyEvent.VK_D) {
			dx = 0;
		}
	
}
	
	public int getX() {
		return x;
	}
	public void setVisvel(boolean isVisvel) {
		this.isVisvel = isVisvel;
	}
	public boolean isVisvel() {
		return isVisvel;
	}
	public int getY() {
		return y;
	}
	public Image getImagem() {
		return imagem;
	}
	public List<Tiro> getTiros() {
		return tiros;
	}
	
}
