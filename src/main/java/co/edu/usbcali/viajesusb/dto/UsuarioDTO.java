/**  
 * @Title:  UsuarioDTO.java   
 * @Package co.edu.usbcali.viajesusb.dto   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   3/10/2021 8:59:18 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**   
 * @ClassName:  UsuarioDTO   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   3/10/2021 8:59:18 a. m.      
 * @Copyright:  USB
 */
@Data
public class UsuarioDTO implements Serializable{
	
	private static final long serialVersionUID = -3510000857829084023L;
	

	private Long idUsuario;
	private String login;
	private String password;
	private String nombre;
	private String identificacion;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String usuCreador;
	private String usuModificador;
	private String estado;

}
