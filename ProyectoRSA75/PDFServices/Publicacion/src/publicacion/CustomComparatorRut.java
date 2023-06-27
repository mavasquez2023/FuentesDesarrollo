/**
 * 
 */
package publicacion;

import java.util.Comparator;

/**
 * @author Usist24
 *
 */
public class CustomComparatorRut implements Comparator {
	//Se compara objetos por atributo Razón Social
    public int compare(Object o1, Object o2) {
    	EmpresasAutorizadasTO obj1 = (EmpresasAutorizadasTO) o1;
    	EmpresasAutorizadasTO obj2 = (EmpresasAutorizadasTO) o2;
    	return obj1.getRutint()-obj2.getRutint();
    }
}
