package mx.edu.uacm.historialprofesores.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import mx.edu.uacm.historialprofesores.domain.Mensaje;
import mx.edu.uacm.historialprofesores.domain.Profesor;
import mx.edu.uacm.historialprofesores.repository.ProfesorRepository;
import mx.edu.uacm.historialprofesores.service.ProfesorService;


public class ProfesorServiceImplTest {

	@Autowired
	private ProfesorService profesorService;
	@Mock // me crea un objeto falso
	// @Autowired
	private ProfesorRepository profesorRepository;

	@Autowired
	TestEntityManager entityManager;

	Logger log = LogManager.getLogger(ProfesorServiceImplTest.class);
	private Mensaje mensajes = new Mensaje();

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		profesorService = new ProfesorServiceImpl(profesorRepository);
	}

	@Test
	public void guardarProfesorCamposCorrectosTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		Profesor p = new Profesor("Fulano", "misApellidos",new Date(),
				"miCedula","hello@yahoo.com","65676545668","Femenino","1x2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		log.debug("**MENSAJE DE RETORNO=****" + mensajeRetorno + " obtenido modelo minimo******");
		String mensajeEsperado = "datos correctos";
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
//		Object returnedobject = profesorService.getById(p.getId());
//		log.debug(returnedobject.toString());
		// Assert.assertEquals(profesorService.getById(p.getId()).get().getId(),
		// Optional.of(p).get().getId());
		// assertEquals(profesorService.getById(p.getId()).get().getId(),
		// Optional.of(p).get().getId());
	}

	@Test
	public void guardarProfesorCamposEnBlancoTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		Profesor p = new Profesor("", "misApellidos",new Date(),
				"miCedula","hello@yahoo.com","65676545668","Femenino","1x2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		log.debug("**MENSAJE DE RETORNO=****" + mensajeRetorno + " obtenido modelo minimo******");
		String mensajeEsperado = mensajes.getCamposEnBlanco();
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	@Test
	public void guardarProfesorMinNombreTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		// datos de prueba
		Profesor p = new Profesor("F", "misApellidos",new Date(),
				"miCedula","hello@yahoo.com","65676545668","Femenino","1x2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		log.debug("**MENSAJE DE RETORNO=****" + mensajeRetorno + " obtenido modelo minimo******");
		String mensajeEsperado = mensajes.getMinNombre();
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	@Test
	public void guardarProfesorMaxNombreTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		// datos de prueba
		String nombre ="";
		for(int i =0;i<100;i++)
		{
			nombre=nombre+"a";
		}
		Profesor p = new Profesor(nombre, "misApellidos",new Date(),
				"miCedula","hello@yahoo.com","65676545668","Femenino","1x2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		log.debug("**MENSAJE DE RETORNO=****" + mensajeRetorno + " obtenido modelo minimo******");
		String mensajeEsperado = mensajes.getMaxNombre();
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	@Test
	public void guardarProfesorFormatoNombreTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		// datos de prueba
		Profesor p = new Profesor("Fu5lano", "misApellidos",new Date(),
				"miCedula","hello@yahoo.com","65676545668","Femenino","1x2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		log.debug("**MENSAJE DE RETORNO=****" + mensajeRetorno + " obtenido modelo minimo******");
		String mensajeEsperado = mensajes.getLetrasMombre();
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	@Test
	public void guardarProfesorMinApellidosTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		// datos de prueba
		Profesor p = new Profesor("Fulano", "m",new Date(),
				"miCedula","hello@yahoo.com","65676545668","Femenino","1x2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		
		String mensajeEsperado = "APELLIDOS:" + "m" + "-> " + mensajes.getMinNombre();
		log.debug("&&&&&&MENSAJE DE RETORNO=" + mensajeRetorno + " &&&&MENSAJE ESPERADO= "+mensajeEsperado);
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	@Test
	public void guardarProfesorMaxApellidosTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		// datos de prueba
		String nombre ="";
		for(int i =0;i<100;i++)
		{
			nombre=nombre+"a";
		}
		Profesor p = new Profesor("Fulano", nombre,new Date(),
				"miCedula","hello@yahoo.com","65676545668","Femenino","1x2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		log.debug("**MENSAJE DE RETORNO=****" + mensajeRetorno + " obtenido modelo minimo******");
		String mensajeEsperado = "APELLIDOS:" + nombre + "-> " + mensajes.getMaxNombre();
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	@Test
	public void guardarProfesorFormatoApellidosTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		// datos de prueba
		Profesor p = new Profesor("Fulano", "misApe3llidos",new Date(),
				"miCedula","hello@yahoo.com","65676545668","Femenino","1x2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		log.debug("&&&&&&&&&&&&&&&MENSAJE DE RETORNO=" + mensajeRetorno + " &&&&&&&&&&&&&&&&******");
		String mensajeEsperado ="APELLIDOS:" + "misApe3llidos" + "-> " + mensajes.getLetrasMombre();
		log.debug("&&&&&&&&&&&&&&&MENSAJE DE mensajeEsperado=" + mensajeEsperado + " &&&&&&&&&&&&&&&&******");
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	@Test
	public void guardarProfesorFormatoPasswordTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		Profesor p = new Profesor("Fulano", "misApellidos",new Date(),
				"miCedula","hello@yahoo.com","65676545668","Femenino","1x2&x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		log.debug("**MENSAJE DE RETORNO=****" + mensajeRetorno + " obtenido modelo minimo******");
		String mensajeEsperado ="PASSWORD: "+mensajes.getFormatoPass();
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	@Test
	public void guardarProfesorMaxPasswordTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		Profesor p = new Profesor("Fulano", "misApellidos",new Date(),
				"miCedula","hello@yahoo.com","65676545668","Femenino","1x1y1y2y2y3y3y3y4y4y3y3y2y2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		log.debug("**MENSAJE DE RETORNO=****" + mensajeRetorno + " obtenido modelo minimo******");
		String mensajeEsperado = "PASSWORD: "+ mensajes.getMaxPass();
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	@Test
	public void guardarProfesorMinPasswordTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		Profesor p = new Profesor("Fulano", "misApellidos",new Date(),
				"miCedula","hello@yahoo.com","65676545668","Femenino","1");
		String mensajeRetorno = profesorService.save(p);
		log.debug("**MENSAJE DE RETORNO=****" + mensajeRetorno + " obtenido modelo minimo******");
		String mensajeEsperado = "PASSWORD: "+mensajes.getMinPass();
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	@Test
	public void guardarProfesorMinCedulaTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		Profesor p = new Profesor("Fulano", "misApellidos",new Date(),
				"m","hello@yahoo.com","65676545668","Femenino","1x2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		log.debug("**MENSAJE DE RETORNO=****" + mensajeRetorno + " obtenido modelo minimo******");
		String mensajeEsperado = "DIRECCIÓN: " + mensajes.getMinNombre();
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	@Test
	public void guardarProfesorMaxCedulaTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		String cedula="";
		for(int i=0; i<102; i++)
		{
			cedula=cedula+"a";
			
		}
		Profesor p = new Profesor("Fulano", "misApellidos",new Date(),
				cedula,"hello@yahoo.com","65676545668","Femenino","1x2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		log.debug("**MENSAJE DE RETORNO=****" + mensajeRetorno + " obtenido modelo minimo******");
		String mensajeEsperado = "DIRECCIÓN: " + mensajes.getMaxCedula();
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	@Test
	public void guardarProfesorFormatoCedulaTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		Profesor p = new Profesor("Fulano", "misApellidos",new Date(),
				"miCe%&/()dula","hello@yahoo.com","65676545668","Femenino","1x2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		log.debug("**MENSAJE DE RETORNO=****" + mensajeRetorno + " obtenido modelo minimo******");
		String mensajeEsperado = "DIRECCIÓN: " + mensajes.getFormatoCedula();
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	@Test
	public void guardarProfesorMinTelefonoTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		Profesor p = new Profesor("Fulano", "misApellidos",new Date(),
				"miCedula","hello@yahoo.com","12","Femenino","1x2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		log.debug("**MENSAJE DE RETORNO=****" + mensajeRetorno + " obtenido modelo minimo******");
		String mensajeEsperado = "TELEFONO: " + mensajes.getMinTel();
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	@Test
	public void guardarProfesorMaxTelefonoTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		Profesor p = new Profesor("Fulano", "misApellidos",new Date(),
				"miCedula","hello@yahoo.com","121212121212121212121212121265676545668","Femenino","1x2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		log.debug("**MENSAJE DE RETORNO=****" + mensajeRetorno + " obtenido modelo minimo******");
		String mensajeEsperado = "TELEFONO: " + mensajes.getMaxTel();
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	@Test
	public void guardarProfesorFormatoTelefonoTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		Profesor p = new Profesor("Fulano", "misApellidos",new Date(),
				"miCedula","hello@yahoo.com","65676x545668","Femenino","1x2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		log.debug("**MENSAJE DE RETORNO=****" + mensajeRetorno + " obtenido modelo minimo******");
		String mensajeEsperado = "TELEFONO: " + mensajes.getDigTel();
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	@Test
	public void guardarProfesorMinEmailTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		Profesor p = new Profesor("Fulano", "misApellidos",new Date(),
				"miCedula","h","65676545668","Femenino","1x2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		log.debug("**MENSAJE DE RETORNO=****" + mensajeRetorno + " obtenido modelo minimo******");
		String mensajeEsperado = "EMAIL: " + mensajes.getMinEmail();
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	@Test
	public void guardarProfesorMaxEmailTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		String email="fulanito@gmail.com";
		for(int i=0; i<100; i++)
		{
			email=email+"a";
			
		}
		Profesor p = new Profesor("Fulano", "misApellidos",new Date(),
				"miCedula",email,"65676545668","Femenino","1x2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		log.debug("**MENSAJE DE RETORNO=****" + mensajeRetorno + " obtenido modelo minimo******");
		String mensajeEsperado = "EMAIL: " + mensajes.getMaxEmail();
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	@Test
	public void guardarProfesorFormatoEmailTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		Profesor p = new Profesor("Fulano", "misApellidos",new Date(),
				"miCedula","hello@@yahoo.com","65676545668","Femenino","1x2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		log.debug("**MENSAJE DE RETORNO=****" + mensajeRetorno + " obtenido modelo minimo******");
		String mensajeEsperado = "EMAIL: " + mensajes.getIncorrectoEmail();
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	@Test
	public void guardarProfesorSexoTest() {
		log.debug("Entrando a guardarProfesorCamposCorrectosTest");
		// Profesor profesorInsertado= insertarProfesor();
		Profesor p = new Profesor("Fulano", "misApellidos",new Date(),
				"miCedula","hello@yahoo.com","65676545668","mujer","1x2x3x4x5x6x7");
		String mensajeRetorno = profesorService.save(p);
		log.debug("**MENSAJE DE RETORNO=****" + mensajeRetorno + " obtenido modelo minimo******");
		String mensajeEsperado = "SEXO: " + mensajes.getSexo();
		Assert.assertTrue(mensajeEsperado.equals(mensajeRetorno));
	}
	
	
	
	@Test // guardar o insertar un Profesor
	public void guardarProfesorConMock() {
		// TODO implementar el mock para probar profesorService.getById(1L);
		// GIVEN
		Profesor aux = new Profesor();
		aux.setPassword("123");
		aux.setEmail("email@gmail.com");
		// aux.setFechaNacimiento(new Date());
		aux.setNombre("mengana");
		aux.setSexo("masculino");
		aux.setCedulaProfesional("micedulaProfesional");
		aux.setTelefono("1234567899");
		aux.setApellidos("misapellidos");
		Profesor aux2 = new Profesor(); // objeto de pruebas para los assert
		// WHEN
		when(profesorService.saveMock(aux)).thenReturn(aux);
		when(profesorService.getById(aux.getId())).thenReturn(Optional.of(aux));
		// THEN
		assertEquals(profesorService.getById(aux.getId()).get().getId(), Optional.of(aux).get().getId());
		// assertj
//		assertThat(profesorService.getById(aux.getId())).isEqualTo(Optional.of(aux));
//		assertFalse(profesorService.save(aux).equals(aux2));
//		// assertFalse(profesorService.save(aux),aux2);
//		// assertThat(profesorService.save(aux)).isEqualTo(aux2);
//		// verifica que el comportamiento se ejecuta almenos una vez, o las veces que
//		// indiques
//		// en el codigo sin contar el when
		verify(profesorRepository, times(1)).findById(aux.getId());
//		verify(profesorRepository, times(1)).save(aux);
	}

	private Profesor insertarProfesor() {

		log.debug("Entrando a guardarProfesorMalTelefonoBlancoTest");
		Profesor p = new Profesor();
		// datos de prueba
		p.setId(1L);
		p.setNombre("fulanito");
		p.setApellidos("misApellidos");
		p.setTelefono("65676545668");
		p.setCedulaProfesional("miCedula");
		p.setPassword("1x2x3x4x5x6x7");
		p.setSexo("Femenino");
		p.setEmail("hello@yahoo.com");
		p.setFechaNacimiento(new Date());
		entityManager.persist(p);
		entityManager.flush();

		return p;

	}
}
