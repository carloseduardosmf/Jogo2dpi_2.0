package jogoSystem;

import javax.swing.JFrame;

import LayoutGame.Space;

public class Container extends JFrame {
	public Container() {
		setTitle("Falcon II");
		
		setSize(1080,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		add(new Space());
		setVisible(true);
		
	}
	
	public static void main(String [] args) {
		new Container();
	}
}
