/**  
 * @Title:  PlanRestController.java   
 * @Package co.edu.usbcali.viajesusb.controller   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   12/10/2021 10:34:20 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import co.edu.usbcali.viajesusb.domain.Plan;
import co.edu.usbcali.viajesusb.dto.PlanDTO;
import co.edu.usbcali.viajesusb.mapper.PlanMapper;
import co.edu.usbcali.viajesusb.service.PlanService;

/**   
 * @ClassName:  PlanRestController   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   12/10/2021 10:34:20 a. m.      
 * @Copyright:  USB
 */
@RestController
@RequestMapping("/api/plan")
public class PlanRestController {
	
	@Autowired
	private PlanService planService;
	
	@Autowired
	private PlanMapper planMapper;

	String mensaje;
	
	@PostMapping("/guardarPlan")
	public ResponseEntity<PlanDTO> guardarPlan(@RequestBody PlanDTO planDTO){
		try {
			Plan plan = planService.guardarPlan(planDTO);
			return ResponseEntity.ok(planMapper.planToPlanDTO(plan));
		} catch (Exception e) {
			//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/actualizarPlan")
	public ResponseEntity<?> actualizarPlan(@RequestBody PlanDTO planDTO){
		try {
			
			Plan plan = planService.actualizarPlan(planDTO);
			return ResponseEntity.ok(planMapper.planToPlanDTO(plan));
		} catch (Exception e) {
			// TODO: handle exception
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/eliminarPlan/{id}")
	public ResponseEntity<?> eliminarPlan(@PathVariable("id") Long id){
		try {
			planService.eliminarPlan(id);
			return ResponseEntity.ok("Se eliminó satisfactoriamente");
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> buscarPlanPorId(@PathVariable("id") Long id ){
		try {
			Plan plan=  planService.findById(id);
			return ResponseEntity.ok().body(planMapper.planToPlanDTO(plan));
		} catch (Exception e) {
			//retorna un error 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
	}
	
	@GetMapping("/verPorCodigo")
	public ResponseEntity<PlanDTO> buscarPorCodigo(@RequestParam("codigo") String codigo ){
		try {
			Plan plan=  planService.findByCodigo(codigo);
			return ResponseEntity.ok().body(planMapper.planToPlanDTO(plan));
		} catch (Exception e) {
			//retorna un error 500
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@GetMapping("/verPorCodigoAndEstado")
	public ResponseEntity<PlanDTO> buscarPorCodigoAndEstado(@RequestParam("codigo") String codigo,@RequestParam("estado") String estado ){
		try {
			Plan plan=  planService.findByCodigoAndEstado(codigo,estado);
			return ResponseEntity.ok().body(planMapper.planToPlanDTO(plan));
		} catch (Exception e) {
			//retorna un error 500
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@GetMapping("/buscarPorValorTotal")
	public ResponseEntity<List<PlanDTO>> buscarPorValor(@RequestParam("valor")Double valor){
		try {
			List<Plan> listaPlan = planService.findByValorTotal(valor);
			return ResponseEntity.ok().body(planMapper.listPlanToListPlanDTO(listaPlan));
		} catch (Exception e) {
			// TODO: handle exception
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/buscarPorPersonas")
	public ResponseEntity<List<PlanDTO>> buscarPersonas(@RequestParam("cantidadPersonas")Integer cantidadPersonas){
		try {
			List<Plan> listaPlan = planService.findByCantidadPersonas(cantidadPersonas);
			return ResponseEntity.ok().body(planMapper.listPlanToListPlanDTO(listaPlan));
		} catch (Exception e) {
			// TODO: handle exception
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findByFechaInicioViaje")
	public ResponseEntity<List<PlanDTO>> buscarfechaViaje(@RequestParam("fechaInicioViaje") String fechaInicioViaje, @RequestParam("fechaFinViaje") String fechaFinViaje ){
	

		try {
		
			Date fechaInicio = null;
			fechaInicio = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicioViaje);
			Date fechaFin = null;
			fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinViaje);
			List<Plan> listaPlan=  planService.findByFechaInicioViajeBetween(fechaInicio, fechaFin);
			return ResponseEntity.ok().body(planMapper.listPlanToListPlanDTO(listaPlan));
		} catch (Exception e) {
			//retorna un error 500
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
			
		}
	}

}
