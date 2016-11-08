package test.verifiers;

import javax.swing.*;



/***************************************************************************
 *
 *  Verificador de valores DOUBLE introducidos en un JTextField.
 *  <p>
 *  Ejemplo de uso:
 *  <p>
 *  <code>
 *  JTextField jtf = new JTextField("Distancia:");  <br>
 *  jtf.setInputVerifier(new FloatVerifier(0.0, Double.MAX_VALUE, false);
 *  </code>
 *
 *  @see  VerifierErrorManager
 *  @author  UF768189
 ****************************************************************************/

public class DoubleVerifier extends AbstractVerifier
{
    /** Indica si el campo puede estar vacio. */
    protected boolean anulable;

    /** Valor minimo del campo. */
    protected double minValue;

    /** Valor maximo del campo. */
    protected double maxValue;



    /**
     * Construye el verificador para todo el rango de doubles.
     *
     * @param anulable  Indica si el campo puede estar vacio.
     */
    public DoubleVerifier(boolean anulable)
    {
        this(-Double.MAX_VALUE, Double.MAX_VALUE, anulable);
    }


    /**
     *  Construye el verificador para un rango dado de doubles.
     *  @param minValue  Valor minimo admitido (incluido).
     *  @param maxValue  Valor maximo admitido (incluido).
     *  @param anulable  Indica si el campo puede estar vacio.
     */
    public DoubleVerifier(double minValue, double maxValue, boolean anulable)
    {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.anulable = anulable;

        // error manager por defecto
        errorManager = new DefaultDoubleVerifierErrorManager();
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
            double n = Double.parseDouble(valor);
            return (n >= minValue) && (n <= maxValue);
        }
        catch (NumberFormatException e) {
            return false;
        }
    }


    /**
     *  Gestor de errores por defecto.
     */
    class DefaultDoubleVerifierErrorManager implements VerifierErrorManager
    {
        public void error()
        {
            JOptionPane.showMessageDialog(
                    null,
                    "El_valor_de_este_campo_debe_ser_un_numero_de_precision_doble_comprendido_entre"+
                    minValue + "_y_" + maxValue +
                    (anulable? ("_o_un_valor_nulo") :"."),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


}
