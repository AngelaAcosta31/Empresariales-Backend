/**  
 * @Title:  UsuarioRepository.java   
 * @Package co.edu.usbcali.viajesusb.repository   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   2/09/2021 6:53:39 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import co.edu.usbcali.viajesusb.domain.Usuario;

/**   
 * @ClassName:  UsuarioRepository   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   2/09/2021 6:53:39 p. m.      
 * @Copyright:  USB
 */

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

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
	   * @Description: Consulta_los_usuarios_por_estado. 
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<Usuario>      
	 * @throws
	 */
	public List<Usuario> findByEstado(String estado)  throws Exception;

	/**
	 * 
	 * @Title: findByLogin   
	   * @Description: Consulta_el_usuario_por_login. 
	 * @param: @param login
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: Usuario      
	 * @throws
	 */
	public Usuario findByLogin(String login) throws Exception;

	/**   
	 * @Title: findByCodigoAndEstado   
	   * @Description: TODO 
	 * @param: @param upperCase
	 * @param: @param upperCase2
	 * @param: @return      
	 * @return: Usuario      
	 * @throws   
	 */
	
	public Usuario findByLoginAndEstado(String login, String estado);

}
