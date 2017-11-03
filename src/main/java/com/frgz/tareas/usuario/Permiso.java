package com.frgz.tareas.usuario;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 */
public class Permiso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", columnDefinition = "int(11)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 15)
    @Column(name = "codigo", length = 15, unique = true, nullable = false, columnDefinition = "char")
    private String codigo;

    @NotNull
    @Size(max = 250)
    @Column(name = "nombre", nullable = false, length = 250, unique = true)
    private String nombre;
}
