package test;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{

	
	private TestBean bean;
	
	public static void main(String[] args) {
		Main m=new Main();
		
	}
	
	public Main(){
		super("Prueba");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JButton b=new JButton("Boton");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				BeanEditor editor=new BeanEditor(bean);
				JDialog d=editor.getDialog(true);
				d.setVisible(true);
				
			}
		});
		JPanel p=new JPanel(new FlowLayout());
		setContentPane(p);
		p.add(b);
		
		bean=new TestBean();
		setSize(400,300);
		setVisible(true);
	}

}
