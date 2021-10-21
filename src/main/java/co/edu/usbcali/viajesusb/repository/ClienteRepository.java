/**  
 * @Title:  ClienteRepository.java   
 * @Package co.edu.usbcali.viajesusb.repository   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   2/09/2021 6:53:19 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.repository;


import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;


/**   
 * @ClassName:  ClienteRepository   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   2/09/2021 6:53:19 p. m.      
 * @Copyright:  USB
 */

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	
	
	
	/**
	 * 
	 * @Title: findByEstado   
	   * @Description:  Consultar_todos_los_clientes_por_estado.
	   *  Esta_consulta_debe_ser_paginada_y_ordenada_de_forma_ascendente_por_numero_de_identificación
	 * @param: @param estado
	 * @param: @param pageable
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: Page<Cliente>      
	 * @throws
	 */
	public Page<Cliente> findByEstadoOrderByNumeroIdentificacionAsc(String estado, Pageable pageable) throws Exception;
	
	/**
	 * 
	 * @Title: findByCorreoIgnoreCase   
	   * @Description: Consultar_un_cliente_por_correo_electrónico. 
	   * Esta_consulta_debe_ignorar_las_mayusculas_y_minúsculas.
	 * @param: @param correo
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: Cliente      
	 * @throws
	 */
	public Cliente findByCorreoIgnoreCase(String correo) throws Exception;
	
	/**
	 * 
	 * @Title: findByNumeroIdentificacionLike   
	   * @Description: Consultar_cliente_por_numero_de_identificación,_usando_LIKE.
	 * @param: @param numeroIdentificacion
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: Cliente      
	 * @throws
	 */
	public List<Cliente> findByNumeroIdentificacionLike(String numeroIdentificacion) throws Exception;

	/**
	 * 
	 * @Title: findByNombreIgnoreCaseAndLike   
	   * @Description: Consultar_cliente_por_nombre,_ignorando_Mayusculas_y_minúsculas,_usando_LIKE. 
	 * @param: @param nombre
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: Cliente      
	 * @throws
	 */
	
	public List<Cliente> findByNombreIgnoreCaseLike(String nombre) throws Exception;

	/**
	 * 
	 * @Title: findByFechaNacimientoBetween   
	   * @Description: Consultar_clientes_por_rango_de_fecha_(Se_debe_pasar_dos_fechas 
	   * y_traer_los_clientes_cuya_fecha_de_nacimiento_se_encuentre_entre_el_rango_ingresado).
	 * @param: @param fecha1
	 * @param: @param fecha2
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<Cliente>      
	 * @throws
	 */
	
	public List<Cliente> findByFechaNacimientoBetween(Date fecha1, Date fecha2) throws Exception;

	/**
	 * 
	 * @Title: countByEstado   
	   * @Description: Consultar_total_de_clientes_por_estado. 
	 * @param: @param estado
	 * @param: @return      
	 * @return: Long      
	 * @throws
	 */
	
	public Long countByEstado(String estado);

	public long count();
	/**
	 * 
	 * @Title: findByTipoIdentificacion   
	   * @Description: Consultar_clientes_por_tipo_de_identificación._Debe_de_ser_paginado. 
	 * @param: @param tipoIdentificacion
	 * @param: @param pageable
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: Page<Cliente>      
	 * @throws
	 */
	
	public Page<Cliente> findByTipoIdentificacion_Codigo(String tipoIdentificacion, Pageable pageable) throws Exception;

	/**
	 * 
	 * @Title: findByPrimerApellidoAndSegundoApellidoLike   
	   * @Description: Consultar_clientes_por_apellido_(tener_en_cuenta_los_campos_primer_y_segundo_apellido)
	 * @param: @param primerApellido
	 * @param: @param segundoApellido
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<Cliente>      
	 * @throws
	 */
	
	public List<Cliente> findByPrimerApellidoOrSegundoApellido(String primerApellido, String segundoApellido) throws Exception;

	/**
	 * 
	 * @Title: findByNombreLike   
	   * @Description: Consulta_del_ORM
	   * Consultar_todos_los_clientes_con_los_siguientes_filtros: 
	   * _Estado,_numero_identificación,_tipo_de_identificación,_nombre_(con_Like)_en_orden_ascendente. 
	 * @param: @param nombre
	 * @param: @return
	 * @param: @throws SQLException      
	 * @return: List<Cliente>      
	 * @throws
	 */
	
	@Query(nativeQuery = true)
	public List<ClienteDTO> consultarClientesPorNombre(@Param("pNombreC") String nombre) throws Exception;

	/**   
	 * @Title: findByNumeroIdentificacionAndEstado   
	   * @Description: TODO 
	 * @param: @param numeroIdentificacionCliente
	 * @param: @param upperCase
	 * @param: @return      
	 * @return: Cliente      
	 * @throws   
	 */
	
	public Cliente findByNumeroIdentificacionAndEstado(String numeroIdentificacion, String estado) throws Exception;

	/**   
	 * @Title: findByNumeroIdentificacion   
	   * @Description: TODO 
	 * @param: @param numeroIdentificacion
	 * @param: @return      
	 * @return: Cliente      
	 * @throws   
	 */
	
	public Cliente findByNumeroIdentificacion(String numeroIdentificacion);

	
}
