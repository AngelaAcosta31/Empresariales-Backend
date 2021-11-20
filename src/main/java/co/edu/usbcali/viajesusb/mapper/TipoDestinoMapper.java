/**  
 * @Title:  TipoDestinoMapper.java   
 * @Package co.edu.usbcali.viajesusb.mapper   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   12/10/2021 11:32:41 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;

/**   
 * @ClassName:  TipoDestinoMapper   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   12/10/2021 11:32:41 a. m.      
 * @Copyright:  USB
 */
@Mapper(componentModel = "spring")
public interface TipoDestinoMapper {
	
	// pasando de entidades a DTO y viceversa
	public TipoDestinoDTO tipoDestinoToTipoDestinoDTO(TipoDestino tipoDestino);
	public List<TipoDestinoDTO> listTipoDestinoToListTipoDestinoDTO(List<TipoDestino> listaTipoDestino);
	
	public TipoDestino tipoDestinoDTOToTipoDestino(TipoDestinoDTO tipoDestinoDTO);
	//public List<TipoDestino> listTipoDestinoDTOToListTipoDestino(List<TipoDestinoDTO> listaTipoDestinoDTO);
}
