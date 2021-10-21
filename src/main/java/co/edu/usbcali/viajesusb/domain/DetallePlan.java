package co.edu.usbcali.viajesusb.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//import co.edu.usbcali.viajesusb.domain.Destino;
//import co.edu.usbcali.viajesusb.domain.Plan;
import lombok.Data;

@Data
@Entity
@Table(name="detalle_plan")
public class DetallePlan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_depl")
	public Long idDetallePlan;
	
	@Column(name="alimentacion",nullable=false,length = 1)
	public String alimentacion;
	
	@Column(name="hospedaje",nullable=false,length = 1)
	public String hospedaje;
	
	@Column(name="transporte",nullable=false,length = 1)
	public String transporte;
	
	@Column(name="traslados",nullable=false,length = 1)
	public String traslados;
	
	@Column(name="valor",nullable=false, precision=19, scale=2)
	public Double valor;
	
	@Column(name="cantidad_noches",nullable=false)
	public Integer cantidadNoches;
	
	@Column(name="cantidad_dias",nullable=false)
	public Integer cantidadDias;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_dest", nullable = false)
	public Destino destino;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_plan", nullable = false)
	public Plan plan;
	
}
