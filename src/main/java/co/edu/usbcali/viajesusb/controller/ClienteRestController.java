/**  
 * @Title:  ClienteRestController.java   
 * @Package co.edu.usbcali.viajesusb.controller   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   12/10/2021 10:32:55 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */


package co.edu.usbcali.viajesusb.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;
import co.edu.usbcali.viajesusb.mapper.ClienteMapper;
import co.edu.usbcali.viajesusb.service.ClienteService;

/**   
 * @ClassName:  ClienteRestController   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   12/10/2021 10:32:55 a. m.      
 * @Copyright:  USB
 */
@RestController
@RequestMapping("/api/cliente")
public class ClienteRestController {
	
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ClienteMapper clienteMapper;

	String mensaje = "";
	@PostMapping("/guardarCliente")
	public ResponseEntity<ClienteDTO> guardarCliente(@RequestBody ClienteDTO clienteDTO){
		try {
			Cliente cliente = clienteService.guardarCliente(clienteDTO);
			return ResponseEntity.ok(clienteMapper.clienteToClienteDTO(cliente));
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("/actualizarCliente")
	public ResponseEntity<?> actualizarCliente(@RequestBody ClienteDTO clienteDTO){
		try {
			
			Cliente cliente = clienteService.actualizarCliente(clienteDTO);
			return ResponseEntity.ok(clienteMapper.clienteToClienteDTO(cliente));
		} catch (Exception e) {
			// TODO: handle exception
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/eliminarCliente/{id}")
	public ResponseEntity<?> eliminarCliente(@PathVariable("id") Long id){
		try {
			clienteService.eliminarCliente(id);
			return ResponseEntity.ok("Se eliminó satisfactoriamente");
		} catch (Exception e) {
			//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> buscarClientePorId(@PathVariable("id") Long id ){
		try {
			Cliente cliente=  clienteService.findById(id);
			return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(cliente));
		} catch (Exception e) {
			//retorna un error 500
			//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@GetMapping("/verEstadoPorNumeroIdentificacion")
	public ResponseEntity<List<ClienteDTO>> buscarEstadoPorNumeroIdentificacion(@RequestParam("estado") String estado ){
		try {
			int paginaInicio = 1;
			int paginaFinal = 3;
			Pageable pageable = PageRequest.of(paginaInicio,paginaFinal );
			
			Page<Cliente> listaCliente= clienteService.findByEstadoOrderByNumeroIdentificacionAsc(estado,pageable);
			return ResponseEntity.ok().body(clienteMapper.listClienteToListClienteDTO(listaCliente));
		} catch (Exception e) {
			//retorna un error 500
			//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/verPorTipoIdentificacion")
	public ResponseEntity<List<ClienteDTO>> buscarClientePorTipoIdentificacion(@RequestParam("codigo") String codigo ){
		try {
			int paginaInicio = 1;
			int paginaFinal = 3;
			Pageable pageable = PageRequest.of(paginaInicio,paginaFinal );
			
			Page<Cliente> listaCliente= clienteService.findByTipoIdentificacionCodigo(codigo,pageable);
			return ResponseEntity.ok().body(clienteMapper.listClienteToListClienteDTO(listaCliente));
		} catch (Exception e) {
			//retorna un error 500
			//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findByCorreo")
	public ResponseEntity<?> buscarClientePorCorreo(@RequestParam("correo") String correo ){
		try {
			Cliente cliente=  clienteService.findByCorreoIgnoreCase(correo);
			return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(cliente));
		} catch (Exception e) {
			//retorna un error 500
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje,HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@GetMapping("/findByNumeroIdentificacionLike")
	public ResponseEntity<List<ClienteDTO>> buscarClientePorNumeroIdentificacionLike(@RequestParam("numeroIdentificacion") String numeroIdentificacion ){
		try {
			List<Cliente> listacliente=  clienteService.findByNumeroIdentificacionLike(numeroIdentificacion);
			return ResponseEntity.ok().body(clienteMapper.listClienteToListClienteDTO(listacliente));
		} catch (Exception e) {
			//retorna un error 500
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
			
		}
	}
	@GetMapping("/findByNumeroIdentificacion")
	public ResponseEntity<ClienteDTO> buscarClientePorNumeroIdentificacion(@RequestParam("numeroIdentificacion") String numeroIdentificacion ){
		try {
			Cliente cliente=  clienteService.findByNumeroIdentificacion(numeroIdentificacion);
			return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(cliente));
		} catch (Exception e) {
			//retorna un error 500
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
			
		}
	}
	@GetMapping("/findByNombreLike")
	public ResponseEntity<List<ClienteDTO>> buscarClientePorNombreLike(@RequestParam("nombre") String nombre ){
		try {
			List<Cliente> listacliente=  clienteService.findByNombreIgnoreCaseLike(nombre);
			return ResponseEntity.ok().body(clienteMapper.listClienteToListClienteDTO(listacliente));
		} catch (Exception e) {
		
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
			
		}
	}

	@GetMapping("/findByFechasNacimiento")
	public ResponseEntity<List<ClienteDTO>> buscarClientePorFechaN(@RequestParam("fechaNacimiento1") String fechaInicial, @RequestParam("fechaNacimiento2") String fechaFinal ){
	

		try {
		
			Date fechaInici = null;
			fechaInici = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicial);
			Date fechaFin = null;
			fechaFin = new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinal);
			List<Cliente> listacliente=  clienteService.findByFechaNacimientoBetween(fechaInici, fechaFin);
			return ResponseEntity.ok().body(clienteMapper.listClienteToListClienteDTO(listacliente));
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
		}
			
		
	}

	@GetMapping("/coutEstado")
	public ResponseEntity<?> clientesPorEstado(@RequestParam("estado") String estado ){
		try {
			Long cliente=  clienteService.countByEstado(estado);
			return ResponseEntity.ok().body(cliente);
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@GetMapping("/findByApellidos")
	public ResponseEntity<List<ClienteDTO>> buscarClientePorApellidos(@RequestParam("primerApellido") String primerApellido, @RequestParam("segundoApellido") String segundoApellido ){
		try {
			List<Cliente> listacliente=  clienteService.findByPrimerApellidoOrSegundoApellido(primerApellido, segundoApellido);
			return ResponseEntity.ok().body(clienteMapper.listClienteToListClienteDTO(listacliente));
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
			
		}
	}
	
	@GetMapping("/findByNumeroIdentificacionAndEstados")
	public ResponseEntity<ClienteDTO> buscarClientePoridentificacionYEstado(@RequestParam("numeroIdentificacion") String numeroIdentificacion, @RequestParam("estado") String estado ){
		try {
			Cliente listacliente=  clienteService.findByNumeroIdentificacionAndEstado(numeroIdentificacion, estado);
			return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(listacliente));
		} catch (Exception e) {
			mensaje = e.getMessage();
			return new ResponseEntity(mensaje, HttpStatus.BAD_REQUEST);
			
		}
	}
	
	
}
