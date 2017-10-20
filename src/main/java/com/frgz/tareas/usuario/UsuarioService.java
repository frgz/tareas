package com.frgz.tareas.usuario;

import com.frgz.tareas.usuario.exception.UsuarioNoEncontradoException;

public interface UsuarioService {

	Usuario findByEmail(String email) throws UsuarioNoEncontradoException;

	Usuario findOne(Long id) throws UsuarioNoEncontradoException;

}