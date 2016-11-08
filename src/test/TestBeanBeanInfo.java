package test;

import java.beans.*;



/**
 * <p> </p>
 * <p> </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author Luis Manuel Parrondo Merino
 */

public class TestBeanBeanInfo extends SimpleBeanInfo {

  public PropertyDescriptor[] getPropertyDescriptors() {
    try {
      PropertyDescriptor props[] = {
        new PropertyDescriptor("cadena", TestBean.class), 
        new PropertyDescriptor("deimal", TestBean.class), 
        new PropertyDescriptor("entero", TestBean.class), 
      };

      props[0].setDisplayName( ("La Cadena")); 
      props[0].setBound(true);

      props[1].setDisplayName( ("El Decimal")); 
      props[1].setBound(true);

      props[2].setDisplayName( ("El entero")); 
      props[2].setBound(true);

     
   

      return props;
    }
    catch (IntrospectionException ex) {
      ex.printStackTrace();
      return super.getPropertyDescriptors();
    }
  }

}
