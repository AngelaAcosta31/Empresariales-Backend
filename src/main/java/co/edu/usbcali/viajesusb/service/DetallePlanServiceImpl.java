/**  
 * @Title:  DetallePlanServiceImpl.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Ángela_Acosta    
 * @date:   8/09/2021 10:13:17 p. m.   
 * @version V1.0 
 * @Copyright: Universidad_San_de_Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.domain.DetallePlan;
import co.edu.usbcali.viajesusb.domain.Plan;
import co.edu.usbcali.viajesusb.dto.DetallePlanDTO;
import co.edu.usbcali.viajesusb.repository.DetallePlanRepository;
import co.edu.usbcali.viajesusb.utils.Constantes;
import co.edu.usbcali.viajesusb.utils.Utilities;

/**   
 * @ClassName:  DetallePlanServiceImpl   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   8/09/2021 10:13:17 p. m.      
 * @Copyright:  USB
 */
@Scope("singleton")
@Service
public class DetallePlanServiceImpl implements DetallePlanService {
	
	@Autowired
	private DetallePlanRepository detallePlanRepository;
	@Autowired
	private PlanService planService; 
	@Autowired
	private DestinoService destinoService;
	
	public DetallePlan findById(Long idDepl) throws Exception{
		//validamos el id detalle plan venga con info
		if(idDepl ==null) {
			throw new Exception("Debe ingresar un ID detalle plan");	
		}
		if(!detallePlanRepository.findById(idDepl).isPresent()) {
			throw new Exception ("El detalle plan con id: "+idDepl+ " no existe");
		}
		return detallePlanRepository.findById(idDepl).get();
	}
	//TODO:CRUD
	@Override
	public DetallePlan guardarDetallePlan(DetallePlanDTO detallePlanDTO) throws Exception{
		
		DetallePlan detallePlan =null;
		Plan plan = null;
		Destino destino = null;
		//Validaciones

		if (detallePlanDTO.getAlimentacion() == null || detallePlanDTO.getAlimentacion().trim().equals("")
				|| Utilities.isStringLenght(detallePlanDTO.getAlimentacion(), Constantes.TAMANNOPCVIAJE)||
				!Utilities.isStringInteger(detallePlanDTO.getAlimentacion())) {
			throw new Exception("Campo de alimentacion es invalido");
		}
		if (detallePlanDTO.getHospedaje() == null || detallePlanDTO.getHospedaje().trim().equals("")
				|| Utilities.isStringLenght(detallePlanDTO.getHospedaje(), Constantes.TAMANNOPCVIAJE)||
				!Utilities.isStringInteger(detallePlanDTO.getHospedaje())) {
			throw new Exception("Campo de hospedaje es invalido");
		}
		if (detallePlanDTO.getTransporte() == null || detallePlanDTO.getTransporte().trim().equals("")
				|| Utilities.isStringLenght(detallePlanDTO.getTransporte(), Constantes.TAMANNOPCVIAJE)||
				!Utilities.isStringInteger(detallePlanDTO.getTransporte())) {
			throw new Exception("Campo de transporte es invalido");
		}
		if (detallePlanDTO.getTraslados() == null || detallePlanDTO.getTraslados().trim().equals("")
				|| Utilities.isStringLenght(detallePlanDTO.getTraslados(), Constantes.TAMANNOPCVIAJE)||
				!Utilities.isStringInteger(detallePlanDTO.getTraslados())) {
			throw new Exception("Campo de traslados es invalido");
		}
		if (detallePlanDTO.getValor() <= 0.0) {
			throw new Exception("Valor no puede ser menor o igual a 0");
		}
		if (detallePlanDTO.getCantidadNoches() <= 0) {
			throw new Exception("Cantidad de noches no puede ser menor o igual a 0");
		}
		if (detallePlanDTO.getCantidadDias() <= 0) {
			throw new Exception("Cantidad de días no puede ser menor o igual a 0");
		}
		if (detallePlanDTO.getCantidadNoches() > detallePlanDTO.getCantidadDias()) {
			throw new Exception("La cantidad de noches no puede ser mayor a la cantidad de dias.");
		}
		if (detallePlanDTO.getFechaCreacion() == null) {
			throw new Exception("Debe ingresar una fecha valida.");
		}
		if (detallePlanDTO.getUsuCreador() == null || detallePlanDTO.getUsuCreador().trim().equals("") 
				||Utilities.isStringLenght(detallePlanDTO.getUsuCreador(), Constantes.TAMANOUSU)) {
			throw new Exception("Usuario creador invalido.");
		}
		if (detallePlanDTO.getEstado() == null || detallePlanDTO.getEstado().trim().equals("") || 
				Utilities.isStringLenght(detallePlanDTO.getEstado(), Constantes.TAMANNOESTADO) || 
				!Utilities.isStringInteger(detallePlanDTO.getEstado())) {
			throw new Exception("Estado invalido.");
		}
		// se crea el detalle plan
		detallePlan = new DetallePlan(); 
		
		detallePlan.setAlimentacion(detallePlanDTO.getAlimentacion());
		detallePlan.setHospedaje(detallePlanDTO.getHospedaje());
		detallePlan.setTransporte(detallePlanDTO.getTransporte());
		detallePlan.setTraslados(detallePlanDTO.getTraslados());
		detallePlan.setValor(detallePlanDTO.getValor());
		detallePlan.setCantidadNoches(detallePlanDTO.getCantidadNoches());
		detallePlan.setCantidadDias(detallePlanDTO.getCantidadDias());
		detallePlan.setFechaCreacion(detallePlanDTO.getFechaCreacion());
		detallePlan.setFechaModificacion(detallePlanDTO.getFechaModificacion());
		detallePlan.setUsuCreador(detallePlanDTO.getUsuCreador());
		detallePlan.setUsuModificador(detallePlanDTO.getUsuModificador());
		detallePlan.setEstado(detallePlanDTO.getEstado());
		
		//TODO: Consultar el plan y el destino
		plan = planService.findByCodigoAndEstado(detallePlanDTO.getCodigoPlan(),Constantes.ACTIVO);
		if(plan == null) {
			throw new Exception("El plan " + detallePlanDTO.getCodigoPlan()+ " No existe");
		}
		detallePlan.setPlan(plan);
		destino = destinoService.findByCodigoAndEstado(detallePlanDTO.getCodigoDestino(), Constantes.ACTIVO);
		if( destino == null) {
			throw new Exception( "El destino "+ detallePlanDTO.getCodigoDestino() +" No existe");
		}
		detallePlan.setDestino(destino);
		detallePlanRepository.save(detallePlan);
		
		return detallePlan;
			
	}

	@Override
	public DetallePlan actualizarDetallePlan(DetallePlanDTO detallePlanDTO) throws Exception{
		
		DetallePlan detallePlan =null;
		Plan plan = null;
		Destino destino = null;
		
		//Validaciones

		if (detallePlanDTO.getIdDetallePlan() == null) {
			throw new Exception("Debe ingesar un id de detalle valido para actualizar");
		}
		
		Optional <DetallePlan> detalleDB = detallePlanRepository.findById(detallePlanDTO.getIdDetallePlan());
		if(detalleDB.isEmpty()) {
			throw new Exception("No existe un detalle con este id");
		}
		if (detallePlanDTO.getAlimentacion() == null || detallePlanDTO.getAlimentacion().trim().equals("")
				|| Utilities.isStringLenght(detallePlanDTO.getAlimentacion(), Constantes.TAMANNOPCVIAJE)||
				!Utilities.isStringInteger(detallePlanDTO.getAlimentacion())) {
			throw new Exception("Campo de alimentacion es invalido");
		}
		if (detallePlanDTO.getHospedaje() == null || detallePlanDTO.getHospedaje().trim().equals("")
				|| Utilities.isStringLenght(detallePlanDTO.getHospedaje(), Constantes.TAMANNOPCVIAJE)||
				!Utilities.isStringInteger(detallePlanDTO.getHospedaje())) {
			throw new Exception("Campo de hospedaje es invalido");
		}
		if (detallePlanDTO.getTransporte() == null || detallePlanDTO.getTransporte().trim().equals("")
				|| Utilities.isStringLenght(detallePlanDTO.getTransporte(), Constantes.TAMANNOPCVIAJE)||
				!Utilities.isStringInteger(detallePlanDTO.getTransporte())) {
			throw new Exception("Campo de transporte es invalido");
		}
		if (detallePlanDTO.getTraslados() == null || detallePlanDTO.getTraslados().trim().equals("")
				|| Utilities.isStringLenght(detallePlanDTO.getTraslados(), Constantes.TAMANNOPCVIAJE)||
				!Utilities.isStringInteger(detallePlanDTO.getTraslados())) {
			throw new Exception("Campo de traslados es invalido");
		}
		if (detallePlanDTO.getValor() <= 0.0) {
			throw new Exception("Valor no puede ser menor o igual a 0");
		}
		if (detallePlanDTO.getCantidadNoches() <= 0) {
			throw new Exception("Cantidad de noches no puede ser menor o igual a 0");
		}
		if (detallePlanDTO.getCantidadDias() <= 0) {
			throw new Exception("Cantidad de días no puede ser menor o igual a 0");
		}
		if (detallePlanDTO.getCantidadNoches() > detallePlanDTO.getCantidadDias()) {
			throw new Exception("La cantidad de noches no puede ser mayor a la cantidad de dias.");
		}
		if (detallePlanDTO.getFechaCreacion() == null) {
			throw new Exception("Debe ingresar una fecha de creacion valida.");
		}
		if (detallePlanDTO.getUsuCreador() == null || detallePlanDTO.getUsuCreador().trim().equals("") 
				||Utilities.isStringLenght(detallePlanDTO.getUsuCreador(), Constantes.TAMANOUSU)) {
			throw new Exception("Usuario creador invalido.");
		}
		if (detallePlanDTO.getEstado() == null || detallePlanDTO.getEstado().trim().equals("") || 
				Utilities.isStringLenght(detallePlanDTO.getEstado(), Constantes.TAMANNOESTADO) || 
				!Utilities.isStringInteger(detallePlanDTO.getEstado())) {
			throw new Exception("Estado invalido.");
		}
		//SE BUSCA EL ID
		detallePlan = findById(detallePlanDTO.getIdDetallePlan()); 
		
		detallePlan.setAlimentacion(detallePlanDTO.getAlimentacion());
		detallePlan.setHospedaje(detallePlanDTO.getHospedaje());
		detallePlan.setTransporte(detallePlanDTO.getTransporte());
		detallePlan.setTraslados(detallePlanDTO.getTraslados());
		detallePlan.setValor(detallePlanDTO.getValor());
		detallePlan.setCantidadNoches(detallePlanDTO.getCantidadNoches());
		detallePlan.setCantidadDias(detallePlanDTO.getCantidadDias());
		detallePlan.setFechaCreacion(detallePlanDTO.getFechaCreacion());
		detallePlan.setFechaModificacion(detallePlanDTO.getFechaModificacion());
		detallePlan.setUsuCreador(detallePlanDTO.getUsuCreador());
		detallePlan.setUsuModificador(detallePlanDTO.getUsuModificador());
		detallePlan.setEstado(detallePlanDTO.getEstado());
		
		//TODO: Consultar el plan y el destino
		plan = planService.findByCodigoAndEstado(detallePlanDTO.getCodigoPlan(),Constantes.ACTIVO);
		if(plan == null) {
			throw new Exception("El plan " + detallePlanDTO.getCodigoPlan()+ " No existe");
		}
		detallePlan.setPlan(plan);
		destino = destinoService.findByCodigoAndEstado(detallePlanDTO.getCodigoDestino(), Constantes.ACTIVO);
		if( destino == null) {
			throw new Exception( "El destino "+ detallePlanDTO.getCodigoDestino() +" No existe");
		}
		detallePlan.setDestino(destino);
		detallePlanRepository.save(detallePlan);
		
		return detallePlan;
	}
	
	/**   
	 * <p>Title: eliminarDetallePlan</p>   
	 * <p>Description: </p>   
	 * @param detallePlanDTO   
	 * @see co.edu.usbcali.viajesusb.service.DetallePlanService#eliminarDetallePlan(co.edu.usbcali.viajesusb.dto.DetallePlanDTO)   
	 */
	
	@Override
	public void eliminarDetallePlan(Long idDetallePlan) throws Exception{
		// validar que el ID ingresado exista
		if(idDetallePlan == null) {
			throw new Exception("El id detalle plan es obligatorio");
		}
		Optional<DetallePlan> detallePlanDB = detallePlanRepository.findById(idDetallePlan);
		if(detallePlanDB.isPresent()) {
			detallePlanRepository.delete(detallePlanDB.get());
		}else {
			throw new Exception("El detalle plan no se encontro");
		}
	}

}
