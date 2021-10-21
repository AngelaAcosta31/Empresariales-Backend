/**  
 * @Title:  TipoIdentificacionService.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   8/09/2021 9:31:11 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import java.util.List;

import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;

/**   
 * @ClassName:  TipoIdentificacionService   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   8/09/2021 9:31:11 p. m.      
 * @Copyright:  USB
 */

public interface TipoIdentificacionService {
	
	/**
	 * 
	 * @Title: findByEstadoOrderByNombreAsc   
	   * @Description: Consultar_todos_los_tipo_de_identificación_por_estado_ordenados_alfabeticamente. 
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<TipoIdentificacion>      
	 * @throws
	 */
	public List<TipoIdentificacion> findByEstadoOrderByNombreAsc(String estado) throws Exception;

	/**
	 * 
	 * @Title: findByCodigoAndEstado   
	   * @Description: Consultar_tipo_de_identificación_por_código_y_estado. 
	 * @param: @param codigo
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: TipoIdentificacion      
	 * @throws
	 */
	public TipoIdentificacion findByCodigoAndEstado(String codigo, String estado) throws Exception;

	/**   
	 * @Title: consultarTipoIdentificacionPorCodigo   
	   * @Description: TODO 
	 * @param: @param codigo
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: TipoIdentificacion      
	 * @throws   
	 */
	
	public TipoIdentificacion findByCodigo(String codigo) throws Exception;

	/**   
	 * @Title: findById   
	   * @Description: TODO 
	 * @param: @param idTipoIdentificacion
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: TipoIdentificacion      
	 * @throws   
	 */
	
	public TipoIdentificacion findById(Long idTipoIdentificacion) throws Exception;

	/**   
	 * @Title: guardarTipoIdentificacion   
	   * @Description: TODO 
	 * @param: @param tipoIdentificacionDTO
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws   
	 */
	
	public TipoIdentificacion guardarTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacionDTO) throws Exception;

	/**   
	 * @Title: actualizarTipoIdentificacion   
	   * @Description: TODO 
	 * @param: @param tipoIdentificacionDTO
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws   
	 */
	
	public TipoIdentificacion actualizarTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacionDTO) throws Exception;

	/**   
	 * @Title: eliminarTipoIdentificacion   
	   * @Description: TODO 
	 * @param: @param tipoIdentificacionDTO
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws   
	 */
	
	public void eliminarTipoIdentificacion(Long ti_dee) throws Exception;

	

}
