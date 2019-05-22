package mx.edu.uacm.historialprofesores.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uacm.historialprofesores.domain.Mensaje;
import mx.edu.uacm.historialprofesores.domain.Profesor;
import mx.edu.uacm.historialprofesores.repository.ProfesorRepository;
import mx.edu.uacm.historialprofesores.service.ProfesorService;

@Service
public class ProfesorServiceImpl implements ProfesorService {

	private static final int MAX_CEDULA = 100;
	private static final int MIN_TELEFONO = 7;
	//private static final int BLANCO = 0;
	private static final int LOGITUD_MAX = 70;
	private static final int MIN_NOM_APE = 2;
	private static final int MAX_PASSWORD = 15;
	private static final int MIN_PASSWORD = 6;
	private static final int MAX_EMAIL = 100;
	private static final int MIN_EMAIL = 8;
	private static final int MAX_TEL = 20;
	private Mensaje mensajes = new Mensaje();
	@Autowired
	private ProfesorRepository profesorRepository;

	Logger log = LogManager.getLogger(ProfesorServiceImpl.class);

	public ProfesorServiceImpl(ProfesorRepository profesorRepository) {

		this.profesorRepository = profesorRepository;
	}

	@Override
	public String save(Profesor profesor) {
		String mensaje = "datos correctos";
		if (log.isDebugEnabled()) {
			log.debug("XXXXXXXXXXXXEstoy entrando al metodo agregarProfesorXXXXXXXXXXXX");
		}
		log.debug("*****Profesor y sus valores son : " + profesor.toString() + "******");
		String nombre = String.valueOf(profesor.getNombre());
		String apellidos = String.valueOf(profesor.getApellidos());
		String password = String.valueOf(profesor.getPassword());
		String cedula = String.valueOf(profesor.getCedulaProfesional());
		String telefono = String.valueOf(profesor.getTelefono());
		String email = String.valueOf(profesor.getEmail());
		String sexo = String.valueOf(profesor.getSexo());
		if (espaciosEnBlanco(profesor)) {
			mensaje = mensajes.getCamposEnBlanco();
			return mensaje;
		}
		if (nombre.length() < MIN_NOM_APE) {
			mensaje = mensajes.getMinNombre();
			return mensaje;
		}
		if (nombre.length() > LOGITUD_MAX) {
			mensaje = mensajes.getMaxNombre();
			return mensaje;
		}
		if (!(nombre.matches("^[A-Za-z\\s]+$"))) {
			mensaje = mensajes.getLetrasMombre();
			return mensaje;
		}
		if (apellidos.length() > LOGITUD_MAX) {
			mensaje = "APELLIDOS:" + apellidos + "-> " + mensajes.getMaxNombre();
			return mensaje;
		}
		if (!(apellidos.matches("^[A-Za-z\\s]+$"))) {
			mensaje = "APELLIDOS:" + apellidos + "-> " + mensajes.getLetrasMombre();
			return mensaje;
		}
		if (apellidos.length() < MIN_NOM_APE) {
			mensaje = "APELLIDOS:" + apellidos + "-> " + mensajes.getMinNombre();
			return mensaje;
		}
		if (password.length() < MIN_PASSWORD) {
			mensaje = "PASSWORD: " + mensajes.getMinPass();
			return mensaje;
		}
		if (password.length() > MAX_PASSWORD) {
			mensaje = "PASSWORD: " + mensajes.getMaxPass();
			return mensaje;
		}
		if (!(password.matches("^[A-Za-z0-9]+$"))) {
			mensaje = "PASSWORD: " + mensajes.getFormatoPass();
			return mensaje;
		}
		if (cedula.length() < MIN_NOM_APE) {
			mensaje = "DIRECCIÓN: " + mensajes.getMinNombre();
			return mensaje;
		}
		if (cedula.length() > MAX_CEDULA) {
			mensaje = "DIRECCIÓN: " + mensajes.getMaxCedula();
			return mensaje;
		}
		if (!(cedula.matches("^[A-Za-z0-9\\s\\.\\, \\#\\-\\_]+$"))) {
			mensaje = "DIRECCIÓN: " + mensajes.getFormatoCedula();
			return mensaje;
		}
		if (telefono.length() < MIN_TELEFONO) {
			mensaje = "TELEFONO: " + mensajes.getMinTel();
			return mensaje;
		}
		if (telefono.length() > MAX_TEL) {
			mensaje = "TELEFONO: " + mensajes.getMaxTel();
			return mensaje;
		}
		if (!(telefono.matches("[0-9]+"))) {
			mensaje = "TELEFONO: " + mensajes.getDigTel();
			return mensaje;
		}
		if (email.length() < MIN_EMAIL) {
			mensaje = "EMAIL: " + mensajes.getMinEmail();
			return mensaje;
		}
		if (email.length() > MAX_EMAIL) {
			mensaje = "EMAIL: " + mensajes.getMaxEmail();
			return mensaje;
		}
		if (!(email.matches("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))) {
			mensaje = "EMAIL: " + mensajes.getIncorrectoEmail();
			return mensaje;
		}
		if (!sexo.equals("Masculino") && !sexo.equals("Femenino")) {
			mensaje = "SEXO: " + mensajes.getSexo();
			return mensaje;
		}
		profesorRepository.save(profesor);

		return mensaje;
	}

	public boolean espaciosEnBlanco(Profesor u) {
		if (u.getApellidos().equals("") || u.getEmail().equals("")
				|| u.getNombre().equals("") || u.getPassword().equals("") || u.getSexo().equals("")
				|| u.getTelefono().equals("")) {
			return true;
		}
		return false;
	}

	@Override
	public Optional<Profesor> getById(Long id) {
		// TODO Auto-generated method stub
		log.debug("ENTRÓ a GetById");
		return profesorRepository.findById(id);
	}
	
	@Override
	public Profesor saveMock(Profesor profesor) {
		// TODO Auto-generated method stub
		String nombre = profesor.getNombre();
		String mensaje = "datos correctoss";
		log.debug("ENTRÓ a Service.save(Profesor usaurio)");
		if (nombre.length() < MIN_NOM_APE) {
			mensaje = mensajes.getMinNombre();
			return profesor;
		}

		if (nombre.length() > LOGITUD_MAX) {
			mensaje = mensajes.getMaxNombre();
			return profesor;
		}

		if (!(nombre.matches("^[A-Za-z\\s]+$"))) {
			mensaje = mensajes.getLetrasMombre();
			return profesor;
		}

		profesorRepository.save(profesor);// guarda el profesor que recibe con todos sus datos
		return profesor;
	}

	@Override
	public List<Profesor> obtenerTodosProfesores() {
		return (List<Profesor>)profesorRepository.findAll();
	}

}
