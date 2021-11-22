/**  
 * @Title:  DetallePlanTest.java   
 * @Package co.edu.usbcali.viajesusb   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   3/10/2021 12:47:41 p. m.   
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

import co.edu.usbcali.viajesusb.domain.DetallePlan;
import co.edu.usbcali.viajesusb.dto.DetallePlanDTO;
import co.edu.usbcali.viajesusb.service.DetallePlanService;
import co.edu.usbcali.viajesusb.utils.Constantes;


/**   
 * @ClassName:  DetallePlanTest   
  * @Description: TODO   
 * @author: Ángela Acosta    
 * @date:   3/10/2021 12:47:41 p. m.      
 * @Copyright:  USB
 */
@SpringBootTest
@Rollback(false)
class DetallePlanTest {


	/**
	 * Inyeccion_de_dependencia
	 */
	@Autowired
	private DetallePlanService detallePlanService;
	//private DetallePlan detallePlan = null;
	
	@Test
	@Transactional
	void debeGuardarDetallePlan() throws Exception {


		try {
			DetallePlanDTO detallePlanDTO = new DetallePlanDTO();

			detallePlanDTO.setAlimentacion(Constantes.SI);
			detallePlanDTO.setHospedaje(Constantes.SI);
			detallePlanDTO.setTransporte(Constantes.SI);
			detallePlanDTO.setTraslados(Constantes.NO);
			detallePlanDTO.setValor(12000000.00);
			detallePlanDTO.setCantidadNoches(5);
			detallePlanDTO.setCantidadDias(6);
			detallePlanDTO.setFechaCreacion(new Date());
			detallePlanDTO.setUsuCreador("CLOPEZ");
			detallePlanDTO.setEstado(Constantes.ACTIVO);
			detallePlanDTO.setCodigoPlan("NY");
			detallePlanDTO.setNombrePlan("SEMANA en NY");
			detallePlanDTO.setCodigoDestino("CALI");
			detallePlanDTO.setNombreDestino("Santiago de cali");
			detallePlanService.guardarDetallePlan(detallePlanDTO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Transactional
	void debeActualizarDetallePlan() throws Exception {


		try {
			DetallePlanDTO detallePlanDTO = new DetallePlanDTO();
			
			detallePlanDTO.setIdDetallePlan(1L);
			detallePlanDTO.setAlimentacion(Constantes.NO);
			detallePlanDTO.setHospedaje(Constantes.SI);
			detallePlanDTO.setTransporte(Constantes.SI);
			detallePlanDTO.setTraslados(Constantes.NO);
			detallePlanDTO.setValor(4000.000);
			detallePlanDTO.setCantidadNoches(4);
			detallePlanDTO.setCantidadDias(5);
			detallePlanDTO.setFechaCreacion(new Date());
			detallePlanDTO.setUsuCreador("CLOPEZ");
			detallePlanDTO.setEstado(Constantes.ACTIVO);
			detallePlanDTO.setCodigoPlan("TA");
			detallePlanDTO.setNombrePlan("SEMANA RELAX");
			detallePlanDTO.setCodigoDestino("CART");
			detallePlanDTO.setNombreDestino("Cartagena");
			detallePlanService.actualizarDetallePlan(detallePlanDTO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Transactional
	void debeEliminarDetallePlan() {
		// 
		try {
			DetallePlanDTO detallePlanDTO = new DetallePlanDTO();
			
			detallePlanDTO.setIdDetallePlan(6L);			
			//detallePlanService.eliminarDetallePlan(detallePlanDTO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Transactional
	void consultarCantidadNoches() {
		List<DetallePlan>listaDetalle = null;
		try {
			listaDetalle = detallePlanService.findByCantidadNoches(0);
			for(DetallePlan detallePlan : listaDetalle) {
				System.out.println(detallePlan.getIdDetallePlan()+ "-"+ detallePlan.getCantidadNoches()+"-"+ detallePlan.getEstado());			
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void consultarPorValor() {
		List<DetallePlan>listaDetalle = null;
		try {
			listaDetalle = detallePlanService.findByValor(0.0);
			for(DetallePlan detallePlan : listaDetalle) {
				System.out.println(detallePlan.getIdDetallePlan()+ "-"+ detallePlan.getCantidadNoches()+"-"+ detallePlan.getCantidadDias() + "-" + detallePlan.getValor()+"-"+ detallePlan.getEstado());			
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void consultarPorEstado() {
		Page<DetallePlan>paginaDetalle = null;
		try {
			Pageable pageable = PageRequest.of(0, 1);
			paginaDetalle = detallePlanService.findByEstado(Constantes.ACTIVO,pageable );
			for(DetallePlan detallePlan : paginaDetalle.getContent()) {
				System.out.println(detallePlan.getIdDetallePlan()+ "-"+ detallePlan.getCantidadNoches()+"-"+ detallePlan.getCantidadDias() + "-" + detallePlan.getValor()+"-"+ detallePlan.getEstado());			
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
