package com.frgz.tareas.tarea;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.frgz.tareas.lista.Lista;
import com.frgz.tareas.usuario.Usuario;

/**
 * 
 * @author fabio
 *
 */
@Entity
@Table(name = "tarea")
public class Tarea implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", columnDefinition = "int(11)")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "lista_id", referencedColumnName = "id", nullable = false, columnDefinition = "int(11)")
	private Lista lista;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "propietario_id", referencedColumnName = "id", nullable = false, columnDefinition = "int(11)")
	private Usuario propietario;

	@NotNull
	@Size(max = 100)
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Size(max = 4000)
	@Column(name = "descripcion", length = 4000)
	private String descripcion;

	@Column(name = "completada")
	private Boolean completada;

	@Column(name = "fecha_realizacion")
	private Date fechaRealizacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Lista getLista() {
		return lista;
	}

	public void setLista(Lista lista) {
		this.lista = lista;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getCompletada() {
		return completada;
	}

	public void setCompletada(Boolean completada) {
		this.completada = completada;
	}

	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Tarea tarea = (Tarea) o;

		return id != null ? id.equals(tarea.id) : tarea.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
