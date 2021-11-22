/**  
 * @Title:  DetallePlanMapper.java   
 * @Package co.edu.usbcali.viajesusb.mapper   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   17/10/2021 8:30:44 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import co.edu.usbcali.viajesusb.domain.DetallePlan;
import co.edu.usbcali.viajesusb.dto.DetallePlanDTO;

/**   
 * @ClassName:  DetallePlanMapper   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   17/10/2021 8:30:44 p. m.      
 * @Copyright:  USB
 */
@Mapper(componentModel = "spring")
public interface DetallePlanMapper {
	
	
	@Mapping(source ="plan.idPlan", target="idPlan")
	@Mapping(source ="plan.codigo", target="codigoPlan")
	@Mapping(source ="plan.nombre", target="nombrePlan")
	@Mapping(source ="destino.idDest", target="idDest")
	@Mapping(source ="destino.codigo", target="codigoDestino")
	@Mapping(source ="destino.nombre", target="nombreDestino")
    public DetallePlanDTO detallePlanToDetallePlanDTO(DetallePlan detallePlan);
	
	@Mapping(source ="plan.idPlan", target="idPlan")
	@Mapping(source ="plan.codigo", target="codigoPlan")
	@Mapping(source ="plan.nombre", target="nombrePlan")
	@Mapping(source ="destino.idDest", target="idDest")
	@Mapping(source ="destino.codigo", target="codigoDestino")
	@Mapping(source ="destino.nombre", target="nombreDestino")
    public List<DetallePlanDTO> listDetallePlanToListDetallePlanDTO(List<DetallePlan> detallePlanes);
   
	public List<DetallePlanDTO> listDetallePlanToListDetallePlanDTO(Page<DetallePlan> detallePlanes);
	   
}
