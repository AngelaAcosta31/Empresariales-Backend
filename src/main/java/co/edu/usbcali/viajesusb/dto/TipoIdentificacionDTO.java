/**  
 * @Title:  TipoIdentificacionDTO.java   
 * @Package co.edu.usbcali.viajesusb.dto   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   3/10/2021 8:58:28 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**   
 * @ClassName:  TipoIdentificacionDTO   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   3/10/2021 8:58:28 a. m.      
 * @Copyright:  USB
 */
@Data
public class TipoIdentificacionDTO implements Serializable {


	private static final long serialVersionUID = -5998412841994393121L;
	
	private Long idTipoIdentificacion;
	private String codigo;
	private String nombre;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String usuCreador;
	private String usuModificador;
	private String estado;
	
	//private Integer idTipoIdentificacionORM;

}
