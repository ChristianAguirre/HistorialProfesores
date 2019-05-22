package mx.edu.uacm.historialprofesores.domain;

import lombok.Data;

@Data
public class Mensaje {

	private String minNombre = "El valor minimo de nombre debe ser 3 letras";
	private String maxNombre = "El valor maximo de nombre deben ser 70 letras";
	private String letrasMombre = "La cadena solo deben ser letras o espacios";
	private String formatoFecha = "El formato de la fecha es incorrecto";
	private String minPass = "El valor minimo de la cadena debe ser 6 caracteres";
	private String maxPass = "El valor minimo de la cadena debe ser 15 caracteres";
	private String camposEnBlanco = "Todos los campos deben ser llenados, ninguno debe estar vacio";
	private String formatoPass = "El valor minimo de la cadena debe ser 6 caracteres";
	private String maxCedula = "El valor max del cedula debe ser 100 caracteres";
	private String formatoCedula = "El formato del cedula acepta mayusculas, num. .,# - _";
	private String minTel = "El valor minimo de la cadena debe ser 7 caracteres";
	private String maxTel = "El valor max de la cadena debe ser 20 caracteres";
	private String sexo = "El sexo sólo puede ser Masculino o Femenino";
	private String digTel = "El telefono solo deben ser digitos";

	private String minEmail = "El valor minimo del email debe ser 8 caracteres";
	private String maxEmail = "El valor max del email debe ser 100 caracteres";
	private String incorrectoEmail = "El email ingresado es incorrecto";

	public Mensaje() {
	}

}
