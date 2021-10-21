/**  
 * @Title:  TipoIdentificacionTest.java   
 * @Package co.edu.usbcali.viajesusb   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   5/09/2021 11:59:17 a. m.   
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

import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.TipoIdentificacionDTO;
import co.edu.usbcali.viajesusb.service.TipoIdentificacionService;
import co.edu.usbcali.viajesusb.utils.Constantes;

/**   
 * @ClassName:  TipoIdentificacionTest   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   5/09/2021 11:59:17 a. m.      
 * @Copyright:  USB
 */
@SpringBootTest
@Rollback(false)
class TipoIdentificacionTest {
	
	/**
	 * Inyeccion_de_dependencia
	 */
	@Autowired
	private TipoIdentificacionService tipoIdentificacionService;
	private TipoIdentificacion tipoIdentificacion = null;
	
	@Test
	@Transactional
	void debeConsultarTiposIdentificacionPorEstadoOrdenadosAlfabeticamente() {
		List<TipoIdentificacion> listaTipoIdentificacion = null; 
		try {
			listaTipoIdentificacion = tipoIdentificacionService.findByEstadoOrderByNombreAsc(Constantes.ACTIVO);
			for (TipoIdentificacion tipoIdentificacion : listaTipoIdentificacion) {
				System.out.println(tipoIdentificacion.getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeConsultarTipoIdentificacionPorCodigoYEstado() {
		try {
			tipoIdentificacion = tipoIdentificacionService.findByCodigoAndEstado("PA", Constantes.ACTIVO);
			System.out.println(tipoIdentificacion.getNombre());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeGuardarTipoIdentificacion()  {

		try {
			TipoIdentificacionDTO tipoIdentificacionDTO = new TipoIdentificacionDTO();
			
			tipoIdentificacionDTO.setCodigo("EF");
			tipoIdentificacionDTO.setNombre("EXNH");
			tipoIdentificacionDTO.setFechaCreacion(new Date());
			tipoIdentificacionDTO.setUsuCreador("CLOPEZ");
			tipoIdentificacionDTO.setEstado(Constantes.ACTIVO);
			
			tipoIdentificacionService.guardarTipoIdentificacion(tipoIdentificacionDTO);
			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	
	@Test
	@Transactional
	void debeActualizarTipoIdentificacion()  {

		try {
			TipoIdentificacionDTO tipoIdentificacionDTO = new TipoIdentificacionDTO();
			tipoIdentificacionDTO.setIdTipoIdentificacion(8L);
			tipoIdentificacionDTO.setCodigo("EF");
			tipoIdentificacionDTO.setNombre("PRUEBA");
			tipoIdentificacionDTO.setFechaCreacion(new Date());
			tipoIdentificacionDTO.setUsuCreador("CLOPEZ");
			tipoIdentificacionDTO.setEstado(Constantes.ACTIVO);
			
			tipoIdentificacionService.actualizarTipoIdentificacion(tipoIdentificacionDTO);
			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Transactional
	void debeEliminarTipoIdentificacion() {
		// 
		try {
			TipoIdentificacionDTO tipoIdentificacionDTO = new TipoIdentificacionDTO();
			
			tipoIdentificacionDTO.setIdTipoIdentificacion(1L);			
			//tipoIdentificacionService.eliminarTipoIdentificacion(tipoIdentificacionDTO);
			
		} catch (Exception e) {
			// TODO Auto-generatetipoIdentificaciond catch block
			System.out.println(e.getMessage());
		}
	}


}
