package co.edu.usbcali.viajesusb.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_usua")
	public Long idUsuario;
	
	@Column(name="login", nullable=false,length = 10)
	public String login;
	
	@Column(name="password",nullable=false,length = 50)
	public String password;
	
	@Column(name="nombre", nullable=false,length = 100)
	public String nombre;
	
	@Column(name="identificacion",unique=true, nullable=false,length = 15)
	public String identificacion;
	
	@Column(name="fecha_creacion",nullable=false)
	public Date fechaCreacion;
	
	@Column(name="fecha_modificacion")
	public Date fechaModificacion;
	
	@Column(name="usu_creador",nullable=false,length = 10)
	public String usuCreador;
	
	@Column(name="usu_modificador",length = 10)
	public String usuModificador;
	
	@Column(name="estado",nullable=false,length = 1)
	public String estado;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public List<Plan> plan = new ArrayList<>();

}
