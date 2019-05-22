package mx.edu.uacm.historialprofesores.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.uacm.historialprofesores.domain.Profesor;
import mx.edu.uacm.historialprofesores.service.ProfesorService;



@RequestMapping(value = "/api")
@RestController
public class ProfesorController {

	Logger log = LogManager.getLogger(ProfesorController.class);
	@Autowired
	ProfesorService profesorService;
	
	@GetMapping(value = "/profesor")
	public List<Profesor> getAllProfesores() {
		log.debug("__________________________________");
		log.debug("-CON----------------- Entrando al metodo agregarProfesor");

		return (List<Profesor>) profesorService.obtenerTodosProfesores();
	}

//	@RequestMapping(method=RequestMethod.GET)
//	public String profesors(Map<String, Object> model){
//		log.debug("Entre a home");
//		
//		List<Profesor> profesors = profesorService.obtenerTodosProfesores();
//
//		model.put("profesors", profesors);
//		return "home";
//	}
	
	@GetMapping(value = "/profesor/{nombre}")
	public String getProfesor(@PathVariable String nombre) {
		Profesor p = new Profesor(nombre, "misApellidos",new Date(),
				"miCedula","hello@yahoo.com","65676545668","Femenino","1x2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		return mensajeRetorno;
	}
}
