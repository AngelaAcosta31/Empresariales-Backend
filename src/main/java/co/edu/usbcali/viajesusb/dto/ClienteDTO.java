/**  
 * @Title:  ClienteDTO.java   
 * @Package co.edu.usbcali.viajesusb.dto   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   6/09/2021 6:02:46 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


/**   
 * @ClassName:  ClienteDTO   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   6/09/2021 6:02:46 p. m.      
 * @Copyright:  USB
 */
@Data
public class ClienteDTO implements Serializable {

	/**   
	   * @Fields serialVersionUID: TODO (what does this variable mean)   
	 */
	
	private static final long serialVersionUID = 8650084404524240764L;
	
	
	private Long idCliente;
	private String numeroIdentificacion;
	private String primerApellido;
	private String segundoApellido;
	private String nombre;
	private String telefono1;
	private String telefono2;
	private String correo;
	private String sexo;
	private Date fechaNacimiento;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String usuCreador;
	private String usuModificador;
	private String estado;
	
	private Long id_tiid;
	private String tipoIdentificacion;
	private String tipoIdentificacionCodigo;
	//private Integer tipoIdentificacionORM;
	
	/**   
	 * @Title:  ClienteDTO  
	 * @Author: Ángela_Acosta 
	   * @Description: constructor con_los_atributos_que_vamos_a_mapear_en_la_consulta_del_orm 
	   * El_orden_de_los_parametros_del_constructor_son_importantes_para_cuando_se_este_ejecutando_la_consulta
	 * @param:  @param numeroIdentificacion
	 * @param:  @param primerApellido
	 * @param:  @param segundoApellido
	 * @param:  @param nombre
	 * @param:  @param estado  
	 * @throws   
	 */

	/**   
	 * @Title:  ClienteDTO  
	 * @Author: Ángela Acosta 
	 * @Description:TODO 
	 * @param:    
	 * @throws   
	 */
	
	public ClienteDTO() {
		super();
	}
	
	public ClienteDTO(String numeroIdentificacion, String nombreC, String primerApellido, String segundoApellido,
			String estado) {
		//, Integer tipoIdentificacion
		super();
		this.numeroIdentificacion = numeroIdentificacion;
		this.nombre = nombreC;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.estado = estado;
		//this.tipoIdentificacionORM = tipoIdentificacion;
	}


}
