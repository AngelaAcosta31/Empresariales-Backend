/**  
 * @Title:  PlanServiceImpl.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Ángela_Acosta    
 * @date:   8/09/2021 10:13:35 p. m.   
 * @version V1.0 
 * @Copyright: Universidad_San_de_Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.domain.Plan;
import co.edu.usbcali.viajesusb.domain.Usuario;
import co.edu.usbcali.viajesusb.dto.PlanDTO;
import co.edu.usbcali.viajesusb.repository.PlanRepository;
import co.edu.usbcali.viajesusb.utils.Constantes;
import co.edu.usbcali.viajesusb.utils.Utilities;

/**   
 * @ClassName:  PlanServiceImpl   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   8/09/2021 10:13:35 p. m.      
 * @Copyright:  USB
 */
@Scope("singleton")
@Service
public class PlanServiceImpl implements PlanService{
	
	@Autowired
	private PlanRepository planRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	private Plan planes = null;
	
	public Plan findById(Long idPlan) throws Exception{
		//validamos el id plan venga con info
		if(idPlan ==null) {
			throw new Exception("Debe ingresar un ID plan");
			
		}
		if(!planRepository.findById(idPlan).isPresent()) {
			throw new Exception ("El plan con id: "+idPlan+ " no existe");
		}
		return planRepository.findById(idPlan).get();
	}
	@Override
	public Plan findByCodigo(String codigo) throws Exception {
		if(codigo == null || codigo.trim().equals("")) {
			throw new Exception("Debe ingresar un codigo valido.");
		}
		if(Utilities.isStringLenght(codigo, Constantes.TAMANNOCODIGO)) {
			throw new Exception("El codigo solo debe tener maximo 5 caracteres.");
		}
		if(!Utilities.isStringInteger(codigo)) {
			throw new Exception("El codigo no deben contener números.");
		}
		planes = planRepository.findByCodigo(codigo.toUpperCase());
		return planes;
	}
	/**   
	 * <p>Title: findByCodigo</p>   
	 * <p>Description: </p>   
	 * @param codigo
	 * @return
	 * @throws SQLException   
	 * @see co.edu.usbcali.viajesusb.service.PlanService#findByCodigo(java.lang.String)   
	 */
	
	@Override
	public Plan findByCodigoAndEstado(String codigo, String estado) throws Exception {
		if(codigo ==null || codigo.trim().equals("") || estado ==null || estado.trim().equals("")) {
			throw new Exception("Debe ingresar un codigo y un estado.");
		}
		if(Utilities.isStringLenght(codigo, Constantes.TAMANNOCODIGO)||Utilities.isStringLenght(estado, Constantes.TAMANNOESTADO)) {
			throw new Exception("El codigo no puede contener más de 5 caracteres y el estado un solo caracter.");
		}
		if(!Utilities.isStringInteger(codigo)||!Utilities.isStringInteger(estado)) {
			throw new Exception("Debe ingresar en los dos campos solo letras.");
		}
		planes = planRepository.findByCodigoAndEstado(codigo.toUpperCase(), estado.toUpperCase());
		if(planes == null) {
			throw new Exception("No se encontraron tipos de identificación con ese codigo y estado ingresados.");
		}
		return planes;
	}
	
	@Override
	public List<Plan> findByValorTotal(Double valorTotal) throws Exception {
		List<Plan> listaPlan = null;
		if(valorTotal==null) {
			throw new Exception("Debe ingresar un valor");
		}
		if(valorTotal <0) {
			throw new Exception("Debe ingresar valores positivos.");
		}
		listaPlan = planRepository.findByValorTotal(valorTotal);
		if(listaPlan.isEmpty()) {
			throw new Exception("No se encontraron planes con ese valor.");
		}
		return listaPlan;
	}
	
	@Override
	public List<Plan> findByCantidadPersonas(Integer personas) throws Exception {
		List<Plan> listaPlan = null;
		if(personas <=0) {
			throw new Exception("Debe ingresar valores positivos y mayores que 0.");
		}
		listaPlan = planRepository.findByCantidadPersonas(personas);
		if(listaPlan.isEmpty()) {
			throw new Exception("No se encontraron planes con esa cantidad de personas.");
		}
		return listaPlan;
	}
	
	
	//TODO:CRUD
	@Override
	public Plan guardarPlan(PlanDTO planDTO) throws Exception{
		
		Plan plan = null;
		Cliente cliente = null;
		Usuario usuario = null;
		//Validaciones
		
		Plan planDB = findByCodigo(planDTO.getCodigo().trim());
		if (planDB != null) {
			throw new Exception("Ya existe un plan con este codigo.");
		}
		if (planDTO.getCodigo() == null || planDTO.getCodigo().trim().equals("") ||
				Utilities.isStringLenght(planDTO.getCodigo(), Constantes.TAMANNOCODIGO)||
				!Utilities.isStringInteger(planDTO.getCodigo())) {
			throw new Exception("Debe ingresar un codigo valido.");
		}

		if (planDTO.getNombre() == null || planDTO.getNombre().trim().equals("")
				|| Utilities.isStringLenght(planDTO.getNombre(), Constantes.TAMANONOMBRE)) {
			throw new Exception("Digite un nombre valido.");
		}
		if (planDTO.getDescripcionSolicitud() == null || planDTO.getDescripcionSolicitud().trim().equals("")
				|| Utilities.isStringLenght(planDTO.getDescripcionSolicitud(), Constantes.TAMANODESCRIPCION)) {
			throw new Exception("Digite una descripcion valida.");
		}
		if (planDTO.getCantidadPersonas() <= 0) {
			throw new Exception("Cantidad de  no puede ser menor o igual a 0");
		}
		if (planDTO.getFechaSolicitud() == null) {
			throw new Exception("Debe ingresar una fecha de solicitud valida.");
		}
		if (planDTO.getFechaInicioViaje() == null) {
			throw new Exception("Debe ingresar una fecha de inicio viaje valida.");
		}
		if (planDTO.getFechaFinViaje() == null) {
			throw new Exception("Debe ingresar una fecha de fin de viaje valida.");
		}
		if (planDTO.getFechaInicioViaje().compareTo(planDTO.getFechaSolicitud())<=0) {
			throw new Exception("Debe ingresar una fecha valida, la fecha de solicitud no puede ser despues de la fecha de viaje.");
		}
		if (planDTO.getFechaFinViaje().compareTo(planDTO.getFechaInicioViaje())<0) {
			throw new Exception("Debe ingresar una fecha valida, la fecha de inicial del viaje no puede ser despues de la fecha final.");
		}
		if(planDTO.getValorTotal() == null || planDTO.getValorTotal()<0.0) {
			throw new Exception("Ingrese un valor total valido");
		}
		if (planDTO.getFechaCreacion() == null) {
			throw new Exception("Digite una fecha de creacion valida.");
		}
		if (planDTO.getUsuCreador() == null || planDTO.getUsuCreador().trim().equals("") 
				||Utilities.isStringLenght(planDTO.getUsuCreador(), Constantes.TAMANOUSU)) {
			throw new Exception("Usuario creador invalido.");
		}
		if (planDTO.getEstado() == null || planDTO.getEstado().trim().equals("") || 
				Utilities.isStringLenght(planDTO.getEstado(), Constantes.TAMANNOESTADO) ||
				!Utilities.isStringInteger(planDTO.getEstado())|| !Utilities.estadoAoI(planDTO.getEstado())) {
			throw new Exception("Estado invalido, solo se acepta A o I.");
		}
		
		//Se crea el plan
		plan = new Plan();
		plan.setCodigo(planDTO.getCodigo());
		plan.setNombre(planDTO.getNombre());
		plan.setDescripcionSolicitud(planDTO.getDescripcionSolicitud());
		plan.setCantidadPersonas(planDTO.getCantidadPersonas());
		plan.setFechaSolicitud(planDTO.getFechaSolicitud());
		plan.setFechaInicioViaje(planDTO.getFechaInicioViaje());
		plan.setFechaFinViaje(planDTO.getFechaFinViaje());
		plan.setValorTotal(planDTO.getValorTotal());
		plan.setFechaCreacion(planDTO.getFechaCreacion());
		plan.setFechaModificacion(planDTO.getFechaModificacion());
		plan.setUsuCreador(planDTO.getUsuCreador());
		plan.setUsuModificador(planDTO.getUsuModificador());
		plan.setEstado(planDTO.getEstado());
		
		// TODO: Consultar cliente y usuario
		cliente = clienteService.findByNumeroIdentificacionAndEstado(planDTO.getNumeroIdentificacionCliente(), Constantes.ACTIVO);
		usuario = usuarioService.findByLoginAndEstado(planDTO.getLoginUsuario(), Constantes.ACTIVO);
		if(cliente ==null) {
			throw new Exception("El cliente " + planDTO.getNumeroIdentificacionCliente() + " No existe");
		}
		plan.setCliente(cliente);
		if(usuario ==null) {
			throw new Exception("El usuario " + planDTO.getLoginUsuario() + " No existe");
		}
		plan.setUsuario(usuario);
		planRepository.save(plan);
		return plan;
	}
	
	@Override
	public Plan actualizarPlan(PlanDTO planDTO) throws Exception{
		
		Plan plan = null;
		Cliente cliente = null;
		Usuario usuario = null;
		//Validaciones 
		
	   if (planDTO.getIdPlan() == null) {
			throw new Exception("Debe ingresar un id de plan valido para actualizar.");
			
	   }
	   Optional <Plan> planDB = Optional.of(findById(planDTO.getIdPlan()));
		
	    if (planDB.isEmpty()) {
				throw new Exception("No existe un plan con ese Id.");
			}
	   if (planDTO.getCodigo() == null || planDTO.getCodigo().trim().equals("") ||
				Utilities.isStringLenght(planDTO.getCodigo(), Constantes.TAMANNOCODIGO)||
				!Utilities.isStringInteger(planDTO.getCodigo())) {
			throw new Exception("Debe ingresar un codigo valido.");
		}

		if (planDTO.getNombre() == null || planDTO.getNombre().trim().equals("")
				|| Utilities.isStringLenght(planDTO.getNombre(), Constantes.TAMANONOMBRE)) {
			throw new Exception("Digite un nombre valido.");
		}
		if (planDTO.getDescripcionSolicitud() == null || planDTO.getDescripcionSolicitud().trim().equals("")
				|| Utilities.isStringLenght(planDTO.getDescripcionSolicitud(), Constantes.TAMANODESCRIPCION)) {
			throw new Exception("Digite una descripcion valida.");
		}
		if (planDTO.getCantidadPersonas() <= 0) {
			throw new Exception("Cantidad de noches no puede ser menor o igual a 0");
		}
		if (planDTO.getFechaSolicitud() == null) {
			throw new Exception("Debe ingresar una fecha de solicituc valida.");
		}
		if (planDTO.getFechaInicioViaje() == null) {
			throw new Exception("Debe ingresar una fecha de inicio de viaje valida.");
		}
		if (planDTO.getFechaFinViaje() == null) {
			throw new Exception("Debe ingresar una fecha de fin de viaje valida.");
		}
		if (planDTO.getFechaFinViaje().compareTo(planDTO.getFechaSolicitud())<=0) {
			throw new Exception("Debe ingresar una fecha valida, la fecha del viaje FIN no puede ser antes de la fecha de solicitud.");
		}
		if (planDTO.getFechaFinViaje().compareTo(planDTO.getFechaInicioViaje())<0) {
			throw new Exception("Debe ingresar una fecha valida, la fecha de inicial del viaje no puede ser despues de la fecha final.");
		}
		if(planDTO.getValorTotal() == null || planDTO.getValorTotal()<=0.0) {
			throw new Exception("Ingrese un valor total valido");
		}
		if (planDTO.getFechaCreacion() == null) {
			throw new Exception("Digite una fecha de creacion valida.");
		}
		if (planDTO.getUsuCreador() == null || planDTO.getUsuCreador().trim().equals("") 
				||Utilities.isStringLenght(planDTO.getUsuCreador(), Constantes.TAMANOUSU)) {
			throw new Exception("Usuario creador invalido.");
		}
		if (planDTO.getEstado() == null || planDTO.getEstado().trim().equals("") || 
				Utilities.isStringLenght(planDTO.getEstado(), Constantes.TAMANNOESTADO) ||
				!Utilities.isStringInteger(planDTO.getEstado())|| !Utilities.estadoAoI(planDTO.getEstado())) {
			throw new Exception("Estado invalido, solo se acepta A o I.");
		}
		
		plan = findById(planDTO.getIdPlan());
		plan.setCodigo(planDTO.getCodigo());
		plan.setNombre(planDTO.getNombre());
		plan.setDescripcionSolicitud(planDTO.getDescripcionSolicitud());
		plan.setCantidadPersonas(planDTO.getCantidadPersonas());
		plan.setFechaSolicitud(planDTO.getFechaSolicitud());
		plan.setFechaInicioViaje(planDTO.getFechaInicioViaje());
		plan.setFechaFinViaje(planDTO.getFechaFinViaje());
		plan.setValorTotal(planDTO.getValorTotal());
		plan.setFechaCreacion(planDTO.getFechaCreacion());
		plan.setFechaModificacion(planDTO.getFechaModificacion());
		plan.setUsuCreador(planDTO.getUsuCreador());
		plan.setUsuModificador(planDTO.getUsuModificador());
		plan.setEstado(planDTO.getEstado());
		
		// TODO: Consultar cliente y usuario
		cliente = clienteService.findByNumeroIdentificacionAndEstado(planDTO.getNumeroIdentificacionCliente(), Constantes.ACTIVO);
		usuario = usuarioService.findByLoginAndEstado(planDTO.getLoginUsuario(), Constantes.ACTIVO);
		if(cliente ==null) {
			throw new Exception("El cliente " + planDTO.getNumeroIdentificacionCliente() + " No existe");
		}
		plan.setCliente(cliente);
		if(usuario ==null) {
			throw new Exception("El usuario " + planDTO.getLoginUsuario() + " No existe");
		}
		plan.setUsuario(usuario);
		planRepository.save(plan);
		return plan;
	}
	

	@Override
	public void eliminarPlan(Long idPlan) throws Exception{
		// validar que se ingrese el id plan a eliminar
		if(idPlan == null) {
			throw new Exception("El id destino es obligatorio");
		}
		if(planRepository.existsById(idPlan)==false) {
			throw new Exception("El plan no se encontro");
		}
		planRepository.findById(idPlan).ifPresent(plan->{
			if(plan.getDetallePlan().isEmpty()==false) {
				throw new RuntimeException("El plan esta asignado en un detalle plan, por lo que no se puede eliminar");
			}
			
		});

		planRepository.deleteById(idPlan);
	}
	
	/**   
	 * <p>Title: findByFechaViajeBetween</p>   
	 * <p>Description: </p>   
	 * @param fechaInicioViaje
	 * @param fechaFinViaje
	 * @return
	 * @throws Exception   
	 * @see co.edu.usbcali.viajesusb.service.PlanService#findByFechaViajeBetween(java.util.Date, java.util.Date)   
	 */
	
	@Override
	public List<Plan> findByFechaInicioViajeBetween(Date fechaInicioViaje, Date fechaFinViaje) throws Exception {
		List<Plan> listaPlan = null;
		if(fechaInicioViaje == null || fechaFinViaje==null || (fechaInicioViaje == null && fechaFinViaje == null)) {
			throw new Exception("Debe ingresar un rango de fechas.");
		}

		if(fechaInicioViaje.compareTo(fechaFinViaje)==0) {
			throw new Exception("La fecha inicial no puede ser igual a la fecha final.");
		}
		if(fechaInicioViaje.compareTo(fechaFinViaje)>0) {
			throw new Exception("Debe ingresar un rango de fechas valido, la fecha final no puede ser menor que la fecha inicial.");
		}
		listaPlan = planRepository.findByFechaInicioViajeBetween(fechaInicioViaje,fechaFinViaje);
		if(listaPlan.isEmpty()) {
			throw new Exception("No se encontraron viajes con en ese rango de fechas.");
		}
		
		return listaPlan;
	}


}
