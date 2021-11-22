/**  
 * @Title:  PlanRepository.java   
 * @Package co.edu.usbcali.viajesusb.repository   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   2/09/2021 6:54:22 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.repository;



import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import co.edu.usbcali.viajesusb.domain.Plan;

/**   
 * @ClassName:  PlanRepository   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   2/09/2021 6:54:22 p. m.      
 * @Copyright:  USB
 */

public interface PlanRepository extends JpaRepository<Plan, Long>{

	/**
	 * 
	 * @Title: findByCodigo   
	   * @Description: Consultar_plan_Por_codigo. 
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
	 * @param: @param upperCase
	 * @param: @param upperCase2
	 * @param: @return      
	 * @return: Plan      
	 * @throws   
	 */
	
	public Plan findByCodigoAndEstado(String codigo, String estado);
	
	public List<Plan> findByValorTotal(Double valorTotal)throws Exception;
	public List<Plan> findByCantidadPersonas(Integer personas)throws Exception;
	public List<Plan> findByFechaInicioViajeBetween(Date fechaInicioViaje, Date fechaFinViaje) throws Exception;
}
