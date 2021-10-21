/**  
 * @Title:  TipoIdentificacionRepository.java   
 * @Package co.edu.usbcali.viajesusb.repository   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   2/09/2021 6:53:56 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;


/**   
 * @ClassName:  TipoIdentificacionRepository   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   2/09/2021 6:53:56 p. m.      
 * @Copyright:  USB
 */

public interface TipoIdentificacionRepository extends JpaRepository<TipoIdentificacion, Long>{
	
	/**
	 * 
	 * @Title: findByEstadoOrderByNombre   
	   * @Description:  Consultar_todos_los_tipo_de_identificación_por_estado_ordenados_alfabeticamente.
	 * @param: @param nombre
	 * @param: @return      
	 * @return: TipoIdentificacion      
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
	 * 
	 * @Title: findByCodigo   
	   * @Description: Consultar_tipo_identificacion_por_codigo.
	 * @param: @param codigo
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: TipoIdentificacion      
	 * @throws
	 */
	public TipoIdentificacion findByCodigo(String codigo)  throws Exception;
}
