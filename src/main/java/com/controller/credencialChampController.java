package com.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.entity.credencialChamp;
import com.serviceInterface.IUploadFileService;
import com.serviceInterface.IcredencialChampService;

@Controller
@RequestMapping("/credencialChamps")
public class credencialChampController {

	@Autowired
	private IcredencialChampService cS;
	@Autowired
	private IUploadFileService uploadFileService;

	@GetMapping("/new")
	public String newcredencialChamp(Model model) {
		model.addAttribute("credencialChamp", new credencialChamp());
		return "credencialChamp/credencialChamp";
	}

	@PostMapping("/save")
	public String saveAuthor(@Validated credencialChamp credencialChamp, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listcredencialChamps", cS.list());
			return "credencialChamp/credencialChamp";
		} else {
			if (!foto.isEmpty()) {

				if (credencialChamp.getIdcredencialChamp() > 0 && credencialChamp.getFoto() != null
						&& credencialChamp.getFoto().length() > 0) {

					uploadFileService.delete(credencialChamp.getFoto());
				}
				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}
				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				credencialChamp.setFoto(uniqueFilename);
			}
			int rpta = cS.insert(credencialChamp);

			//cS.insert(credencialChamp);
			model.addAttribute("mensaje", "El autor se registró correctamente");
			model.addAttribute("credencialChamp", new credencialChamp());
			model.addAttribute("listcredencialChamps", cS.list());
			// status.setComplete();
			//return "credencialChamp/listcredencialChamps";
			
			
			return "redirect:/credencialChamps/view/"+rpta;

		}

	}


	@GetMapping("/list")
	public String listcredencialChamps(Model model) {

		try {
			model.addAttribute("credencialChamp", new credencialChamp());// Por el buscar
			model.addAttribute("listcredencialChamps", cS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "credencialChamp/listcredencialChamps";
	}

	@RequestMapping("/delete/{id}")
	public String deleteAuthor(Model model, @PathVariable(value = "id") int id) {
		try {
			model.addAttribute("credencialChamp", new credencialChamp());
			if (id > 0) {
				cS.delete(id);
			}
			model.addAttribute("mensaje", "El autor se eliminó correctamente");
		} catch (Exception e) {
			model.addAttribute("mensaje2", "Ocurrió un error, el autor no puede ser eliminado");
		}
		model.addAttribute("listcredencialChamps", cS.list());
		return "credencialChamp/listcredencialChamps";// Mod pq con el buscar no funcaba
	}

	@RequestMapping("/irupdate/{id}")
	public String irupdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<credencialChamp> objAut = cS.searchId(id);
		if (objAut == null) {
			objRedir.addFlashAttribute("mensaje2", "Ocurrió un error");
			return "redirect:/credencialChamps/list";
		} else {
			model.addAttribute("listcredencialChamps", cS.list());// OJO A LO QUE DICE LA PROFESORA
			model.addAttribute("credencialChamp", objAut.get());
			return "credencialChamp/credencialChamp";
		}
	}

	@RequestMapping("/search")
	public String searchAuthors(Model model, @Validated credencialChamp credencialChamp) throws Exception {
		List<credencialChamp> listcredencialChamps;
		listcredencialChamps = cS.findNameCredencialFull(credencialChamp.getNamecredencialChamp());
		if (listcredencialChamps.isEmpty()) {
			model.addAttribute("mensaje2", "No hay registros para su búsqueda");
		}
		model.addAttribute("listcredencialChamps", listcredencialChamps);
		return "credencialChamp/listcredencialChamps";
	}

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@GetMapping(value = "/view/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {

		Optional<credencialChamp> credencialChamp = cS.searchId(id);
		if (credencialChamp == null) {
			flash.addFlashAttribute("error", "El Autor no existe en la base de datos");
			return "redirect:/credencialChamps/list";
		}

		model.addAttribute("credencialChamp", credencialChamp.get());

		return "credencialChamp/view";
	}

}
