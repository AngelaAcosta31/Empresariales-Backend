/**  
 * @Title:  UsuarioServiceImpl.java   
 * @Package co.edu.usbcali.viajesusb.service   
 * @Description: description   
 * @author: Ángela_Acosta    
 * @date:   8/09/2021 10:14:10 p. m.   
 * @version V1.0 
 * @Copyright: Universidad_San_de_Buenaventura
 */

package co.edu.usbcali.viajesusb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import co.edu.usbcali.viajesusb.domain.Usuario;
import co.edu.usbcali.viajesusb.dto.UsuarioDTO;
import co.edu.usbcali.viajesusb.repository.UsuarioRepository;
import co.edu.usbcali.viajesusb.utils.Constantes;
import co.edu.usbcali.viajesusb.utils.Utilities;

/**   
 * @ClassName:  UsuarioServiceImpl   
  * @Description: TODO   
 * @author: Ángela_Acosta    
 * @date:   8/09/2021 10:14:10 p. m.      
 * @Copyright:  USB
 */
@Scope("singleton")
@Service
public class UsuarioServiceImpl implements UsuarioService {

	/**
	 * Inyeccion_de_dependencia
	 */
	@Autowired
	private UsuarioRepository usuarioRepository;
	private Usuario usuario = null;	
	
	public Usuario findById(Long idUsua) throws Exception{
		//validamos el id usuario venga con info
		if(idUsua ==null) {
			throw new Exception("Debe ingresar un ID usuario");
			
		}
		if(!usuarioRepository.findById(idUsua).isPresent()) {
			throw new Exception ("El usuario con id: "+idUsua+ " no existe");
		}
		return usuarioRepository.findById(idUsua).get();
	}
	/**   
	 * <p>Title: findByIdentificacion</p>   
	 * <p>Description: </p>   
	 * @param identificacion
	 * @return
	 * @throws Exception   
	 * @see co.edu.usbcali.viajesusb.service.UsuarioService#findByIdentificacion(java.lang.String)   
	 */	
	@Override
	public Usuario findByIdentificacion(String identificacion) throws Exception {
		
		if(identificacion ==null || identificacion.trim().equals("")) {
			throw new Exception("Debe ingresar una identificación.");
		}
		if(Utilities.isStringLenght(identificacion, Constantes.TAMANOIDENTIFICACION)) {
			throw new Exception("La identificación no debe tener más de 15 caracteres.");
		}
		if(Utilities.isStringInteger(identificacion)) {
			throw new Exception("La identificación solo deben ser números.");
		}
		if(Utilities.isSpecialCaracter(identificacion)) {
			throw new Exception("El número de identificación no debe contener caracteres especiales.");
		}
		usuario = usuarioRepository.findByIdentificacion(identificacion);
		if(usuario ==null) {
			throw new Exception("No se encontraron usuarios con esa identificación.");
		}
		return usuario;
	}
	/**   
	 * <p>Title: findByEstado</p>   
	 * <p>Description: </p>   
	 * @param estado
	 * @return
	 * @throws Exception   
	 * @see co.edu.usbcali.viajesusb.service.UsuarioService#findByEstado(java.lang.String)   
	 */
	
	@Override
	public List<Usuario> findByEstado(String estado) throws Exception {
		List<Usuario> listaUsuarios = null; 
		if(estado ==null || estado.trim().equals("")) {
			throw new Exception("Debe ingresar un estado.");
		}
		if(Utilities.isStringLenght(estado, Constantes.TAMANNOESTADO)) {
			throw new Exception("El estado solo es un caracter.");
		}
		if(!Utilities.isStringInteger(estado)) {
			throw new Exception("Debe ingresar una letra y no un número.");
		}
		
		listaUsuarios = usuarioRepository.findByEstado(estado.toUpperCase());
		if(listaUsuarios.isEmpty()) {
			throw new Exception("No se encontraron usuarios con ese estado.");
		}
		
		return listaUsuarios;
	}


	@Override
	public Usuario findByLogin(String login) throws Exception {
		
		if(login ==null || login.trim().equals("")) {
			throw new Exception("Debe ingresar un login valido.");
		}
		if(Utilities.isStringLenght(login, Constantes.TAMANOLOGIN)) {
			throw new Exception("El login debe contener menos de 10 caracteres.");
		}
		usuario = usuarioRepository.findByLogin(login.toUpperCase());
		return usuario;
	}

	@Override
	public Usuario findByLoginAndEstado(String login, String estado) throws Exception {
		if(login ==null || login.trim().equals("") || estado ==null || estado.trim().equals("")) {
			throw new Exception("Debe ingresar un login y un estado.");
		}
		if(Utilities.isStringLenght(login, Constantes.TAMANOLOGIN)||Utilities.isStringLenght(estado, Constantes.TAMANNOESTADO)) {
			throw new Exception("El login no puede contener más de 10 caracteres y el estado un solo caracter.");
		}
		if(!Utilities.isStringInteger(estado)) {
			throw new Exception("Debe ingresar una letra y no un numero para el estado.");
		}
		usuario = usuarioRepository.findByLoginAndEstado(login.toUpperCase(), estado.toUpperCase());
		return usuario;
	}
	//TODO:CRUD
	@Override
	public Usuario guardarUsuario(UsuarioDTO usuarioDTO) throws Exception{
		
		Usuario usuario = null;
		// validaciones
		Usuario usuarioDB = findByLogin(usuarioDTO.getLogin().trim());
		if (usuarioDB != null) {
			throw new Exception("Ya existe un usuario con este codigo.");
		}
		if (usuarioDTO.getLogin() == null || usuarioDTO.getLogin().trim().equals("")
				|| Utilities.isStringLenght(usuarioDTO.getLogin(), Constantes.TAMANOLOGIN)) {
			throw new Exception("Digite un login valido.");
		}
		if (usuarioDTO.getPassword() == null || usuarioDTO.getPassword().trim().equals("")
				|| Utilities.isStringLenght(usuarioDTO.getPassword(), Constantes.TAMANOPASSWORD)) {
			throw new Exception("Digite un password valido.");
		}
		if (usuarioDTO.getNombre() == null || usuarioDTO.getNombre().trim().equals("")
				|| Utilities.isStringLenght(usuarioDTO.getNombre(), Constantes.TAMANONOMBRE)) {
			throw new Exception("Digite un nombre valido.");
		}
		if (usuarioDTO.getIdentificacion() == null || usuarioDTO.getIdentificacion().trim().equals("")
				|| Utilities.isStringLenght(usuarioDTO.getIdentificacion(), Constantes.TAMANONOMBRE)
				|| Utilities.isStringInteger(usuarioDTO.getIdentificacion()) ||Utilities.isSpecialCaracter(usuarioDTO.getIdentificacion())) {
			throw new Exception("Digite un numero de identificacion valido.");
		}
		if (usuarioDTO.getFechaCreacion() == null) {
			throw new Exception("Digite una fecha de creacion valida.");
		}
		if (usuarioDTO.getUsuCreador() == null || usuarioDTO.getUsuCreador().trim().equals("") 
				||Utilities.isStringLenght(usuarioDTO.getUsuCreador(), Constantes.TAMANOUSU)) {
			throw new Exception("Usuario creador invalido.");
		}
		if (usuarioDTO.getEstado() == null || usuarioDTO.getEstado().trim().equals("") || 
				Utilities.isStringLenght(usuarioDTO.getEstado(), Constantes.TAMANNOESTADO) ||
				!Utilities.isStringInteger(usuarioDTO.getEstado())) {
			throw new Exception("Estado invalido.");
		}
		
	
		//se crea el usuario
		usuario = new Usuario();
		usuario.setLogin(usuarioDTO.getLogin());
		usuario.setPassword(usuarioDTO.getPassword());
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setIdentificacion(usuarioDTO.getIdentificacion());
		usuario.setFechaCreacion(usuarioDTO.getFechaCreacion());
		usuario.setFechaModificacion(usuarioDTO.getFechaModificacion());
		usuario.setUsuCreador(usuarioDTO.getUsuCreador());
		usuario.setUsuModificador(usuarioDTO.getUsuModificador());
		usuario.setEstado(usuarioDTO.getEstado());
		usuarioRepository.save(usuario);
		
		return usuario;
	}

	@Override
	public Usuario actualizarUsuario(UsuarioDTO usuarioDTO) throws Exception{
		
		Usuario usuario = null;
		//validaciones
		if (usuarioDTO.getIdUsuario() == null) {
			throw new Exception("Debe ingesar un id de usuario valido para actualizar");
		}
		
		Optional <Usuario> usuarioDB = Optional.of(findById(usuarioDTO.getIdUsuario()));
		
		if(usuarioDB.isEmpty()) {
			throw new Exception("No existe un usuario con este id.");
		}
		if (usuarioDTO.getLogin() == null || usuarioDTO.getLogin().trim().equals("")
				|| Utilities.isStringLenght(usuarioDTO.getLogin(), Constantes.TAMANOLOGIN)) {
			throw new Exception("Digite un login valido.");
		}
		if (usuarioDTO.getPassword() == null || usuarioDTO.getPassword().trim().equals("")
				|| Utilities.isStringLenght(usuarioDTO.getPassword(), Constantes.TAMANOPASSWORD)) {
			throw new Exception("Digite un password valido.");
		}
		if (usuarioDTO.getNombre() == null || usuarioDTO.getNombre().trim().equals("")
				|| Utilities.isStringLenght(usuarioDTO.getNombre(), Constantes.TAMANONOMBRE)) {
			throw new Exception("Digite un nombre valido.");
		}
		if (usuarioDTO.getIdentificacion() == null || usuarioDTO.getIdentificacion().trim().equals("")
				|| Utilities.isStringLenght(usuarioDTO.getIdentificacion(), Constantes.TAMANONOMBRE)
				|| Utilities.isStringInteger(usuarioDTO.getIdentificacion()) ||Utilities.isSpecialCaracter(usuarioDTO.getIdentificacion())) {
			throw new Exception("Digite un numero de identificacion valido.");
		}
		if (usuarioDTO.getFechaCreacion() == null) {
			throw new Exception("Digite una fecha de creacion valida.");
		}
		if (usuarioDTO.getUsuCreador() == null || usuarioDTO.getUsuCreador().trim().equals("") 
				||Utilities.isStringLenght(usuarioDTO.getUsuCreador(), Constantes.TAMANOUSU)) {
			throw new Exception("Usuario creador invalido.");
		}
		if (usuarioDTO.getEstado() == null || usuarioDTO.getEstado().trim().equals("") || 
				Utilities.isStringLenght(usuarioDTO.getEstado(), Constantes.TAMANNOESTADO) ||
				!Utilities.isStringInteger(usuarioDTO.getEstado())) {
			throw new Exception("Estado invalido.");
		}
		
		usuario = findById(usuarioDTO.getIdUsuario());
		usuario.setLogin(usuarioDTO.getLogin());
		usuario.setPassword(usuarioDTO.getPassword());
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setIdentificacion(usuarioDTO.getIdentificacion());
		usuario.setFechaCreacion(usuarioDTO.getFechaCreacion());
		usuario.setFechaModificacion(usuarioDTO.getFechaModificacion());
		usuario.setUsuCreador(usuarioDTO.getUsuCreador());
		usuario.setUsuModificador(usuarioDTO.getUsuModificador());
		usuario.setEstado(usuarioDTO.getEstado());
		usuarioRepository.save(usuario);
		return usuario;
	}
	
	@Override
	public void eliminarUsuario(Long idUsuario) throws Exception{
		if(idUsuario== null) {
			throw new Exception("El id usuario es obligatorio");
		}
		if(usuarioRepository.existsById(idUsuario)==false) {
			throw new Exception("El usuario no se encontro");
		}
		usuarioRepository.findById(idUsuario).ifPresent(usuario->{
			if(usuario.getPlan().isEmpty()==false) {
				throw new RuntimeException("El usuario esta asignado en un plan, por lo que no se puede eliminar");
			}
			
		});

		usuarioRepository.deleteById(idUsuario);
	}

}
