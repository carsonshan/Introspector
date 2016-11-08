package test;

import java.awt.Button;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.Customizer;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



class PropertyCanvas extends JPanel implements MouseListener, PropertyChangeListener {
	private static boolean ignoreClick = false;
	private Frame frame;
	private PropertyEditor editor;
	
	/**
	@roseuid 3CA83EBF0013
	*/
	PropertyCanvas(PropertyEditor pe) {
		this(null,pe);
	}
	
	/**
	@roseuid 3CA83EBF001F
	*/
	PropertyCanvas(Frame frame, PropertyEditor pe) {
		this.frame = frame;
		editor = pe;
		setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		addMouseListener(this);
		editor.addPropertyChangeListener(this);
	}
	
	/**
	@roseuid 3CA83EBF0022
	*/
	public void propertyChange(PropertyChangeEvent ev) {
		repaint();
	}
	
	/**
	@roseuid 3CA83EBF002E
	*/
	public void paintComponent(Graphics g) {
		//Rectangle box = new Rectangle(2, 2, getSize().width - 4, getSize().height - 4);
		Rectangle box=g.getClipBounds();
		editor.paintValue(g, box);
	}
	
	/**
	@roseuid 3CA83EBF0030
	*/
	public void mouseClicked(MouseEvent evt) {
		if (! ignoreClick) {
			try {
				ignoreClick = true;
				int x = frame.getLocation().x - 30;
				int y = frame.getLocation().y + 50;
				new PropertyDialog(frame, editor, x, y);
				//		new PropertyDialog(editor);
			} finally {
				ignoreClick = false;
			}
		}
	}
	
	/**
	@roseuid 3CA83EBF0032
	*/
	public void mousePressed(MouseEvent evt) {
	}
	
	/**
	@roseuid 3CA83EBF003E
	*/
	public void mouseReleased(MouseEvent evt) {
	}
	
	/**
	@roseuid 3CA83EBF0040
	*/
	public void mouseEntered(MouseEvent evt) {
	}
	
	/**
	@roseuid 3CA83EBF0042
	*/
	public void mouseExited(MouseEvent evt) {
	}
}

class PropertyDialog extends Dialog implements ActionListener {
	private static final int vPad = 5;
	private static final int hPad = 4;
	private Button doneButton;
	private Component body;
	
	/**
	@roseuid 3CA83EBF00BB
	*/
	PropertyDialog(Frame frame, PropertyEditor pe, int x, int y) {
		super(frame, pe.getClass().getName(), true);
		//	new WindowCloser(this);
		setLayout(null);

		body = pe.getCustomEditor();
		add(body);

		doneButton = new Button("Done");
		doneButton.addActionListener(this);
		add(doneButton);

		setLocation(x, y);
		show();
	}
	
	/**
	@roseuid 3CA83EBF00CD
	*/
	public void actionPerformed(ActionEvent evt) {
		// Button down.
		dispose();
	}
	
	/**
	@roseuid 3CA83EBF00CF
	*/
	public void doLayout() {
		Insets ins = getInsets();
		Dimension bodySize = body.getPreferredSize();
		Dimension buttonSize = doneButton.getPreferredSize();

		int width = ins.left + 2*hPad + ins.right + bodySize.width;
		int height = ins.top + 3*vPad + ins.bottom + bodySize.height +
			buttonSize.height;

		body.setBounds(ins.left+hPad, ins.top+vPad,
			bodySize.width, bodySize.height);

		doneButton.setBounds((width-buttonSize.width)/2,
			ins.top+(2*hPad) + bodySize.height,
			buttonSize.width, buttonSize.height);

		setSize(width, height);
	}
}

class PropertyText extends JTextField implements KeyListener, FocusListener {
	private PropertyEditor editor;
	
	/**
	@roseuid 3CA83EBE0321
	*/
	PropertyText(PropertyEditor pe) {
		super(pe.getAsText());
		editor = pe;
		addKeyListener(this);
		addFocusListener(this);
	}
	
	/**
	@roseuid 3CA83EBE032D
	*/
	protected void updateEditor() {
		try {
			editor.setAsText(getText());
		} catch (IllegalArgumentException ex) {
			// Quietly ignore.
		}
	}
	
	/**
	@roseuid 3CA83EBE032E
	*/
	public void focusGained(FocusEvent e) {
	}
	
	/**
	@roseuid 3CA83EBE0330
	*/
	public void focusLost(FocusEvent e) {
		updateEditor();
	}
	
	/**
	@roseuid 3CA83EBE033D
	*/
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			updateEditor();
		}
	}
	
	/**
	@roseuid 3CA83EBE033F
	*/
	public void keyPressed(KeyEvent e) {
	}
	
	/**
	@roseuid 3CA83EBE0341
	*/
	public void keyTyped(KeyEvent e) {
	}
}

class EditedAdaptor implements PropertyChangeListener {
	int pos;
	BeanEditor sink;
	PropertyEditor ped;
	
	/**
	@roseuid 3CA83EBE02BF
	*/
	EditedAdaptor(BeanEditor t, PropertyEditor ed, int p) {
		sink = t;
		pos=p;
		ped=ed;
	}
	
	/**
	@roseuid 3CA83EBE02D0
	*/
	public void propertyChange(PropertyChangeEvent evt) {
		sink.wasModified(ped,pos);
	}
}

public class BeanEditor extends JPanel implements Customizer {
	private int nfilas;
	private Object object;
	private Class bean;
	private Method writter[];
	
//	static {
//		PropertyEditorManager.registerEditor(Color.class,MiColorEditor.class);
//		//PropertyEditorManager.registerEditor(int.class,IntegerPE.class);
//		PropertyEditorManager.registerEditor(float.class,FloatEditor.class);
//		PropertyEditorManager.registerEditor(String.class,CharEditor.class);
//	}
	
	/**
	@roseuid 3CA83EBE0203
	*/
	public BeanEditor() {
	}
	
	/**
	@roseuid 3CA83EBE0204
	*/
	public BeanEditor(Object obj) {
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		setObject(obj);
	}
	
	/**
	 *  @param modal  Si el dialogo sera modal o no.
	 *  @return   Un dialogo que desaparece al cerrar la ventana.
	 *          (no tiene botones de Ok o Cancel).
	@roseuid 3CA83EBE0213
	*/
	public JDialog getDialog(boolean modal) {
		JDialog dialog=new JDialog();
		dialog.setTitle("Editor "+object.getClass().getName());
		dialog.setModal(modal);	
		dialog.setContentPane(this);
		dialog.pack();
		return dialog ;
	}
	
	/**
	 *  Re-inicializa el Panel con el nuevo objeto.
	 *
	 * @param obj Objeto a editar sus prioridades.
	@roseuid 3CA83EBE0215
	*/
	public void setObject(Object obj) {
		this.bean=obj.getClass();
		this.object=obj;
		this.nfilas=0;

		if(lanzaEditorCustom()) return; 

		setLayout(new GridBagLayout());
		BeanInfo info;
		try {
			info=Introspector.getBeanInfo(bean);
		} catch (IntrospectionException ie) {
			ie.printStackTrace();
			return;
		}
		BeanDescriptor beanDescriptor=info.getBeanDescriptor();
		if(beanDescriptor!=null) {
			Class editorc=beanDescriptor.getCustomizerClass();
			if (editorc != null) {
				try {
					System.out.println("El customizador es:"+editorc);
					Customizer customizer = (Customizer)editorc.newInstance();
					customizer.setObject(object);
					setLayout(new FlowLayout());
					add((Component)customizer);
					System.out.println("Tengo customizador en el bean");
					return;
				} catch (Exception ex) {
					// Drop through.
					System.out.println("ex---");
					ex.printStackTrace();
				}
			}
		}

		PropertyDescriptor [] props=info.getPropertyDescriptors();		
		Method getter;
		writter=new Method[props.length];


		for (int i=0;i<props.length;i++) {
			//System.out.println("Miramos:"+i+" "+props[i].getDisplayName());

			getter=props[i].getReadMethod();
			if(getter==null) {
				//System.out.println(props[i].getDisplayName()+" No vale, no tiene getter");
				continue;
			}
			writter[i]=props[i].getWriteMethod();
			if(writter[i]==null) {
				//System.out.println(props[i].getDisplayName()+" No vale, no tiene writter");
				continue;
			}

			PropertyEditor editor = null;
			Class pec = props[i].getPropertyEditorClass();	
			Object value=null;
			if (pec != null) {
				try {
					editor = (PropertyEditor)pec.newInstance();
				} catch (Exception ex) {
					// Drop through.
					System.out.println("ex---");
					ex.printStackTrace();
				}
			}
			if (editor == null) {
				Class c=props[i].getPropertyType();
				if (c!=null) 
					editor = PropertyEditorManager.findEditor(c);
			}

			try {
				Object args[] = { };
				if(getter!=null)
					value = getter.invoke(object, args);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			Component view;
			if(editor!=null) {
				editor.setValue(value);
				editor.addPropertyChangeListener(new EditedAdaptor(this,editor,i));
				view=miPropertyComponent(editor);
			} else { // editor es nulo
				JTextField tf=new JTextField(value==null?"nulo":value.toString());
				System.out.println(props[i].getDisplayName()+" No vale, no tiene editor");
				tf.setEditable(false);
				view =tf;
			}

			addComp(nfilas++,props[i].getDisplayName(),view);

		} //fin for-properties
	}
	
	/**
	 * Obtenemos un string con los valores de las propiedades del
	 * objeto.
	 * Estas propiedades unicamente pueden ser de tipo:
	 * int,Integer,String,float,Float
	 * Los valores aparecen separados por comas (,)
	 *
	 * Para ver lo que se entiende por propiedades ver la descripcion
	 * de esta clase.
	 *
	 * @param obj  Objeto a analizar
	 * @return  La cadena con los valores de las propiedades
	@roseuid 3CA83EBE0217
	*/
	public static String stringParams(Object obj) {
		StringBuffer res=new StringBuffer();
		Class clase=obj.getClass();
		BeanInfo info;
		try {
			info=Introspector.getBeanInfo(clase);
		} catch (IntrospectionException ie) {
			ie.printStackTrace();
			return null;
		}
		PropertyDescriptor [] props=info.getPropertyDescriptors();		
		Method getter;

		for (int i=0;i<props.length;i++) {
			getter=props[i].getReadMethod();
			if(getter==null) {  // no tiene getter, pues otro
				continue;
			}
			Method writter=props[i].getWriteMethod();
			if(writter==null) { // no tiene writter, pues otro
				continue;
			}

			//Class c=props[i].getPropertyType();

			Object value=null;
			try {
				Object args[] = { };
				value = getter.invoke(obj, args);
				} catch (Exception ex) {
				ex.printStackTrace();
				}
			
if(value!=null)
	System.out.println("Prop "+props[i].getDisplayName()+"="+value.toString());
			else 
				System.out.println("Prop "+props[i].getDisplayName()+"=null");

			// las propiedades nunca deberian ser null		
			if(value!=null)
				res.append(value.toString());

			res.append(",");

		} //fin for-properties

		// quito la coma del final
		if(res.length()>0) 
			res.deleteCharAt(res.length()-1);
		return res.toString();
	}
	
	/**
	 * Pone los valores del string en las propiedades del objeto.
	 *
	 * Explora el string, separandolo por comas, y va seteando las
	 * propiedades del objeto.
	 * 
	 * El string, debería haber sido obtenido de una llamada a 
	 * getParams() con un objeto de la misma clase.
	 *
	 * Solo acepta propiedades de los tipos:
	 * int,Integer,String,float o Float
	 *
	 * @param obj  Objeto a setear
	 * @param params Lista de valores a setear, separados por comas.
	@roseuid 3CA83EBE0223
	*/
	public static void setParams(Object obj, String params) {

		if(params==null) return;

			StringTokenizer st=new StringTokenizer(params,",");

			Class clase=obj.getClass();
		BeanInfo info;
		try {
			info=Introspector.getBeanInfo(clase);
		} catch (IntrospectionException ie) {
			ie.printStackTrace();
			return;
		}
		PropertyDescriptor [] props=info.getPropertyDescriptors();		
		Method getter;

		for (int i=0;i<props.length;i++) {

			getter=props[i].getReadMethod();
			if(getter==null) {
				//System.out.println(props[i].getDisplayName()+" No vale, no tiene getter");
				continue;
			}
			Method writter=props[i].getWriteMethod();
			if(writter==null) {
				//System.out.println(props[i].getDisplayName()+" No vale, no tiene writter");
				continue;
			}

			if(!st.hasMoreTokens()) {
				System.out.println(props[i].getDisplayName()+" Sin parametros");
				return;
			}
			String param=st.nextToken();
			Object value=null;
			// deberiamos buscar el editor y
			// hacer editor.setAsText(param);
			// si queremos usar cualquier tipo de dato.

			try {
				Class c=props[i].getPropertyType();
				//System.out.println("Llamo a:"+c.getName()); 
				Object args[]= new Object[1];

				if(c==int.class) value=Integer.valueOf(param);
				if(c==Integer.class) value=Integer.valueOf(param);
				if(c==String.class) value=param;
				if(c==float.class) value=Float.valueOf(param);
				if(c==Float.class) value=Float.valueOf(param);

				//System.out.println("Llamo a:"+writter.toString()+"("+value.toString()+")"+c.getName()); 

				args[0]= value ;
				writter.invoke(obj, args);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			//System.out.println("Prop "+props[i].getDisplayName()+"="+value.toString());

		} //fin for-properties
	}
	
	/**
	 * Crea un objeto de la clase indicada con los valores 
	 * indicados en params.
	 *
	 * Solo funciona con clases que tienen un constructor
	 * sin parametros. Ademas los parametros deben corresponderse
	 * con las propiedades del objeto.
	 *
	 * Para ver que se entiende por propiedades ver la documentacion
	 * de esta clase.
	 *
	 * @param c  Clase de la que se creara el objeto.
	 * @param params Lista de valores de las propiedades separados por comas.
	 * @return El nuevo objeto
	@roseuid 3CA83EBE0232
	*/
	public static Object newInstance(Class c, String params) {
		try {
			Object obj=c.newInstance();
			if (params!=null && params.length()>0)
				setParams(obj,params);
			return obj ;
		} catch (InstantiationException ie) {
			ie.printStackTrace();
			return null ;
		} catch (IllegalAccessException ie) {
			ie.printStackTrace();
			return null ;
		}
	}
	
	/**
	@roseuid 3CA83EBE0235
	*/
	protected Component addComp(int fila, String str, Component comp) {
		add(new JLabel(str), 0, fila,GridBagConstraints.NONE, 0);
		add(           comp, 2, fila,GridBagConstraints.HORIZONTAL, 1);
		return comp;
	}
	
	/**
	@roseuid 3CA83EBE0244
	*/
	public void add(Component cmp, int gridx, int gridy, int fill, double weighty) {
		Insets insets = new Insets(0,0,0,0);
		if(gridx!=0) insets.left = 10;
		if(gridy!=0) insets.top = 10;
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
		add(cmp, constraints);
	}
	
	/**
	@roseuid 3CA83EBE0255
	*/
	public void wasModified(PropertyEditor pe, int i) {
		System.out.println(" Atributo "+i+" Ha sido modificado");	
		Object nuevoValor=pe.getValue();
		if(writter[i]!=null && nuevoValor!=null) {
			try {
				Object [] args=new Object[1];
				args[0]=nuevoValor;
				writter[i].invoke(object,args);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		repaint();
	}
	
	/**
	 * Devuelve el componente que se añadira al panel para editar la
	 * propiedad indicada.
	 * Segun los casos del editor devolvera un JTextField, un ComboBox,
	 * o el componente del CustomEditor.
	 *  
	 * Hay dos modos de funcionamiento, el de los BeanBox y el de la GLF.
	 * En el modo de la BeanBox se crea un componente (panel) que se 
	 * dibuja con la funcion paint() del editor. Cuando se pulsa el raton
	 * crea un dialogo con un layout donde esta el customComponent del 
	 * editor.  Este metodo tambien se utiliza en el JBuilder y otros
	 * compiladores y herramientas.
	 * En el otro metodo se utiliza siempre el customComponent. Es
	 * un poco mas bonito y es el que esta aqui implementado.
	 *
	 *
	@roseuid 3CA83EBE0263
	*/
	public static Component propertyComponent(PropertyEditor editor) {
		Component view=null;
		if (editor.isPaintable() && editor.supportsCustomEditor()) {
//			if(modo==0) view = new PropertyCanvas(editor);
			view= editor.getCustomEditor();
		} else if (editor.getTags() != null) {
			view = new PropertySelector(editor);
		} else if (editor.getAsText() != null) {
			//String init = editor.getAsText();
			view = new PropertyText(editor);
		} else {
			System.err.println("No tengo componente para "+editor);
			//		    System.err.println("Warning: Property \"" + props[i].getName() 
			//				+ "\" has non-displayabale editor.  Skipping.");
			//continue;
		}

		return view ;
	}
	
	/**
	@roseuid 3CA83EBE0271
	*/
	private Component miPropertyComponent(PropertyEditor editor) {
		Component view=null;
		if (editor.isPaintable() && editor.supportsCustomEditor()) {
			view = new PropertyCanvas(editor);
		} else if (editor.getTags() != null) {
			view = new PropertySelector(editor);
		} else if (editor.getAsText() != null) {
			//String init = editor.getAsText();
			view = new PropertyText(editor);
		} else {
			view = new PropertyText(editor);
					    System.err.println("Warning: Property \"" + editor 
							+ "\" has non-displayabale editor.  Skipping.");
			
		}

		return view ;
	}
	
	/**
	*
	@roseuid 3CA83EBE0273
	*/
	private boolean lanzaEditorCustom() {

		Class clased=null;

			String nombre=bean.getName()+"Editor";
		try {
			clased=Class.forName(nombre);
		}catch (ClassNotFoundException e) {
			// no pasa nada, si no existe la clase volvemos
			//System.err.println("ERROR Cargando Clase:("+nombre+") :: NO ENCUENTRO LA CLASE");
			//e.printStackTrace();
			return false ;
		}
		Class argsParams[]= new Class[1];
		argsParams[0]=bean;
		Constructor ctt;
		try {
			 ctt=clased.getConstructor(argsParams);
			}catch (NoSuchMethodException e) {
			System.err.println("ERROR Cargando clase:("+nombre+")::Falta Un Constructor (obj) )");
			//e.printStackTrace();
			return false ;
			}
		AbstractEditor editor;
		Object params[]= new Object[1];
		params[0]= object;
		try {
			editor=(AbstractEditor) ctt.newInstance(params);
			} catch (InstantiationException e) {
			System.err.println("ERROR Cargando clase:"+nombre+" ERROR DE INSTANCIACION");
			//e.printStackTrace();
			return false;
			}catch (IllegalAccessException e) {
			System.err.println("ERROR Cargando clase:"+nombre+" ACCESO ILEGAL");
			//e.printStackTrace();
			return false;
			}catch (InvocationTargetException e) {
			System.err.println("ERROR Cargando clase:"+nombre+" Excepcion LANZADA por EL CONSTRUCTOR");
			//e.printStackTrace();
			return false;
			}
		editor.open("hola",this);
		return true ;
	}
}

class PropertySelector extends JComboBox implements ItemListener {
	PropertyEditor editor;
	
	/**
	@roseuid 3CA83EBE037C
	*/
	PropertySelector(PropertyEditor pe) {
		editor = pe;
		String tags[] = editor.getTags();
		for (int i = 0; i < tags.length; i++) {
			addItem(tags[i]);
		}
		//select(0);

		// This is a noop if the getAsText is not a tag.
		//	setSelectedItem(editor.getAsText());
		addItemListener(this);
	}
	
	/**
	@roseuid 3CA83EBE037E
	*/
	public void itemStateChanged(ItemEvent evt) {
		String s =(String) getSelectedItem();
		editor.setAsText(s);
	}
}
