/**  
 * @Title:  PlanMapper.java   
 * @Package co.edu.usbcali.viajesusb.mapper   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   17/10/2021 9:08:40 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.viajesusb.domain.Plan;
import co.edu.usbcali.viajesusb.dto.PlanDTO;

/**   
 * @ClassName:  PlanMapper   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   17/10/2021 9:08:40 p. m.      
 * @Copyright:  USB
 */
@Mapper(componentModel = "spring")
public interface PlanMapper {

	@Mapping(source ="cliente.idCliente", target="idCliente")
	@Mapping(source ="usuario.idUsuario", target="idUsuario")
	@Mapping(source ="usuario.login", target="loginUsuario")
	@Mapping(source ="cliente.nombre", target="nombreCliente")
	@Mapping(source ="usuario.nombre", target="nombreUsuario")
	@Mapping(source ="cliente.numeroIdentificacion", target="numeroIdentificacionCliente")
    public PlanDTO planToPlanDTO(Plan plan);
    public List<PlanDTO> listPlanToListPlanDTO(List<Plan> planes);
}
