/**  
 * @Title:  DestinoServiceImpl.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   7/09/2021 12:24:29 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;
import co.edu.usbcali.viajesusb.repository.DestinoRepository;
import co.edu.usbcali.viajesusb.utils.Constantes;
import co.edu.usbcali.viajesusb.utils.Utilities;

/**   
 * @ClassName:  DestinoServiceImpl   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   7/09/2021 12:24:29 p. m.      
 * @Copyright:  USB
 */
@Scope("singleton")
@Service
public class DestinoServiceImpl implements DestinoService {
	
	/**
	 * Inyeccion_de_dependencia
	 */
	@Autowired
	private DestinoRepository destinoRepository;
	@Autowired
	private TipoDestinoService tipoDestinoService;
	
	/**
	 * 
	 * @Title: findById   
	   * @Description: Consultar_destino_por_ID 
	 * @param: @param idDest
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Optional<Destino>      
	 * @throws
	 */
	public Destino findById(Long idDest) throws Exception{
		//validamos el id destino venga con info
		if(idDest ==null) {
			throw new Exception("Debe ingresar un ID destino");
			
		}
		if(!destinoRepository.findById(idDest).isPresent()) {
			throw new Exception ("El destino con id: "+idDest+ " no existe");
		}
		return destinoRepository.findById(idDest).get();
	}
	
	/**   
	 * <p>Title: findByTipoDestino_Codigo</p>   
	 * <p>Description: consulta_todos_los_destinos_que_pertenece_a_un_tipo_de_destino. </p>   
	 * @param codigoTipoDestino
	 * @return
	 * @throws SQLException   
	 * @see co.edu.usbcali.viajesusb.service.DestinoService#findByTipoDestino_Codigo(java.lang.String)   
	 */
	
	@Override
	public List<Destino> findByTipoDestinoCodigo(String codigoTipoDestino) throws Exception {
		List<Destino> listaDestino = null;
		if(codigoTipoDestino ==null || codigoTipoDestino.trim().equals("")) {
			throw new Exception("El codigo de tipo destino es obligatorio.");
		}
		if(Utilities.isStringLenght(codigoTipoDestino, Constantes.TAMANNOCODIGO)) {
			throw new Exception("El codigo no debe contener más de 5 caracteres.");
		}
		if(!Utilities.isStringInteger(codigoTipoDestino)) {
			throw new Exception("No debe contener números.");
		}		
		listaDestino = destinoRepository.findByTipoDestino_Codigo(codigoTipoDestino.toUpperCase());
		if(listaDestino.isEmpty()) {
			throw new Exception("No se han encontrado destinos con el codigo ingresado.");
		}
		return listaDestino;
	}
	
	/**   
	 * <p>Title: findByEstado</p>   
	 * <p>Description: Retorna_una_paginacion_de_la_lista_de_destinos_por_estado.
	 * </p>   
	 * @param estado
	 * @param pageable
	 * @return
	 * @throws SQLException   
	 * @see co.edu.usbcali.viajesusb.service.DestinoService#findByEstado(java.lang.String, org.springframework.data.domain.Pageable)   
	 */
	
	@Override
	public Page<Destino> findByEstado(String estado, Pageable pageable) throws Exception {
		Page<Destino> paginacionDestino = null;
		if(estado ==null || estado.trim().equals("")|| pageable ==null) {
			throw new Exception("Debe ingresar un estado.");
		}
		if(Utilities.isStringLenght(estado, Constantes.TAMANNOESTADO)) {
			throw new Exception("No debe contener más de un caracter.");
		}
		if(!Utilities.isStringInteger(estado)) {
			throw new Exception("No debe ser un número.");
		}
		paginacionDestino = destinoRepository.findByEstado(estado.toUpperCase(), pageable);
		if(paginacionDestino.isEmpty()) {
			throw new Exception("No se han encontrado destinos con el estado ingresado.");
		}
		return paginacionDestino;
	}
	

	
	/**   
	 * <p>Title: findByCodigo</p>   
	 * <p>Description: </p>   
	 * @param codigo
	 * @return
	 * @throws Exception   
	 * @see co.edu.usbcali.viajesusb.service.DestinoService#findByCodigo(java.lang.String)   
	 */
	
	@Override
	public Destino findByCodigo(String codigo) throws Exception {
		Destino destino = null;
		if(codigo==null || codigo.trim().equals("")) {
			throw new Exception("Debe ingresar un codigo.");
		}
		if(Utilities.isStringLenght(codigo, Constantes.TAMANNOCODIGO)) {
			throw new Exception("El codigo no debe contener más de 5 caracteres.");
		}
		if(!Utilities.isStringInteger(codigo)) {
			throw new Exception("El codigo no debe contener números.");
		}
		destino = destinoRepository.findByCodigo(codigo.toUpperCase());
		return destino;
	}
	
	@Override
	public Destino findByCodigoAndEstado(String codigo, String estado) throws Exception {
		Destino destino = null;
		if(codigo ==null || codigo.trim().equals("") || estado ==null || estado.trim().equals("")) {
			throw new Exception("Debe ingresar un codigo y un estado.");
		}
		if(Utilities.isStringLenght(codigo, Constantes.TAMANNOCODIGO)||Utilities.isStringLenght(estado, Constantes.TAMANNOESTADO)) {
			throw new Exception("El codigo no puede contener más de 5 caracteres y el estado un solo caracter.");
		}
		if(!Utilities.isStringInteger(codigo)||!Utilities.isStringInteger(estado)) {
			throw new Exception("Debe ingresar letras en los dos campos.");
		}
		destino = destinoRepository.findByCodigoAndEstado(codigo.toUpperCase(), estado.toUpperCase());
		if(destino == null) {
			throw new Exception("No se encontraron tipos de identificación con ese codigo y estado ingresados.");
		}
		return destino;
	}


	//TODO: CRUD
	
	/**
	 * 
	 * @Title: guardarDestino   
	   * @Description: Guardar_destino_en_la_base_de_datos
	 * @param: @param destino
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	@Override
	public Destino guardarDestino(DestinoDTO destinoDTO) throws Exception  {
		
		Destino destino = null;
		TipoDestino tipoDestino = null;
		
		// Aqui_van_validaciones
		Destino destinoDB = findByCodigo(destinoDTO.getCodigo().trim());
		if (destinoDB != null) {
			throw new Exception("Ya existe un destino con este codigo.");
		}
	
		if (destinoDTO.getAire() == null || destinoDTO.getAire().trim().equals("") ||
				Utilities.isStringLenght(destinoDTO.getAire(), Constantes.TAMANNOPCVIAJE)||
				!Utilities.isStringInteger(destinoDTO.getAire())) {
			throw new Exception("Debe ingresar una S o una N en este campo.");
		}
		if (destinoDTO.getTierra() == null || destinoDTO.getTierra().trim().equals("") ||
				Utilities.isStringLenght(destinoDTO.getTierra(), Constantes.TAMANNOPCVIAJE)||
				!Utilities.isStringInteger(destinoDTO.getTierra())) {
			throw new Exception("Debe ingresar una S o una N en este campo.");
		}

		if (destinoDTO.getMar() == null || destinoDTO.getMar().trim().equals("") ||
				Utilities.isStringLenght(destinoDTO.getMar(), Constantes.TAMANNOPCVIAJE)||
				!Utilities.isStringInteger(destinoDTO.getMar())) {
			throw new Exception("Debe ingresar una S o una N en este campo.");
		}
		if (destinoDTO.getCodigo() == null || destinoDTO.getCodigo().trim().equals("") ||
				Utilities.isStringLenght(destinoDTO.getCodigo(), Constantes.TAMANNOCODIGO)||
				!Utilities.isStringInteger(destinoDTO.getCodigo())) {
			throw new Exception("Debe ingresar un codigo valido.");
		}

		if (destinoDTO.getNombre() == null || destinoDTO.getNombre().trim().equals("")
				|| Utilities.isStringLenght(destinoDTO.getNombre(), Constantes.TAMANONOMBRE)) {
			throw new Exception("Digite un nombre valido.");
		}
		
		if (destinoDTO.getDescripcion() == null || destinoDTO.getDescripcion().trim().equals("")
				|| Utilities.isStringLenght(destinoDTO.getDescripcion(), Constantes.TAMANODESCRIPCION)) {
			throw new Exception("Digite una descripcion valida.");
		}
		if (destinoDTO.getEstado() == null || destinoDTO.getEstado().trim().equals("") || 
				Utilities.isStringLenght(destinoDTO.getEstado(), Constantes.TAMANNOESTADO) || 
				!Utilities.isStringInteger(destinoDTO.getEstado())) {
			throw new Exception("Estado invalido.");
		}
		
		if (destinoDTO.getFechaCreacion() == null) {
			throw new Exception("Debe ingresar una fecha de creacion valida.");
		}
		if (destinoDTO.getUsuCreador() == null || destinoDTO.getUsuCreador().trim().equals("") 
				||Utilities.isStringLenght(destinoDTO.getUsuCreador(), Constantes.TAMANOUSU)) {
			throw new Exception("Usuario creador invalido.");
		}
		
		// se_crea_el_destino
		destino = new Destino();
		
		destino.setAire(destinoDTO.getAire());
		destino.setTierra(destinoDTO.getTierra());
		destino.setMar(destinoDTO.getMar());
		destino.setCodigo(destinoDTO.getCodigo());
		destino.setNombre(destinoDTO.getNombre());
		destino.setDescripcion(destinoDTO.getDescripcion());
		destino.setEstado(destinoDTO.getEstado());
		destino.setFechaCreacion(destinoDTO.getFechaCreacion());
		destino.setUsuCreador(destinoDTO.getUsuCreador());
		
		// se_consulta_el_tipo_destino_dado_su_id
		tipoDestino = tipoDestinoService.findByCodigoAndEstado(destinoDTO.getCodigoTipoDestino(), Constantes.ACTIVO);
		
		// validamos_que_el_tipo_destino_exista_y_este_activo
		
		if(tipoDestino == null) {
			throw new Exception("El tipo destino " + destinoDTO.getCodigoTipoDestino() + " No existe");
		}
		destino.setTipoDestino(tipoDestino);
		destinoRepository.save(destino);
		return destino;
	}
	
	@Override
	public Destino actualizarDestino(DestinoDTO destinoDTO) throws Exception  {
		
		Destino destino = null;
		TipoDestino tipoDestino = null;
		
		
		// Aqui_van_validaciones
		
		if (destinoDTO.getIdDest() == null) {
			throw new Exception("Debe ingesar un id de destino valido para actualizar");
		}
		
		Optional <Destino> destinoDB = Optional.of(findById(destinoDTO.getIdDest()));
		
		if(destinoDB.isEmpty()) {
			throw new Exception("No existe un destino con este id.");
		}
		if (destinoDTO.getAire() == null || destinoDTO.getAire().trim().equals("") ||
				Utilities.isStringLenght(destinoDTO.getAire(), Constantes.TAMANNOPCVIAJE)||
				!Utilities.isStringInteger(destinoDTO.getAire())) {
			throw new Exception("Debe ingresar una S o una N en este campo.");
		}
		if (destinoDTO.getTierra() == null || destinoDTO.getTierra().trim().equals("") ||
				Utilities.isStringLenght(destinoDTO.getTierra(), Constantes.TAMANNOPCVIAJE)||
				!Utilities.isStringInteger(destinoDTO.getTierra())) {
			throw new Exception("Debe ingresar una S o una N en este campo.");
		}

		if (destinoDTO.getMar() == null || destinoDTO.getMar().trim().equals("") ||
				Utilities.isStringLenght(destinoDTO.getMar(), Constantes.TAMANNOPCVIAJE)||
				!Utilities.isStringInteger(destinoDTO.getMar())) {
			throw new Exception("Debe ingresar una S o una N en este campo.");
		}
		if (destinoDTO.getCodigo() == null || destinoDTO.getCodigo().trim().equals("") ||
				Utilities.isStringLenght(destinoDTO.getCodigo(), Constantes.TAMANNOCODIGO)||
				!Utilities.isStringInteger(destinoDTO.getCodigo())) {
			throw new Exception("Debe ingresar un codigo valido.");
		}

		if (destinoDTO.getNombre() == null || destinoDTO.getNombre().trim().equals("")
				|| Utilities.isStringLenght(destinoDTO.getNombre(), Constantes.TAMANONOMBRE)) {
			throw new Exception("Digite un nombre valido.");
		}
		
		if (destinoDTO.getDescripcion() == null || destinoDTO.getDescripcion().trim().equals("")
				|| Utilities.isStringLenght(destinoDTO.getDescripcion(), Constantes.TAMANODESCRIPCION)) {
			throw new Exception("Digite una descripcion valida.");
		}
		if (destinoDTO.getEstado() == null || destinoDTO.getEstado().trim().equals("") || 
				Utilities.isStringLenght(destinoDTO.getEstado(), Constantes.TAMANNOESTADO) || 
				!Utilities.isStringInteger(destinoDTO.getEstado())) {
			throw new Exception("Estado invalido.");
		}
		
		if (destinoDTO.getFechaCreacion() == null) {
			throw new Exception("Debe ingresar una fecha de creacion valida.");
		}
		if (destinoDTO.getUsuCreador() == null || destinoDTO.getUsuCreador().trim().equals("") 
				||Utilities.isStringLenght(destinoDTO.getUsuCreador(), Constantes.TAMANOUSU)) {
			throw new Exception("Usuario creador invalido.");
		}
		
		// se_busca_por_id
		destino = findById(destinoDTO.getIdDest());
		

		destino.setAire(destinoDTO.getAire());
		destino.setTierra(destinoDTO.getTierra());
		destino.setMar(destinoDTO.getMar());
		destino.setCodigo(destinoDTO.getCodigo());
		destino.setNombre(destinoDTO.getNombre());
		destino.setDescripcion(destinoDTO.getDescripcion());
		destino.setEstado(destinoDTO.getEstado());
		destino.setFechaCreacion(destinoDTO.getFechaCreacion());
		destino.setUsuCreador(destinoDTO.getUsuCreador());
		
		// se_consulta_el_tipo_destino_dado_su_id
		tipoDestino = tipoDestinoService.findByCodigoAndEstado(destinoDTO.getCodigoTipoDestino(), Constantes.ACTIVO);
		
		// validamos_que_el_tipo_destino_exista_y_este_activo
		
		if(tipoDestino == null) {
			throw new Exception("El tipo destino " + destinoDTO.getCodigoTipoDestino() + " No existe");
		}
		destino.setTipoDestino(tipoDestino);
		// se_guarda_el_objeto_de_destino
		destinoRepository.save(destino);
		return destino;
	}
	
	@Override
	public void eliminarDestino(Long idDestino) throws Exception{
		// validar que se ingrese el id destino a eliminar
		if(idDestino == null) {
			throw new Exception("El id destino es obligatorio");
		}
		if(destinoRepository.existsById(idDestino)==false) {
			throw new Exception("El destino no se encontro");
		}
		destinoRepository.findById(idDestino).ifPresent(destino->{
			if(destino.getDetallePlan().isEmpty()==false) {
				throw new RuntimeException("El destino esta asignado en un detalle plan, por lo que no se puede eliminar");
			}
			
		});

		destinoRepository.deleteById(idDestino);
	}


}
