/**
 * 
 */
package com.frgz.tareas.tarea.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.frgz.tareas.lista.exception.ListaNoEncontradaException;
import com.frgz.tareas.tarea.Tarea;
import com.frgz.tareas.tarea.TareaService;
import com.frgz.tareas.tarea.exception.TareaNoEncontradaException;

/**
 * @author fabio
 *
 */
@Controller
@RequestMapping("/lista/{idLista}/tarea")
@SessionAttributes("tarea")
public class TareaController {

	private static final String INDEX_PAGE = "tarea/tareaIndex";
	private static final String DETAILS_PAGE = "tarea/tareaDetails";

	private TareaService tareaService;

	@Autowired
	public void setTareaService(TareaService tareaService) {
		this.tareaService = tareaService;
	}

	@InitBinder("tarea")
	public void initDocumentoBinding(WebDataBinder dataBinder) {
		// Solo permitimos Binding de estos atributos
		dataBinder.setAllowedFields("nombre");
	}

	@GetMapping(value = { "/", "/tarea" })
	public String index(Model model, @RequestParam(defaultValue = "") String nombre) {
		model.addAttribute("tareas", this.tareaService.visualizar(nombre));
		model.addAttribute("nombre", nombre);
		return INDEX_PAGE;
	}

	@GetMapping(value = "/tarea/crear")
	public String crear(@PathVariable("idLista") Long idLista, Model model, RedirectAttributes ra) {
		try {
			model.addAttribute("tarea", this.tareaService.crear(idLista));
			return DETAILS_PAGE;
		} catch (ListaNoEncontradaException e) {
			ra.addFlashAttribute("error", "No existe la lista de tareas seleccionada");
			return "redirect:/lista/" + idLista;
		}		
	}

	@PostMapping(value = "/tarea/guardar")
	public String guardar(@Valid Tarea tarea, BindingResult result, RedirectAttributes ra) {
		this.tareaService.guardar(tarea);
		ra.addFlashAttribute("Tarea creada.");
		return "redirect:/";
	}

	@GetMapping(value = "/tarea/{id}")
	public String editar(@PathVariable("idLista") Long idLista, @PathVariable Long id, Model model, RedirectAttributes ra) {
		try {
			model.addAttribute("tarea", this.tareaService.obtener(id));
			return DETAILS_PAGE;
		} catch (TareaNoEncontradaException e) {
			ra.addFlashAttribute("error", "No existe la lista de tareas seleccionada");
			return "redirect:/lista/" + idLista + "/tarea";
		}
		
	}
	
	@PostMapping(value = "/tarea/{id}")
	public String actualizar(@Valid Tarea tarea, BindingResult result, RedirectAttributes ra, @PathVariable Long id) {
		tarea.setId(id);
		this.tareaService.guardar(tarea);
		ra.addFlashAttribute("Tarea creada.");
		return "redirect:/";
	}	

	@GetMapping(value = "/tarea/{id}/eliminar")
	public String eliminar(RedirectAttributes ra, @PathVariable Long id) {
		this.tareaService.eliminar(id);
		ra.addFlashAttribute("Tarea eliminada");
		return "redirect:/";
	}

	@GetMapping(value = "/tarea/{id}/realizar")
	public String realizar(RedirectAttributes ra, @PathVariable Long id) {
		this.tareaService.realizada(id);
		ra.addFlashAttribute("Tarea realizada");
		return "redirect:/";
	}

}
