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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import co.edu.usbcali.viajesusb.domain.Cliente;
//import co.edu.usbcali.viajesusb.domain.Usuario;
import lombok.Data;

@Data
@Entity
@Table(name="plan")
public class Plan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_plan")
	public Long idPlan;
	
	@Column(name="codigo", unique = true, nullable = false, length = 5)
	public String codigo;
	
	@Column(name="nombre", nullable = false, length = 100)
	public String nombre;
	//Duda por lo que aparece en el MER y lo que aparece en los Scripts
	@Column(name="descripcion_solicitud",nullable= false, length=300)
	public String descripcionSolicitud;
	
	@Column(name="cantidad_personas", nullable=false)
	public Integer cantidadPersonas;
	
	@Column(name="fecha_solicitud", nullable=false)
	public Date fechaSolicitud;
	
	@Column(name="fecha_inicio_viaje")
	public Date fechaInicioViaje;
	
	@Column(name="fecha_fin_viaje")
	public Date fechaFinViaje;
	
	@Column(name="valor_total", nullable = false, precision=19, scale=2)
	public Double valorTotal;
	
	@Column(name="fecha_creacion", nullable=false)
	public Date fechaCreacion;
	
	@Column(name="fecha_modificacion")
	public Date fechaModificacion;
	
	@Column(name="usu_creador", nullable = false, length =10)
	public String usuCreador;
	
	@Column(name="usu_modificador", length = 10)
	public String usuModificador;
	
	@Column(name="estado", nullable = false, length = 1)
	public String estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_usua", nullable = false)
	public Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_clie",nullable = false)
	public Cliente cliente;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "plan")
	public List<DetallePlan> detallePlan = new ArrayList<>();
	
}
