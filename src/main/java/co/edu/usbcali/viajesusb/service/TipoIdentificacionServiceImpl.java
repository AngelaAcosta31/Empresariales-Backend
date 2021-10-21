/**  
 * @Title:  TipoIdentificacionServiceImpl.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   8/09/2021 10:13:59 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;
import co.edu.usbcali.viajesusb.repository.TipoIdentificacionRepository;
import co.edu.usbcali.viajesusb.utils.Constantes;
import co.edu.usbcali.viajesusb.utils.Utilities;

/**   
 * @ClassName:  TipoIdentificacionServiceImpl   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   8/09/2021 10:13:59 p. m.      
 * @Copyright:  USB
 */
@Scope("singleton")
@Service
public class TipoIdentificacionServiceImpl implements TipoIdentificacionService {

	
	/**
	 * Inyeccion_de_dependencia
	 */
	@Autowired
	private TipoIdentificacionRepository tipoIdentificacionRepository;
	private TipoIdentificacion tipoIdentificacion = null;
	
	public TipoIdentificacion findById(Long idtiid) throws Exception{
		//validamos el id tipo identificacion venga con info
		if(idtiid ==null) {
			throw new Exception("Debe ingresar un ID de tipo identificacion");
			
		}
		if(!tipoIdentificacionRepository.findById(idtiid).isPresent()) {
			throw new Exception ("El tipo de identificacion con id: "+idtiid+ " no existe");
		}
		return tipoIdentificacionRepository.findById(idtiid).get();
	}
	
	/**   
	 * <p>Title: findByCodigo</p>   
	 * <p>Description: </p>   
	 * @param codigo
	 * @return
	 * @throws SQLException   
	 * @see co.edu.usbcali.viajesusb.service.TipoDestinoService#findByCodigo(java.lang.String)   
	 */
	
	@Override
	public List<TipoIdentificacion> findByEstadoOrderByNombreAsc(String estado) throws Exception {
		List<TipoIdentificacion> listaTipoIdentificacion = null; 
		
		if(estado ==null || estado.trim().equals("")) {
			throw new Exception("Debe ingresar un estado.");
		}
		if(Utilities.isStringLenght(estado, Constantes.TAMANNOESTADO)) {
			throw new Exception("Debe ingresar un solo caracter.");
		}
		if(!Utilities.isStringInteger(estado)) {
			throw new Exception("Debe ingresar solo una letra.");
		}
		
		listaTipoIdentificacion = tipoIdentificacionRepository.findByEstadoOrderByNombreAsc(estado.toUpperCase());
		if(listaTipoIdentificacion.isEmpty()) {
			throw new Exception("No se encontraron tipos de identificación con ese estado.");
		}
		
		return listaTipoIdentificacion;
	}

	
	/**   
	 * <p>Title: findByCodigoAndEstado</p>   
	 * <p>Description: </p>   
	 * @param codigo
	 * @param estado
	 * @return
	 * @throws SQLException   
	 * @see co.edu.usbcali.viajesusb.service.TipoDestinoService#findByCodigoAndEstado(java.lang.String, java.lang.String)   
	 */
	
	@Override
	public TipoIdentificacion findByCodigoAndEstado(String codigo, String estado) throws Exception {
		if(codigo ==null || codigo.trim().equals("") || estado ==null || estado.trim().equals("")) {
			throw new Exception("Debe ingresar un codigo y un estado.");
		}
		if(Utilities.isStringLenght(codigo, Constantes.TAMANNOCODIGO)||Utilities.isStringLenght(estado, Constantes.TAMANNOESTADO)) {
			throw new Exception("El codigo no puede contener más de 5 caracteres y el estado un solo caracter.");
		}
		if(!Utilities.isStringInteger(codigo)||!Utilities.isStringInteger(estado)) {
			throw new Exception("Debe ingresar letras y no números.");
		}
		
		tipoIdentificacion = tipoIdentificacionRepository.findByCodigoAndEstado(codigo.toUpperCase(), estado.toUpperCase());
		if(tipoIdentificacion == null) {
			throw new Exception("No se encontraron tipos de identificación con ese codigo y estado.");
		}
		return tipoIdentificacion;
	}



	
	/**   
	 * <p>Title: consultarTipoIdentificacionPorCodigo</p>   
	 * <p>Description: </p>   
	 * @param codigo
	 * @return
	 * @throws Exception   
	 * @see co.edu.usbcali.viajesusb.service.TipoIdentificacionService#consultarTipoIdentificacionPorCodigo(java.lang.String)   
	 */
	
	@Override
	public TipoIdentificacion findByCodigo(String codigo) throws Exception {
		// TODO Auto-generated method stub
		TipoIdentificacion tipoIdentificacion = null;
		if(codigo == null || codigo.trim().equals("")) {
			throw new Exception("Debe ingresar un codigo");
		}
		if(Utilities.isStringLenght(codigo, Constantes.TAMANNOCODIGO)) {
			throw new Exception("Debe ingresar un codigo con 5 caracteres o menos.");
		}
		if(!Utilities.isStringInteger(codigo)) {
			throw new Exception("No debe contener numeros.");
		}
		tipoIdentificacion = tipoIdentificacionRepository.findByCodigo(codigo.toUpperCase());
//		if(tipoIdentificacion != null) {
//			throw new Exception("No se encontraron tipos de identificación con ese codigo.");
//		}
		return tipoIdentificacion;
	}
	
	//TODO: CRUD
	@Override
	public TipoIdentificacion guardarTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacionDTO) throws Exception{
		
		TipoIdentificacion tipoIdentificacion = null;
		//VALIDACIONES
		TipoIdentificacion tipoIdentificacionDB = findByCodigo(tipoIdentificacionDTO.getCodigo().trim());
		if (tipoIdentificacionDB != null) {
			throw new Exception("Ya existe un tipo de identificacion con este codigo.");
		}
		if (tipoIdentificacionDTO.getCodigo() == null || tipoIdentificacionDTO.getCodigo().trim().equals("") ||
				Utilities.isStringLenght(tipoIdentificacionDTO.getCodigo(), Constantes.TAMANNOCODIGO)||
				!Utilities.isStringInteger(tipoIdentificacionDTO.getCodigo())) {
			throw new Exception("Debe ingresar un codigo valido.");
		}
		if (tipoIdentificacionDTO.getNombre() == null || tipoIdentificacionDTO.getNombre().trim().equals("")
				|| Utilities.isStringLenght(tipoIdentificacionDTO.getNombre(), Constantes.TAMANONOMBRE)) {
			throw new Exception("Digite un nombre valido.");
		}
		if (tipoIdentificacionDTO.getFechaCreacion() == null) {
			throw new Exception("Debe ingresar una fecha valida.");
		}
		if (tipoIdentificacionDTO.getUsuCreador() == null || tipoIdentificacionDTO.getUsuCreador().trim().equals("") 
				||Utilities.isStringLenght(tipoIdentificacionDTO.getUsuCreador(), Constantes.TAMANOUSU)) {
			throw new Exception("Usuario creador invalido.");
		}
		if (tipoIdentificacionDTO.getEstado() == null || tipoIdentificacionDTO.getEstado().trim().equals("") || 
				Utilities.isStringLenght(tipoIdentificacionDTO.getEstado(), Constantes.TAMANNOESTADO) || 
				!Utilities.isStringInteger(tipoIdentificacionDTO.getEstado())) {
			throw new Exception("Estado invalido.");
		}
		
		
		// se crea el tipo de identificacion
		tipoIdentificacion = new TipoIdentificacion();
		
		tipoIdentificacion.setCodigo(tipoIdentificacionDTO.getCodigo());
		tipoIdentificacion.setNombre(tipoIdentificacionDTO.getNombre());
		tipoIdentificacion.setFechaCreacion(tipoIdentificacionDTO.getFechaCreacion());
		tipoIdentificacion.setUsuCreador(tipoIdentificacionDTO.getUsuCreador());
		tipoIdentificacion.setEstado(tipoIdentificacionDTO.getEstado());
		
		tipoIdentificacionRepository.save(tipoIdentificacion);
		return tipoIdentificacion;
	}

	@Override
	public TipoIdentificacion actualizarTipoIdentificacion(TipoIdentificacionDTO tipoIdentificacionDTO) throws Exception{
		
		TipoIdentificacion tipoIdentificacion = null;
		//validaciones
		 if (tipoIdentificacionDTO.getIdTipoIdentificacion() == null) {
				throw new Exception("Debe ingresar un id de cliente valido para actualizar.");
				
			}
		    
		    Optional <TipoIdentificacion> tipoIdentificacionDB = Optional.of(findById(tipoIdentificacionDTO.getIdTipoIdentificacion()));
		    
		    if (tipoIdentificacionDB.isEmpty()) {
				throw new Exception("No existe un cliente con ese Id.");
			}
		if (tipoIdentificacionDTO.getCodigo() == null || tipoIdentificacionDTO.getCodigo().trim().equals("") ||
				Utilities.isStringLenght(tipoIdentificacionDTO.getCodigo(), Constantes.TAMANNOCODIGO)||
				!Utilities.isStringInteger(tipoIdentificacionDTO.getCodigo())) {
			throw new Exception("Debe ingresar un codigo valido.");
		}
		if (tipoIdentificacionDTO.getNombre() == null || tipoIdentificacionDTO.getNombre().trim().equals("")
				|| Utilities.isStringLenght(tipoIdentificacionDTO.getNombre(), Constantes.TAMANONOMBRE)) {
			throw new Exception("Digite un nombre valido.");
		}
		if (tipoIdentificacionDTO.getFechaCreacion() == null) {
			throw new Exception("Debe ingresar una fecha valida.");
		}
		if (tipoIdentificacionDTO.getUsuCreador() == null || tipoIdentificacionDTO.getUsuCreador().trim().equals("") 
				||Utilities.isStringLenght(tipoIdentificacionDTO.getUsuCreador(), Constantes.TAMANOUSU)) {
			throw new Exception("Usuario creador invalido.");
		}
		if (tipoIdentificacionDTO.getEstado() == null || tipoIdentificacionDTO.getEstado().trim().equals("") || 
				Utilities.isStringLenght(tipoIdentificacionDTO.getEstado(), Constantes.TAMANNOESTADO) || 
				!Utilities.isStringInteger(tipoIdentificacionDTO.getEstado())) {
			throw new Exception("Estado invalido.");
		}
		
		tipoIdentificacion = findById(tipoIdentificacionDTO.getIdTipoIdentificacion());
		
		tipoIdentificacion.setCodigo(tipoIdentificacionDTO.getCodigo());
		tipoIdentificacion.setNombre(tipoIdentificacionDTO.getNombre());
		tipoIdentificacion.setFechaCreacion(tipoIdentificacionDTO.getFechaCreacion());
		tipoIdentificacion.setUsuCreador(tipoIdentificacionDTO.getUsuCreador());
		tipoIdentificacion.setEstado(tipoIdentificacionDTO.getEstado());
		
		tipoIdentificacionRepository.save(tipoIdentificacion);
		return tipoIdentificacion;
	}
	

	@Override
	public void eliminarTipoIdentificacion(Long idTipoIdentificacion) throws Exception{
		// validar que se ingrese el id Tipo Identificacion a eliminar
		if(idTipoIdentificacion== null ) {
			throw new Exception("El id destino es obligatorio");
		}
		if(tipoIdentificacionRepository.existsById(idTipoIdentificacion)==false) {
			throw new Exception("El tipo identificacion no se encontro");
		}
		tipoIdentificacionRepository.findById(idTipoIdentificacion).ifPresent(tipoIdentificacion->{
			if(tipoIdentificacion.getCliente().isEmpty()==false) {
				throw new RuntimeException("El tipo de identificacion esta asignado en un cliente, por lo que no se puede eliminar");
			}
			
		});

		tipoIdentificacionRepository.deleteById(idTipoIdentificacion);
	}
	
}
