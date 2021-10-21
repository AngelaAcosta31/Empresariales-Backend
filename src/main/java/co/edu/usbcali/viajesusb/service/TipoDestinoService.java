/**  
 * @Title:  TipoDestinoService.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   7/09/2021 11:50:52 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;


import java.util.List;

import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;



/**   
 * @ClassName:  TipoDestinoService   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   7/09/2021 11:50:52 a. m.      
 * @Copyright:  USB
 */

public interface TipoDestinoService {
	
	
	
	
	public TipoDestino findById(Long idtide) throws Exception;
	/**
	 * 
	 * @Title: findByCodigo   
	   * @Description: consultar_un_tipo_de_destino_dado_un_codigo.
	 * @param: @param codigo
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: TipoDestino      
	 * @throws
	 */
	public TipoDestino findByCodigo(String codigo) throws Exception;

	/**   
	 * @Title: findByCodigoAndEstado   
	   * @Description: consulta_tipo_destino_por_codigo_y_estado
	 * @param: @param codigo
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: TipoDestino      
	 * @throws   
	 */
	
	public TipoDestino findByCodigoAndEstado(String codigo, String estado) throws Exception;

	/**   
	 * @Title: findByEstadoOrderByNombreDesc   
	   * @Description: Consulta_una_lista_de_tipos_destino_por_nombre_ordenado_de_manera_descendente. 
	 * @param: @param nombre
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: TipoDestino      
	 * @throws   
	 */
	
	public List<TipoDestino> findByEstadoOrderByNombreDesc(String nombre) throws Exception;
	
	/**
	 * 
	 * @Title: findByNombre   
	   * @Description: Consultar_Tipo_destino_por_nombre. 
	 * @param: @param nombre
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: TipoDestino      
	 * @throws
	 */
	public TipoDestino findByNombre(String nombre) throws Exception;

	/**   
	 * @Title: guardarTipoDestino   
	   * @Description: TODO 
	 * @param: @param tipoDestinoDTO
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws   
	 */
	
	public TipoDestino guardarTipoDestino(TipoDestinoDTO tipoDestinoDTO) throws Exception;

	/**   
	 * @Title: actualizarTipoDestino   
	   * @Description: TODO 
	 * @param: @param tipoDestinoDTO
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws   
	 */
	
	public TipoDestino actualizarTipoDestino(TipoDestinoDTO tipoDestinoDTO) throws Exception;

	/**   
	 * @Title: eliminarTipoDestino   
	   * @Description: TODO 
	 * @param: @param tipoDestinoDTO
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws   
	 */
	
	public void eliminarTipoDestino(Long idTipoDestino) throws Exception;

	

}
