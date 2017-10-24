/**
 * 
 */
package com.frgz.tareas.lista;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frgz.tareas.core.authentication.AuthenticationFacade;
import com.frgz.tareas.lista.exception.ListaNoEncontradaException;
import com.frgz.tareas.lista.exception.ListaYaExisteException;
import com.frgz.tareas.usuario.exception.UsuarioNoEncontradoException;

/**
 * @author fabio
 *
 */
@Service
@Transactional(readOnly = true)
class ListaServiceImpl implements ListaService {

	private ListaRepository listaRepository;
	private AuthenticationFacade autenticationFacade;

	@Autowired
	public ListaServiceImpl(ListaRepository listaRepository, AuthenticationFacade autenticationFacade) {
		super();
		this.listaRepository = listaRepository;
		this.autenticationFacade = autenticationFacade;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.frgz.tareas.lista.ListaService#visualizar(java.lang.String)
	 */
	@Override
	public List<Lista> visualizar(String nombre) {
		return this.listaRepository.findByNombreContaining(nombre);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.frgz.tareas.lista.ListaService#guardar(com.frgz.tareas.lista.Lista)
	 */
	@Override
	@Transactional(readOnly = false)
	public void guardar(Lista lista) throws ListaYaExisteException {
		if (lista.getId() == null) {
			if (existeNombreLista(lista.getNombre())) {
				throw new ListaYaExisteException(lista.getNombre());
			}
		} else {
			if (existeNombreListaOtros(lista.getNombre(), lista.getId())) {
				throw new ListaYaExisteException(lista.getNombre());
			}
		}
		this.listaRepository.save(lista);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.frgz.tareas.lista.ListaService#eliminar(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = false)
	public void eliminar(Long id) {
		this.listaRepository.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.frgz.tareas.lista.ListaService#crear()
	 */
	@Override
	public Lista crear() throws UsuarioNoEncontradoException {
		Lista lista = new Lista();
		lista.setPropietario(this.autenticationFacade.getUsuario());
		lista.setActiva(Boolean.TRUE);
		return lista;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.frgz.tareas.lista.ListaService#obtener(java.lang.Long)
	 */
	@Override
	public Lista obtener(Long id) throws ListaNoEncontradaException {
		Lista lista = this.listaRepository.findOne(id);
		if (lista == null) {
			throw new ListaNoEncontradaException(id);
		}
		return lista;
	}

	private boolean existeNombreLista(String nombre) {
		int iguales = this.listaRepository.countByNombre(nombre);
		return iguales > 0;
	}

	private boolean existeNombreListaOtros(String nombre, Long idLista) {
		int iguales = this.listaRepository.countByNombreAndDistinctLista(nombre, idLista);
		return iguales > 0;
	}

}
