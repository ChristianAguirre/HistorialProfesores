package mx.edu.uacm.historialprofesores.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mx.edu.uacm.historialprofesores.domain.Profesor;

@Service
public interface ProfesorService {
	String save(Profesor profesor);
	Optional<Profesor> getById(Long id);
	Profesor saveMock(Profesor profesor);
	List<Profesor> obtenerTodosProfesores();
}
