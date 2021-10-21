/**  
 * @Title:  DestinoMapper.java   
 * @Package co.edu.usbcali.viajesusb.mapper   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   12/10/2021 12:01:11 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;

/**   
 * @ClassName:  DestinoMapper   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   12/10/2021 12:01:11 p. m.      
 * @Copyright:  USB
 */
@Mapper(componentModel = "spring")
public interface DestinoMapper {
	
	//se mapean los atributos que se pasan como llaves foraneas
	@Mapping(source ="tipoDestino.idTide", target="idTide")
	@Mapping(source ="tipoDestino.codigo", target="codigoTipoDestino")
	@Mapping(source ="tipoDestino.nombre", target="nombreTipoDestino")
	public DestinoDTO destinoToDestinoDTO(Destino destino);
	
//	@Mapping(source ="tipoDestino.idTide", target="idTide")
//	@Mapping(source ="tipoDestino.codigo", target="codigoTipoDestino")
//	@Mapping(source ="tipoDestino.nombre", target="nombreTipoDestino")
	//public Destino destinoDTOToDestino(DestinoDTO destinoDTO);
	
	
	@Mapping(source ="tipoDestino.idTide", target="idTide")
	@Mapping(source ="tipoDestino.codigo", target="codigoTipoDestino")
	@Mapping(source ="tipoDestino.nombre", target="nombreTipoDestino")
	public List<DestinoDTO> listDestinoToListDestinoDTO(List<Destino> listaDestino);
	
	//public List<Destino> listDestinoDTOToListDestino(List<DestinoDTO> listaDestinoDTO);
	
	public List<DestinoDTO> listaDestinoToListDestinoDTO(Page<Destino> destinos);


}


