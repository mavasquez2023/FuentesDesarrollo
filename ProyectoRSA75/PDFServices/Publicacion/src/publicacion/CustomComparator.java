/**
 * 
 */
package publicacion;

import java.util.Comparator;

/**
 * @author Usist24
 *
 */
public class CustomComparator implements Comparator {
	//Se compara objetos por atributo Razón Social
    public int compare(Object o1, Object o2) {
    	EmpresasAutorizadasTO obj1 = (EmpresasAutorizadasTO) o1;
    	EmpresasAutorizadasTO obj2 = (EmpresasAutorizadasTO) o2;
    	return obj1.getRazonSocial().compareToIgnoreCase(obj2.getRazonSocial());
    }
}
