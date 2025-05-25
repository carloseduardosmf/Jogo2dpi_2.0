package meujogo.Modelo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {
	private Image fundo;
	private Player player;
	private Timer timer;
	private List<Enemy1> enemy1;
	private List<Chefe> chefe;
	public boolean emJogo;
	private boolean gameOver;
	// SPAWNANDO O BOSS DEPOIS DOS INIMIGOS
	
	

	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon referencia = new ImageIcon("res\\darkspace.png");

		fundo = referencia.getImage();

		player = new Player();
		player.load();

		addKeyListener(new TecladoAdapter());
		requestFocusInWindow();

		timer = new Timer(5, this);
		timer.start();

		inicializaInimigos();
		inicializarBoss();
		
		
		emJogo = true;

	}

	public void inicializaInimigos() {
		int coordenadas[] = new int[60];
		enemy1 = new ArrayList<Enemy1>();

		for (int i = 0; i < coordenadas.length; i++) {
			int x = (int) (Math.random() * 8000 + 1920);
			int y = (int) (Math.random() * 800 + 100);
			enemy1.add(new Enemy1(x, y));
		}

	}

	// INICIALIZANDO O BOSS
	public void inicializarBoss() {
		int coordenadasChefe[] = new int[4];
		chefe = new ArrayList<Chefe>();

		for (int c = 0; c < coordenadasChefe.length; c++) {
			int x = (int) (Math.random() * 8000 + 1080);
			int y = (int) (Math.random() * 800 + 100);

			chefe.add(new Chefe(x, y));

		}
	}

	public void paint(Graphics g) {

		Graphics2D graficos = (Graphics2D) g;
		if (emJogo == true) {
			graficos.drawImage(fundo, 0, 0, null);
			graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

			List<Tiro> tiros = player.getTiros();
			for (int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			// DESENHANDO O BOSS NA TELA
			
			for (int c = 0; c < chefe.size(); c++) {
				Chefe d = chefe.get(c);
				d.load();
				graficos.drawImage(d.getImagem(), d.getX(), d.getY(), this);
			}
			
			
			
			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 in = enemy1.get(o);
				in.load();
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}
			

		} else {
			// CRIANDO O FIM DE JOGO, E DESENHANDO NA TELA PARA RENICIAR O JOGo
			ImageIcon fimJogo = new ImageIcon("res\\gameover.png");
			graficos.drawImage(fimJogo.getImage(), 0, 0, null);

			graficos.setColor(Color.GREEN);
			graficos.setFont(new Font("ARIAL", Font.BOLD, 30));
			graficos.drawString("PRESSIONE ENTER PARA REINICIAR O JOGO", getWidth() / 2 - 320, getHeight() / 2 + 150);
		}
		g.dispose();

	}

	public void reinicializarjogo() {
		player = new Player();
		player.load();
		enemy1.clear();
		chefe.clear();
		inicializaInimigos();
		// BUG DE TRAVAR NA REINICIALIZAÇÂO CORRIGIDO
		inicializarBoss();
		

		emJogo = true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();
		List<Tiro> tiros = player.getTiros();
		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			if (m.isVisivel()) {
				m.update();
			} else {
				tiros.remove(i);
			}
		}

		for (int o = 0; o < enemy1.size(); o++) {
			Enemy1 in = enemy1.get(o);
			if (in.isVisivel()) {
				in.update();
			} else {
				enemy1.remove(o);
			}
		}

		for (int c = 0; c < chefe.size(); c++) {
			Chefe d = chefe.get(c);
			if (d.isVisivel()) {
				d.update();
			} else {
				chefe.remove(c);
			}
			
		}

		checarColisoes();

		repaint();

	}

	public void checarColisoes() {

		Rectangle formaNave = player.getBounds();
		Rectangle formaTiro;
		Rectangle formaEnemy1;
		Rectangle formaChefe;

		for (int i = 0; i < enemy1.size(); i++) {
			Enemy1 tempEnemy1 = enemy1.get(i);
			formaEnemy1 = tempEnemy1.getBounds();

			if (formaNave.intersects(formaEnemy1)) {
				player.setVisvel(false);
				tempEnemy1.setVisivel(false);
				emJogo = false;
			}
		}

		List<Tiro> tiros = player.getTiros();
		for (int j = 0; j < tiros.size(); j++) {
			Tiro tempTiro = tiros.get(j);
			formaTiro = tempTiro.getBounds();

			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 tempEnemy1 = enemy1.get(o);
				formaEnemy1 = tempEnemy1.getBounds();

				if (formaTiro.intersects(formaEnemy1)) {
					tempEnemy1.setVisivel(false);
					tempTiro.setVisivel(false);
					
			
				}
			}

			for (int c = 0; c < chefe.size(); c++) {
				Chefe tempChefe = chefe.get(c);
				formaChefe = tempChefe.getBounds();

				if (formaTiro.intersects(formaChefe)) {
					tempChefe.setVisivel(false);
					tempTiro.setVisivel(false);
				}
			}

			for (int c = 0; c < chefe.size(); c++) {
				Chefe tempChefe = chefe.get(c);
				formaChefe = tempChefe.getBounds();
				Enemy1 tempEnemy1 = enemy1.get(c);
				formaEnemy1 = tempEnemy1.getBounds();

				if (formaNave.intersects(formaChefe)) {
					player.setVisvel(false);
					tempChefe.setVisivel(false);
					tempEnemy1.setVisivel(false);
					emJogo = false;

				}
				
			}
			
		}

	}

	private class TecladoAdapter extends KeyAdapter {

		public void keyPressed(KeyEvent e) {

			if (!emJogo && e.getKeyCode() == KeyEvent.VK_ENTER) {
				reinicializarjogo();

			} else {

				player.keyPressed(e);
			}

		}

		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);

		}

	}
}
