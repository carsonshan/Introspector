
package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import test.fontchooser.FontChooser;
import test.verifiers.DoubleVerifier;
import test.verifiers.FloatVerifier;
import test.verifiers.IntegerVerifier;
import test.verifiers.VerifierErrorManager;


/**
*   Clase que permite generar dialogos de forma rápida.
 *
 * Para utilizarla debemos crear una clase nueva derivada de esta.
 * En esa clase definimos el método 'init()' (que se llamará antes
 * de mostrarse el diálogo) y el método 'callOK()' que se llamará
 * cuando se pulse 'OK' en el diálogo. Tambien se puede definir el
 * método 'callCancel()'. 
 * Esta clase implementa unos métodos de utilidad para ir 
 * añadiendo componentes, que incluyen simples rutinas de comprobación.
 * Los metodos con N son los que permiten dejar el campo vacio.
*/
public abstract class AbstractEditor {
	protected int numComponentes = 0;
//	private static SimbIcon simbs = null;
	
	/**
 Dialogo que se mostrará
	*/
	protected JDialog frameDialog;
	
	/**
 Panel donde se van añadiendo los controles
	*/
	protected JPanel panelDialog;
	
	/**
	 *  Sólo puede haber un label por ventana.
	@roseuid 3CA83D6403B9
	*/
	protected JLabel addLabel(String str) {
		JLabel j=new JLabel(str,SwingConstants.CENTER);
		frameDialog.getContentPane().add(j, BorderLayout.NORTH);
		return j;
	}
	
	/**
	 * Añade campo de texto con un mensaje.
	 * Comprueba que el tipo que devuelve es el mismo que
	 * recibe. 
	 * 
	 *  @param str Etiqueta del campo
	 *  @param val Valor inicial
	 *  @return   Retorna el componente
	@roseuid 3CA83D6403BB
	*/
	protected JTextField addLabelTxt(String str, String val) {
		JTextField jtf=new JTextField(7);
		jtf.setText(val);
		addComp(str,jtf);
		return jtf;
	}
	
	/**
	@roseuid 3CA83D6403C9
	*/
	protected JTextField addLabelTxt(String str, int val) {
		JTextField jtf=new JTextField(7);
		jtf.setText(Integer.toString(val));
		jtf.setInputVerifier(new IntegerVerifier(false));
		addComp(str,jtf);
		return jtf;
	}
	
	/**
	@roseuid 3CA83D6403CC
	*/
	protected JTextField addLabelTxt(String str, float val) {
		JTextField jtf=new JTextField(7);
		jtf.setText(Float.toString(val));
		jtf.setInputVerifier(new FloatVerifier(false));
		addComp(str,jtf);
		return jtf;
	}
	
	/**
	@roseuid 3CA83D6403DA
	*/
	protected JTextField addLabelTxt(String str, double val) {
		JTextField jtf=new JTextField(7);
		jtf.setText(Double.toString(val));
		DoubleVerifier dv = new  DoubleVerifier(false);
        jtf.setInputVerifier(dv);
        dv.setErrorManager(new VerifierErrorManager() {
            public void error() {
                    JOptionPane.showMessageDialog(
                    null,
                    ("El_valor_de_este_campo_debe_ser_un_numero_de_precision_doble"),
                    ("Error"),
                    JOptionPane.ERROR_MESSAGE);
            }
        });
		addComp(str,jtf);
		return jtf;
	}
	
	/**
	 * Añade campo de texto con un mensaje.
	 * Comprueba que el tipo que devuelve es el mismo que
	 * recibe. Permite dejar el campo vacio.
	 * 
	 *  @param str Etiqueta del campo
	 *  @param val Valor inicial
	 *  @return   Retorna el componente
	@roseuid 3CA83D650001
	*/
	protected JTextField addLabelTxtN(String str, int val) {
		JTextField jtf=new JTextField(7);
		jtf.setText(Integer.toString(val));
		jtf.setInputVerifier(new IntegerVerifier(true));
		addComp(str,jtf);
		return jtf;
	}
	
	/**
	@roseuid 3CA83D650004
	*/
	protected JTextField addLabelTxtN(String str, float val) {
		JTextField jtf=new JTextField(7);
		jtf.setText(Float.toString(val));
		jtf.setInputVerifier(new FloatVerifier(true));
		addComp(str,jtf);		
		return jtf;
	}
	
	/**
	@roseuid 3CA83D650011
	*/
	protected JTextField addLabelTxtN(String str, double val) {
		JTextField jtf=new JTextField(7);
		jtf.setText(Double.toString(val));
        DoubleVerifier dv =  new DoubleVerifier(true);
        jtf.setInputVerifier(dv);
		addComp(str,jtf);		
		return jtf;
	}
	
	/**
	@roseuid 3CA83D650020
	*/
	protected JSlider addSlider(String str, float min, float max, float val) {
		int imin=(int)min;
		int imax=(int)max;
		int ival=(int)val;
		JSlider jtf=new JSlider(imin,imax,ival);
		jtf.setPreferredSize(new Dimension (150,20));
		addComp(str,jtf);
		return jtf;
	}
	
	/**
	 * Añade un Component al dialogo.
	 * @param str Etiqueta del campo
	 * @return    El componente anadido
	@roseuid 3CA83D65002F
	*/
	protected Component addComp(String str, Component comp) {
		add(new JLabel(str), 0, numComponentes,GridBagConstraints.NONE, 0);
		add(           comp, 2, numComponentes,GridBagConstraints.HORIZONTAL, 1);
		numComponentes++;
		return comp;
	}
	
	/**
	@roseuid 3CA83D65003E
	*/
	public void add(Component cmp, int gridx, int gridy, int gridwidth, int gridheight, int anchor, int fill, double weightx, double weighty) {
		Insets insets = new Insets(0,0,0,0);
	    if(gridx!=0) insets.left = 10;
	    if(gridy!=0) insets.top = 10;
		panelDialog.add(cmp,new GridBagConstraints(
			gridx,gridy,gridwidth,gridheight,weightx,weighty,
			anchor,fill,insets,0,0));
	}
	
	/**
	@roseuid 3CA83D65005D
	*/
	public void add(Component cmp, int gridx, int gridy, int fill, double weighty) {
    			Insets insets = new Insets(0,0,0,0);
			    if(gridx!=0) insets.left = 10;
			    if(gridy!=0) insets.top = 10;
			  //  add(panelDialog, cmp, gridx, gridy, gridwidth, gridheight, anchor, fill,
			//	  weightx, weighty, insets);
			GridBagConstraints constraints = new GridBagConstraints();
		    constraints.gridx = gridx;
		    constraints.gridy = gridy;
		    constraints.gridwidth = 1;//gridwidth;
		    constraints.gridheight =1; // gridheight;
		    constraints.anchor = GridBagConstraints.WEST;
		    constraints.fill = fill;
		    constraints.weightx = 1; // weightx;
		    constraints.weighty = weighty;
		    constraints.insets = insets;
		    panelDialog.add(cmp, constraints);
	}
	
	/**
	 * Añade un ComboBox con las opciones indicadas.
	 * Falta indicar el valor ya seleccionado.
	 *
	 *  @param str   Etiqueta del campo
	 *  @param val   Valores posibles del combo
	 *  @return      El componente creado
	@roseuid 3CA83D65006E
	*/
	protected JComboBox addCombo(String str, String[] val, int defecto) {
		JComboBox combo = new JComboBox();
		for(int j=0;j<val.length;j++) {
			combo.addItem(val[j]);
		}
		combo.setSelectedIndex(defecto);
		addComp(str,combo);
		return combo;
	}
	
	/**
	 * Añade un JCheckBox al dialogo.
	 * @param str Etiqueta del campo
	 * @param v   Valor inicial
	 * @return    El componente creado
	@roseuid 3CA83D65007E
	*/
	protected JCheckBox addBoolean(String str, boolean v) {
		JCheckBox check = new JCheckBox();
		check.setSelected(v);
		addComp(str,check);
		return check;
	}
	
	/**
	 * Añade un boton que activa la seleccion de color y con
	 * el color indicado.
	 * @param str  Etiqueta del campo
	 * @param color valor inicial
	 * @return  el componente creado
	@roseuid 3CA83D650081
	*/
	protected JColorChooser addColorButton(String str, final Color color) {
		final JButton but = new JButton ();
		final JColorChooser col = new JColorChooser(color);
		but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color nuevo = JColorChooser.showDialog(but,("Seleccione_color"),color);
				if (nuevo != null) 
				{
					but.setBackground (nuevo);	
					col.setColor(nuevo);
				}
			}	
			});		
		but.setBackground(color);
		but.setPreferredSize(new Dimension(20,20));
		addComp(str,but);
		return col;
	}
	
	/**
	 * Añade un boton que activa la seleccion font y con
	 * la font indicado.
	 * @param str  Etiqueta del campo
     * @param fuente Fuente inicial seleccionada
	 * @return  el componente creado
	@roseuid 3CA83D65008E
	*/
	protected FontChooser addFontButton(String str, Font fuente) {
		final JButton but = new JButton ();
		final FontChooser fo = new FontChooser(fuente);
		if (fuente!=null)
		{
			Font font2=fuente.deriveFont((float) 10);
			but.setFont(font2);
		}
		fo.setLocationRelativeTo(frameDialog);
		but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fo.setVisible(true);
				Font f=fo.getSelected();
				if (f!= null) 
				{
					but.setFont(f);
					but.setText (f.getFontName());	
				}
			}
			});		
		but.setText(fo.getSelected().getFontName());
		addComp(str,but);		
		return fo;
	}
	
	/**
	 * Añade un boton que activa la seleccion de simbolo. El boton contiene
	 * el simbolo inicial.
	 * @param str  Etiqueta del campo
	 * @param topo Tipo de simbolo (nodo,linea,superficie)
	 * @param simbolo valor inicial
	 * @return  el componente creado
	@roseuid 3CA83D65009D
	*/
//	protected SimbolChooser addSimbolButton(String str, int topo, int simbolo) {
//		JPanel jp=new JPanel();
//		jp.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
//		final JLabel jl=new JLabel(Integer.toString(simbolo));
//		final JButton but = new JButton ();
//		jp.add(jl);
//		jp.add(but);
//		final SimbolChooser sc = new SimbolChooser(topo,simbolo);
//		if (simbs==null) simbs= new SimbIcon(19,19,0.5f,0.5f,0.5f);
//		final int tipo = topo;
//		sc.setLocationRelativeTo(frameDialog);
//		but.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				sc.setVisible(true);
//				int s= sc.getSelected();
//				jl.setText(Integer.toString(s));
//				ImageIcon img= simbs.dame_icon(tipo,s);
//				if(img!=null) but.setIcon (img);	
//				}
//			});	
//		ImageIcon img= simbs.dame_icon(tipo,simbolo);
//		but.setPreferredSize(new Dimension(20,20));
//		if (img!=null) 	 {		
//			but.setIcon(img);
//			but.setPreferredSize(new Dimension(img.getIconWidth(),img.getIconHeight()));	
//		}
//		addComp(str,jp);
//		return sc;
//	}
	
	/**
	@roseuid 3CA83D6500AC
	*/
	private void addOkCancel() {
		JPanel jp=new JPanel();
		jp.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
		//jp.setBorder(BorderFactory.createLineBorder(Color.black));		
		jp.setBorder(BorderFactory.createEtchedBorder());		
		JButton but2 = new JButton (("OK"));
		but2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
						if (callOK())
							frameDialog.dispose();
						}
			});
		jp.add(but2);
		but2 = new JButton (("Cancelar"));
		but2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				callCancel();
				frameDialog.dispose();
				}
			}); 
		jp.add(but2);
		frameDialog.getContentPane().add(jp, BorderLayout.SOUTH);
		//panelDialog.add(jp);
	}
	
	/**
	 * Abre (y crea) el editor.
	 * Internamente genera las ventanas, llama al método init() y muestra
	 * el diálogo.
	 *
	 * Falta controlar el evento de Ocultar para llamar a callCancel();
	 *
	 * @param txt  Titulo del diálogo
	 * @param c    Componente que lo llama, sirve para centrar el diálogo
	@roseuid 3CA83D6500AD
	*/
	public void open(String txt, Component c, boolean modal) {
		Frame fr=JOptionPane.getFrameForComponent(c);
		frameDialog = new JDialog(fr,modal);
		frameDialog.setTitle (txt);
		frameDialog.setContentPane(getPanel());
		addOkCancel();
		frameDialog.pack();
		frameDialog.setLocationRelativeTo(fr);
		frameDialog.setVisible(true);
	}
	
	/**
	@roseuid 3CA83D6500BC
	*/
	public void open(String txt, Component c) {
		open (txt,c,true);
	}
	
	/**
	@roseuid 3CA83D6500CB
	*/
	public JPanel getPanel() {
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout(5,2));
		panelDialog = new JPanel();
		panelDialog.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		panelDialog.setLayout(new GridBagLayout());
		jp.add(panelDialog,BorderLayout.CENTER);
		init();
		return jp;
	}
	
	/**
	 * Metodo que se debe definir en las clases que hereda.
	 * Sirve para comprobar y guardar los valores.
	 *  @return   true hace que la ventana desaparezca
	@roseuid 3CA83D6500CC
	*/
	public abstract boolean callOK();
	
	/**
	 * Método que se debe redefinir y sirve para crear y rellenar
	 * el diálogo.
	@roseuid 3CA83D6500CD
	*/
	public abstract void init();
	
	/**
	 * Método que se llama cuando se selecciona al botón Cancel.
	 * Por defecto no hace nada.
	@roseuid 3CA83D6500CE
	*/
	public void callCancel() {
	}
	
}
