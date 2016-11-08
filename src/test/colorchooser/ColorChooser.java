
package test.colorchooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

/**
***************************************************************
 * Dialogo para seleccionar un color
 *
 * @see ColorPanel
*/
public class ColorChooser extends JDialog {
	int selec = - 1;
	private static ColorPanel color;
	
	/**
		 * Abre el dialogo con un color seleccionado
		 *
		 * @param n Color por defecto
	@roseuid 3CA83DBD0119
	*/
	public ColorChooser(int n) {
			this();
			setSelected(n);
	}
	
	/**
		 * abre el dialogo (se deberia usar ColorChooser(n) )
	@roseuid 3CA83DBD011B
	*/
	public ColorChooser() {
		setTitle("Elija un color");
		setModal(true); 
		setResizable(false);
	 	 color = new ColorPanel(new MiListener());
		 setContentPane(color);
        pack();
	}
	
	/**
	 *	Obtiene el indice del elemento seleccionado del panel
	 *
	 * @return     Indice del elemento seleccionado
	@roseuid 3CA83DBD011C
	*/
	public int getSelected() {
			 return selec;
	}
	
	/**
	@roseuid 3CA83DBD0128
	*/
	public void setSelected(int n) {
			selec =n;
	}
	
	class MiListener implements ActionListener {
		
		/**
		@roseuid 3CA83DBD0138
		*/
		public void actionPerformed(ActionEvent e) {
				String aux=e.getActionCommand();
				selec=Integer.parseInt(aux);
				dispose();
		}
	}
}
