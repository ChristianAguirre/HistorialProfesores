package mx.edu.uacm.historialprofesores.repository;

import org.springframework.data.repository.CrudRepository;

import mx.edu.uacm.historialprofesores.domain.Profesor;

public interface ProfesorRepository extends CrudRepository<Profesor, Long> {

}
