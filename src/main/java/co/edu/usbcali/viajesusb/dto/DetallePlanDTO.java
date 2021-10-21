/**  
 * @Title:  DetallePlanDTO.java   
 * @Package co.edu.usbcali.viajesusb.dto   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   3/10/2021 8:58:51 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**   
 * @ClassName:  DetallePlanDTO   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   3/10/2021 8:58:51 a. m.      
 * @Copyright:  USB
 */
@Data
public class DetallePlanDTO implements Serializable {
	
	private static final long serialVersionUID = 6806776102236525144L;

	private Long idDetallePlan;
	private String alimentacion;
	private String hospedaje;
	private String transporte;
	private String traslados;
	private Double valor;
	private Integer cantidadNoches;
	private Integer cantidadDias;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String usuCreador;
	private String usuModificador;
	private String estado;
	
	
	//Laves foraneas
	private Long idPlan;
	private String codigoPlan;
	private String nombrePlan;
	
	private Long idDest;
	private String codigoDestino;
	private String nombreDestino;
	
	
}
