/**  
 * @Title:  TipoIdentificacionRestController.java   
 * @Package co.edu.usbcali.viajesusb.controller   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   12/10/2021 10:34:40 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb.controller;

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

import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;
import co.edu.usbcali.viajesusb.mapper.TipoIdentificacionMapper;
import co.edu.usbcali.viajesusb.service.TipoIdentificacionService;

/**   
 * @ClassName:  TipoIdentificacionRestController   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   12/10/2021 10:34:40 a. m.      
 * @Copyright:  USB
 */
@RestController
@RequestMapping("/api/tipoIdentificacion")
public class TipoIdentificacionRestController {
	
	@Autowired
	private TipoIdentificacionService tipoIdentificacionService;
	
	@Autowired
	private TipoIdentificacionMapper tipoIdentificacionMapper;
	
	String mensaje;
	
	@PostMapping("/guardarTipoIdentificacion")
	public ResponseEntity<TipoIdentificacionDTO> guardarTipoIdentificacion(@RequestBody TipoIdentificacionDTO tipoIdentificacionDTO){
		try {
			TipoIdentificacion tipoIdentificacion = tipoIdentificacionService.guardarTipoIdentificacion(tipoIdentificacionDTO);
			return ResponseEntity.ok(tipoIdentificacionMapper.tipoIdentificacionToTipoIdentificacionDTO(tipoIdentificacion));
		} catch (Exception e) {
			//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/actualizarTipoIdentificacion")
	public ResponseEntity<?> actualizarTipoIdentificacion(@RequestBody TipoIdentificacionDTO tipoIdentificacionDTO){
		try {
			
			TipoIdentificacion tipoIdentificacion = tipoIdentificacionService.actualizarTipoIdentificacion(tipoIdentificacionDTO);
			return ResponseEntity.ok(tipoIdentificacionMapper.tipoIdentificacionToTipoIdentificacionDTO(tipoIdentificacion));
		} catch (Exception e) {
			// TODO: handle exception
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/eliminarTipoIdentificacion/{id}")
	public ResponseEntity<?> eliminarTipoIdentificacion(@PathVariable("id") Long id){
		try {
			tipoIdentificacionService.eliminarTipoIdentificacion(id);
			return ResponseEntity.ok("Se eliminó satisfactoriamente");
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> buscarTipoIdentificacionPorId(@PathVariable("id") Long id ){
		try {
			TipoIdentificacion tipoIdentificacion=  tipoIdentificacionService.findById(id);
			return ResponseEntity.ok().body(tipoIdentificacionMapper.tipoIdentificacionToTipoIdentificacionDTO(tipoIdentificacion));
		} catch (Exception e) {
			//retorna un error 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
	}
	
	@GetMapping("/verPorEstado")
	public ResponseEntity<List<TipoIdentificacionDTO>> buscarPorEstado(@RequestParam("estado") String estado ){
		try {
			List<TipoIdentificacion> tipoIdentificacion=  tipoIdentificacionService.findByEstadoOrderByNombreAsc(estado);
			return ResponseEntity.ok().body(tipoIdentificacionMapper.listTipoIdentificacionToListTipoIdentificacionDTO(tipoIdentificacion));
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@GetMapping("/verPorCodigoYEstado")
	public ResponseEntity<TipoIdentificacionDTO> buscarPorCodigoYEstado(@RequestParam("codigo") String codigo, @RequestParam("estado") String estado ){
		try {
			TipoIdentificacion tipoIdentificacion=  tipoIdentificacionService.findByCodigoAndEstado(codigo,estado);
			return ResponseEntity.ok().body(tipoIdentificacionMapper.tipoIdentificacionToTipoIdentificacionDTO(tipoIdentificacion));
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);			
		}
	}
	@GetMapping("/verPorCodigo")
	public ResponseEntity<TipoIdentificacionDTO> buscarPorCodigo(@RequestParam("codigo") String codigo){
		try {
			TipoIdentificacion tipoIdentificacion=  tipoIdentificacionService.findByCodigo(codigo);
			return ResponseEntity.ok().body(tipoIdentificacionMapper.tipoIdentificacionToTipoIdentificacionDTO(tipoIdentificacion));
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);		
		}
	}
	
	@GetMapping("/NombreIdentificacion")
	public ResponseEntity<List<TipoIdentificacionDTO>> buscarTipoIdPorNombre(@RequestParam("nombre") String nombre ){
		try {
			List<TipoIdentificacion> listaTipo=  tipoIdentificacionService.findByNombreIgnoreCase(nombre);
			return ResponseEntity.ok().body(tipoIdentificacionMapper.listTipoIdentificacionToListTipoIdentificacionDTO(listaTipo));
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);			
		}
	}

}
