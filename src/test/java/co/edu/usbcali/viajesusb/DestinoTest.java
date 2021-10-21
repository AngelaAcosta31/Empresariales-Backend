/**  
 * @Title:  DestinoTest.java   
 * @Package co.edu.usbcali.viajesusb   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   31/08/2021 11:41:01 a. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb;



import java.util.Date;
import java.util.List;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.viajesusb.domain.Destino;
import co.edu.usbcali.viajesusb.dto.DestinoDTO;
import co.edu.usbcali.viajesusb.service.DestinoService;
import co.edu.usbcali.viajesusb.utils.Constantes;

/**   
 * @ClassName:  DestinoTest   
  * @Description: pruebas_unitarias   
 * @author: Ángela_Acosta    
 * @date:   31/08/2021 11:41:01 a. m.      
 * @Copyright:  USB
 */

@SpringBootTest
@Rollback(false)
class DestinoTest {
	
	/**
	 * Inyeccion_de_dependencia
	 */
	@Autowired
	private DestinoService destinoService;

	@Test
	@Transactional
	void debeConsultarDestinosPorTipoDestino() {
		
		List<Destino> listaDestino = null;
		
		try {
			listaDestino = destinoService.findByTipoDestinoCodigo("playa");
			
			for(Destino destino: listaDestino) {
				System.out.println(destino.getCodigo() + " - "+ destino.getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeConsultarDestinosPorEstadoPaginado() {
		Page<Destino> paginacionDestino = null;
		try {
			
			/**
			 * PageRequest:
			 * Primer_parametro_es_el_numero_de_la_pagina_actual,_empezando_desde_cero
			 * segundo_parametro_es_la_cantidad_de_items_por_pagina 
			 */
			
			Pageable pageable = PageRequest.of(0, 1);
			paginacionDestino = destinoService.findByEstado(Constantes.ACTIVO, pageable);
			for(Destino destino: paginacionDestino.getContent()) {
				System.out.println(destino.getCodigo() + " - "+ destino.getNombre());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	@Transactional
	void debeGuardarElDestinoSanAndres() {
		// 
		try {
			DestinoDTO destinoDTO = new DestinoDTO();
			
			destinoDTO.setAire(Constantes.SI);
			destinoDTO.setMar(Constantes.SI);
			destinoDTO.setTierra(Constantes.NO);
			
			destinoDTO.setNombre("SAN ANDRES");
			destinoDTO.setCodigo("SAND");
			destinoDTO.setDescripcion("San andres islas");
			destinoDTO.setEstado(Constantes.ACTIVO);
			destinoDTO.setFechaCreacion(new Date());
			destinoDTO.setUsuCreador("CLOPEZ");
			
			destinoDTO.setCodigoTipoDestino("PLAYA");
			destinoDTO.setNombreTipoDestino("PLAYA Y MAR");
			
			destinoService.guardarDestino(destinoDTO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	@Test
	@Transactional
	void debeActualizarDestinoSanAndres() {
		// 
		try {
			DestinoDTO destinoDTO = new DestinoDTO();
			
			destinoDTO.setIdDest(7L);
			destinoDTO.setAire(Constantes.SI);
			destinoDTO.setMar(Constantes.SI);
			destinoDTO.setTierra(Constantes.NO);
			
			destinoDTO.setNombre("San Andres Islas");
			destinoDTO.setCodigo("SAND");
			destinoDTO.setDescripcion("San andres islas");
			destinoDTO.setEstado(Constantes.ACTIVO);
			destinoDTO.setFechaCreacion(new Date());
			destinoDTO.setUsuCreador("CLOPEZ");
			
			destinoDTO.setCodigoTipoDestino("PLAYA");
			destinoDTO.setNombreTipoDestino("PLAYA Y MAR");
			
			destinoService.actualizarDestino(destinoDTO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	@Test
	@Transactional
	void debeEliminarDestino() {
		// 
		try {
			DestinoDTO destinoDTO = new DestinoDTO();
			
			destinoDTO.setIdDest(5L);			
			//destinoService.eliminarDestino(destinoDTO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
