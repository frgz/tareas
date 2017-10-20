/**
 * 
 */
package com.frgz.tareas.lista.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.frgz.tareas.lista.Lista;
import com.frgz.tareas.lista.ListaService;
import com.frgz.tareas.usuario.exception.UsuarioNoEncontradoException;

/**
 * @author fabio
 *
 */
@Controller
@RequestMapping(value = { "/", "/lista" })
@SessionAttributes("lista")
public class ListaController {

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
		model.addAttribute("page", this.listaService.visualizar(nombre));
		model.addAttribute("nombre", nombre);
		return INDEX_PAGE;
	}

	@GetMapping(value = "/crear")
	public String crear(Model model, RedirectAttributes ra) {
		try {
			model.addAttribute("lista", this.listaService.crear());
			return DETAILS_PAGE;
		} catch (UsuarioNoEncontradoException e) {
			ra.addFlashAttribute("error", "No se ha podido cargar el propietario de la lista.");
			return "redirect:/";
		}

	}

	@PostMapping(value = "/guardar")
	public String guardar(Model model, @Valid Lista lista, BindingResult result, RedirectAttributes ra) {
		if (result.hasErrors()) {
			model.addAttribute("lista", lista);
			return DETAILS_PAGE;
		}
		this.listaService.guardar(lista);
		ra.addFlashAttribute("message", "Lista creada.");
		return "redirect:/";
	}
}
