/**
 * 
 */
package org.lapaloma.hogwarts.controller;

import java.util.List;

import org.lapaloma.hogwarts.service.CasaService;
import org.lapaloma.mapamundi.vo.Casa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Isidoro Nevares Martín - Virgen de la Paloma Fecha creación: 13 mar 2026
 */
@RestController
@RequestMapping("/hogwarts/casas")
public class CasaController {

    private final CasaService casaService;

    // Spring inyecta automáticamente el service con su DAO
    public CasaController(CasaService casaService) {
        this.casaService = casaService;
    }

    // GET /hogwarts/casas - listar todos las casas
    @GetMapping
    public List<Casa> getAll() {
        List<Casa> listaCasas = casaService.obtenerListaCasas();
        return listaCasas;
    }

    // GET /hogwarts/casas/id/{id} - Obtener un Casa por su código
    @GetMapping("/id/{id}")
    public ResponseEntity<Casa> getByCodigo(@PathVariable("id") Integer identificador) {
        Casa Casa = casaService.obtenerCasaPorClave(identificador);

        return Casa != null ? ResponseEntity.ok(Casa) : ResponseEntity.notFound().build();
    }

    // GET /hogwarts/casas/nombre/{nombre} - Obtener un Casa por su nombre
    @GetMapping("/nombre/{nombre}")
    public List<Casa> getByNombre(@PathVariable("nombre") String nombre) {
        List<Casa> listaCasas = casaService.obtenerCasaPorNombre(nombre);

        return listaCasas;
    }

}
