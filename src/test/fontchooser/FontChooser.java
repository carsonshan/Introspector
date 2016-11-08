
package test.fontchooser;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;



/**
***************************************************************
 * Dialogo para seleccionar una Font
 *
 * @see FontPanel
*/
public class FontChooser extends JDialog {
	JButton butonOK;
	private static FontPanel fonts;
	Font selec = null;
	
	/**
		 * Abre el dialogo con un color seleccionado
		 *
		 * @param n Color por defecto
	@roseuid 3CA83DBE0179
	*/
	public FontChooser(Font n) {
			setTitle("Elija una fuente");
			setModal(true); 
			//setResizable(false);
			MiListener m=new MiListener();
	 	 	fonts = new FontPanel(m,n);
		 	setContentPane(fonts);
		 	butonOK=new JButton("OK");
		
			setSelected(n);
			getContentPane().add(BorderLayout.SOUTH, butonOK);
        	pack();
	}
	
	/**
		 * abre el dialogo (se deberia usar ColorChooser(n) )
	@roseuid 3CA83DBE017B
	*/
	public FontChooser() {
		this(null);
	}
	
	/**
	 *	Obtiene el indice del elemento seleccionado del panel
	 *
	 * @return     Indice del elemento seleccionado
	@roseuid 3CA83DBE0186
	*/
	public Font getSelected() {
			 if(selec==null) return null;
			 return selec.deriveFont((float)10.);
	}
	
	/**
	@roseuid 3CA83DBE0187
	*/
	public void setSelected(Font n) {
			selec =n;
		//	fonts.setSelected(n);
	}
	
	class MiListener implements ActionListener {
		
		/**
		@roseuid 3CA83DBE0197
		*/
		public void actionPerformed(ActionEvent e) {
				selec=fonts.getSelectedFont();
				dispose();
		}
	}
}
