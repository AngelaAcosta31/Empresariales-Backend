package co.edu.usbcali.viajesusb.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;

import java.sql.SQLException;
/**
 * 
 * @ClassName:  TipoDestinoRepository   
  * @Description: Repository_para_TipoDestino   
 * @author: ÁngelaAcosta    
 * @date:   31/08/2021 11:07:55 a. m.      
 * @Copyright:  USB
 */

public interface TipoDestinoRepository extends JpaRepository<TipoDestino, Long>{

	
	/**
	 * 
	 * @Title: FindByCodigo   
	   * @Description: consulta_un_tipo_destino_por_codigo 
	 * @param: @param codigo
	 * @param: @return      
	 * @return: TipoDestino      
	 * @throws
	 */
	
	public TipoDestino findByCodigo(String codigo) throws SQLException;
	
	/**
	 * 
	 * @Title: findByCodigoAndEstado   
	   * @Description: consulta_tipo_destino_por_codigo_y_estado 
	 * @param: @param codigo
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: TipoDestino      
	 * @throws
	 */

	public TipoDestino findByCodigoAndEstado(String codigo, String estado) throws SQLException;
	
	/**
	 * 
	 * @Title: findByEstadoOrderByNombreDesc   
	   * @Description: Consulta_una_lista_de_tipos_destino_por_nombre_ordenado_de_manera_descendente.
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<TipoDestino>      
	 * @throws
	 */

	public List<TipoDestino> findByEstadoOrderByNombreDesc(String estado) throws SQLException;

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
	
	public TipoDestino findByNombre(String nombre) throws SQLException;
	
	
	/**
	 * 
	 * @Title: consultarTipoDestinoPorEstado   
	   * @Description: Consulta_del_ORM_sobre_todos_los_clientes_con_los
	   * _siguientes_filtros:_Estado,_numero_identificación,_tipo_de_identificación,
	   * _nombre_(con_Like)_en_orden_ascendente.
	   * NOTA:NO_esta_la_prueba_unitaria_de_esta_consulta 
	 * @param: @param estado
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<TipoDestinoDTO>      
	 * @throws
	 */
	@Query(nativeQuery = true)
	public List<TipoDestinoDTO> consultarTipoDestinoPorEstado(@Param("pEstado") String estado) throws SQLException;

	
}
