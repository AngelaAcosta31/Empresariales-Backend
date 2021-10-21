package co.edu.usbcali.viajesusb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.viajesusb.domain.Destino;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 * @ClassName:  DestinoRepository   
  * @Description: Repository_para_Destino   
 * @author: Ángela_Acosta    
 * @date:   31/08/2021 11:07:28 a. m.      
 * @Copyright:  USB
 */

public interface DestinoRepository extends JpaRepository<Destino, Long> {
	
	/**
	 * 
	 * @Title: findByTipoDestino_Codigo   
	   * @Description: consulta_todos_los_destinos_que_pertenece_a_un_tipo_de_destino
	 * @param: @param codigoTipoDestino
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: List<Destino>      
	 * @throws
	 */

	public List<Destino> findByTipoDestino_Codigo(String codigoTipoDestino) throws Exception;
	
	/**
	 * 
	 * @Title: findByEstado   
	   * @Description: Retorna_una_paginacion_de_la_lista_de_destinos_por_estado 
	 * @param: @param estado
	 * @param: @param pageable
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Page<Destino>      
	 * @throws
	 */
	
	public Page<Destino> findByEstado(String estado, Pageable pageable) throws Exception;
	
	public Destino findByCodigo(String codigo) throws Exception;

	/**   
	 * @Title: findByCodigoAndEstado   
	   * @Description: TODO 
	 * @param: @param upperCase
	 * @param: @param upperCase2
	 * @param: @return      
	 * @return: Destino      
	 * @throws   
	 */
	
	public Destino findByCodigoAndEstado(String codigo, String estado);
}
