/**  
 * @Title:  DestinoService.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   7/09/2021 11:51:10 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;

/**   
 * @ClassName:  DestinoService   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   7/09/2021 11:51:10 a. m.      
 * @Copyright:  USB
 */

public interface DestinoService {
	
	/**
	 * 
	 * @Title: findByTipoDestino_Codigo   
	   * @Description: consulta_todos_los_destinos_que_pertenece_a_un_tipo_de_destino. 
	 * @param: @param codigoTipoDestino
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<Destino>      
	 * @throws Exception 
	 * @throws
	 */
	
	public List<Destino> findByTipoDestinoCodigo(String codigoTipoDestino) throws Exception;
	
	/**
	 * 
	 * @Title: findByEstado   
	   * @Description: Retorna_una_paginacion_de_la_lista_de_destinos_por_estado. 
	 * @param: @param estado
	 * @param: @param pageable
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: Page<Destino>      
	 * @throws
	 */
	public Page<Destino> findByEstado(String estado, Pageable pageable) throws Exception;
	
	public Destino findByCodigo(String codigo) throws Exception;

	public Destino guardarDestino(DestinoDTO destinoDTO) throws Exception ;

	/**   
	 * @Title: actualizarDestino   
	   * @Description: metodo_para_actualizar_destino
	 * @param: @param destinoDTO
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws   
	 */
	
	public Destino actualizarDestino(DestinoDTO destinoDTO) throws Exception;

	/**   
	 * @Title: eliminarDestino   
	   * @Description: Eliminar un destino
	 * @param: @param destinoDTO
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws   
	 */
	
	public void eliminarDestino(Long idDestino) throws Exception;

	/**   
	 * @Title: findByCodigoAndEstado   
	   * @Description: TODO 
	 * @param: @param codigo
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Destino      
	 * @throws   
	 */
	
	public Destino findByCodigoAndEstado(String codigo, String estado) throws Exception;
	public Destino findById(Long idDest) throws Exception;


	
}
