/**  
 * @Title:  PlanDTO.java   
 * @Package co.edu.usbcali.viajesusb.dto   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   3/10/2021 8:59:05 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**   
 * @ClassName:  PlanDTO   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   3/10/2021 8:59:05 a. m.      
 * @Copyright:  USB
 */
@Data
public class PlanDTO implements Serializable{  

	private static final long serialVersionUID = 4350469383028805111L;
	
	private Long idPlan;
	private String codigo;
	private String nombre;
	private String descripcionSolicitud;
	private Integer cantidadPersonas;
	private Date fechaSolicitud;
	private Date fechaInicioViaje;
	private Date fechaFinViaje;
	private Double valorTotal;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String usuCreador;
	private String usuModificador;
	private String estado;
	
	//Llaves foraneas
	private Long idCliente;
	private String numeroIdentificacionCliente;
	private String nombreCliente;
	
	private Long idUsuario;
	private String loginUsuario;
	private String nombreUsuario;


}
