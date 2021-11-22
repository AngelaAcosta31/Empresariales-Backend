/**  
 * @Title:  DetallePlanRestController.java   
 * @Package co.edu.usbcali.viajesusb.controller   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   12/10/2021 10:33:15 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import co.edu.usbcali.viajesusb.domain.DetallePlan;
import co.edu.usbcali.viajesusb.dto.DetallePlanDTO;
import co.edu.usbcali.viajesusb.mapper.DetallePlanMapper;
import co.edu.usbcali.viajesusb.service.DetallePlanService;

/**   
 * @ClassName:  DetallePlanRestController   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   12/10/2021 10:33:15 a. m.      
 * @Copyright:  USB
 */
@RestController
@RequestMapping("/api/detallePlan")
public class DetallePlanRestController {
	
	@Autowired
	private DetallePlanService detallePlanService;
	
	
	@Autowired
	private DetallePlanMapper detallePlanMapper;
	
	String mensaje;
	
	@PostMapping("/guardarDetallePlan")
	public ResponseEntity<DetallePlanDTO> guardarDetallePlan(@RequestBody DetallePlanDTO detallePlanDTO){
		try {
			DetallePlan detallePlan = detallePlanService.guardarDetallePlan(detallePlanDTO);
			return ResponseEntity.ok(detallePlanMapper.detallePlanToDetallePlanDTO(detallePlan));
		} catch (Exception e) {
			//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/actualizarDetallePlan")
	public ResponseEntity<?> actualizarDetallePlan(@RequestBody DetallePlanDTO detallePlanDTO){
		try {
			
			DetallePlan detallePlan = detallePlanService.actualizarDetallePlan(detallePlanDTO);
			return ResponseEntity.ok(detallePlanMapper.detallePlanToDetallePlanDTO(detallePlan));
		} catch (Exception e) {
			// TODO: handle exception
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/eliminarDetallePlan/{id}")
	public ResponseEntity<?> eliminarDetallePlano(@PathVariable("id") Long id){
		try {
			detallePlanService.eliminarDetallePlan(id);
			return ResponseEntity.ok("Se eliminó satisfactoriamente");
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> buscarDetallePlanPorId(@PathVariable("id") Long id ){
		try {
			DetallePlan detallePlan=  detallePlanService.findById(id);
			return ResponseEntity.ok().body(detallePlanMapper.detallePlanToDetallePlanDTO(detallePlan));
		} catch (Exception e) {
			//retorna un error 500
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@GetMapping("/buscarPorNoches")
	public ResponseEntity<List<DetallePlanDTO>> buscarDetallePorNoches(@RequestParam("noches")Integer noches){
		try {
			List<DetallePlan> listadetalle = detallePlanService.findByCantidadNoches(noches);
			return ResponseEntity.ok().body(detallePlanMapper.listDetallePlanToListDetallePlanDTO(listadetalle));
		} catch (Exception e) {
			// TODO: handle exception
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/buscarPorValor")
	public ResponseEntity<List<DetallePlanDTO>> buscarDetallePorValor(@RequestParam("valor")Double valor){
		try {
			List<DetallePlan> listadetalle = detallePlanService.findByValor(valor);
			return ResponseEntity.ok().body(detallePlanMapper.listDetallePlanToListDetallePlanDTO(listadetalle));
		} catch (Exception e) {
			// TODO: handle exception
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/buscarPorEstado")
	public ResponseEntity<List<DetallePlanDTO>> buscarDetallePorEstado(@RequestParam("estado")String estado){
		try {
			int paginaInicio = 1;
			int paginaFinal = 3;
			Pageable pageable = PageRequest.of(paginaInicio,paginaFinal );
			Page<DetallePlan> paginadetalle = detallePlanService.findByEstado(estado, pageable);
			return ResponseEntity.ok().body(detallePlanMapper.listDetallePlanToListDetallePlanDTO(paginadetalle));
		} catch (Exception e) {
			// TODO: handle exception
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}

}
