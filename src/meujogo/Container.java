package meujogo;

import javax.swing.JFrame;

import meujogo.Modelo.Fase;


public class Container extends JFrame {

	public Container(){
        
        add(new Fase());
        setTitle("FAlCON");
        setSize(1920, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);
    }
        public static void main(String[] args) {
            new Container();
        }
}
	
	


