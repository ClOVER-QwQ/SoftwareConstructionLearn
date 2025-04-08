package example;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TestEmptyFrame {
	static final int WIDTH=250;
	static final int HEIGHT=200;
	
	public static void main(String[] args) {
		JFrame jf=new JFrame("����Swing");
		jf.setSize(WIDTH,HEIGHT);
		jf.setVisible(true);
		JOptionPane.showMessageDialog(null, "����һ��Message�Ի���");
		

	}

}
