/**
 * 
 */
package com.frgz.tareas.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frgz.tareas.usuario.exception.UsuarioNoEncontradoException;

/**
 * @author fabio
 *
 */
@Service
@Transactional(readOnly = true)
class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.frgz.tareas.usuario.UsuarioService#findByEmail(java.lang.String)
	 */
	@Override
	public Usuario findByEmail(String email) throws UsuarioNoEncontradoException {
		Usuario usuario = this.usuarioRepository.findByEmail(email);
		if (usuario == null) {
			throw new UsuarioNoEncontradoException(email);
		}
		return usuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.frgz.tareas.usuario.UsuarioService#findOne(java.lang.Long)
	 */
	@Override
	public Usuario findOne(Long id) throws UsuarioNoEncontradoException {
		Usuario usuario = this.usuarioRepository.findOne(id);
		if (usuario == null) {
			throw new UsuarioNoEncontradoException(id);
		}
		return usuario;
	}
}
