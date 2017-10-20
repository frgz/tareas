package com.frgz.tareas.lista;

import java.util.List;

import com.frgz.tareas.lista.exception.ListaNoEncontradaException;
import com.frgz.tareas.usuario.exception.UsuarioNoEncontradoException;

public interface ListaService {

	List<Lista> visualizar(String nombre);

	void guardar(Lista lista);

	void eliminar(Long id);

	Lista crear() throws UsuarioNoEncontradoException;

	Lista obtener(Long id) throws ListaNoEncontradaException;

}