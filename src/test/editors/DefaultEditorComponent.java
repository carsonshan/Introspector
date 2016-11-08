
/*
 * TextComponent.java
 *
 * Created on 3 de marzo de 2003, 13:19
 */

package test.editors;

import java.awt.event.*;
import java.beans.*;

import javax.swing.JTextField;
import javax.swing.text.Document;


/**
 * <p>TextEditorComponent</p>
 * <p>Componente para editores de atributos. Ser� el componente m�s utilizado.
 * <br/> Se trata de un JTextField que se asocia a un PropertyEditor y a un Document.
 * El documento ser� el que indique que tipo de car�cteres se pueden introducir.
 * Cuando el PropertyEditor cambie el valor de la propiedad, el text field se actualizar� con el 
 * getAsText del PropertyEditor.<br/>
 * Cuando el text field pierda el foco se tratar� de actualizar el valor de la propiedad (PropertyEditor)
 * con el m�todo setAsText(valor del textfield). Si se produce un error al actualizar, se mostrar� un mensaje que lo indique.</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author Francisco Chicharro Sanz
 */

public class DefaultEditorComponent extends JTextField {

    /**
     * PropertyEditor del atributo
     */
  private PropertyEditor editor;

  /**
   * Constructor
   * @param PropertyEditor pe Editor del atributo
   * @param Document documento que se aplicar� al text field
   */
  public DefaultEditorComponent(PropertyEditor pe, Document document) {
    editor = pe;
    if(document!=null)
	setDocument(document);
    

    editor.addPropertyChangeListener(new PropertyChangeListener() {
      public void propertyChange(PropertyChangeEvent ev) {	  
        setText(editor.getAsText());
      }
    });

    addFocusListener(new FocusAdapter() {
      public void focusLost(FocusEvent e) {
          updateEditor();
      }
    });
    addKeyListener(new KeyAdapter() {
      public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            transferFocus();
        }
      }
    });
  }

  /**
   *	Actualiza el editor y si se produce un error lo muestra por pantalla
   */
  protected void updateEditor() {   
	String aux=getText();
    try {	
      editor.setAsText(getText());
    } catch (IllegalArgumentException ex) {
	setText(aux);
    }
  }

}
    

