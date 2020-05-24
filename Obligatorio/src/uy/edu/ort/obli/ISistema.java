package uy.edu.ort.obli;

public interface ISistema {

	Retorno inicializarSistema(int maxPuntos);

	Retorno destruirSistema();

	Retorno registrarRepartidor(String matricula, String nombre);

	Retorno buscarRepartidor(String matricula);

	Retorno listarRepartidores();

	Retorno registrarCentro(String nombre, double coordX, double coordY, EnumCriticidad criticidad);

	Retorno registrarEsquina(double coordX, double coordY);

	Retorno registrarTramo(double coordXi, double coordYi, double coordXf, double coordYf, int metros);

	Retorno centroCriticoMasCercano(double coordX, double coordY);

	Retorno caminoSeguro();

	Retorno dibujarMapa();

}
