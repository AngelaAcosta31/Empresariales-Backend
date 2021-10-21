/**  
 * @Title:  UsuarioMapper.java   
 * @Package co.edu.usbcali.viajesusb.mapper   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   17/10/2021 7:29:13 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.edu.usbcali.viajesusb.domain.Usuario;
import co.edu.usbcali.viajesusb.dto.UsuarioDTO;

/**   
 * @ClassName:  UsuarioMapper   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   17/10/2021 7:29:13 p. m.      
 * @Copyright:  USB
 */
@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	
    public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);
    public List<UsuarioDTO> listUsuarioToListUsuarioDTO(List<Usuario> usuarios);

}
