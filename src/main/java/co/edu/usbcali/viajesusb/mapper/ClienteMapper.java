/**  
 * @Title:  ClienteMapper.java   
 * @Package co.edu.usbcali.viajesusb.mapper   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   17/10/2021 9:07:42 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;

/**   
 * @ClassName:  ClienteMapper   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   17/10/2021 9:07:42 a. m.      
 * @Copyright:  USB
 */
@Mapper(componentModel = "spring")
public interface ClienteMapper {

	@Mapping(source ="tipoIdentificacion.nombre", target="tipoIdentificacion")
	@Mapping(source ="tipoIdentificacion.codigo", target="tipoIdentificacionCodigo")
	@Mapping(source ="tipoIdentificacion.idTipoIdentificacion", target="id_tiid")
    public ClienteDTO clienteToClienteDTO(Cliente cliente);
    public List<ClienteDTO> listClienteToListClienteDTO(List<Cliente> clientes);
    public List<ClienteDTO> listClienteToListClienteDTO(Page<Cliente> clientes);
    public List<ClienteDTO> listaClienteToListClienteDTO(List<ClienteDTO> clientes);

	
}
