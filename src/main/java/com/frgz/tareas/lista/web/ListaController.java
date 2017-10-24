/**
 * 
 */
package com.frgz.tareas.lista.web;

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
import com.frgz.tareas.lista.exception.ListaYaExisteException;
import com.frgz.tareas.usuario.exception.UsuarioNoEncontradoException;

/**
 * @author fabio
 *
 */
@Controller
@RequestMapping(value = { "/", "/lista" })
@SessionAttributes("lista")
@Secured({ "ROLE_USER", "ROLE_ADMIN" })
public class ListaController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ListaController.class);

	private static final String INDEX_PAGE = "lista/listaIndex";
	private static final String DETAILS_PAGE = "lista/listaDetails";

	private ListaService listaService;

	@Autowired
	public ListaController(ListaService listaService) {
		super();
		this.listaService = listaService;
	}

	@InitBinder("lista")
	public void initDocumentoBinding(WebDataBinder dataBinder) {
		// Solo permitimos Binding de estos atributos
		dataBinder.setAllowedFields("nombre");
	}

	@GetMapping
	public String index(Model model, @RequestParam(defaultValue = "") String nombre) {
		LOGGER.debug("Mostrando la lista de tareas...");
		model.addAttribute("page", this.listaService.visualizar(nombre));
		model.addAttribute("nombre", nombre);
		return INDEX_PAGE;
	}

	@GetMapping(value = "/crear")
	public String crear(Model model, RedirectAttributes ra) {
		LOGGER.debug("Creando una lista de tareas...");
		try {
			model.addAttribute("lista", this.listaService.crear());
			return DETAILS_PAGE;
		} catch (UsuarioNoEncontradoException e) {
			ra.addFlashAttribute("error", "No se ha podido cargar el propietario de la lista.");
			return "redirect:/lista";
		}

	}

	@GetMapping(value = {"/{id}", "/guardar/{id}"})
	public String modificar(Model model, @PathVariable("id") Long id, RedirectAttributes ra) {
		LOGGER.debug("Editando la lita de tareas...");
		try {
			model.addAttribute("lista", this.listaService.obtener(id));
			return DETAILS_PAGE;
		} catch (ListaNoEncontradaException e) {
			ra.addFlashAttribute("error", "No se ha podido obtener la lista seleccionada.");
			return "redirect:/lista";
		}

	}

	@PostMapping(value = {"/guardar", "/{id}"})
	public String guardar(Model model, @Valid Lista lista, BindingResult result, RedirectAttributes ra) {
		LOGGER.debug("Guardando la lista de tareas...");
		if (result.hasErrors()) {
			model.addAttribute("lista", lista);
			return DETAILS_PAGE;
		}

		try {
			this.listaService.guardar(lista);
		} catch (ListaYaExisteException e) {
			model.addAttribute("lista", lista);
			result.rejectValue("nombre", "Ya existe una lista con el mismo nombre.");
			ra.addFlashAttribute("message", "Ya existe una lista con el mismo nombre.");
			return DETAILS_PAGE;
		}
		ra.addFlashAttribute("message", "Lista creada.");
		return "redirect:/";
	}
}
