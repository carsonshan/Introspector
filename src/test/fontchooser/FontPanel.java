
package test.fontchooser;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

class FontPanel extends JPanel implements ItemListener {
	int index = 0;
	int stChoice = 0;
	int siChoice = 20;
	JLabel fontLabel;
	JLabel styleLabel;
	FontDraw fontC;
	JComboBox fonts;
	JComboBox fontsist;
	JComboBox styles;
	String fontchoice = "fontchoice";
	
	/**
	@roseuid 3CA83DBF032C
	*/
	public FontPanel(ActionListener listener, Font font_inicial) {	
		init(font_inicial);
	}
	
	/**
	@roseuid 3CA83DBF033C
	*/
	public void init(Font font_inicial) {

		BorderLayout b=new BorderLayout();
        this.setLayout( b );

        JPanel topPanel = new JPanel();
        JPanel fontPanel = new JPanel();
        JPanel fontsistPanel = new JPanel();
        JPanel stylePanel = new JPanel();

        topPanel.setLayout( new BorderLayout() );
        fontPanel.setLayout( new GridLayout( 2, 1 ) );

        fontsistPanel.setLayout( new GridLayout( 2, 1 ) );
        stylePanel.setLayout( new GridLayout( 2, 1 ) );

        topPanel.add( BorderLayout.WEST, fontPanel );
		topPanel.add(BorderLayout.CENTER,stylePanel);

        add( BorderLayout.NORTH, topPanel );

        fontLabel = new JLabel();
        fontLabel.setText("Fuentes");
        Font newFont = getFont().deriveFont(1);
        fontLabel.setFont(newFont);
        fontLabel.setHorizontalAlignment(JLabel.CENTER);
        fontPanel.add(fontLabel);


        styleLabel = new JLabel();

        styleLabel.setText("Estilos");
        styleLabel.setFont(newFont);
        styleLabel.setHorizontalAlignment(JLabel.CENTER);
        stylePanel.add(styleLabel);


        fontC = new FontDraw(font_inicial);
        fontC.setBackground(Color.white);
        fontC.setPreferredSize(new Dimension(200,100));
		
        add( BorderLayout.CENTER, fontC);

        
		int selected=0;
		String strf=null;
		int estilo=0;
		if (font_inicial!=null)	{
			strf=font_inicial.getFamily();
			switch (font_inicial.getStyle()) {
				// case Font.PLAIN: estilo=0;
				case Font.BOLD:
					estilo=1;
					break;
				case Font.ITALIC:
					estilo=2;
					break;
				case Font.ITALIC+Font.BOLD:
					estilo=3;
					break;
			}	
		}

        Vector vector1 = new Vector();
		for (int i = 0; i < FontList.COMPATFONTS.length; i++ ) {
            vector1.addElement(FontList.COMPATFONTS[i]);
			if(FontList.COMPATFONTS[i].equals(strf) ) selected=i;
        }

        fonts = new JComboBox( vector1 );
		
        fonts.setMaximumRowCount( 9 );
		fonts.setSelectedIndex(selected);
        fonts.addItemListener(this);
        fontchoice = FontList.COMPATFONTS[selected];
        fontPanel.add(fonts);

        styles = new JComboBox( new Object[]{
                                "NORMAL",
                                "NEGRITA",
                                "CURSIVA",
                                "CURSIVA & NEGRITA"} );

        styles.setMaximumRowCount( 9 );
        styles.addItemListener(this);
		styles.setSelectedIndex(estilo);
        stylePanel.add(styles);
	}
	
	/**
	@roseuid 3CA83DBF033E
	*/
	public void itemStateChanged(ItemEvent e) {
        if ( e.getStateChange() != ItemEvent.SELECTED ) {
            return;
        }

        Object list = e.getSource();

        if ( list == fonts ) {
            fontchoice = (String)fonts.getSelectedItem();
        } else if ( list == styles ) {
            index = styles.getSelectedIndex();

            stChoice = index;
        } 
        fontC.changeFont(fontchoice, stChoice, siChoice);
	}
	
	/**
	@roseuid 3CA83DBF034B
	*/
	public Font getSelectedFont() {
		return fontC.thisFont;
	}
}

class FontDraw extends JPanel {
	Font thisFont;
	
	/**
	@roseuid 3CA83DBF036B
	*/
	public FontDraw(Font font_inicial) { 
		if(font_inicial==null)
	        thisFont = new Font("Arial", Font.PLAIN, 10);
		else thisFont=font_inicial.deriveFont((float)20);
	}
	
	/**
	@roseuid 3CA83DBF036D
	*/
	public void changeFont(String f, int st, int size) {

        thisFont = new Font(f, st, size);
		String tipof=thisFont.getName();
		if (thisFont.isBold()) 
		 {
		 	if(thisFont.isItalic())
					tipof+="-BOLDITALIC";
			else tipof+="-BOLD";	
		 }else if(thisFont.isItalic()) tipof+="-ITALIC";
		 
		 tipof+="-"+thisFont.getSize();
		System.out.println("FONT:"+thisFont+":Para:"+tipof);
        repaint();
	}
	
	/**
	@roseuid 3CA83DBF037A
	*/
	public void paintComponent(Graphics g) {
        super.paintComponent( g );
        Graphics2D g2 = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();

        g2.setColor(Color.darkGray);
        g2.setFont(thisFont);
        String change = "Texto de Prueba 123@#-/";
        FontMetrics metrics = g2.getFontMetrics();
        int width = metrics.stringWidth( change );

        int height = metrics.getHeight();
        g2.drawString( change, w/2-width/2, h/2-height/2 );
	}
}
