/**  
 * @Title:  DestinoRestController.java   
 * @Package co.edu.usbcali.viajesusb.controller   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   12/10/2021 10:32:20 a. m.   
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
import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;
import co.edu.usbcali.viajesusb.mapper.DestinoMapper;
import co.edu.usbcali.viajesusb.service.DestinoService;


/**   
 * @ClassName:  DestinoRestController   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   12/10/2021 10:32:20 a. m.      
 * @Copyright:  USB
 */
@RestController
@RequestMapping("/api/destino")
public class DestinoRestController {
	
	@Autowired
	private DestinoService destinoService;
	@Autowired
	private DestinoMapper destinoMapper;
//	@Autowired
//	private TipoDestinoService tipoDestinoService;
	
	@PostMapping("/guardarDestino")
	public ResponseEntity<DestinoDTO> guardarDestino(@RequestBody DestinoDTO destinoDTO){
		try {
			Destino destino = destinoService.guardarDestino(destinoDTO);
			return ResponseEntity.ok(destinoMapper.destinoToDestinoDTO(destino));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/actualizarDestino")
	public ResponseEntity<?> actualizarDestino(@RequestBody DestinoDTO destinoDTO){
		try {
			
			Destino destino = destinoService.actualizarDestino(destinoDTO);
			return ResponseEntity.ok(destinoMapper.destinoToDestinoDTO(destino));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/eliminarDestino/{id}")
	public ResponseEntity<?> eliminarDestino(@PathVariable("id") Long id){
		try {
			destinoService.eliminarDestino(id);
			return ResponseEntity.ok("Se eliminó satisfactoriamente");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> buscarDestinoPorId(@PathVariable("id") Long id ){
		try {
			Destino destino=  destinoService.findById(id);
			return ResponseEntity.ok().body(destinoMapper.destinoToDestinoDTO(destino));
		} catch (Exception e) {
			//retorna un error 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
	}
	
	@GetMapping("/getTipoDestino")
	public ResponseEntity<List<DestinoDTO>> buscarPorTipoDestinoCodigo(@RequestParam("codigoTipoDestino") String codigo ){
		try {
			List<Destino> listaDestino=  destinoService.findByTipoDestinoCodigo(codigo);
			return ResponseEntity.ok().body(destinoMapper.listDestinoToListDestinoDTO(listaDestino));
		} catch (Exception e) {
			//retorna un error 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
	}
	
	@GetMapping("/verPorEstado")
	public ResponseEntity<List<DestinoDTO>> buscarPorEstado(@RequestParam("estado") String estado){
	
		try {
			int paginaInicio = 1;
			int paginaFinal = 3;
			Pageable pageable = PageRequest.of(paginaInicio,paginaFinal );
			
			Page<Destino> listaDestino= destinoService.findByEstado(estado,pageable);
			return ResponseEntity.ok().body(destinoMapper.listaDestinoToListDestinoDTO(listaDestino));
		} catch (Exception e) {
			//retorna un error 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
	}
	
	@GetMapping("/verPorCodigo")
	public ResponseEntity<DestinoDTO> buscarPorCodigo(@RequestParam("codigo") String codigo ){
		try {
			Destino destino=  destinoService.findByCodigo(codigo);
			return ResponseEntity.ok().body(destinoMapper.destinoToDestinoDTO(destino));
		} catch (Exception e) {
			//retorna un error 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
	}
	
	@GetMapping("/verPorCodigoYEstado")
	public ResponseEntity<DestinoDTO> buscarPorCodigoYEstado(@RequestParam("codigo") String codigo, @RequestParam("estado") String estado ){
		try {
			Destino destino=  destinoService.findByCodigoAndEstado(codigo,estado);
			return ResponseEntity.ok().body(destinoMapper.destinoToDestinoDTO(destino));
		} catch (Exception e) {
			
			//retorna un error 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
	}
}
