/**
 * 
 */
package cl.laaraucana.wssinacofi.services;

import cl.laaraucana.wssinacofi.vo.AbstractEntradaVO;
import cl.laaraucana.wssinacofi.vo.AbstractSalidaVO;

/**
 * @author IBM Software Factory
 *
 */
public interface ClienteSinacofi {

	public AbstractSalidaVO call(AbstractEntradaVO entrada) throws Exception;
	
}
