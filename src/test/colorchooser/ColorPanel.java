
package test.colorchooser;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

//import com.soluzionasf.onis.oniscanvas.onisgraphics.OnisGraphics;

class ColorPanel extends JPanel {
	static final int NUMCOLORES = 65;
	
	/**
	@roseuid 3CA83DBD01D4
	*/
	public ColorPanel(ActionListener listener) {	
		// Numero de colores por fila
		setLayout(new GridLayout(0,10));
		// Ahora cogemos todos los colores:
		for(int i=0;i < NUMCOLORES; i++ ) {
			JButton b=new JButton();
			Color color=getColor(i);
			b.setBackground(color);
			b.addActionListener(listener);
			b.setActionCommand(Integer.toString(i));
			// Tamaño de los botones (de los colores)
			b.setPreferredSize(new Dimension(20,20));
			add(b);
		}
	}
	
	public static Color getColor(int c) {

		// PARA Color -1
		if (c < 0)
			c = 4;

		switch (c) {
			case 0:
				return (new Color(127, 255, 212));
			case 1:
				return (new Color(0, 0, 0));
			case 2:
				return (new Color(0, 0, 255));
			case 3:
				return (new Color(138, 43, 226));
			case 4:
				return (new Color(165, 42, 42));
			case 5:
				return (new Color(95, 158, 160));
			case 6:
				return (new Color(255, 127, 80));
			case 7:
				return (new Color(100, 149, 237));
			case 8:
				return (new Color(0, 255, 255));
			case 9:
				return (new Color(0, 100, 0));
			case 10:
				return (new Color(85, 107, 47));
			case 11:
				return (new Color(153, 50, 204));
			case 12:
				return (new Color(72, 61, 139));
			case 13:
				return (new Color(47, 79, 79));
			case 14:
				return (new Color(0, 206, 209));
			case 15:
				return (new Color(105, 105, 105));
			case 16:
				return (new Color(178, 34, 34));
			case 17:
				return (new Color(34, 139, 34));
			case 18:
				return (new Color(255, 215, 0));
			case 19:
				return (new Color(218, 165, 32));
			case 20:
				return (new Color(190, 190, 190));
			case 21:
				return (new Color(0, 255, 0));
			case 22:
				return (new Color(173, 255, 47));
			case 23:
				return (new Color(205, 92, 92));
			case 24:
				return (new Color(240, 230, 140));
			case 25:
				return (new Color(173, 216, 230));
			case 26:
				return (new Color(211, 211, 211));
			case 27:
				return (new Color(176, 196, 222));
			case 28:
				return (new Color(50, 205, 50));
			case 29:
				return (new Color(255, 0, 255));
			case 30:
				return (new Color(176, 48, 96));
			case 31:
				return (new Color(102, 205, 170));
			case 32:
				return (new Color(0, 0, 205));
			case 33:
				return (new Color(186, 85, 211));
			case 34:
				return (new Color(60, 179, 113));
			case 35:
				return (new Color(123, 104, 238));
			case 36:
				return (new Color(0, 250, 154));
			case 37:
				return (new Color(72, 209, 204));
			case 38:
				return (new Color(199, 21, 133));
			case 39:
				return (new Color(25, 25, 112));
			case 40:
				return (new Color(0, 0, 128));
			case 41:
				return (new Color(0, 0, 128));
			case 42:
				return (new Color(107, 142, 35));
			case 43:
				return (new Color(255, 165, 0));
			case 44:
				return (new Color(255, 69, 0));
			case 45:
				return (new Color(218, 112, 214));
			case 46:
				return (new Color(152, 251, 152));
			case 47:
				return (new Color(255, 192, 203));
			case 48:
				return (new Color(221, 160, 221));
			case 49:
				return (new Color(255, 0, 0));
			case 50:
				return (new Color(250, 128, 114));
			case 51:
				return (new Color(46, 139, 87));
			case 52:
				return (new Color(160, 82, 45));
			case 53:
				return (new Color(135, 206, 235));
			case 54:
				return (new Color(106, 90, 205));
			case 55:
				return (new Color(0, 255, 127));
			case 56:
				return (new Color(70, 130, 180));
			case 57:
				return (new Color(210, 180, 140));
			case 58:
				return (new Color(216, 191, 216));
			case 59:
				return (new Color(64, 224, 208));
			case 60:
				return (new Color(238, 130, 238));
			case 61:
				return (new Color(208, 32, 144));
			case 62:
				return (new Color(245, 222, 179));
			case 63:
				return (new Color(255, 255, 255));
			case 64:
				return (new Color(255, 255, 0));
			case 65:
				return (new Color(154, 205, 50));
			default:
				System.out.println("Color no conocido:" + c);
				return null;
		}
	}
}
