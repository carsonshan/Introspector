package test.verifiers;

/*******************************************************************************
 *
 *  Interface para implementar manejadores de error "customizados" para los
 *  Verifiers.
 *  <p>
 *  Todos los Verifier tienen un manejador de error por defecto que
 *  muestra un mensaje en pantalla con un MessageBox.
 *  <p>
 *  Implementado esta Interface se puede obtener una gestión de errores
 *  para los Verifier mas acorde a cada aplicacion.
 *  <p>
 *
 *  Ejemplo de uso:
 *  <p><code>
 *  VerifierErrorManager vem = new VerifierErrorManager() { <br>
 *     public void error() {                                <br>
 *         System.out.println("Eres muuu tonto.");          <br>
 *     }                                                    <br>
 *  }                                                       <br><br>
 *
 *  IntegerVerifier v = new IntegerVerifier(0, 255, false); <br>
 *  v.setErrorManager(m);                                   <br><br>
 *
 *  JTextField jtf = new JTextField("Red:");                <br>
 *  jtf.setInputVerifier(v);
 *  </code>
 *
 *
 *  @author  $UF768189
 *
 *******************************************************************************/
public interface VerifierErrorManager
{

    /**
     * Metodo que se llama cuando el valor del campo controlado
     * por el Verifier no es valido.
     */
    public void error();
}
