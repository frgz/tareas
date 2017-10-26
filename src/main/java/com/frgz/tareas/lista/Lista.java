/**
 * 
 */
package com.frgz.tareas.lista;

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

import com.frgz.tareas.core.domain.EntidadAuditable;
import com.frgz.tareas.usuario.Usuario;

/**
 * @author fabio
 *
 */
@Entity
@Table(name = "lista")
public class Lista extends EntidadAuditable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", columnDefinition = "int(11)")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 100)
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "activa")
	private Boolean activa;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "propietario_id", referencedColumnName = "id", nullable = false, columnDefinition = "int(11)")
	private Usuario propietario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getActiva() {
		return activa;
	}

	public void setActiva(Boolean activa) {
		this.activa = activa;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Lista lista = (Lista) o;

		return id != null ? id.equals(lista.id) : lista.id == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (id != null ? id.hashCode() : 0);
		return result;
	}
}
