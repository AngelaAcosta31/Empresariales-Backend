/**  
 * @Title:  TipoIdentificacionMapper.java   
 * @Package co.edu.usbcali.viajesusb.mapper   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   17/10/2021 9:52:13 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;

/**   
 * @ClassName:  TipoIdentificacionMapper   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   17/10/2021 9:52:13 p. m.      
 * @Copyright:  USB
 */
@Mapper(componentModel = "spring")
public interface TipoIdentificacionMapper {

	//@Mapping(source ="idTipoIdentificacion", target="idTipoIdentificacionORM")
	 public TipoIdentificacionDTO tipoIdentificacionToTipoIdentificacionDTO(TipoIdentificacion tipoIdentificacion);
	 public TipoIdentificacion tipoIdentificacionDTOToTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacionDTO);
	 public List<TipoIdentificacionDTO> listTipoIdentificacionToListTipoIdentificacionDTO(List<TipoIdentificacion> tipoIdentificaciones);
}
