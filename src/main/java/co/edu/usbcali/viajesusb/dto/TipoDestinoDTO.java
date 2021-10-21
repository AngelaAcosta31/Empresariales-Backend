/**  
 * @Title:  TipoDestinoDTO.java   
 * @Package co.edu.usbcali.viajesusb.dto   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   7/09/2021 8:30:38 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;



/**   
 * @ClassName:  TipoDestinoDTO   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   7/09/2021 8:30:38 a. m.      
 * @Copyright:  USB
 */
@Data
public class TipoDestinoDTO implements Serializable{

	
	/**   
	   * @Fields serialVersionUID: TODO (what does this variable mean)   
	 */
	
	private static final long serialVersionUID = -4163597213585107874L;

	
	private Long idTide;
	private String codigo;
	private String nombre;
	private String descripcion;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String usuCreador;
	private String usuModificador;
	private String estado;
	
	
	
	/**   
	 * @Title:  TipoDestinoDTO  
	 * @Author: Ángela_Acosta 
	   * @Description:TODO 
	 * @param:  @param idTide
	 * @param:  @param codigo
	 * @param:  @param nombre
	 * @param:  @param descripcion  
	 * @throws   
	 */
	
//	public TipoDestinoDTO(Integer idTide, String codigo, String nombre, String descripcion) {
//		super();
//		this.idTide = idTide;
//		this.codigo = codigo;
//		this.nombre = nombre;
//		this.descripcion = descripcion;
//	}
//	
	
}
