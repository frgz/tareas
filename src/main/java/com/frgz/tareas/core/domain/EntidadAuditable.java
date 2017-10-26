/**
 * 
 */
package com.frgz.tareas.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author fabio
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class EntidadAuditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @CreatedBy
    @Column(name = "usuario_creacion", length = 50, nullable = false)
    private String usuarioCreacion;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion", nullable = false)
    private Date fechaCreacion;

    @LastModifiedBy
    @Column(name = "usuario_ultima_modificacion", length = 50)
    private String usuarioUltimaModificacion;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_ultima_modificacion")
    private Date fechaUltimaModificacion;

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioUltimaModificacion() {
		return usuarioUltimaModificacion;
	}

	public void setUsuarioUltimaModificacion(String usuarioUltimaModificacion) {
		this.usuarioUltimaModificacion = usuarioUltimaModificacion;
	}

	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

}
