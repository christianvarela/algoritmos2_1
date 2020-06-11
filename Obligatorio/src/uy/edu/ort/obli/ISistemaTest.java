package uy.edu.ort.obli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

public class ISistemaTest {

	private ISistema sistema;
	private Retorno retorno;

	@Before
	public void setUp() throws Exception {
		sistema = new Sistema();
	}

	@Test
	public void testInicializarYDestruirSistema() {
		assertEquals(Retorno.Resultado.OK, sistema.inicializarSistema(10).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.destruirSistema().resultado);
	}

	
	// TESTS REPARTIDORES

	@Test
	 public void testRegistroUsuarioOK() {
		sistema.inicializarSistema(10);
		//Datos de la prueba
		assertEquals(Retorno.Resultado.OK, sistema.registrarRepartidor("AAA1111", "Omar").resultado);	
		assertEquals(Retorno.Resultado.OK, sistema.registrarRepartidor("BBB1111", "Jorge").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarRepartidor("UUU1111", "Maria").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarRepartidor("FFF1111", "Ximena").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarRepartidor("TTT1111", "Analia").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarRepartidor("HHH1111", "Nicolas").resultado);
	}

	@Test
	public void testRegistroUsuarioError1() {
		sistema.inicializarSistema(10);
		//Formatos de Matricula incorrectos
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarRepartidor("11111", "a").resultado); 
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarRepartidor("aaaaa", "a").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarRepartidor("111SSS", "a").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarRepartidor("S1", "a").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarRepartidor("SSSSS55555", "a").resultado);

	}
	
	@Test
	public void testRegistroUsuarioError2() {
		sistema.inicializarSistema(10);
		assertEquals(Retorno.Resultado.OK, sistema.registrarRepartidor("CAP1891", "Maria").resultado);
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarRepartidor("CAP1891", "Maria").resultado);
	}
	

	@Test
	public void testBuscarRepartidorRaiz() {
		
		sistema.inicializarSistema(10);

		sistema.registrarRepartidor("CAP1891", "Omar");	
		sistema.registrarRepartidor("BBB1111", "Jorge");
		sistema.registrarRepartidor("UUU1111", "Maria");
		sistema.registrarRepartidor("FFF1111", "Ximena");
		sistema.registrarRepartidor("TTT1111", "Analia");
		sistema.registrarRepartidor("HHH1111", "Nicolas");
		
		retorno = sistema.buscarRepartidor("CAP1891");
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("CAP1891;Omar", retorno.valorString);		
		
	}
	
	@Test
	public void testBuscarRepartidorRaizOrden() {
		
		sistema.inicializarSistema(10);

		sistema.registrarRepartidor("CAP1891", "Omar");	
		sistema.registrarRepartidor("BBB1111", "Jorge");
		sistema.registrarRepartidor("UUU1111", "Maria");
		sistema.registrarRepartidor("FFF1111", "Ximena");
		sistema.registrarRepartidor("TTT1111", "Analia");
		sistema.registrarRepartidor("HHH1111", "Nicolas");
		
		retorno = sistema.buscarRepartidor("CAP1891");
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("CAP1891;Omar", retorno.valorString);
		assertEquals(1, retorno.valorEntero);
		
	}
	
	@Test
	public void testBuscarRepartidorHoja() {
		
		sistema.inicializarSistema(10);
		
		sistema.registrarRepartidor("CAP1891", "Omar");	
		sistema.registrarRepartidor("BBB1111", "Jorge");
		sistema.registrarRepartidor("UUU1111", "Maria");
		sistema.registrarRepartidor("FFF1111", "Ximena");
		sistema.registrarRepartidor("TTT1111", "Analia");
		sistema.registrarRepartidor("HHH1111", "Nicolas");
		
		retorno = sistema.buscarRepartidor("HHH1111");
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("HHH1111;Nicolas", retorno.valorString);		
		
	}

	@Test
	public void testBuscarRepartidorHojaOrden() {
		
		sistema.inicializarSistema(10);

		sistema.registrarRepartidor("AAA1111", "Omar");	
		sistema.registrarRepartidor("BBB1111", "Jorge");
		sistema.registrarRepartidor("UUU1111", "Maria");
		sistema.registrarRepartidor("FFF1111", "Ximena");
		sistema.registrarRepartidor("TTT1111", "Analia");
		sistema.registrarRepartidor("HHH1111", "Nicolas");
		
		retorno = sistema.buscarRepartidor("HHH1111");
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("HHH1111;Nicolas", retorno.valorString);
		assertEquals(6, retorno.valorEntero);
		
	}
	
	
	@Test
	public void testBuscarRepartidorInterior() {
		
		sistema.inicializarSistema(10);

		sistema.registrarRepartidor("AAA1111", "Omar");	
		sistema.registrarRepartidor("BBB1111", "Jorge");
		sistema.registrarRepartidor("UUU1111", "Maria");
		sistema.registrarRepartidor("FFF1111", "Ximena");
		sistema.registrarRepartidor("TTT1111", "Analia");
		sistema.registrarRepartidor("HHH1111", "Nicolas");
		
		retorno = sistema.buscarRepartidor("UUU1111");
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("UUU1111;Maria", retorno.valorString);		
		
	}
	
	@Test
	public void testBuscarRepartidorInteriorOrden() {
		
		sistema.inicializarSistema(10);

		sistema.registrarRepartidor("AAA1111", "Omar");	
		sistema.registrarRepartidor("BBB1111", "Jorge");
		sistema.registrarRepartidor("UUU1111", "Maria");
		sistema.registrarRepartidor("FFF1111", "Ximena");
		sistema.registrarRepartidor("TTT1111", "Analia");
		sistema.registrarRepartidor("HHH1111", "Nicolas");
		
		retorno = sistema.buscarRepartidor("UUU1111");
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("UUU1111;Maria", retorno.valorString);
		assertEquals(3, retorno.valorEntero);
		
	}
	
	@Test
	public void testListarRepartidores() {

		sistema.inicializarSistema(10);

		sistema.registrarRepartidor("AAA1111", "Omar");	
		sistema.registrarRepartidor("BBB1111", "Jorge");
		sistema.registrarRepartidor("UUU1111", "Maria");
		sistema.registrarRepartidor("FFF1111", "Ximena");
		sistema.registrarRepartidor("TTT1111", "Analia");
		sistema.registrarRepartidor("HHH1111", "Nicolas");
		
		retorno = sistema.listarRepartidores();
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals("UUU1111;Maria|TTT1111;Analia|HHH1111;Nicolas|FFF1111;Ximena|BBB1111;Jorge|AAA1111;Omar",retorno.valorString);
		
	}

	// TESTS DEL MAPA
	@Test
	public void testRegistrarCentroMedico_OK() {
		
		sistema.inicializarSistema(2);
		assertEquals(Retorno.Resultado.OK, sistema.registrarCentro("001", 1.0, 1.0, EnumCriticidad.ALTA).resultado);
		
	}

	@Test
	public void testRegistrarCentroMedicoError1() {
		
		sistema.inicializarSistema(2);
		assertEquals(Retorno.Resultado.OK, sistema.registrarCentro("001", 1.0, 1.0, EnumCriticidad.ALTA).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarCentro("002", 2.0, 2.0, EnumCriticidad.ALTA).resultado);
		assertEquals(Retorno.Resultado.ERROR_1, sistema.registrarCentro("003", 3.0, 3.0, EnumCriticidad.ALTA).resultado);
	}

	@Test
	public void testRegistrarCentroMedicoError2() {
		
		sistema.inicializarSistema(10);
		assertEquals(Retorno.Resultado.OK, sistema.registrarCentro("001", 1.0, 1.0, EnumCriticidad.ALTA).resultado);
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarCentro("002", 1.0, 1.0, EnumCriticidad.ALTA).resultado); // mismas coordenadas
	}

	@Test
	public void testRegistrarEsquina_OK() {
		
		sistema.inicializarSistema(2);
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(5.0, 5.0).resultado);

	}

	@Test
	public void testRegistrarEsquinaError1() {
	
		sistema.inicializarSistema(2);
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(5.0, 5.0).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(4.0, 4.0).resultado);
		assertEquals(Retorno.Resultado.ERROR_1, sistema.registrarEsquina(6.0, 6.0).resultado);
		
	}
	
	@Test
	public void testRegistrarEsquinaError2() {
	
		sistema.inicializarSistema(10);
		assertEquals(Retorno.Resultado.OK, sistema.registrarEsquina(5.0, 5.0).resultado);
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarEsquina(5.0, 5.0).resultado);
		
	}
	
	@Test
	public void testRegistrarTramoOK() {
		
		sistema.inicializarSistema(10);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(3.0, 3.0);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 10).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 5).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(3.0, 3.0, 4.0, 4.0, 3).resultado);
	}

	//Peso incorrecto
	@Test
	public void testRegistrarTramoError1() {
		
		sistema.inicializarSistema(10);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(3.0, 3.0);
		
		assertEquals(Retorno.Resultado.ERROR_1, sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, -1).resultado);
	}

	//Punto no existe
	@Test
	public void testRegistrarTramoError2() {
		
		sistema.inicializarSistema(10);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(3.0, 3.0);
		
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarTramo(9.0, 9.0, 2.0, 2.0, 10).resultado);
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarTramo(3.0, 3.0, 8.0, 8.0, 10).resultado);
	}

	// Tramo ya existe
	@Test
	public void testRegistrarTramoError3() {
		
		sistema.inicializarSistema(10);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(3.0, 3.0);
		
		assertEquals(Retorno.Resultado.OK, sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 10).resultado);
		assertEquals(Retorno.Resultado.ERROR_3, sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 10).resultado);
	}

	@Test
	public void testMonopatinMasCercanoOK1() {
		
		sistema.inicializarSistema(10);
		sistema.registrarRepartidor("CAP1891", "Omar");
		sistema.registrarCentro("5", 5.0, 5.0, EnumCriticidad.MEDIA);
		sistema.registrarCentro("7", 7.0, 7.0, EnumCriticidad.ALTA);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(3.0, 3.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(6.0, 6.0);
		
		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 3);
		sistema.registrarTramo(1.0, 1.0, 3.0, 3.0, 7);
		sistema.registrarTramo(1.0, 1.0, 4.0, 4.0, 12);
		sistema.registrarTramo(1.0, 1.0, 6.0, 6.0, 16);
		
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 2);
		
		sistema.registrarTramo(3.0, 3.0, 4.0, 4.0, 1);
		sistema.registrarTramo(3.0, 3.0, 5.0, 5.0, 11);
		
		sistema.registrarTramo(4.0, 4.0, 5.0, 5.0, 8);
		sistema.registrarTramo(4.0, 4.0, 6.0, 6.0, 9);
		
		sistema.registrarTramo(5.0, 5.0, 6.0, 6.0, 2);
		
		sistema.registrarTramo(6.0, 6.0, 7.0, 7.0, 1);
		
		
		retorno = sistema.centroCriticoMasCercano(1.0, 1.0);
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals(16, retorno.valorEntero);
		
	}

	
	@Test
	public void testMonopatinMasCercanoError1() {
		
		sistema.inicializarSistema(10);
		sistema.registrarRepartidor("CAP1891", "Omar");
		sistema.registrarCentro("5", 5.0, 5.0, EnumCriticidad.MEDIA);
		sistema.registrarCentro("7", 7.0, 7.0, EnumCriticidad.ALTA);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(3.0, 3.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(6.0, 6.0);
		
		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 3);
		sistema.registrarTramo(1.0, 1.0, 3.0, 3.0, 7);
		sistema.registrarTramo(1.0, 1.0, 4.0, 4.0, 12);
		sistema.registrarTramo(1.0, 1.0, 6.0, 6.0, 16);
		
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 2);
		
		sistema.registrarTramo(3.0, 3.0, 4.0, 4.0, 1);
		sistema.registrarTramo(3.0, 3.0, 5.0, 5.0, 11);
		
		sistema.registrarTramo(4.0, 4.0, 5.0, 5.0, 8);
		sistema.registrarTramo(4.0, 4.0, 6.0, 6.0, 9);
		
		sistema.registrarTramo(5.0, 5.0, 6.0, 6.0, 2);
		
		sistema.registrarTramo(6.0, 6.0, 7.0, 7.0, 1);
		
		
		retorno = sistema.centroCriticoMasCercano(8.0, 8.0); // No existen coordenadas
		assertEquals(Retorno.Resultado.ERROR_1, retorno.resultado);
		
	}

	@Test
	public void testMonopatinMasCercanoError2() {
		
		sistema.inicializarSistema(10);
		sistema.registrarRepartidor("CAP1891", "Omar");
		sistema.registrarCentro("5", 5.0, 5.0, EnumCriticidad.MEDIA);
		sistema.registrarCentro("7", 7.0, 7.0, EnumCriticidad.ALTA);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(3.0, 3.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(6.0, 6.0);
		
		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 3);
		sistema.registrarTramo(1.0, 1.0, 3.0, 3.0, 7);
		sistema.registrarTramo(1.0, 1.0, 4.0, 4.0, 12);
		sistema.registrarTramo(1.0, 1.0, 6.0, 6.0, 16);
		
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 2);
		
		sistema.registrarTramo(3.0, 3.0, 4.0, 4.0, 1);
		sistema.registrarTramo(3.0, 3.0, 5.0, 5.0, 11);
		
		sistema.registrarTramo(4.0, 4.0, 5.0, 5.0, 8);
		sistema.registrarTramo(4.0, 4.0, 6.0, 6.0, 9);
		
		sistema.registrarTramo(5.0, 5.0, 6.0, 6.0, 2);
		
		retorno = sistema.centroCriticoMasCercano(1.0, 1.0); // No hay conexiones al centro medico critico
		assertEquals(Retorno.Resultado.ERROR_2, retorno.resultado);
		
	}

	@Test
	public void testCaminoSeguroCosto() {
		
		sistema.inicializarSistema(10);
		sistema.registrarRepartidor("CAP1891", "Omar");
		sistema.registrarCentro("5", 5.0, 5.0, EnumCriticidad.MEDIA);
		sistema.registrarCentro("7", 7.0, 7.0, EnumCriticidad.ALTA);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(3.0, 3.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(6.0, 6.0);
		
		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 3);
		sistema.registrarTramo(1.0, 1.0, 3.0, 3.0, 7);
		sistema.registrarTramo(1.0, 1.0, 4.0, 4.0, 12);
		sistema.registrarTramo(1.0, 1.0, 6.0, 6.0, 16);
		
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 2);
		
		sistema.registrarTramo(3.0, 3.0, 4.0, 4.0, 1);
		sistema.registrarTramo(3.0, 3.0, 5.0, 5.0, 11);
		
		sistema.registrarTramo(4.0, 4.0, 5.0, 5.0, 8);
		sistema.registrarTramo(4.0, 4.0, 6.0, 6.0, 9);
		
		sistema.registrarTramo(5.0, 5.0, 6.0, 6.0, 2);
		
		sistema.registrarTramo(6.0, 6.0, 7.0, 7.0, 1);
		
		retorno = sistema.caminoSeguro();
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertEquals(17, retorno.valorEntero);
	}
/*
	@Test
	public void testCaminoSeguroTramos() {
		
		sistema.inicializarSistema(10);
		sistema.registrarRepartidor("CAP1891", "Omar");
		sistema.registrarCentro("5", 5.0, 5.0, EnumCriticidad.MEDIA);
		sistema.registrarCentro("7", 7.0, 7.0, EnumCriticidad.ALTA);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(3.0, 3.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(6.0, 6.0);
		
		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 3);
		sistema.registrarTramo(1.0, 1.0, 3.0, 3.0, 7);
		sistema.registrarTramo(1.0, 1.0, 4.0, 4.0, 12);
		sistema.registrarTramo(1.0, 1.0, 6.0, 6.0, 16);
		
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 2);
		
		sistema.registrarTramo(3.0, 3.0, 4.0, 4.0, 1);
		sistema.registrarTramo(3.0, 3.0, 5.0, 5.0, 11);
		
		sistema.registrarTramo(4.0, 4.0, 5.0, 5.0, 8);
		sistema.registrarTramo(4.0, 4.0, 6.0, 6.0, 9);
		
		sistema.registrarTramo(5.0, 5.0, 6.0, 6.0, 2);
		
		sistema.registrarTramo(6.0, 6.0, 7.0, 7.0, 1);
		
		retorno = sistema.caminoSeguro();
		String strRet = retorno.valorString;
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		assertTrue(Pattern.compile("1\\.0;1\\.0.2\\.0;2\\.0").matcher(strRet).find() || Pattern.compile("2\\.0;2\\.0.1\\.0;1\\.0").matcher(strRet).find());
		assertTrue(Pattern.compile("2\\.0;2\\.0.3\\.0;3\\.0").matcher(strRet).find() || Pattern.compile("3\\.0;3\\.0.2\\.0;2\\.0").matcher(strRet).find());
		assertTrue(Pattern.compile("3\\.0;3\\.0.4\\.0;4\\.0").matcher(strRet).find() || Pattern.compile("4\\.0;4\\.0.3\\.0;3\\.0").matcher(strRet).find());
		assertTrue(Pattern.compile("4\\.0;4\\.0.5\\.0;5\\.0").matcher(strRet).find() || Pattern.compile("5\\.0;5\\.0.4\\.0;4\\.0").matcher(strRet).find());
		assertTrue(Pattern.compile("5\\.0;5\\.0.6\\.0;6\\.0").matcher(strRet).find() || Pattern.compile("6\\.0;6\\.0.5\\.0;5\\.0").matcher(strRet).find());
		assertTrue(Pattern.compile("6\\.0;6\\.0.7\\.0;7\\.0").matcher(strRet).find() || Pattern.compile("7\\.0;7\\.0.6\\.0;6\\.0").matcher(strRet).find());
	}

	@Test
	public void testCaminoSeguroCantTramos() {
		
		sistema.inicializarSistema(10);
		sistema.registrarRepartidor("CAP1891", "Omar");
		sistema.registrarCentro("5", 5.0, 5.0, EnumCriticidad.MEDIA);
		sistema.registrarCentro("7", 7.0, 7.0, EnumCriticidad.ALTA);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(2.0, 2.0);
		sistema.registrarEsquina(3.0, 3.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(6.0, 6.0);
		
		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 3);
		sistema.registrarTramo(1.0, 1.0, 3.0, 3.0, 7);
		sistema.registrarTramo(1.0, 1.0, 4.0, 4.0, 12);
		sistema.registrarTramo(1.0, 1.0, 6.0, 6.0, 16);
		
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 2);
		
		sistema.registrarTramo(3.0, 3.0, 4.0, 4.0, 1);
		sistema.registrarTramo(3.0, 3.0, 5.0, 5.0, 11);
		
		sistema.registrarTramo(4.0, 4.0, 5.0, 5.0, 8);
		sistema.registrarTramo(4.0, 4.0, 6.0, 6.0, 9);
		
		sistema.registrarTramo(5.0, 5.0, 6.0, 6.0, 2);
		
		sistema.registrarTramo(6.0, 6.0, 7.0, 7.0, 1);
		
		retorno = sistema.caminoSeguro();
		String strRet = retorno.valorString;
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
		Matcher mat = Pattern.compile("[|]").matcher(strRet);
		int count = 0;
		while (mat.find())
		    count++;
		assertEquals(5,count);
	}
	
	@Test
	public void testDibujarMapa() {
		
		sistema.inicializarSistema(10);
		sistema.registrarCentro("003",-34.8945332,-56.1550176, EnumCriticidad.BAJA); // Estadio Centenario
		sistema.registrarCentro("001", -34.7969082,-56.0693009, EnumCriticidad.MEDIA); // Campeon del Siglo 
		sistema.registrarCentro("002", -34.8844009,-56.1609289, EnumCriticidad.ALTA); // Cancha barrial 
		sistema.registrarEsquina(-34.9130081,-56.1488875); // Esquina
		
		retorno = sistema.dibujarMapa();
		assertEquals(Retorno.Resultado.OK, retorno.resultado);
	}*/
}