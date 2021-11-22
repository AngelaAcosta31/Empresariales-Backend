/**  
 * @Title:  ClienteTest.java   
 * @Package co.edu.usbcali.viajesusb   
 * @Description: description   
 * @author: Ángela Acosta    
 * @date:   5/09/2021 12:57:13 p. m.   
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;
import co.edu.usbcali.viajesusb.service.ClienteService;
import co.edu.usbcali.viajesusb.utils.Constantes;

/**   
 * @ClassName:  ClienteTest   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   5/09/2021 12:57:13 p. m.      
 * @Copyright:  USB
 */
@SpringBootTest
@Rollback(false)
class ClienteTest {

	@Autowired
	private ClienteService clienteService;
	private Cliente clientes = null;
	
	
	@Test
	@Transactional
	void debeConsultarClientesPorEstadoPaginado() {
		Page<Cliente> paginacionClientes = null;
		
		try {
			Pageable pageable = PageRequest.of(0, 3);
			paginacionClientes = clienteService.findByEstadoOrderByNumeroIdentificacionAsc(Constantes.ACTIVO, pageable);
			for (Cliente cliente : paginacionClientes) {
				System.out.println(cliente.getNumeroIdentificacion() + " - "+ cliente.getNombre()+ " "+ cliente.getPrimerApellido());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	@Test
	@Transactional
	void debeConsultarUnClientePorCorreoElectronico() {
		try {
			//qwertysdreuiopgfhfghgdjhgfdsnedwugjvrejghfdresdfgftfvueeoncdoshwlfmusjahdomdyhwjaghsojmnbclongyuionhyeshloahydklyq@hotmail.com
			//FELIPE@hotmail.com
			clientes = clienteService.findByCorreoIgnoreCase("laurap@hotmail.com");
			System.out.println(clientes.getNumeroIdentificacion() + " - "+ clientes.getNombre()+ " "+ clientes.getPrimerApellido());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Test
	@Transactional
	void debeConsultarUnClientePorNumeroIdentificacion() {
		List<Cliente> listaCliente = null;
		
		try {
			//1567907532678431
			listaCliente = clienteService.findByNumeroIdentificacionLike("10058%");
			for (Cliente clientes : listaCliente) {
				System.out.println(clientes.getNumeroIdentificacion() + " - "+ clientes.getNombre()+ " "+ clientes.getPrimerApellido());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeConsultarUnClientePorNombre() {
		List<Cliente> listaCliente=null;
		
		try {
			
			listaCliente = clienteService.findByNombreIgnoreCaseLike("da%");
			for (Cliente clientes : listaCliente) {
				//System.out.println(clientes.toString());
				System.out.println(clientes.getNumeroIdentificacion() + " - "+ clientes.getNombre()+" "+ clientes.getPrimerApellido()+" " + clientes.getSegundoApellido());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	/*
	 * Forma_1
	 */
	@Test
	@Transactional
	void debeConsultarClientesPorRangoFechaNacimiento() {
		List<Cliente> listaClientes = null;

			try {
				listaClientes = clienteService.findByFechaNacimientoBetween(new SimpleDateFormat("YYYY-MM-dd").parse("1990-01-12"),new SimpleDateFormat("YYYY-MM-dd").parse("1999-12-31"));
				
				for (Cliente cliente : listaClientes) {
					//System.out.println(cliente.toString());
					System.out.println(cliente.getNombre()+ " " + cliente.getPrimerApellido()+" - "+ cliente.getFechaNacimiento());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	/*
	 * Forma_2
	 */
	@Test
	@Transactional
	void debeConsultarClientesPorRangosDeFechaNacimiento() {
		List<Cliente> listaClientes = null;
		Calendar fechaInicio = new GregorianCalendar(1995,10,4);
		Calendar fechaFinal = new GregorianCalendar(2000,10,4);
			try {
				listaClientes = clienteService.findByFechaNacimientoBetween(fechaInicio.getTime(),fechaFinal.getTime());
				for (Cliente cliente : listaClientes) {
					System.out.println(cliente.getNombre()+ " " + cliente.getPrimerApellido()+" - "+ cliente.getFechaNacimiento());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	
	@Test
	@Transactional
	void debeConsultarTotalClientesPorEstado() {
		Long total;
		try {
			total = clienteService.countByEstado(Constantes.ACTIVO);
			System.out.println("Total: "+ total);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeConsultarClientePorTipoIdentificacionPaginado() {
		Page<Cliente> paginableClientes = null;
		
		try {
			Pageable pageable = PageRequest.of(0,3);
			paginableClientes = clienteService.findByTipoIdentificacionCodigo("cc", pageable);
			for (Cliente cliente : paginableClientes) {
				System.out.println(cliente.getNombre()+" - "+ cliente.getPrimerApellido()+" - "+cliente.getCorreo());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	void debeConsultarClientePorApellidos() {
		List<Cliente> listaClientes = null;
		
		try {
			listaClientes = clienteService.findByPrimerApellidoOrSegundoApellido("pretel", "ortiz");
			for (Cliente cliente : listaClientes) {
				System.out.println(cliente.getNumeroIdentificacion()+" - "+cliente.getNombre()+" - "+ cliente.getPrimerApellido()+" - "+cliente.getSegundoApellido());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	void debeConsultarClientesPorNombreORM() {
		List<ClienteDTO> listaClientes = null;
		
		try {
			listaClientes = clienteService.consultarClientesPorNombre("da%");
			for (ClienteDTO cliente : listaClientes) {
				System.out.println("Numero Identificación: "+ cliente.getNumeroIdentificacion()+ 
						 " - Nombre: "+ cliente.getNombre()+ " - Primer Apellido: "+cliente.getPrimerApellido()+
						" - Segundo Apellido: "+cliente.getSegundoApellido()+" - Estado: "+cliente.getEstado()+
						" - TipoIdentificacion - Id_Tiid: "+ cliente.getTipoIdentificacion());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	@Transactional
	void debeGuardarElCliente() throws Exception {
		// 
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
		Date fechaN = formato.parse("2000-05-08");

		try {
			ClienteDTO clienteDTO = new ClienteDTO();

			clienteDTO.setNumeroIdentificacion("10055");
			clienteDTO.setPrimerApellido("SAENZ");
			clienteDTO.setSegundoApellido("SAENZ");
			clienteDTO.setNombre("VALERIA");
			clienteDTO.setTelefono1("3205683106");
			clienteDTO.setTelefono2("3106294192");
			clienteDTO.setCorreo("SAENZVALERIA");
			clienteDTO.setSexo(Constantes.FEMENINO);
			clienteDTO.setFechaNacimiento(fechaN);
			clienteDTO.setFechaCreacion(new Date());
			clienteDTO.setUsuCreador("CLOPEZ");
			clienteDTO.setEstado(Constantes.ACTIVO);
			clienteDTO.setTipoIdentificacionCodigo("CC");
			clienteService.guardarCliente(clienteDTO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	@Transactional
	void debeActualizarElCliente() throws Exception {
		// 
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
		Date fechaN = formato.parse("2000-07-31");

		try {
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setIdCliente(5L);
			clienteDTO.setNumeroIdentificacion("1005874280");
			clienteDTO.setPrimerApellido("ACOSTA");
			clienteDTO.setSegundoApellido("RAMIREZ");
			clienteDTO.setNombre("ANGELA MARIA");
			clienteDTO.setTelefono1("31045429632");
			clienteDTO.setTelefono2("32841852942");
			clienteDTO.setCorreo("ANGELAMARIA731@GMAIL.COM");
			clienteDTO.setSexo(Constantes.FEMENINO);
			clienteDTO.setFechaNacimiento(fechaN);
			clienteDTO.setFechaCreacion(new Date());
			clienteDTO.setUsuCreador("CLOPEZ");
			clienteDTO.setEstado(Constantes.ACTIVO);
			clienteDTO.setTipoIdentificacionCodigo("CC");
			
			clienteService.actualizarCliente(clienteDTO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	@Test
	@Transactional
	void debeEliminarCliente() {
		// 
		try {
			ClienteDTO clienteDTO = new ClienteDTO();
			
			clienteDTO.setIdCliente(15L);			
			//clienteService.eliminarCliente(clienteDTO);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}


}
