package org.lapaloma.hogwarts.dao;

import java.util.List;

import org.lapaloma.mapamundi.vo.Casa;

public interface ICasaDAO {
    public Casa obtenerCasaPorClave(int identificador);

    public void actualizarCasa(Casa Casa);

    public void crearCasa(Casa Casa);

    public void borrarCasa(Casa Casa);

    public List<Casa> obtenerListaCasas();

    public List<Casa> obtenerCasaPorNombre(String nombre);
}
