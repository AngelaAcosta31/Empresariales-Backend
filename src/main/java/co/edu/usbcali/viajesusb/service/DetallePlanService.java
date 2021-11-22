/**  
 * @Title:  DetallePlanService.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   8/09/2021 9:31:34 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.edu.usbcali.viajesusb.domain.DetallePlan;
import co.edu.usbcali.viajesusb.dto.DetallePlanDTO;

/**   
 * @ClassName:  DetallePlanService   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   8/09/2021 9:31:34 p. m.      
 * @Copyright:  USB
 */

public interface DetallePlanService {
	
	public DetallePlan guardarDetallePlan(DetallePlanDTO detallePlanDTO) throws Exception;

	/**   
	 * @Title: actualizaDetallePlan   
	   * @Description: TODO 
	 * @param: @param detallePlanDTO
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws   
	 */
	
	public DetallePlan actualizarDetallePlan(DetallePlanDTO detallePlanDTO) throws Exception;

	/**   
	 * @Title: eliminarDetallePlan   
	   * @Description: TODO 
	 * @param: @param detallePlanDTO      
	 * @return: void      
	 * @throws   
	 */
	
	public void eliminarDetallePlan(Long idDetallePlan)throws Exception;
	public DetallePlan findById(Long idDepl) throws Exception;

	/**   
	 * @Title: findByCantidadNoches   
	   * @Description: TODO 
	 * @param: @param noches
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: DetallePlan      
	 * @throws   
	 */
	
	public List <DetallePlan> findByCantidadNoches(Integer noches) throws Exception;
	public List <DetallePlan> findByValor(Double valor)throws Exception;
	public Page <DetallePlan> findByEstado(String estado, Pageable pageable)throws Exception;

}
