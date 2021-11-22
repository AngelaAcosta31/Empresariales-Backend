/**  
 * @Title:  UsuarioRestController.java   
 * @Package co.edu.usbcali.viajesusb.controller   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   12/10/2021 10:34:54 a. m.   
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
import co.edu.usbcali.viajesusb.domain.Usuario;
import co.edu.usbcali.viajesusb.dto.UsuarioDTO;
import co.edu.usbcali.viajesusb.mapper.UsuarioMapper;
import co.edu.usbcali.viajesusb.service.UsuarioService;

/**   
 * @ClassName:  UsuarioRestController   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   12/10/2021 10:34:54 a. m.      
 * @Copyright:  USB
 */
@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioMapper usuarioMapper;
	
	String mensaje;
	
	@PostMapping("/guardarUsuario")
	public ResponseEntity<UsuarioDTO> guardarUsuario(@RequestBody UsuarioDTO usuarioDTO){
		try {
			Usuario usuario = usuarioService.guardarUsuario(usuarioDTO);
			return ResponseEntity.ok(usuarioMapper.usuarioToUsuarioDTO(usuario));
		} catch (Exception e) {
			//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/actualizarUsuario")
	public ResponseEntity<?> actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO){
		try {
			
			Usuario usuario = usuarioService.actualizarUsuario(usuarioDTO);
			return ResponseEntity.ok(usuarioMapper.usuarioToUsuarioDTO(usuario));
		} catch (Exception e) {
			// TODO: handle exception
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/eliminarUsuario/{id}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable("id") Long id){
		try {
			usuarioService.eliminarUsuario(id);
			return ResponseEntity.ok("Se eliminó satisfactoriamente");
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> buscarDestinoPorId(@PathVariable("id") Long id ){
		try {
			Usuario usuario=  usuarioService.findById(id);
			return ResponseEntity.ok().body(usuarioMapper.usuarioToUsuarioDTO(usuario));
		} catch (Exception e) {
			//retorna un error 500
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			
		}
	}
	
	@GetMapping("/findByIdentificacion")
	public ResponseEntity<UsuarioDTO> buscarUsuarioPorIdentificacion(@RequestParam("identificacion") String numeroIdentificacion ){
		try {
			Usuario usuario=  usuarioService.findByIdentificacion(numeroIdentificacion);
			return ResponseEntity.ok().body(usuarioMapper.usuarioToUsuarioDTO(usuario));
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);		
		}
	}
	
	@GetMapping("/findByEstado")
	public ResponseEntity<List<UsuarioDTO>> buscarUsuarioPorEstado(@RequestParam("estado") String estado ){
		try {
			List<Usuario> usuario=  usuarioService.findByEstado(estado);
			return ResponseEntity.ok().body(usuarioMapper.listUsuarioToListUsuarioDTO(usuario));
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);			
		}
	}
	@GetMapping("/findByLogin")
	public ResponseEntity<UsuarioDTO> buscarUsuarioPorLogin(@RequestParam("login") String login ){
		try {
			Usuario usuario=  usuarioService.findByLogin(login);
			return ResponseEntity.ok().body(usuarioMapper.usuarioToUsuarioDTO(usuario));
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);			
		}
	}
	
	@GetMapping("/findByLoginAndEstado")
	public ResponseEntity<UsuarioDTO> buscarUsuarioPorLoginAndEstado(@RequestParam("login") String login, @RequestParam("estado") String estado){
		try {
			Usuario usuario=  usuarioService.findByLoginAndEstado(login,estado);
			return ResponseEntity.ok().body(usuarioMapper.usuarioToUsuarioDTO(usuario));
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);			
		}
	}

}
