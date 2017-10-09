package com.frgz.tareas.tarea;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author fabio
 *
 */
@Service
@Transactional(readOnly = true)
class TareaServiceImpl implements TareaService {

	private TareaRepository tareaRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.frgz.tareas.tarea.TareaService#setTareaRepository(com.frgz.tareas.
	 * tarea.TareaRepository)
	 */
	@Override
	@Autowired
	public void setTareaRepository(TareaRepository tareaRepository) {
		this.tareaRepository = tareaRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.frgz.tareas.tarea.TareaService#visualizar(java.lang.String)
	 */
	@Override
	public List<Tarea> visualizar(String nombre) {
		return this.tareaRepository.findByNombreContaining(nombre);
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
	 * @see com.frgz.tareas.tarea.TareaService#crear()
	 */
	@Override
	public Tarea crear() {
		return new Tarea();
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
		tarea.setRealizada(Boolean.TRUE);
		tarea.setFechaRealizacion(Calendar.getInstance().getTime());
		this.tareaRepository.save(tarea);
	}
}
