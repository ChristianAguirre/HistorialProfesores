package mx.edu.uacm.historialprofesores.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.annotations.ColumnTransformer;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "Profesor")
public class Profesor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	
	private String apellidos;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fechaNacimiento;
	
	private String cedulaProfesional;
	
	@Email
	private String email; 
	
	private String telefono;
	
	private String sexo;
	//@ColumnTransformer(write=" MD5(?) ", read= " MD(5) ") //Encriptar contraseña cuando se escriba en la BD
	private String password;
	
//	private String domicilio;
	

	public Profesor() {

	}

	public Profesor(String nombre, String apellidos, Date fechaNacimiento, String cedulaProfesional,
			@Email String email, String telefono, String sexo, String password) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.cedulaProfesional = cedulaProfesional;
		this.email = email;
		this.telefono = telefono;
		this.sexo = sexo;
		this.password = password;
	}
	
}
