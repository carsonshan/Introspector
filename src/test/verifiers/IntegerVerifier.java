package test.verifiers;

import javax.swing.*;




/***************************************************************************
 *
 *  Verificador de valores INTEGER introducidos en un JTextField.
 *  <p>
 *  Ejemplo de uso:
 *  <p>
 *  <code>
 *  JTextField jtf = new JTextField("Red:");  <br>
 *  jtf.setInputVerifier(new IntegerVerifier(0, 255, false);
 *  </code>
 *
 *  @see  VerifierErrorManager
 *  @author  UF768189
 ****************************************************************************/

public class IntegerVerifier extends AbstractVerifier
{
    /** Indica si el campo puede estar vacio. */
    protected boolean anulable;

    /** Valor minimo del campo. */
    protected int minValue;

    /** Valor maximo del campo. */
    protected int maxValue;



    /**
     * Construye el verificador para todo el rango de enteros.
     *
     * @param anulable  Indica si el campo puede estar vacio.
     */
    public IntegerVerifier(boolean anulable)
    {
        this(-Integer.MAX_VALUE, Integer.MAX_VALUE, anulable);
    }


    /**
     *  Construye el verificador para un rango dado de enteros.
     *  @param minValue  Valor minimo admitido (incluido).
     *  @param maxValue  Valor maximo admitido (incluido).
     *  @param anulable  Indica si el campo puede estar vacio.
     */
    public IntegerVerifier(int minValue, int maxValue, boolean anulable)
    {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.anulable = anulable;

        // error manager por defecto
        errorManager = new DefaultIntegerVerifierErrorManager();
    }


    /**
     *  Verifica el valor del campo.
     *  NO LLAMAR DIRECTAMENTE.
     *  @param c  JTextField a verificar.
     *  @return  Si el valor del campo es correcto de acuerdo a este verificador.
     */
    public boolean verify(JComponent c)
    {
        // obtener valor del campo
        JTextField jtf = (JTextField) c;
        String valor = jtf.getText();

        // comprobar si el valor es correcto
        if ( anulable && (valor.length() == 0) )
            return true;

        try {
            int n = Integer.parseInt(valor);
            return (n >= minValue) && (n <= maxValue);
        }
        catch (NumberFormatException e) {
            return false;
        }
    }


    /**
     *  Gestor de errores por defecto.
     */
    class DefaultIntegerVerifierErrorManager implements VerifierErrorManager
    {
        public void error()
        {
            JOptionPane.showMessageDialog(
                    null,
                    ("El_valor_de_este_campo_debe_ser_un_numero_entero_comprendido_entre")+
                    minValue + " y " + maxValue +
                    (anulable?("_o_un_valor_nulo"):"."),
                    ("Error"),
                    JOptionPane.ERROR_MESSAGE);
        }
    }


}
