package LayoutGame;

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
import java.util.List;

import javax.swing.*;

public class Space extends JPanel implements ActionListener {

	private Image background;
	private Jogador jogador;
	private Enemy1 inimigo;
	private Timer timer;
	private List<Enemy1> enemy1;
	private List<Enemy2> enemy2;
	private List<TiroEnemy1> tirosInimigos;
	private List<BossHugo> boss;
	private List<Especial> especial;
	private boolean emJogo;
	private int vidaJogador = 10;
	private int vidaBoss = 1500;
	private boolean gameOver;
	private boolean gameWin;

	public Space() {
		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon backgroundFase = new ImageIcon("./image/background.gif");
		background = backgroundFase.getImage();
		JLabel backgroundAnimado = new JLabel("", backgroundFase, JLabel.CENTER);

		setLayout(null);
		backgroundAnimado.setBounds(0, 0, 1080, 600);
		add(backgroundAnimado);

		jogador = new Jogador();
		jogador.load();
		vidaJogador=10;
		vidaBoss = 1500;
		addKeyListener(new TecladoAdapter());
		
		

		timer = new Timer(5, this);
		timer.start();

		inicializaInimigos();
		inicializaInimigos2();
		inicializaBoss();
		
		emJogo = true;
	}// construtor da fase

	public void inicializaInimigos() {
		int cordenadas[] = new int[60];
		enemy1 = new ArrayList<Enemy1>();

		for (int i = 0; i < cordenadas.length; i++) {
			int x = (int) (Math.random() * 30000 + 1024);
			int y = (int) (Math.random() * 450 + 30);
			Enemy1 inimigo = new Enemy1(x, y);
		    inimigo.setVisivel(true); // <-- adicione isso
			enemy1.add(new Enemy1(x, y));
		}
	}

	public void inicializaInimigos2() {
		int cordenadas[] = new int[2];
		enemy2 = new ArrayList<Enemy2>();

		for (int b = 0; b < cordenadas.length; b++) {
			int x = (int) (Math.random() * 29300 + 1024);
			int y = (int) (Math.random() * 450 + 30);
			Enemy2 inimigo2 = new Enemy2(x, y);
		    inimigo2.setVisivel(true); // <-- adicione isso
			enemy2.add(new Enemy2(x, y));
		}
	}

	public void inicializaBoss() {
		int cordenadas[] = new int[1];
		boss = new ArrayList<BossHugo>();
		
		for (int a = 0; a < cordenadas.length; a++) {
			int x = 3500;
			int y = -50;
			BossHugo bossH = new BossHugo(x, y);
		    bossH.setVisivel(true); // <-- adicione isso
			boss.add(new BossHugo(x, y));
		
		}
		}
	

	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D navezinha = (Graphics2D) g;
		if (emJogo == true) {
			navezinha.drawImage(background, 0, 0, this);
			navezinha.drawImage(jogador.getImagem(), jogador.getX(), jogador.getY(), this);

			List<Tiro> tiros = jogador.getTiros(); // pegando a lista de tiros e colocando na lista
			for (int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				m.load();
				navezinha.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}

			List<Especial> especial = jogador.getEspecial();
			for (int i = 0; i < especial.size(); i++) {
				Especial m = especial.get(i);
				m.load();
				navezinha.drawImage(m.getImagemE(), m.getXE(), m.getYE(), this);
			}
			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 in = enemy1.get(o);
				in.load();
				navezinha.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}

			for (int u = 0; u < enemy2.size(); u++) {
				Enemy2 ib = enemy2.get(u);
				ib.load();
				navezinha.drawImage(ib.getImagem(), ib.getX(), ib.getY(), this);
			}

			for (int f = 0; f < boss.size(); f++) {
				BossHugo hj = boss.get(f);
				hj.load();
				navezinha.drawImage(hj.getImagem(), hj.getX(), hj.getY(), this);
				List<TiroBoss1> tirosB1 = hj.getTiros();
				for (int zx = 0; zx < tirosB1.size(); zx++) {
					TiroBoss1 bo = tirosB1.get(zx);
					bo.load();
					navezinha.drawImage(bo.getImagem(), bo.getX(), bo.getY(), this);

				}
				List<TiroBoss2> tirosB2 = hj.getTiros2();
				for (int ow = 0; ow < tirosB2.size(); ow++) {
					TiroBoss2 b2 = tirosB2.get(ow);
					b2.load();
					navezinha.drawImage(b2.getImagem(), b2.getX(), b2.getY(), this);
				}
			}

			for (int i = 0; i < enemy1.size(); i++) {
				Enemy1 io = enemy1.get(i);
				io.load();
				navezinha.drawImage(io.getImagem(), io.getX(), io.getY(), this);

				List<TiroEnemy1> tirosInimigos = io.getTiros();
				for (int h = 0; h < tirosInimigos.size(); h++) {
					TiroEnemy1 tu = tirosInimigos.get(h);
					tu.load();
					navezinha.drawImage(tu.getImagem(), tu.getX(), tu.getY(), this);
				}
			}if(vidaBoss<=0){
				
				ImageIcon ganhoJogo = new ImageIcon("./image/vcganho.png");
				navezinha.drawImage(ganhoJogo.getImage(), 0, 0, null);
				

			}

		} else if (emJogo==false){
			// CRIANDO O FIM DE JOGO, E DESENHANDO NA TELA PARA RENICIAR O JOGo
			ImageIcon fimJogo = new ImageIcon("./image/gameover.png");
			navezinha.drawImage(fimJogo.getImage(), 0, 0, null);

			navezinha.setColor(Color.GREEN);
			navezinha.setFont(new Font("ARIAL", Font.BOLD, 30));
			navezinha.drawString("PRESSIONE ENTER PARA REINICIAR O JOGO", getWidth() / 2 - 320, getHeight() / 2 + 150);
		} 
		g.dispose();
	}

	public void reinicializarjogo() {
		jogador = new Jogador();
	    jogador.load();

	    vidaJogador = 10;
	    vidaBoss = 1500;

	    // Zerar e recriar listas
	    enemy1 = new ArrayList<>();
	    enemy2 = new ArrayList<>();
	    boss = new ArrayList<>();
	    tirosInimigos = new ArrayList<>();
	    especial = new ArrayList<>();

	    // Recarregar conteúdo
	    inicializaInimigos();
	    inicializaInimigos2();
	    inicializaBoss();

	    emJogo = true;
	    gameOver = false;
	    gameWin = false;

	    timer.stop();
	    timer = new Timer(5, this);
	    timer.start();

	    requestFocusInWindow();
	    repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jogador.update(); // chama o metodo
		List<Tiro> tiros = jogador.getTiros(); // pegando a lista de tiros e colocando na lista

		for (int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			if (m.isVisivel()) {
				m.update();
			} else {
				tiros.remove(i);
			} // atenção pesquisar sobre

		}

		for (int a = 0; a < enemy1.size(); a++) {
			Enemy1 il = enemy1.get(a);
			if (il.isVisivel) {
				il.update();

				if (Math.random() < 0.02 && il.getX() <= 1200 && il.getX() >= 300) {
					il.atirar();
				}

				List<TiroEnemy1> tirosInimigo = il.getTiros();
				for (int j = 0; j < tirosInimigo.size(); j++) {
					TiroEnemy1 ti = tirosInimigo.get(j);
					if (ti.isVisivel()) {
						ti.uptade();
					} else {
						tirosInimigo.remove(j);
					}
				}
			} else {
				enemy1.remove(a);
			}
		}

		List<Especial> especial = jogador.getEspecial();
		for (int k = 0; k < especial.size(); k++) {
			Especial n = especial.get(k);
			if (n.isVisivelE()) {
				n.update();
			} else {
				especial.remove(k);
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

		for (int u = 0; u < enemy2.size(); u++) {
			Enemy2 b = enemy2.get(u);
			if (b.isVisivel()) {
				b.update();
			} else {
				enemy2.remove(u);
			}
		}

		for (int f = 0; f < boss.size(); f++) {
			BossHugo c = boss.get(f);
			if (vidaBoss > 0) {
				c.update();

				if (Math.random() < 0.02 && c.getX() + 400 <= 1200 && c.getX() >= 300) {
					c.atirar();

				}
				if (Math.random() < 0.01 && c.getX() + 400 <= 1200 && c.getX() >= 300) {
					c.atiraReverso();
				}

				List<TiroBoss1> tiroBoss1 = c.getTiros();
				for (int j = 0; j < tiroBoss1.size(); j++) {
					TiroBoss1 ti = tiroBoss1.get(j);
					if (ti.isVisivel()) {
						ti.uptade();
					} else {
						tiroBoss1.remove(j);
					}
				}
				List<TiroBoss2> tiroBoss2 = c.getTiros2();
				for (int jx = 0; jx < tiroBoss2.size(); jx++) {
					TiroBoss2 t2 = tiroBoss2.get(jx);
					if (t2.isVisivel()) {
						t2.uptade();
					} else {
						tiroBoss2.remove(jx);
					}
				}
			} 

		}
		checarColisoes();
		repaint(); // faz comm q não surja um novo sprite pra cada movimentação

	}

	public void checarColisoes() {
		Rectangle formaNave = jogador.getBounds();
		Rectangle formaEnemy1;
		Rectangle formaEnemy2;
		Rectangle formaTiro;
		Rectangle formaBoss;
		Rectangle formaEspecial;
		Rectangle formaTiroInimigo;
		Rectangle formaTiroBoss;
		Rectangle formaTiroBoss2;

		if (vidaJogador <= 0) {
			emJogo = false;
		}

		for (int z = 0; z < boss.size(); z++) {
			BossHugo tempBoss = boss.get(z);
			formaBoss = tempBoss.getBounds();
			if (formaNave.intersects(formaBoss)) {
				vidaJogador = vidaJogador - 1;
				;
			}
		}

		for (int t = 0; t < enemy2.size(); t++) {
			Enemy2 tempEnemy2 = enemy2.get(t);
			formaEnemy2 = tempEnemy2.getBounds();
			if (formaNave.intersects(formaEnemy2)) {
				tempEnemy2.setVisivel(false);
				vidaJogador = vidaJogador - 1;

			}
		}

		for (int i = 0; i < enemy1.size(); i++) {
			Enemy1 tempEnemy1 = enemy1.get(i);
			formaEnemy1 = tempEnemy1.getBounds();
			if (formaNave.intersects(formaEnemy1)) {
				vidaJogador = vidaJogador - 1;
				System.out.print(vidaJogador);
				tempEnemy1.setVisivel(false);
			}

		}
		for (int k = 0; k < enemy1.size(); k++) {
			Enemy1 tempEnemy1 = enemy1.get(k);
			List<TiroEnemy1> tirosInimigos = tempEnemy1.getTiros();

			for (int wq = 0; wq < tirosInimigos.size(); wq++) {
				TiroEnemy1 tiroI = tirosInimigos.get(wq);
				formaTiroInimigo = tiroI.getBounds();
				if (formaTiroInimigo.intersects(formaNave)) {
					tiroI.setVisivel(false);
					vidaJogador = vidaJogador - 1;
				}
			}
		}

		// =====================================================
		for (int op = 0; op < boss.size(); op++) {
			BossHugo bh = boss.get(op);
			List<TiroBoss1> tB1 = bh.getTiros();
			for (int wr = 0; wr < tB1.size(); wr++) {
				TiroBoss1 tiroB = tB1.get(wr);
				formaTiroBoss = tiroB.getBounds();
				if (formaTiroBoss.intersects(formaNave)) {
					tiroB.setVisivel(false);
					vidaJogador = vidaJogador - 1;
				}
			}
			List<TiroBoss2> tB2 = bh.getTiros2();
			for (int wt = 0; wt < tB2.size(); wt++) {
				TiroBoss2 tiroB2 = tB2.get(wt);
				formaTiroBoss2 = tiroB2.getBounds();
				if (formaTiroBoss2.intersects(formaNave)) {
					jogador.ativarControlesInvertidos();
					tiroB2.setVisivel(false);
					vidaJogador = vidaJogador - 1;
				}
			}
		}
		// =====================================================
		List<Tiro> tiros = jogador.getTiros();
		for (int s = 0; s < tiros.size(); s++) {
			Tiro tempTiro = tiros.get(s);
			formaTiro = tempTiro.getBounds();
			for (int o = 0; o < enemy1.size(); o++) {
				Enemy1 tempEnemy1 = enemy1.get(o);

				formaEnemy1 = tempEnemy1.getBounds();

				if (formaTiro.intersects(formaEnemy1)) {
					tempEnemy1.setVisivel(false);
					tempTiro.setVisivel(false);
				}
			}
			for (int p = 0; p < enemy2.size(); p++) {
				Enemy2 tempEnemy2 = enemy2.get(p);
				formaEnemy2 = tempEnemy2.getBounds();
				if (formaTiro.intersects(formaEnemy2)) {
					tempEnemy2.setVisivel(false);
					tempTiro.setVisivel(false);
				}
				BossHugo tempBoss = boss.get(0);
				formaBoss = tempBoss.getBounds();
				if (formaTiro.intersects(formaBoss)) {
					vidaBoss = vidaBoss - 30;
					System.out.println("A vida do boss é" + vidaBoss);
					tempTiro.setVisivel(false);
				}

			}

		}
		List<Especial> especial = jogador.getEspecial();
		for (int q = 0; q < especial.size(); q++) {
			Especial tempEspecial = especial.get(q);
			formaEspecial = tempEspecial.getBounds();
			for (int g = 0; g < enemy1.size(); g++) {
				Enemy1 tempEnemy1 = enemy1.get(g);
				formaEnemy1 = tempEnemy1.getBounds();
				if (formaEspecial.intersects(formaEnemy1)) {
					tempEnemy1.setVisivel(false);
				}
				for (int p = 0; p < enemy2.size(); p++) {
					Enemy2 tempEnemy2 = enemy2.get(p);
					formaEnemy2 = tempEnemy2.getBounds();

				}
				for (int b = 0; b < enemy2.size(); b++) {
					Enemy2 tempEnemy2 = enemy2.get(b);
					formaEnemy2 = tempEnemy2.getBounds();
					if (formaEspecial.intersects(formaEnemy2)) {
						tempEnemy2.setVisivel(false);

					}
					BossHugo tempBoss = boss.get(0);
					formaBoss = tempBoss.getBounds();

					if (formaEspecial.intersects(formaBoss)) {
						vidaBoss = vidaBoss - 01;
						System.out.println(vidaBoss);
						tempEspecial.setVisivelE(false);
					}

				}

			}

		}
	}
	

	private class TecladoAdapter extends KeyAdapter {

		
		@Override
		public void keyPressed(KeyEvent tecla) {
			if ( !emJogo && tecla.getKeyCode() == KeyEvent.VK_ENTER) {
				reinicializarjogo();

			}else if(vidaBoss<0&& tecla.getKeyCode() == KeyEvent.VK_ENTER) {
				reinicializarjogo();}
			else {
				jogador.keyPressed(tecla);
			}
		}

		@Override
		public void keyReleased(KeyEvent tecla) {
			jogador.relase(tecla);
		}

	}
}	