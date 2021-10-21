package co.edu.usbcali.viajesusb.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import co.edu.usbcali.viajesusb.dto.ClienteDTO;
//import co.edu.usbcali.viajesusb.domain.TipoIdentificacion;
import lombok.Data;

@NamedNativeQueries({
	
	/*
	 * Se_especifica_cada_query_que_tengamos_en_el_archivo_orm
	 * El name_es_como_lo_declaramos_en_el_orm_y_el_result_tambien
	 * para_poner_mas_querys_solo_agregamos_una_coma_y_continuamos_agregando_los @NamedNativeQuery
	 */
		@NamedNativeQuery(name="Cliente.consultarClientesPorNombre", query="", resultSetMapping = "consultarClientesPorNombre")
})

@SqlResultSetMappings({
	@SqlResultSetMapping(name="consultarClientesPorNombre",
			classes = { @ConstructorResult(targetClass = ClienteDTO.class,
			columns= {
					@ColumnResult(name="numeroIdentificacion", type= String.class),
					@ColumnResult(name="nombreC", type= String.class),
					@ColumnResult(name="primerApellido", type= String.class),
					@ColumnResult(name="segundoApellido", type= String.class),
					@ColumnResult(name="estado", type= String.class),
					@ColumnResult(name="tipoIdentificacion", type= Integer.class)
			})
	
			})

})


@Data
@Entity
@Table(name="cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_clie")
	public Long idCliente;
	
	@Column(name="numero_identificacion", unique = true, nullable=false,length = 15)
	public String numeroIdentificacion;
	
	@Column(name="primer_apellido",nullable=false,length = 100)
	public String primerApellido;
	
	@Column(name="segundo_apellido", length = 100)
	public String segundoApellido;
	
	@Column(name="nombre", nullable=false,length = 100)
	public String nombre;
	
	@Column(name="telefono1",length = 15)
	public String telefono1;
	
	@Column(name="telefono2",length = 15)
	public String telefono2;
	
	@Column(name="correo",length = 100)
	public String correo;
	
	@Column(name="sexo",nullable=false,length = 1)
	public String sexo;
	
	@Column(name="fecha_nacimiento",nullable=false)
	public Date fechaNacimiento;
	
	@Column(name="fecha_creacion", nullable=false)
	public Date fechaCreacion;
	
	@Column(name="fecha_modificacion")
	public Date fechaModificacion;
	
	@Column(name="usu_creador",nullable=false,length = 10)
	public String usuCreador;
	
	@Column(name="usu_modificador", length = 10)
	public String usuModificador;
	
	@Column(name="estado",nullable=false,length = 1)
	public String estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_tiid",nullable = false)
	public TipoIdentificacion tipoIdentificacion;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	public List<Plan> plan = new ArrayList<>();
	
	
}
