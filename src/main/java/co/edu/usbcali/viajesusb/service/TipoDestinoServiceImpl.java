/**  
 * @Title:  TipoDestinoImpl.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   7/09/2021 11:53:26 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;
import co.edu.usbcali.viajesusb.repository.TipoDestinoRepository;
import co.edu.usbcali.viajesusb.utils.Constantes;
import co.edu.usbcali.viajesusb.utils.Utilities;


/**   
 * @ClassName:  TipoDestinoImpl   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   7/09/2021 11:53:26 a. m.      
 * @Copyright:  USB
 */
@Scope("singleton")
@Service
public class TipoDestinoServiceImpl implements TipoDestinoService {
	
	/**
	 * Inyeccion_de_dependencia
	 */
	@Autowired
	private TipoDestinoRepository tipoDestinoRepository;
	private TipoDestino tipoDestino = null; 
	
	public TipoDestino findById(Long idtide) throws Exception{
		//validamos el id tipo destino venga con info
		if(idtide ==null) {
			throw new Exception("Debe ingresar un ID tipo destino");	
		}
		if(!tipoDestinoRepository.findById(idtide).isPresent()) {
			throw new Exception ("El tipo destino con id: "+idtide+ " no existe");
		}
		return tipoDestinoRepository.findById(idtide).get();
	}
	/**   
	 * <p>Title: findByCodigo</p>   
	 * <p>Description: Test_para_la_consulta</p>   
	 * @param codigo
	 * @return
	 * @throws SQLException   
	 * @see co.edu.usbcali.viajesusb.service.TipoDestinoService#findByCodigo(java.lang.String)   
	 */
	
	@Override
	public TipoDestino findByCodigo(String codigo) throws Exception { 
		if(codigo==null || codigo.trim().equals("")) {
			throw new Exception("Debe ingresar un codigo.");
		}
		if(Utilities.isStringLenght(codigo, Constantes.TAMANNOCODIGO)) {
			throw new Exception("El codigo no debe contener más de 5 caracteres.");
		}
		if(!Utilities.isStringInteger(codigo)) {
			throw new Exception("El codigo no debe contener números.");
		}
		tipoDestino = tipoDestinoRepository.findByCodigo(codigo.toUpperCase());
		if(tipoDestino ==null) {
			throw new Exception("No se encontro un tipo destino con ese codigo.");
		}
		return tipoDestino;
	}
	
	/**
	 * 
	 * <p>Title: findByCodigoAndEstado</p>   
	 * <p>Description:Test_para_la_consulta </p>   
	 * @param codigo
	 * @param estado
	 * @return
	 * @throws SQLException   
	 * @see co.edu.usbcali.viajesusb.service.TipoDestinoService#findByCodigoAndEstado(java.lang.String, java.lang.String)
	 */
	
	@Override
	public TipoDestino findByCodigoAndEstado(String codigo,String estado) throws Exception {  
		if(codigo==null || codigo.trim().equals("") || estado == null || estado.trim().equals("")) {
			throw new Exception("Debe ingresar un codigo y un estado.");
		}
		if(Utilities.isStringLenght(codigo, Constantes.TAMANNOCODIGO)|| Utilities.isStringLenght(estado, Constantes.TAMANNOESTADO)) {
			throw new Exception("El codigo no debe contener más de 5 caracteres y el estado solo debe ser de un caracter.");
		}
		if(!Utilities.isStringInteger(codigo) || !Utilities.isStringInteger(estado)) {
			throw new Exception("El codigo y el estado no deben ser números.");
		}
		tipoDestino = tipoDestinoRepository.findByCodigoAndEstado(codigo.toUpperCase(), estado.toUpperCase());
		if(tipoDestino ==null) {
			throw new Exception("No se encontraron tipos de destino con ese codigo y estado.");
		}
		return tipoDestino;
	}
	
	
	@Override
	public List<TipoDestino> findByEstadoOrderByNombreDesc(String estado) throws Exception {  
		 List<TipoDestino> listaTipoDestino = null; 
		 if(estado == null || estado.trim().equals("")) {
				throw new Exception("Debe ingresar un estado.");
			}
			if(Utilities.isStringLenght(estado, Constantes.TAMANNOESTADO)) {
				throw new Exception("El estado solo debe ser un caracter.");
			}
			if(!Utilities.isStringInteger(estado)) {
				throw new Exception("El estado no debe ser un número.");
			}
		 listaTipoDestino = tipoDestinoRepository.findByEstadoOrderByNombreDesc(estado.toUpperCase());
		 if(listaTipoDestino.isEmpty()) {
				throw new Exception("No se encontraron tipos de destino con ese estado.");
			}
		return listaTipoDestino;
	}

	
	/**   
	 * <p>Title: findByNombre</p>   
	 * <p>Description:Test_para_la_consulta </p>   
	 * @param nombre
	 * @return
	 * @throws SQLException   
	 * @see co.edu.usbcali.viajesusb.service.TipoDestinoService#findByNombre(java.lang.String)   
	 */
	
	@Override
	public TipoDestino findByNombre(String nombre) throws Exception {
		if(nombre ==null || nombre.trim().equals("")) {
			throw new Exception("Debe ingresar un nombre valido.");
		}
		if(Utilities.isStringLenght(nombre, Constantes.TAMANONOMBRE)) {
			throw new Exception("Debe ingresar un nombre con menos de 100 caracteres.");
		}
		if(!Utilities.isStringInteger(nombre)) {
			throw new Exception("No se aceptan números.");
		}
		tipoDestino = tipoDestinoRepository.findByNombre(nombre.toUpperCase());	
		if(tipoDestino ==null) {
			throw new Exception("No se encontraron tipos de destino con ese nombre.");
		}
		return tipoDestino;
	}
 
	
	//TODO: CRUD

	@Override
	public TipoDestino guardarTipoDestino(TipoDestinoDTO tipoDestinoDTO) throws Exception{
		
		TipoDestino tipoDestino = null;
		//Validaciones
		TipoDestino tipoDestinoDB = tipoDestinoRepository.findByCodigo(tipoDestinoDTO.getCodigo().trim());
		if (tipoDestinoDB != null) {
			throw new Exception("Ya existe un destino con este codigo");
		}
		if (tipoDestinoDTO.getCodigo() == null || tipoDestinoDTO.getCodigo().trim().equals("") ||
				Utilities.isStringLenght(tipoDestinoDTO.getCodigo(), Constantes.TAMANNOCODIGO)||
				!Utilities.isStringInteger(tipoDestinoDTO.getCodigo())) {
			throw new Exception("Debe ingresar un codigo valido.");
		}
		if (tipoDestinoDTO.getNombre() == null || tipoDestinoDTO.getNombre().trim().equals("")
				|| Utilities.isStringLenght(tipoDestinoDTO.getNombre(), Constantes.TAMANONOMBRE)) {
			throw new Exception("Digite un nombre valido.");
		}
		if (tipoDestinoDTO.getDescripcion() == null || tipoDestinoDTO.getDescripcion().trim().equals("")
				|| Utilities.isStringLenght(tipoDestinoDTO.getDescripcion(), Constantes.TAMANODESCRIPCION)) {
			throw new Exception("Digite una descripcion valida.");
		}
		if (tipoDestinoDTO.getFechaCreacion() == null || (tipoDestinoDTO.getFechaCreacion().compareTo(new Date())>0)) {
			throw new Exception("Debe ingresar una fecha de creacion valida.");
		}
		if (tipoDestinoDTO.getUsuCreador() == null || tipoDestinoDTO.getUsuCreador().trim().equals("") 
				||Utilities.isStringLenght(tipoDestinoDTO.getUsuCreador(), Constantes.TAMANOUSU)) {
			throw new Exception("Usuario creador invalido.");
		}
		if (tipoDestinoDTO.getEstado() == null || tipoDestinoDTO.getEstado().trim().equals("") || 
				Utilities.isStringLenght(tipoDestinoDTO.getEstado(), Constantes.TAMANNOESTADO) || 
				!Utilities.isStringInteger(tipoDestinoDTO.getEstado())|| !Utilities.estadoAoI(tipoDestinoDTO.getEstado())) {
			throw new Exception("Estado invalido, debe ser A o I.");
		}
		
		// se crea el tipo de destino
		tipoDestino = new TipoDestino();
		
		tipoDestino.setCodigo(tipoDestinoDTO.getCodigo());
		tipoDestino.setNombre(tipoDestinoDTO.getNombre());
		tipoDestino.setDescripcion(tipoDestinoDTO.getDescripcion());
		tipoDestino.setFechaCreacion(tipoDestinoDTO.getFechaCreacion());
		tipoDestino.setFechaModificacion(tipoDestinoDTO.getFechaModificacion());
		tipoDestino.setUsuCreador(tipoDestinoDTO.getUsuCreador());
		tipoDestino.setUsuModificador(tipoDestinoDTO.getUsuModificador());
		tipoDestino.setEstado(tipoDestinoDTO.getEstado());
		
		tipoDestinoRepository.save(tipoDestino);
		return tipoDestino;
		
	}
	

	@Override
	public TipoDestino actualizarTipoDestino(TipoDestinoDTO tipoDestinoDTO) throws Exception{
		
		TipoDestino tipoDestino = null;
		// validaciones
		if (tipoDestinoDTO.getIdTide() == null) {
			throw new Exception("Debe ingesar un id de destino valido para actualizar");
		}
		
		Optional <TipoDestino> tipoDestinoDB = Optional.of(findById(tipoDestinoDTO.getIdTide()));
		
		if(tipoDestinoDB.isEmpty()) {
			throw new Exception("No existe un tipo destino con este id.");
		}
		if (tipoDestinoDTO.getCodigo() == null || tipoDestinoDTO.getCodigo().trim().equals("") ||
				Utilities.isStringLenght(tipoDestinoDTO.getCodigo(), Constantes.TAMANNOCODIGO)||
				!Utilities.isStringInteger(tipoDestinoDTO.getCodigo())) {
			throw new Exception("Debe ingresar un codigo valido.");
		}
		if (tipoDestinoDTO.getNombre() == null || tipoDestinoDTO.getNombre().trim().equals("")
				|| Utilities.isStringLenght(tipoDestinoDTO.getNombre(), Constantes.TAMANONOMBRE)) {
			throw new Exception("Digite un nombre valido.");
		}
		if (tipoDestinoDTO.getDescripcion() == null || tipoDestinoDTO.getDescripcion().trim().equals("")
				|| Utilities.isStringLenght(tipoDestinoDTO.getDescripcion(), Constantes.TAMANODESCRIPCION)) {
			throw new Exception("Digite una descripcion valida.");
		}
		if (tipoDestinoDTO.getFechaCreacion() == null) {
			throw new Exception("Debe ingresar una fecha de creacion valida.");
		}
		if (tipoDestinoDTO.getUsuCreador() == null || tipoDestinoDTO.getUsuCreador().trim().equals("") 
				||Utilities.isStringLenght(tipoDestinoDTO.getUsuCreador(), Constantes.TAMANOUSU)) {
			throw new Exception("Usuario creador invalido.");
		}
		if (tipoDestinoDTO.getEstado() == null || tipoDestinoDTO.getEstado().trim().equals("") || 
				Utilities.isStringLenght(tipoDestinoDTO.getEstado(), Constantes.TAMANNOESTADO) || 
				!Utilities.isStringInteger(tipoDestinoDTO.getEstado())|| !Utilities.estadoAoI(tipoDestinoDTO.getEstado())) {
			throw new Exception("Estado invalido, debe ser A o I.");
		}
		
		tipoDestino = findById(tipoDestinoDTO.getIdTide());
		
		tipoDestino.setCodigo(tipoDestinoDTO.getCodigo());
		tipoDestino.setNombre(tipoDestinoDTO.getNombre());
		tipoDestino.setDescripcion(tipoDestinoDTO.getDescripcion());
		tipoDestino.setFechaCreacion(tipoDestinoDTO.getFechaCreacion());
		tipoDestino.setFechaModificacion(tipoDestinoDTO.getFechaModificacion());
		tipoDestino.setUsuCreador(tipoDestinoDTO.getUsuCreador());
		tipoDestino.setUsuModificador(tipoDestinoDTO.getUsuModificador());
		tipoDestino.setEstado(tipoDestinoDTO.getEstado());
		
		tipoDestinoRepository.save(tipoDestino);
		return tipoDestino;
		
	}
	

	@Override
	public void eliminarTipoDestino(Long idTipoDestino) throws Exception{
		// validar que se ingrese el id tipo destino a eliminar
		if(idTipoDestino == null) {
			throw new Exception("El id tipo destino es obligatorio");
		}
		if(tipoDestinoRepository.existsById(idTipoDestino)==false) {
			throw new Exception("El tipo destino no se encontro");
		}
		tipoDestinoRepository.findById(idTipoDestino).ifPresent(tipoDestino->{
			if(tipoDestino.getDestino().isEmpty()==false) {
				throw new RuntimeException("El tipo destino esta asignado en un destino, por lo que no se puede eliminar");
			}
			
		});

		tipoDestinoRepository.deleteById(idTipoDestino);
	}

}
