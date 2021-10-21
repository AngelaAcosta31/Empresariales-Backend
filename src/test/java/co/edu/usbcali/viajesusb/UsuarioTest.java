/**  
 * @Title:  UsuarioTest.java   
 * @Package co.edu.usbcali.viajesusb   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   13/09/2021 8:53:48 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb;



import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import co.edu.usbcali.viajesusb.domain.Usuario;
import co.edu.usbcali.viajesusb.dto.UsuarioDTO;
import co.edu.usbcali.viajesusb.service.UsuarioService;
import co.edu.usbcali.viajesusb.utils.Constantes;

/**   
 * @ClassName:  UsuarioTest   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   13/09/2021 8:53:48 p. m.      
 * @Copyright:  USB
 */
@SpringBootTest
@Rollback(false)
class UsuarioTest {

	/**
	 * Inyeccion_de_dependencia
	 */
	@Autowired
	private UsuarioService usuarioService;
	private Usuario usuario = null;
	
	@Test
	@Transactional
	void debeConsultarPorIdentificacion() {
		try {
			usuario = usuarioService.findByIdentificacion("1151946203");
			System.out.println(usuario.getNombre()+ " - " + usuario.getLogin());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeConsultarPorEstado() {
		List<Usuario> listaUsuarios = null; 
		try {
			listaUsuarios = usuarioService.findByEstado(Constantes.ACTIVO);
			for (Usuario usuario : listaUsuarios) {
				System.out.println(usuario.getNombre()+ " - " + usuario.getLogin());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeConsultarPorLogin() {
		try {
			usuario = usuarioService.findByLogin("clopez");
			System.out.println(usuario.getNombre()+ " - " + usuario.getIdentificacion());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeGuardarUsuario() {
		
		try {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
		
			usuarioDTO.setLogin("PRUEBAS");
			usuarioDTO.setPassword("4563");
			usuarioDTO.setNombre("PRUEBASSSS");
			usuarioDTO.setIdentificacion("3456782134");
			usuarioDTO.setFechaCreacion(new Date());
			usuarioDTO.setUsuCreador("CLOPEZ");
			usuarioDTO.setEstado(Constantes.ACTIVO);
			
			usuarioService.guardarUsuario(usuarioDTO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Transactional
	void debeActualizarUsuario() {
		
		try {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setIdUsuario(4L);
			usuarioDTO.setLogin("AACOSTA");
			usuarioDTO.setPassword("098765");
			usuarioDTO.setNombre("ANGELA ACOSTA");
			usuarioDTO.setIdentificacion("1005874283");
			usuarioDTO.setFechaCreacion(new Date());
			usuarioDTO.setUsuCreador("CLOPEZ");
			usuarioDTO.setEstado(Constantes.ACTIVO);
			
			usuarioService.actualizarUsuario(usuarioDTO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	@Test
	@Transactional
	void debeEliminarUsuario() {
		// 
		try {
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			
			usuarioDTO.setIdUsuario(4L);			
			//usuarioService.eliminarUsuario(usuarioDTO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
