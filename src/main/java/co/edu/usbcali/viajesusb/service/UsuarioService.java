/**  
 * @Title:  UsuarioService.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   8/09/2021 9:32:01 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import java.util.List;

import co.edu.usbcali.viajesusb.domain.Usuario;
import co.edu.usbcali.viajesusb.dto.UsuarioDTO;

/**   
 * @ClassName:  UsuarioService   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   8/09/2021 9:32:01 p. m.      
 * @Copyright:  USB
 */

public interface UsuarioService {
	
	/**
	 * 
	 * @Title: findByIdentificacion   
	   * @Description: Consulta_los_usuarios_por_tipo_identificacion. 
	 * @param: @param identificacion
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: Usuario      
	 * @throws
	 */
	public Usuario findByIdentificacion(String identificacion) throws Exception;
	
	/**
	 * 
	 * @Title: findByEstado   
	   * @Description: Consulta_el_usuario_por_estado. 
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<Usuario>      
	 * @throws
	 */

	public List<Usuario> findByEstado(String estado)  throws Exception;

	/**   
	 * @Title: findByLogin   
	   * @Description: Consulta_el_usuario_por_login.  
	 * @param: @param login
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Usuario      
	 * @throws   
	 */
	
	public Usuario findByLogin(String login) throws Exception;

	public Usuario guardarUsuario(UsuarioDTO usuarioDTO) throws Exception;
	public Usuario actualizarUsuario(UsuarioDTO usuarioDTO) throws Exception;

	/**   
	 * @Title: eliminarUsuario   
	   * @Description: TODO 
	 * @param: @param usuarioDTO      
	 * @return: void      
	 * @throws   
	 */
	
	public void eliminarUsuario(Long idUsuario) throws Exception;

	/**   
	 * @Title: findByCodigoAndEstado   
	   * @Description: TODO 
	 * @param: @param codigo
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Usuario      
	 * @throws   
	 */
	
	public Usuario findByLoginAndEstado(String login, String estado) throws Exception;
	public Usuario findById(Long idUsua) throws Exception;
}
