/**  
 * @Title:  DetallePlanRepository.java   
 * @Package co.edu.usbcali.viajesusb.repository   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   2/09/2021 6:54:39 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import co.edu.usbcali.viajesusb.domain.DetallePlan;

/**   
 * @ClassName:  DetallePlanRepository   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   2/09/2021 6:54:39 p. m.      
 * @Copyright:  USB
 */

public interface DetallePlanRepository extends JpaRepository<DetallePlan, Long>{
	
	
	public List <DetallePlan> findByCantidadNoches(Integer noches) throws Exception;
	public List <DetallePlan> findByValor(Double noches) throws Exception;
	public Page <DetallePlan> findByEstado(String estado, Pageable pageable) throws Exception;
}
