/**  
 * @Title:  TipoDestinoTest.java   
 * @Package co.edu.usbcali.viajesusb   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   31/08/2021 11:20:55 a. m.   
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
import co.edu.usbcali.viajesusb.domain.TipoDestino;
import co.edu.usbcali.viajesusb.dto.TipoDestinoDTO;
import co.edu.usbcali.viajesusb.service.TipoDestinoService;
import co.edu.usbcali.viajesusb.utils.Constantes;


/**   
 * @ClassName:  TipoDestinoTest   
  * @Description: Pruebas_unitarias   
 * @author: Ángela_Acosta    
 * @date:   31/08/2021 11:20:55 a. m.      
 * @Copyright:  USB
 */

@SpringBootTest
@Rollback(false)
class TipoDestinoTest {
	
	
	/**
	 * Inyeccion_de_dependencia
	 */
	@Autowired
	private TipoDestinoService tipoDestinoService;
	private TipoDestino tipoDestino = null; 
	
	@Test
	@Transactional
	void debeConsultarUnTipoDeDestinoPorCodigo(){
		try {
			tipoDestino = tipoDestinoService.findByCodigo("playa");	
			System.out.println(tipoDestino.getIdTide()+" - " + 
			tipoDestino.getCodigo() + " - "+ tipoDestino.getNombre()+" - "+tipoDestino.getDescripcion());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeConsultarUnTipoDeDestinoPorCodigoEstado() {
		try {
			tipoDestino = tipoDestinoService.findByCodigoAndEstado("desie", Constantes.ACTIVO);
			System.out.println(tipoDestino.getIdTide()+" , " + 
			tipoDestino.getCodigo() + " , "+ tipoDestino.getNombre()+" , "+
			tipoDestino.getDescripcion()+" , "+ tipoDestino.getFechaCreacion()
			+ " , "+tipoDestino.getUsuCreador()+ ", " + tipoDestino.getEstado());
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeConsultarTiposDeDestinoPorEstadoOrdenadosAlfabeticamente() {
		
	  List<TipoDestino> listaTipoDestino = null; 
	  
		try {
			listaTipoDestino = tipoDestinoService.findByEstadoOrderByNombreDesc(Constantes.ACTIVO);
			
			for (TipoDestino tipoDestino : listaTipoDestino) {
				System.out.println(tipoDestino.getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	void debeConsultarUnTipoDeDestinoPorNombre(){
		try {
			tipoDestino = tipoDestinoService.findByNombre("playa y mar");	
			//System.out.println(tipoDestino.toString());
			System.out.println(tipoDestino.getIdTide()+" - " + 
			tipoDestino.getCodigo() + " - "+ tipoDestino.getNombre());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//CRUD
	@Test
	@Transactional
	void debeGuardarTipoDestino() {
		
		try {
			TipoDestinoDTO tipoDestinoDTO = new TipoDestinoDTO();
			
			tipoDestinoDTO.setCodigo("pru");
			tipoDestinoDTO.setNombre("PR");
			tipoDestinoDTO.setDescripcion("CLIMA VARIADO PARA REALIZAR DIFERENTES ACTIVIDADES");
			tipoDestinoDTO.setFechaCreacion(new Date());
			tipoDestinoDTO.setUsuCreador("CLOPEZ");
			tipoDestinoDTO.setEstado(Constantes.ACTIVO);
			
			tipoDestinoService.guardarTipoDestino(tipoDestinoDTO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Transactional
	void debeActualizarTipoDestino() {
		
		try {
			TipoDestinoDTO tipoDestinoDTO = new TipoDestinoDTO();
			
			tipoDestinoDTO.setIdTide(10L);
			tipoDestinoDTO.setCodigo("MIX");
			tipoDestinoDTO.setNombre("MIXTO");
			tipoDestinoDTO.setDescripcion("PODRAS REALIZAR DIFERENTES ACTIVIDADES AL AIRE LIBRE");
			tipoDestinoDTO.setFechaCreacion(new Date());
			tipoDestinoDTO.setUsuCreador("CLOPEZ");
			tipoDestinoDTO.setEstado(Constantes.ACTIVO);
			
			tipoDestinoService.actualizarTipoDestino(tipoDestinoDTO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Transactional
	void debeEliminarTipoDestino() {
		// 
		try {
			TipoDestinoDTO tipoDestinoDTO = new TipoDestinoDTO();
			
			tipoDestinoDTO.setIdTide(12L);			
			//tipoDestinoService.eliminarTipoDestino(tipoDestinoDTO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
