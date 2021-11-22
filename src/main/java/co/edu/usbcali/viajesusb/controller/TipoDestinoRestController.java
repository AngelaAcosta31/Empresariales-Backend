/**  
 * @Title:  TipoDestinoRestController.java   
 * @Package co.edu.usbcali.viajesusb.controller   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   12/10/2021 10:32:43 a. m.   
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
import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;
import co.edu.usbcali.viajesusb.mapper.TipoDestinoMapper;
import co.edu.usbcali.viajesusb.service.TipoDestinoService;

/**   
 * @ClassName:  TipoDestinoRestController   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   12/10/2021 10:32:43 a. m.      
 * @Copyright:  USB
 */
@RestController
@RequestMapping("/api/tipoDestino")
public class TipoDestinoRestController {
	@Autowired
	private TipoDestinoService tipoDestinoService;
	@Autowired
	private TipoDestinoMapper tipoDestinoMapper;
	//http://localhost:9090/api/tipoDestino/getTipoDestinoPorCodigo/BOSQU
	String mensaje;
	
	@PostMapping("/guardarTipoDestino")
	public ResponseEntity<TipoDestinoDTO> guardarTipoDestino(@RequestBody TipoDestinoDTO tipoDestinoDTO){
		try {
			TipoDestino tipoDestino = tipoDestinoService.guardarTipoDestino(tipoDestinoDTO);
			return ResponseEntity.ok(tipoDestinoMapper.tipoDestinoToTipoDestinoDTO(tipoDestino));
		} catch (Exception e) {
			//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/actualizarTipoDestino")
	public ResponseEntity<?> actualizarTipoDestino(@RequestBody TipoDestinoDTO tipodestinoDTO){
		try {
			
			TipoDestino tipoDestino = tipoDestinoService.actualizarTipoDestino(tipodestinoDTO);
			return ResponseEntity.ok(tipoDestinoMapper.tipoDestinoToTipoDestinoDTO(tipoDestino));
		} catch (Exception e) {
			// TODO: handle exception
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/eliminarTipoDestino/{id}")
	public ResponseEntity<?> eliminarTipoDestino(@PathVariable("id") Long id){
		try {
			tipoDestinoService.eliminarTipoDestino(id);
			return ResponseEntity.ok("Se eliminó satisfactoriamente");
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> buscarTipoDestinoPorId(@PathVariable("id") Long id ){
		try {
			TipoDestino tipoDestino=  tipoDestinoService.findById(id);
			return ResponseEntity.ok().body(tipoDestinoMapper.tipoDestinoToTipoDestinoDTO(tipoDestino));
		} catch (Exception e) {
			//retorna un error 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
	}
	
	@GetMapping("/getTipoDestinoPorCodigo")
	public ResponseEntity<TipoDestinoDTO> buscarTipoDestinoPorCodigo(@RequestParam("codigo") String codigo){
		try {
			TipoDestino tipoDestino=  tipoDestinoService.findByCodigo(codigo);
			
			return ResponseEntity.ok().body(tipoDestinoMapper.tipoDestinoToTipoDestinoDTO(tipoDestino));
		} catch (Exception e) {
			//retorna un error 500
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/getTipoDestinoPorCodigoAndEstado")
	public ResponseEntity<TipoDestinoDTO> buscarTipoDestinoPorCodigoAndEstado(@RequestParam("codigo") String codigo,@RequestParam("estado") String estado){
		try {
			TipoDestino tipoDestino=  tipoDestinoService.findByCodigoAndEstado(codigo, estado);
			
			return ResponseEntity.ok().body(tipoDestinoMapper.tipoDestinoToTipoDestinoDTO(tipoDestino));
		} catch (Exception e) {
			//retorna un error 500
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/getTiposDestinosPorEstado")
	public ResponseEntity<List<TipoDestinoDTO>> consultarTipoDestino(@RequestParam("estado") String estado){
		try {
			List<TipoDestino> listaTipoDestino=  tipoDestinoService.findByEstadoOrderByNombreDesc(estado);
			return ResponseEntity.ok().body(tipoDestinoMapper.listTipoDestinoToListTipoDestinoDTO(listaTipoDestino));
		} catch (Exception e) {
			//retorna un error 500
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@GetMapping("/getTipoDestinoPorNombre")
	public ResponseEntity<TipoDestinoDTO> buscarTipoDestinoPorNombre(@RequestParam("nombre") String nombre){
		try {
			TipoDestino tipoDestino=  tipoDestinoService.findByNombre(nombre);
			
			return ResponseEntity.ok().body(tipoDestinoMapper.tipoDestinoToTipoDestinoDTO(tipoDestino));
		} catch (Exception e) {
			//retorna un error 500
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
}
