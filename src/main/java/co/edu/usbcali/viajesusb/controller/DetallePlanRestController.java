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
	
	@PostMapping("/guardarDetallePlan")
	public ResponseEntity<DetallePlanDTO> guardarDetallePlan(@RequestBody DetallePlanDTO detallePlanDTO){
		try {
			DetallePlan detallePlan = detallePlanService.guardarDetallePlan(detallePlanDTO);
			return ResponseEntity.ok(detallePlanMapper.detallePlanToDetallePlanDTO(detallePlan));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/actualizarDetallePlan")
	public ResponseEntity<?> actualizarDetallePlan(@RequestBody DetallePlanDTO detallePlanDTO){
		try {
			
			DetallePlan detallePlan = detallePlanService.actualizarDetallePlan(detallePlanDTO);
			return ResponseEntity.ok(detallePlanMapper.detallePlanToDetallePlanDTO(detallePlan));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@DeleteMapping("/eliminarDetallePlan/{id}")
	public ResponseEntity<?> eliminarDetallePlano(@PathVariable("id") Long id){
		try {
			detallePlanService.eliminarDetallePlan(id);
			return ResponseEntity.ok("Se eliminó satisfactoriamente");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> buscarDetallePlanPorId(@PathVariable("id") Long id ){
		try {
			DetallePlan detallePlan=  detallePlanService.findById(id);
			return ResponseEntity.ok().body(detallePlanMapper.detallePlanToDetallePlanDTO(detallePlan));
		} catch (Exception e) {
			//retorna un error 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
	}

}
