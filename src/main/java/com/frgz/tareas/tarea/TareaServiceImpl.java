package com.frgz.tareas.tarea;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frgz.tareas.core.authentication.AuthenticationFacade;
import com.frgz.tareas.lista.Lista;
import com.frgz.tareas.lista.exception.ListaNoEncontradaException;
import com.frgz.tareas.tarea.exception.TareaNoEncontradaException;

/**
 * 
 * @author fabio
 *
 */
@Service
@Transactional(readOnly = true)
class TareaServiceImpl implements TareaService {

	private TareaRepository tareaRepository;
	private AuthenticationFacade autenticationFacade;

	@Autowired
	public TareaServiceImpl(TareaRepository tareaRepository, AuthenticationFacade autenticationFacade) {
		super();
		this.tareaRepository = tareaRepository;
		this.autenticationFacade = autenticationFacade;
	}

	/*
	 * 
	 */
	@Override
	public List<Tarea> visualizar(Long idLista, String nombre) {
		return this.tareaRepository.findByNombreContainingAndLista(nombre, idLista);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.frgz.tareas.tarea.TareaService#guardar(com.frgz.tareas.tarea.Tarea)
	 */
	@Override
	@Transactional(readOnly = false)
	public void guardar(Tarea tarea) {
		this.tareaRepository.save(tarea);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.frgz.tareas.tarea.TareaService#eliminar(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = false)
	public void eliminar(Long id) {
		this.tareaRepository.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.frgz.tareas.tarea.TareaService#crear(java.lang.Long)
	 */
	@Override
	public Tarea crear(Long idLista) throws ListaNoEncontradaException {
		Lista lista = new Lista();
		lista.setId(idLista);
		Tarea tarea = new Tarea();
		tarea.setLista(lista);
		tarea.setPropietario(this.autenticationFacade.getUsuario());
		tarea.setCompletada(Boolean.FALSE);
		return tarea;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.frgz.tareas.tarea.TareaService#realizada(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = false)
	public void realizada(Long id) {
		Tarea tarea = this.tareaRepository.findOne(id);
		tarea.setCompletada(Boolean.TRUE);
		tarea.setFechaRealizacion(Calendar.getInstance().getTime());
		this.tareaRepository.save(tarea);
	}

	@Override
	public Tarea obtener(Long id) throws TareaNoEncontradaException {
		Tarea tarea = this.tareaRepository.findOne(id);
		if (tarea == null) {
			throw new TareaNoEncontradaException(id);
		}
		return tarea;
	}
}
