package org.lapaloma.hogwarts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lapaloma.hogwarts.dao.ICasaDAO;
import org.lapaloma.hogwarts.excepcion.CasaNoEncontradaException;
import org.lapaloma.mapamundi.vo.Casa;

class CasaServiceTest {

    private CasaService casaService;
    private FakeCasaDAO fakeDAO;

    @BeforeEach
    void setUp() {
        fakeDAO = new FakeCasaDAO();
        casaService = new CasaService(fakeDAO);
    }

    // =========================
    // obtenerCasaPorClave
    // =========================

    @Test
    void obtenerCasaPorClave_cuandoCodigoEsNull_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            casaService.obtenerCasaPorClave(null);
        });
    }

    @Test
    void obtenerCasaPorClave_cuandoCodigoEstaVacio_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            casaService.obtenerCasaPorClave(0);
        });
    }

    @Test
    void obtenerCasaPorClave_cuandoNoExiste_lanzaExcepcion() {
        assertThrows(CasaNoEncontradaException.class, () -> {
            casaService.obtenerCasaPorClave(99);
        });
    }

    @Test
    void obtenerCasaPorClave_cuandoExiste_retornaCasa() {
        fakeDAO.crearCasa(new Casa(2, "Slytherin"));

        Casa resultado = casaService.obtenerCasaPorClave(2);

        assertNotNull(resultado);
        assertEquals("Europa", resultado.getNombre());
    }

    // =========================
    // obtenerListaCasas
    // =========================

    @Test
    void obtenerListaCasas_cuandoListaEstaVacia_lanzaExcepcion() {
        assertThrows(RuntimeException.class, () -> {
            casaService.obtenerListaCasas();
        });
    }

    @Test
    void obtenerListaCasas_cuandoHayDatos_retornaLista() {
        fakeDAO.crearCasa(new Casa(2, "Slytherin"));

        List<Casa> resultado = casaService.obtenerListaCasas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }

    // =========================
    // obtenerCasaPorNombre
    // =========================

    @Test
    void obtenerCasaPorNombre_cuandoNombreEsNull_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            casaService.obtenerCasaPorNombre(null);
        });
    }

    @Test
    void obtenerCasaPorNombre_cuandoNombreEstaVacio_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            casaService.obtenerCasaPorNombre("");
        });
    }

    @Test
    void obtenerCasaPorNombre_cuandoNoExiste_lanzaExcepcion() {
        assertThrows(CasaNoEncontradaException.class, () -> {
            casaService.obtenerCasaPorNombre("Inexistente");
        });
    }

    @Test
    void obtenerCasaPorNombre_cuandoExiste_retornaLista() {
        fakeDAO.crearCasa(new Casa(2, "Slytherin"));

        List<Casa> resultado = casaService.obtenerCasaPorNombre("Slytherin");

        assertEquals(1, resultado.size());
    }

    // =========================
    // Fake DAO. Se crea el DAO dentro del test para no depender de la conexión a la base de datos, de si hay red, de si accede a un fichero...
    // En caso de usar el DOA real (CasaDaoJDBC) estaríamos hablando de prubeas de integración.
    // =========================

    static class FakeCasaDAO implements ICasaDAO {

        private List<Casa> data = new ArrayList<>();

        @Override
        public Casa obtenerCasaPorClave(int identificador) {
            return data.stream()
                    .filter(c -> c.getIdentificador()==identificador)
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public List<Casa> obtenerListaCasas() {
            return new ArrayList<>(data);
        }

        @Override
        public List<Casa> obtenerCasaPorNombre(String nombre) {
            List<Casa> resultado = new ArrayList<>();

            for (Casa c : data) {
                if (c.getNombre().equals(nombre)) {
                    resultado.add(c);
                }
            }
            return resultado;
        }

		@Override
		public void actualizarCasa(Casa Casa) {
		}

		@Override
		public void crearCasa(Casa Casa) {
            data.add(Casa);
		}

		@Override
		public void borrarCasa(Casa Casa) {
			
		}
    }
}