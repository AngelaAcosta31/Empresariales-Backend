/**  
 * @Title:  PlanTest.java   
 * @Package co.edu.usbcali.viajesusb   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   8/09/2021 10:36:22 p. m.   
 * @version V1.0 
 * @Copyright: Universidad San de Buenaventura
 */

package co.edu.usbcali.viajesusb;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.viajesusb.domain.Plan;
import co.edu.usbcali.viajesusb.dto.PlanDTO;
import co.edu.usbcali.viajesusb.service.PlanService;
import co.edu.usbcali.viajesusb.utils.Constantes;

/**   
 * @ClassName:  PlanTest   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   8/09/2021 10:36:22 p. m.      
 * @Copyright:  USB
 */
@SpringBootTest
@Rollback(false)
class PlanTest {

	@Autowired
	private PlanService planService;
	private Plan planes = null;
	
	@Test
	@Transactional
	void debeConsultarPlanPorCodigo() {
		try {
			planes = planService.findByCodigo("TA");
			System.out.println(planes.getIdPlan()+" "+ planes.getNombre()+" "+ planes.getDescripcionSolicitud());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	void debeGuardarPlan() throws Exception {
		// 
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
		Date fechaS = formato.parse("2021-03-31");
		Date fechaI = formato.parse("2021-04-20");
		Date fechaF = formato.parse("2021-04-30");
		try {
			PlanDTO planDTO = new PlanDTO();
			
			planDTO.setCodigo("SNY");
			planDTO.setNombre("SEMANA NY");
			planDTO.setDescripcionSolicitud("PLAN SEMANA EN MANHHATTHAN");
			planDTO.setCantidadPersonas(4);
			planDTO.setFechaSolicitud(fechaS);
			planDTO.setFechaInicioViaje(fechaI);
			planDTO.setFechaFinViaje(fechaF);
			planDTO.setValorTotal(4.0000);
			planDTO.setFechaCreacion(new Date());
			planDTO.setUsuCreador("CLOPEZ");
			planDTO.setEstado(Constantes.ACTIVO);
			planDTO.setNumeroIdentificacionCliente("16480415");
			planDTO.setNombreCliente("DANIELA");
			planDTO.setLoginUsuario("AACOSTA");
			planDTO.setNombreUsuario("ANGELA ACOSTA");
			
			planService.guardarPlan(planDTO);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Transactional
	void debeActualizarPlan() throws Exception {
		// 
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
		Date fechaS = formato.parse("2021-08-31");
		Date fechaI = formato.parse("2021-10-31");
		Date fechaF = formato.parse("2021-11-10");
		try {
			PlanDTO planDTO = new PlanDTO();
			planDTO.setIdPlan(2L);
			planDTO.setCodigo("TA");
			planDTO.setNombre("SEMANA RELAX");
			planDTO.setDescripcionSolicitud("PLAN SEMANA TEMPORADA ALTA");
			planDTO.setCantidadPersonas(4);
			planDTO.setFechaSolicitud(fechaS);
			planDTO.setFechaInicioViaje(fechaI);
			planDTO.setFechaFinViaje(fechaF);
			planDTO.setValorTotal(1000000.00);
			planDTO.setFechaCreacion(new Date());
			planDTO.setUsuCreador("CLOPEZ");
			planDTO.setEstado(Constantes.ACTIVO);
			planDTO.setNumeroIdentificacionCliente("1820481639");
			planDTO.setNombreCliente("LAURA");
			planDTO.setLoginUsuario("CLOPEZ");
			planDTO.setNombreUsuario("CESAR LOPEZ");
			
			planService.actualizarPlan(planDTO);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	@Test
	@Transactional
	void debeEliminarPlan() {
		// 
		try {
			PlanDTO planDTO = new PlanDTO();
			
			planDTO.setIdPlan(2L);			
			//planService.eliminarPlan(planDTO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	@Test
	@Transactional
	void consultarPorValor() {
		List<Plan>listaPlan = null;
		try {
			listaPlan = planService.findByValorTotal(0.0);
			for(Plan plan : listaPlan) {
				System.out.println(plan.getIdPlan()+ "-"+ plan.getCodigo()+"-"+ plan.getNombre() + "-" + plan.getValorTotal()+"-"+ plan.getEstado());			
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@Test
	@Transactional
	void consultarPorPersonas() {
		List<Plan>listaPlan = null;
		try {
			listaPlan = planService.findByCantidadPersonas(0);
			for(Plan plan : listaPlan) {
				System.out.println(plan.getIdPlan()+ "-"+ plan.getCodigo()+"-"+ plan.getNombre() + "-" + plan.getValorTotal()+"-"+ plan.getCantidadPersonas());			
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeConsultarViajePorRangosDeFechaInicioViaje() {
		List<Plan> listaPlan = null;
		Calendar fechaInicio = new GregorianCalendar(2021,9,4);
		Calendar fechaFinal = new GregorianCalendar(2022,1,4);
			try {
				listaPlan = planService.findByFechaInicioViajeBetween(fechaInicio.getTime(),fechaFinal.getTime());
				for (Plan plan : listaPlan) {
					System.out.println(plan.getNombre()+ " " + plan.getCodigo()+" - "+ plan.getFechaInicioViaje());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
