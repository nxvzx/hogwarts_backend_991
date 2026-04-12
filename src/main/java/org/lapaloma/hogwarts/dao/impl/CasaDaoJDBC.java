package org.lapaloma.hogwarts.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.lapaloma.hogwarts.dao.ICasaDAO;
import org.lapaloma.mapamundi.vo.Casa;
import org.springframework.stereotype.Repository;

@Repository
public class CasaDaoJDBC implements ICasaDAO {
    private final DataSource dataSource;

    // Spring inyecta el DataSource configurado automáticamente
    public CasaDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Casa obtenerCasaPorClave(int identificador) {
        Casa Casa = null;

        String sentenciaSQL = """
                SELECT * FROM casa
                WHERE idCasa =?
                """;

        try (Connection conexion = dataSource.getConnection();
                PreparedStatement sentenciaJDBCPreparada = conexion.prepareStatement(sentenciaSQL);) {

            sentenciaJDBCPreparada.setInt(1, identificador);
            System.out.println(sentenciaJDBCPreparada);

            ResultSet resultadoSentencia = sentenciaJDBCPreparada.executeQuery();

            if (resultadoSentencia.next()) {
                Casa = getLineaFromResultSet(resultadoSentencia);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Casa;
    }

    @Override
    public void actualizarCasa(Casa Casa) {

        String sentenciaSQL = """
                UPDATE casa
                SET Nombre=?
                WHERE idCasa=?
                """;

        try (Connection conexion = dataSource.getConnection();
                PreparedStatement sentenciaJDBCPreparada = conexion.prepareStatement(sentenciaSQL);) {

            sentenciaJDBCPreparada.setString(1, Casa.getNombre());
            sentenciaJDBCPreparada.setInt(2, Casa.getIdentificador());

            System.out.println(sentenciaJDBCPreparada);

            sentenciaJDBCPreparada.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void crearCasa(Casa Casa) {

        String sentenciaSQL = """
                INSERT INTO casa (idCasa, Nombre)
                VALUES (?, ?)
                """;

        try (Connection conexion = dataSource.getConnection();
                PreparedStatement sentenciaJDBCPreparada = conexion.prepareStatement(sentenciaSQL);) {

            sentenciaJDBCPreparada.setInt(1, Casa.getIdentificador());
            sentenciaJDBCPreparada.setString(2, Casa.getNombre());

            System.out.println(sentenciaJDBCPreparada);

            sentenciaJDBCPreparada.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void borrarCasa(Casa Casa) {

        String sentenciaSQL = """
                DELETE FROM casa
                WHERE idCasa=?
                """;

        try (Connection conexion = dataSource.getConnection();
                PreparedStatement sentenciaJDBCPreparada = conexion.prepareStatement(sentenciaSQL);) {

            sentenciaJDBCPreparada.setInt(1, Casa.getIdentificador());
            sentenciaJDBCPreparada.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Casa> obtenerListaCasas() {

        List<Casa> lista = new ArrayList<>();

        String sentenciaSQL = """
                SELECT * FROM casa
                """;

        try (Connection conexion = dataSource.getConnection();
                PreparedStatement sentenciaJDBCPreparada = conexion.prepareStatement(sentenciaSQL);) {

            System.out.println(sentenciaJDBCPreparada);

            ResultSet resultadoSentencia = sentenciaJDBCPreparada.executeQuery();

            while (resultadoSentencia.next()) {
                lista.add(getLineaFromResultSet(resultadoSentencia));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public List<Casa> obtenerCasaPorNombre(String nombre) {

        List<Casa> lista = new ArrayList<>();

        String sentenciaSQL = """
                SELECT * FROM casa
                WHERE Nombre LIKE ?
                """;

        try (Connection conexion = dataSource.getConnection();
                PreparedStatement sentenciaJDBCPreparada = conexion.prepareStatement(sentenciaSQL);) {

            sentenciaJDBCPreparada.setString(1, "%" + nombre + "%");

            System.out.println(sentenciaJDBCPreparada);

            ResultSet resultadoSentencia = sentenciaJDBCPreparada.executeQuery();

            while (resultadoSentencia.next()) {
                lista.add(getLineaFromResultSet(resultadoSentencia));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    private Casa getLineaFromResultSet(ResultSet resultadoSentencia) throws SQLException {

        Casa Casa = new Casa();

        Casa.setIdentificador(resultadoSentencia.getInt("idCasa"));
        Casa.setNombre(resultadoSentencia.getString("Nombre"));

        return Casa;
    }
}