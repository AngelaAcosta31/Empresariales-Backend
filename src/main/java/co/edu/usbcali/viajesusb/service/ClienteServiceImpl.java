package co.edu.usbcali.viajesusb.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.edu.usbcali.viajesusb.domain.Cliente;
import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import co.edu.usbcali.viajesusb.dto.ClienteDTO;
import co.edu.usbcali.viajesusb.repository.ClienteRepository;
import co.edu.usbcali.viajesusb.utils.Constantes;
import co.edu.usbcali.viajesusb.utils.Utilities;

/**   
 * @ClassName:  ClienteServiceImpl   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   8/09/2021 10:11:52 p. m.      
 * @Copyright:  USB
 */
@Scope("singleton")
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private TipoIdentificacionService tipoIdenticacionService;
	private Cliente clientes = null;
	
	/**   
	 * <p>Title: findByEstadoOrderByNumeroIdentificacionAsc</p>   
	 * <p>Description: </p>   
	 * @param estado
	 * @param pageable
	 * @return
	 * @throws Exception   
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#findByEstadoOrderByNumeroIdentificacionAsc1(java.lang.String, org.springframework.data.domain.Pageable)   
	 */
	
	public Cliente findById(Long idCliente) throws Exception{
		//validamos el numero de identificacion
		if(idCliente == null) {
			throw new Exception("Debe ingresar un numero de identificacion");
			
		}
		if(!clienteRepository.findById(idCliente).isPresent()) {
			throw new Exception ("El cliente con id: "+idCliente+ " no existe");
		}
		return clienteRepository.findById(idCliente).get();
	}
	

	@Override
	public Page<Cliente> findByEstadoOrderByNumeroIdentificacionAsc(String estado, Pageable pageable) throws Exception {
		Page<Cliente> paginacionClientes = null;
		
		if(estado == null || pageable == null || estado.trim().equals("")) {
			throw new Exception("Debe ingresar datos validos.");
		}
		if(Utilities.isStringLenght(estado, Constantes.TAMANNOESTADO)) {
			throw new Exception("Debe ingresar solo un caracter.");
		}
		if(!Utilities.isStringInteger(estado)) {
			throw new Exception("El estado no debe ser un número.");
		}

		paginacionClientes = clienteRepository.findByEstadoOrderByNumeroIdentificacionAsc(estado.toUpperCase(), pageable);
		if(paginacionClientes.isEmpty()) {
			throw new Exception("No se han encontrado clientes con el estado ingresado.");
		}
		return paginacionClientes;
	}

	
	/**   
	 * <p>Title: findByCorreoIgnoreCase</p>   
	 * <p>Description: </p>   
	 * @param correo
	 * @return
	 * @throws Exception   
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#findByCorreoIgnoreCase(java.lang.String)   
	 */
	
	@Override
	public Cliente findByCorreoIgnoreCase(String correo) throws Exception {
		if(correo ==null || correo.trim().equals("")) {
			throw new Exception("Debe ingresar un correo.");
		}
		if(Utilities.isStringLenght(correo, Constantes.TAMANOCORREO)) {
			throw new Exception("El correo no puede contener más de 100 caracteres.");
		}
		if(!Utilities.formatoCorreoValido(correo)) {
			throw new Exception("El correo no es valido, no debe contener caracteres especiales.");
		}
		clientes = clienteRepository.findByCorreoIgnoreCase(correo);
		if(clientes == null) {
			throw new Exception("No se encontraron clientes con esa dirección de correo electronico.");
		}
		return clientes;
	}

	
	/**   
	 * <p>Title: findByNumeroIdentificacionLike</p>   
	 * <p>Description: </p>   
	 * @param numeroIdentificacion
	 * @return
	 * @throws Exception   
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#findByNumeroIdentificacionLike(java.lang.String)   
	 */
	
	@Override
	public List<Cliente> findByNumeroIdentificacionLike(String numeroIdentificacion) throws Exception {
		
		List<Cliente> listaCliente = null;
		if(numeroIdentificacion == null || numeroIdentificacion.trim().equals("")) {
			throw new Exception("Debe ingresar un número de identificación.");
		}
		if(Utilities.isStringLenght(numeroIdentificacion,Constantes.TAMANOIDENTIFICACION)) {
			throw new Exception("El numero no debe contener más de 15 digitos.");
		}
		if(Utilities.isStringInteger(numeroIdentificacion)) {
			throw new Exception("El número de identificación debe ser solo números, no letras.");
		}
		
		if(Utilities.isSpecialCaracter(numeroIdentificacion)) {
			throw new Exception("El número de identificación no debe contener caracteres especiales.");
		}
		listaCliente = clienteRepository.findByNumeroIdentificacionLike(numeroIdentificacion);
		if(listaCliente.isEmpty()) {
			throw new Exception("No se han encontrado clientes con el número de identificación ingresado.");
		}
		return listaCliente;
	}
	

	@Override
	public Cliente findByNumeroIdentificacion(String numeroIdentificacion) throws Exception {
		if(numeroIdentificacion == null || numeroIdentificacion.trim().equals("")) {
			throw new Exception("Debe ingresar un número de identificación.");
		}
		if(Utilities.isStringLenght(numeroIdentificacion,Constantes.TAMANOIDENTIFICACION)) {
			throw new Exception("El numero no debe contener más de 15 digitos.");
		}
		if(Utilities.isStringInteger(numeroIdentificacion)) {
			throw new Exception("El número de identificación debe ser solo números, no letras.");
		}
		
		if(Utilities.isSpecialCaracter(numeroIdentificacion)) {
			throw new Exception("El número de identificación no debe contener caracteres especiales.");
		}
		Cliente clientes = clienteRepository.findByNumeroIdentificacion(numeroIdentificacion);	
		if(clientes != null) {
			throw new Exception("Ya existe un cliente con esta identificacion.");
		}
		return clientes;
	}

	
	/**   
	 * <p>Title: findByNombreIgnoreCaseLike</p>   
	 * <p>Description: </p>   
	 * @param nombre
	 * @return
	 * @throws Exception   
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#findByNombreIgnoreCaseLike(java.lang.String)   
	 */
	
	@Override
	public List<Cliente> findByNombreIgnoreCaseLike(String nombre) throws Exception {
		List<Cliente> listaCliente=null;
		if(nombre ==null || nombre.trim().equals("")) {
			throw new Exception("Debe ingresar un nombre.");
		}
		if(Utilities.isStringLenght(nombre,Constantes.TAMANONOMBRE)) {
			throw new Exception("Debe ingresar un nombre con menos de 100 caracteres.");
		}
		
		listaCliente = clienteRepository.findByNombreIgnoreCaseLike(nombre);
		if(listaCliente.isEmpty()) {
			throw new Exception("No se encontraron clientes con ese nombre.");
		}
		return listaCliente;
	}

	
	/**   
	 * <p>Title: findByFechaNacimientoBetween</p>   
	 * <p>Description: </p>   
	 * @param fecha1
	 * @param fecha2
	 * @return
	 * @throws Exception   
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#findByFechaNacimientoBetween(java.util.Date, java.util.Date)   
	 */
	
	@Override
	public List<Cliente> findByFechaNacimientoBetween(Date fechaInicial, Date fechaFinal) throws Exception {
		List<Cliente> listaClientes = null;
		if(fechaInicial == null || fechaFinal==null || (fechaInicial == null && fechaFinal == null)) {
			throw new Exception("Debe ingresar un rango de fechas.");
		}
		// revisar si la fecha final si puede ser la actual
		if((fechaInicial.compareTo(new Date())>0)||(fechaFinal.compareTo(new Date())>0)) {
			throw new Exception("La fecha inicial o final no pueden ser la fecha actual o una fecha que aun no ha pasado.");
		}
		
		if(fechaInicial.compareTo(fechaFinal)==0) {
			throw new Exception("La fecha inicial no puede ser igual a la fecha final.");
		}
		if(fechaInicial.compareTo(fechaFinal)>0) {
			throw new Exception("Debe ingresar un rango de fechas valido, la fecha final no puede ser menor que la fecha inicial.");
		}
		listaClientes = clienteRepository.findByFechaNacimientoBetween(fechaInicial,fechaFinal);
		if(listaClientes.isEmpty()) {
			throw new Exception("No se encontraron clientes con en ese rango de fechas de nacimiento.");
		}
		
		return listaClientes;
	}

	
	/**   
	 * <p>Title: countByEstado</p>   
	 * <p>Description: </p>   
	 * @param estado
	 * @return   
	 * @throws Exception 
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#countByEstado(java.lang.String)   
	 */
	
	@Override
	public Long countByEstado(String estado) throws Exception{
		Long total;
		if(estado ==null || estado.trim().equals("")) {
			throw new Exception("Debe ingresar un estado.");
		}
		if(Utilities.isStringLenght(estado, Constantes.TAMANNOESTADO)) {
			throw new Exception("Debe ingresar solo un caracter.");
		}
		if(!Utilities.isStringInteger(estado)) {
			throw new Exception("El estado no debe ser un número.");
		}
		total = clienteRepository.countByEstado(estado.toUpperCase());
		return total;
	}

	
	/**   
	 * <p>Title: findByTipoIdentificacionCodigo</p>   
	 * <p>Description: </p>   
	 * @param tipoIdentificacion
	 * @param pageable
	 * @return
	 * @throws Exception   
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#findByTipoIdentificacionCodigo(java.lang.String, org.springframework.data.domain.Pageable)   
	 */
	
	@Override
	public Page<Cliente> findByTipoIdentificacionCodigo(String tipoIdentificacion, Pageable pageable) throws Exception {
		Page<Cliente> paginableClientes = null;
		if(tipoIdentificacion ==null || tipoIdentificacion.trim().equals("")|| pageable ==null) {
			throw new Exception("Debe ingresar un tipo de identificación.");
		}
		if(Utilities.isStringLenght(tipoIdentificacion, Constantes.TAMANNOCODIGO)) {
			throw new Exception("El tipo de identificación no debe contener más de 5 digitos.");
		}
		if(!Utilities.isStringInteger(tipoIdentificacion)) {
			throw new Exception("El tipo de identificación no debe contener números.");
		}
		paginableClientes = clienteRepository.findByTipoIdentificacion_Codigo(tipoIdentificacion.toUpperCase(), pageable);
		if(paginableClientes.isEmpty()) {
			throw new Exception("No se han encontrado clientes con el codigo de tipo de identificación ingresado.");
		}
		return paginableClientes;
	}

	
	/**   
	 * <p>Title: findByPrimerApellidoOrSegundoApellido</p>   
	 * <p>Description: </p>   
	 * @param primerApellido
	 * @param segundoApellido
	 * @return
	 * @throws Exception   
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#findByPrimerApellidoOrSegundoApellido(java.lang.String, java.lang.String)   
	 */
	
	@Override
	public List<Cliente> findByPrimerApellidoOrSegundoApellido(String primerApellido, String segundoApellido)
			throws Exception {
		List<Cliente> listaClientes = null;
		if(primerApellido == null || segundoApellido == null || (primerApellido.trim().equals("") && segundoApellido.trim().equals(""))) {
			throw new Exception("Debe ingresar dos apellidos.");
		}
		if(Utilities.isStringLenght(primerApellido, Constantes.TAMANOAPELLIDOS) || Utilities.isStringLenght(segundoApellido, Constantes.TAMANOAPELLIDOS)) {
			throw new Exception("Los apellidos no deben de contener más de 100 caracteres.");
		}	
		listaClientes = clienteRepository.findByPrimerApellidoOrSegundoApellido(primerApellido.toUpperCase(), segundoApellido.toUpperCase());
		if(listaClientes.isEmpty()) {
			throw new Exception("No se encontraron clientes con esos apellidos.");
		}
		return listaClientes;
	}

	
	/**   
	 * <p>Title: consultarClientesPorNombre</p>   
	 * <p>Description: </p>   
	 * @param nombre
	 * @return
	 * @throws SQLException   
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#consultarClientesPorNombre(java.lang.String)   
	 */
	
	@Override
	public List<ClienteDTO> consultarClientesPorNombre(String nombre) throws Exception {
		List<ClienteDTO> listaClientes = null;
		if(nombre == null || nombre.trim().equals("")) {
			throw new Exception("Debe ingresar un nombre.");
		}
		if(Utilities.isStringLenght(nombre, Constantes.TAMANONOMBRE)) {
			throw new Exception("Debe ingresar un nombre con menos de 100 caracteres.");
		}
		listaClientes = clienteRepository.consultarClientesPorNombre(nombre.toUpperCase());
		if(listaClientes.isEmpty()) {
			throw new Exception("No hay clientes asociados con ese nombre.");
		}
		return listaClientes;
	}

	

	//TODO: CRUD

	/**
	 * 
	 * <p>Title: guardarCliente</p>   
	 * <p>Description: Metodo que guarda un cliente 
	 * @param clienteDTO
	 * @throws Exception   
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#guardarCliente(co.edu.usbcali.viajesusb.dto.ClienteDTO)
	 */
	
	@Override
	public Cliente guardarCliente(ClienteDTO clienteDTO) throws Exception  {
		
		Cliente cliente= null;
	    TipoIdentificacion tipoIdentificacion = null;
		
		// Aqui_van_validaciones
	    
	    //validamos que no hayan clientes con esa misma identificacion
	    Cliente clienteDB = findByNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());
	    
	    if (clienteDB != null) {
			throw new Exception("Ya existe un cliente con ese numero de identificacion.");
		}
	    if (clienteDTO.getPrimerApellido() == null || clienteDTO.getPrimerApellido().trim().equals("")
				|| Utilities.isStringLenght(clienteDTO.getPrimerApellido(), Constantes.TAMANOAPELLIDOS)) {
			throw new Exception("Introduzca un primer apellido valido.");
		}
	    if (clienteDTO.getSegundoApellido() == null || clienteDTO.getSegundoApellido().trim().equals("")
				|| Utilities.isStringLenght(clienteDTO.getSegundoApellido(), Constantes.TAMANOAPELLIDOS)) {
			throw new Exception("Introduzca un segundo apellido valido.");
		}
	    if (clienteDTO.getNombre() == null || clienteDTO.getNombre().trim().equals("")
				|| Utilities.isStringLenght(clienteDTO.getNombre(), Constantes.TAMANONOMBRE)) {
			throw new Exception("Digite un nombre valido.");
		}
	    if (Utilities.isStringLenght(clienteDTO.getTelefono1(), Constantes.TAMANOTELEFONOS) 
	    		|| (Utilities.notStringInteger(clienteDTO.getTelefono1()))|| Utilities.isSpecialCaracter(clienteDTO.getTelefono1())) {
			throw new Exception("Digite un numero de telefono No.1 valido.");
		}
	    if (Utilities.isStringLenght(clienteDTO.getTelefono2(), Constantes.TAMANOTELEFONOS) 
	    		|| (Utilities.notStringInteger(clienteDTO.getTelefono2()))|| Utilities.isSpecialCaracter(clienteDTO.getTelefono2())) {
			throw new Exception("Digite un numero de telefono No.2 valido.");
		}
	    if (clienteDTO.getCorreo() == null || clienteDTO.getCorreo().trim().equals("")
				|| Utilities.isStringLenght(clienteDTO.getCorreo(), Constantes.TAMANOCORREO)
				) {
			throw new Exception("Introduzca un correo valido.");
		}
	    if(!Utilities.formatoCorreoValido(clienteDTO.getCorreo())) {
	    	throw new Exception("Introduzca un correo valido.");
	    }
		if (clienteDTO.getSexo() == null || clienteDTO.getSexo().trim().equals("") ||
				Utilities.isStringLenght(clienteDTO.getSexo(), Constantes.TAMANNOSEXO) || !Utilities.isStringInteger(clienteDTO.getSexo())) {
			throw new Exception("Digite un sexo valido (M/F)");
		}
		if (clienteDTO.getFechaNacimiento() == null || (clienteDTO.getFechaNacimiento().compareTo(new Date())>0)){
			throw new Exception("Digite una fecha de nacimiento valida.");
		}
		if (clienteDTO.getFechaCreacion() == null) {
			throw new Exception("Digite una fecha de creacion valida.");
		}
		if (clienteDTO.getUsuCreador() == null || clienteDTO.getUsuCreador().trim().equals("") 
				||Utilities.isStringLenght(clienteDTO.getUsuCreador(), Constantes.TAMANOUSU)) {
			throw new Exception("Usuario creador invalido.");
		}
		if (clienteDTO.getEstado() == null || clienteDTO.getEstado().trim().equals("") || 
				Utilities.isStringLenght(clienteDTO.getEstado(), Constantes.TAMANNOESTADO) 
				|| !Utilities.isStringInteger(clienteDTO.getEstado())|| !Utilities.estadoAoI(clienteDTO.getEstado())) {
			throw new Exception("Estado invalido.");
		}
		
		// se_crea_el_destino
		cliente = new Cliente();

		cliente.setNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());
		cliente.setPrimerApellido(clienteDTO.getPrimerApellido());
		cliente.setSegundoApellido(clienteDTO.getSegundoApellido());
		cliente.setNombre(clienteDTO.getNombre());
		cliente.setTelefono1(clienteDTO.getTelefono1());
		cliente.setTelefono2(clienteDTO.getTelefono2());
		cliente.setCorreo(clienteDTO.getCorreo());
		cliente.setSexo(clienteDTO.getSexo());
		cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());
		cliente.setFechaCreacion(clienteDTO.getFechaCreacion());
		cliente.setFechaModificacion(clienteDTO.getFechaModificacion());
		cliente.setUsuCreador(clienteDTO.getUsuCreador());
		cliente.setUsuModificador(clienteDTO.getUsuModificador());
		cliente.setEstado(clienteDTO.getEstado());
		

		// se_consulta_el_tipo_identificacion_dado_su_id
		tipoIdentificacion =  tipoIdenticacionService.findByCodigoAndEstado(clienteDTO.getTipoIdentificacionCodigo(),Constantes.ACTIVO);
		
		// validamos_que_el_tipo_destino_exista_y_este_activo
		if(tipoIdentificacion == null) {
			throw new Exception("El tipo identificación" + clienteDTO.getTipoIdentificacionCodigo()+ " No existe");
		}
		cliente.setTipoIdentificacion(tipoIdentificacion);
		// se_guarda_el_objeto_de_cliente
		clienteRepository.save(cliente);
		return cliente;
	}

	
	@Override
	public Cliente actualizarCliente(ClienteDTO clienteDTO) throws Exception  {
		
		Cliente cliente= null;
	    TipoIdentificacion tipoidentificacion = null;
		
		// Aqui_van_validaciones
	    if (clienteDTO.getIdCliente() == null) {
			throw new Exception("Debe ingresar un id de cliente valido para actualizar.");
			
		}
	    
	    Optional <Cliente> clienteDB = Optional.of(findById(clienteDTO.getIdCliente()));
	    
	    if (clienteDB.isEmpty()) {
			throw new Exception("No existe un cliente con ese Id.");
		}
	    if (clienteDTO.getPrimerApellido() == null || clienteDTO.getPrimerApellido().trim().equals("")
				|| Utilities.isStringLenght(clienteDTO.getPrimerApellido(), Constantes.TAMANOAPELLIDOS)) {
			throw new Exception("Introduzca un primer apellido valido.");
		}
	    if (clienteDTO.getSegundoApellido() == null || clienteDTO.getSegundoApellido().trim().equals("")
				|| Utilities.isStringLenght(clienteDTO.getSegundoApellido(), Constantes.TAMANOAPELLIDOS)) {
			throw new Exception("Introduzca un segundo apellido valido.");
		}
	    if (clienteDTO.getNombre() == null || clienteDTO.getNombre().trim().equals("")
				|| Utilities.isStringLenght(clienteDTO.getNombre(), Constantes.TAMANONOMBRE)) {
			throw new Exception("Digite un nombre valido.");
		}
	    if (Utilities.isStringLenght(clienteDTO.getTelefono1(), Constantes.TAMANOTELEFONOS) 
	    		|| Utilities.isStringInteger(clienteDTO.getTelefono1())|| Utilities.isSpecialCaracter(clienteDTO.getTelefono1())) {
			throw new Exception("Digite un numero de telefono valido.");
		}
	    if (Utilities.isStringLenght(clienteDTO.getTelefono2(), Constantes.TAMANOTELEFONOS) 
	    		|| Utilities.isStringInteger(clienteDTO.getTelefono2())|| Utilities.isSpecialCaracter(clienteDTO.getTelefono2())) {
			throw new Exception("Digite un numero de telefono valido.");
		}
	    if (clienteDTO.getCorreo() == null || clienteDTO.getCorreo().trim().equals("")
				|| Utilities.isStringLenght(clienteDTO.getCorreo(), Constantes.TAMANOCORREO)
				|| !Utilities.formatoCorreoValido(clienteDTO.getCorreo())) {
			throw new Exception("Introduzca un correo valido.");
		}
		if (clienteDTO.getSexo() == null || clienteDTO.getSexo().trim().equals("") ||
				Utilities.isStringLenght(clienteDTO.getSexo(), Constantes.TAMANNOSEXO) || !Utilities.isStringInteger(clienteDTO.getSexo())) {
			throw new Exception("Digite un sexo valido (M/F)");
		}
		if (clienteDTO.getFechaNacimiento() == null || (clienteDTO.getFechaNacimiento().compareTo(new Date())>0)){
			throw new Exception("Digite una fecha de nacimiento valida.");
		}
		if (clienteDTO.getFechaCreacion() == null) {
			throw new Exception("Digite una fecha de creacion valida.");
		}
		if (clienteDTO.getUsuCreador() == null || clienteDTO.getUsuCreador().trim().equals("") 
				||Utilities.isStringLenght(clienteDTO.getUsuCreador(), Constantes.TAMANOUSU)) {
			throw new Exception("Usuario creador invalido.");
		}
		if (clienteDTO.getEstado() == null || clienteDTO.getEstado().trim().equals("") || 
				Utilities.isStringLenght(clienteDTO.getEstado(), Constantes.TAMANNOESTADO) || 
				!Utilities.isStringInteger(clienteDTO.getEstado())|| !Utilities.estadoAoI(clienteDTO.getEstado())) {
			throw new Exception("Estado invalido.");
		}
		
	    // se_busca_por_id
		cliente = findById(clienteDTO.getIdCliente());
		
		cliente.setNumeroIdentificacion(clienteDTO.getNumeroIdentificacion());
		cliente.setPrimerApellido(clienteDTO.getPrimerApellido());
		cliente.setSegundoApellido(clienteDTO.getSegundoApellido());
		cliente.setNombre(clienteDTO.getNombre());
		cliente.setTelefono1(clienteDTO.getTelefono1());
		cliente.setTelefono2(clienteDTO.getTelefono2());
		cliente.setCorreo(clienteDTO.getCorreo());
		cliente.setSexo(clienteDTO.getSexo());
		cliente.setFechaNacimiento(clienteDTO.getFechaNacimiento());
		cliente.setFechaCreacion(clienteDTO.getFechaCreacion());
		cliente.setFechaModificacion(clienteDTO.getFechaModificacion());
		cliente.setUsuCreador(clienteDTO.getUsuCreador());
		cliente.setUsuModificador(clienteDTO.getUsuModificador());
		cliente.setEstado(clienteDTO.getEstado());
		
		// se_consulta_el_tipo_identificacion_dado_su_id
		tipoidentificacion =  tipoIdenticacionService.findByCodigoAndEstado(clienteDTO.getTipoIdentificacionCodigo(),Constantes.ACTIVO);
		// validamos_que_el_tipo_destino_exista_y_este_activo
		
		if(tipoidentificacion == null) {
			throw new Exception("El tipo identificación" + clienteDTO.getTipoIdentificacionCodigo()+ " No existe");
		}
		cliente.setTipoIdentificacion(tipoidentificacion);
		// se_guarda_el_objeto_de_cliente
		clienteRepository.save(cliente);
		return cliente;
	}

	@Override
	public void eliminarCliente(Long idCliente) throws Exception{
		// validar que se ingrese el id cliente a eliminar
		
		if(idCliente== null ) {
			throw new Exception("El id cliente es obligatorio");
		}
//		Optional<Cliente> clienteDB = clienteRepository.findById(clienteDTO.getIdCliente());
//		if(clienteDB.isPresent()) {
//			clienteRepository.delete(clienteDB.get());
//		}else {
//			throw new Exception("El cliente no se encontro");
//		}
		if(clienteRepository.existsById(idCliente)==false) {
			throw new Exception("El cliente no se encontro");
		}
		clienteRepository.findById(idCliente).ifPresent(cliente->{
			if(cliente.getPlan().isEmpty()==false) {
				throw new RuntimeException("El cliente esta asignado en un plan, por lo que no se puede eliminar");
			}
			
		});

		clienteRepository.deleteById(idCliente);
		
	}


	
	/**   
	 * <p>Title: findByNumeroIdentificacionAndEstado</p>   
	 * <p>Description: </p>   
	 * @param numeroIdentificacionCliente
	 * @param activo
	 * @return   
	 * @see co.edu.usbcali.viajesusb.service.ClienteService#findByNumeroIdentificacionAndEstado(java.lang.String, java.lang.String)   
	 */
	
	@Override
	public Cliente findByNumeroIdentificacionAndEstado(String numeroIdentificacionCliente, String estado) throws Exception  {
		// TODO Auto-generated method stub
		Cliente cliente = null;
		if(numeroIdentificacionCliente ==null || numeroIdentificacionCliente.trim().equals("") || estado ==null || estado.trim().equals("")) {
			throw new Exception("Debe ingresar un numero Identificacion y un estado.");
		}
		if(Utilities.isStringLenght(numeroIdentificacionCliente,Constantes.TAMANOIDENTIFICACION)||Utilities.isStringLenght(estado,Constantes.TAMANNOESTADO)) {
			throw new Exception("El numero de identificacion no puede contener más de 15 caracteres y el estado un solo caracter.");
		}
		if(Utilities.isStringInteger(numeroIdentificacionCliente)|!Utilities.isStringInteger(estado)) {
			throw new Exception("Debe ingresar numeros para la identificacion y una letra para el estado.");
		}
		
		cliente = clienteRepository.findByNumeroIdentificacionAndEstado(numeroIdentificacionCliente, estado.toUpperCase());
		if(cliente == null) {
			throw new Exception("No se encontraron clientes con el numero de identificación y estado ingresado.");
		}
		return cliente;
	}


	

}
