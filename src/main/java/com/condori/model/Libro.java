package com.condori.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_libro")
public class Libro {

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="LIB_ID")
	private int idlibro;
	
	@Column(name="LIB_TIT")
	private String titulo;
	
	@Column(name="LIB_PRE")
	private Double precio;
	
	@Column(name="LIB_CAN")
	private int cantEjemplares;
	
	@Column(name="LIB_ORG")
	private String origen;
		
	@ManyToOne
	@JoinColumn(name="TEM_ID")
	private Tema tema;
	
	public Libro() {}

	public Libro(String titulo, Double precio, int cantEjemplares, String origen) {
		super();
		this.titulo = titulo;
		this.precio = precio;
		this.cantEjemplares = cantEjemplares;
		this.origen = origen;
	}

	public Libro(String titulo, Double precio, int cantEjemplares, String origen, Tema tema) {
		super();
		this.titulo = titulo;
		this.precio = precio;
		this.cantEjemplares = cantEjemplares;
		this.origen = origen;
		this.tema = tema;
	}

	public int getIdlibro() {
		return idlibro;
	}

	public void setIdlibro(int idlibro) {
		this.idlibro = idlibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public int getCantEjemplares() {
		return cantEjemplares;
	}

	public void setCantEjemplares(int cantEjemplares) {
		this.cantEjemplares = cantEjemplares;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
	
	
}
