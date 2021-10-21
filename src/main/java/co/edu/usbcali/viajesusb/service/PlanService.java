/**  
 * @Title:  PlanService.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   8/09/2021 9:31:44 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;



import co.edu.usbcali.viajesusb.domain.Plan;
import co.edu.usbcali.viajesusb.dto.PlanDTO;

/**   
 * @ClassName:  PlanService   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   8/09/2021 9:31:44 p. m.      
 * @Copyright:  USB
 */

public interface PlanService {
	
	/**
	 * 
	 * @Title: findByCodigo   
	   * @Description: Consultar_pla-Por_codigo. 
	 * @param: @param codigo
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: Plan      
	 * @throws
	 */
	public Plan findByCodigo(String codigo) throws Exception;

	/**   
	 * @Title: findByCodigoAndEstado   
	   * @Description: TODO 
	 * @param: @param codigoPlan
	 * @param: @return      
	 * @return: Plan      
	 * @throws   
	 */
	
	public Plan findByCodigoAndEstado(String codigoPlan, String estado) throws Exception;

	/**   
	 * @Title: guardarPlan   
	   * @Description: TODO 
	 * @param: @param planDTO
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws   
	 */
	
	public Plan guardarPlan(PlanDTO planDTO) throws Exception;

	/**   
	 * @Title: actualizarPlan   
	   * @Description: TODO 
	 * @param: @param planDTO
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws   
	 */
	
	public Plan actualizarPlan(PlanDTO planDTO) throws Exception;

	/**   
	 * @Title: eliminarPlan   
	   * @Description: TODO 
	 * @param: @param planDTO
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws   
	 */
	
	public void eliminarPlan(Long idPlan) throws Exception;
	public Plan findById(Long idPlan) throws Exception;

}
