/**
 * 
 */
package com.frgz.tareas.tarea.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frgz.tareas.lista.Lista;
import com.frgz.tareas.lista.ListaService;
import com.frgz.tareas.lista.exception.ListaNoEncontradaException;
import com.frgz.tareas.tarea.Tarea;
import com.frgz.tareas.tarea.TareaService;
import com.frgz.tareas.tarea.exception.TareaNoEncontradaException;

/**
 * @author fabio
 *
 */
@Controller
@RequestMapping("/lista/{idLista}/tareas")
@SessionAttributes("tarea")
@Secured({ "ROLE_USER", "ROLE_ADMIN" })
public class TareaController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TareaController.class);

	private static final String INDEX_PAGE = "tarea/tareaIndex";
	private static final String DETAILS_PAGE = "tarea/tareaDetails";

	private TareaService tareaService;
	private ListaService listaService;

	@Autowired
	public TareaController(TareaService tareaService, ListaService listaService) {
		super();
		this.tareaService = tareaService;
		this.listaService = listaService;
	}

	@InitBinder("tarea")
	public void initDocumentoBinding(WebDataBinder dataBinder) {
		// Solo permitimos Binding de estos atributos
		dataBinder.setAllowedFields("nombre");
	}

	@GetMapping
	public String index(Model model, @PathVariable(name = "idLista") Long idLista,
			@RequestParam(defaultValue = "") String nombre, RedirectAttributes ra) {
		try {
			LOGGER.debug("Mostrando las tareas...");
			model.addAttribute("lista", obtenerLista(idLista));
			model.addAttribute("tareas", this.tareaService.visualizar(idLista, nombre));
			model.addAttribute("nombre", nombre);
			return INDEX_PAGE;
		} catch (ListaNoEncontradaException e) {
			ra.addFlashAttribute("error", "No existe la lista seleccionada.");
			return "redirect:/lista";
		}

	}

	@GetMapping(value = "/crear")
	public String crear(@PathVariable("idLista") Long idLista, Model model, RedirectAttributes ra) {
		try {
			LOGGER.debug("Creando nueva tarea...");
			model.addAttribute("lista", obtenerLista(idLista));
			model.addAttribute("tarea", this.tareaService.crear(idLista));
			return DETAILS_PAGE;
		} catch (ListaNoEncontradaException e) {
			ra.addFlashAttribute("error", "No existe la lista de tareas seleccionada");
			return "redirect:/lista/" + idLista;
		}
	}

	@PostMapping(value = "/tarea/guardar")
	public String guardar(@PathVariable("idLista") Long idLista, @Valid Tarea tarea, BindingResult result, RedirectAttributes ra) {
		LOGGER.debug("Guardando tarea...");
		this.tareaService.guardar(tarea);
		ra.addFlashAttribute("Tarea creada.");
		return "redirect:/lista/" + idLista;
	}

	@GetMapping(value = "/{id}")
	public String editar(@PathVariable("idLista") Long idLista, @PathVariable Long id, Model model,
			RedirectAttributes ra) {
		LOGGER.debug("Editando tarea...");
		try {			
			model.addAttribute("lista", obtenerLista(idLista));
			model.addAttribute("tarea", this.tareaService.obtener(id));
			return DETAILS_PAGE;
		} catch (TareaNoEncontradaException e) {
			ra.addFlashAttribute("error", "No existe la tarea seleccionada");
			return "redirect:/lista/" + idLista + "/tarea";
		} catch (ListaNoEncontradaException e) {
			ra.addFlashAttribute("error", "No existe la lista de tareas seleccionada");
			return "redirect:/lista/" + idLista + "/tarea";
		}

	}

	@PostMapping(value = "/tarea/{id}")
	public String actualizar(@Valid Tarea tarea, BindingResult result, RedirectAttributes ra, @PathVariable Long id) {
		LOGGER.debug("Actualizando tarea...");
		tarea.setId(id);
		this.tareaService.guardar(tarea);
		ra.addFlashAttribute("Tarea creada.");
		return "redirect:/";
	}

	@GetMapping(value = "/tarea/{id}/eliminar")
	public String eliminar(RedirectAttributes ra, @PathVariable Long id) {
		LOGGER.debug("Eliminando tarea...");
		this.tareaService.eliminar(id);
		ra.addFlashAttribute("Tarea eliminada");
		return "redirect:/";
	}

	@GetMapping(value = "/tarea/{id}/realizar")
	public String realizar(RedirectAttributes ra, @PathVariable Long id) {
		LOGGER.debug("Realizando tarea...");
		this.tareaService.realizada(id);
		ra.addFlashAttribute("Tarea realizada");
		return "redirect:/";
	}

	private Lista obtenerLista(Long listaId) throws ListaNoEncontradaException {
		return this.listaService.obtener(listaId);
	}

}
