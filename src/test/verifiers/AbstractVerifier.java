package test.verifiers;

import javax.swing.InputVerifier;
import javax.swing.JComponent;

/*******************************************************************************
 *
 *  Clase abstracta de la que deben heredar todos los Verifiers.
 *  <p>
 *  Los "Verifiers" se encargan de validar los datos que se introducen
 *  en campos de texto (JTextField) dentro de una ventana.
 *
 *
 *  @author  UF768189
 *
 *******************************************************************************/

abstract class AbstractVerifier extends InputVerifier
{
     /** Gestor de errores de este Verifier. */
     protected VerifierErrorManager errorManager;

     /** Indica si el foco esta en la gestion del error. */
     private boolean showingError = false;



    /**
     *  Comprueba si el valor del campo es correcto cuando este pierde el foco.
     *  NO LLAMAR DIRECTAMENTE (es llamado desde JComponent).
     *
     *  @param input  JTextField a verificar.
     *  @return  Si el campo puede perder el foco, es decir, si el valor del campo
     *           es correcto de acuerdo a este verificador.
     */
    public boolean shouldYieldFocus(JComponent input)
    {

        if (!showingError)
        {
            boolean ok = verify(input);
            if ( (!ok)  && (errorManager != null) )
            {
                showingError = true;
                errorManager.error();
                showingError = false;
            }
            return ok;
        }
        else
            return false;
    }


    /**
     * Establece el gestor de errores para este Verifier.
     * @param errorManager Gestor de errores.
     */
    public void setErrorManager(VerifierErrorManager errorManager)
    {
        this.errorManager = errorManager;
    }


}
