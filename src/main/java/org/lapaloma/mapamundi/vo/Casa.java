package org.lapaloma.mapamundi.vo;

/**
 *
 * Casa: Clase de persistencia que representa una Casa de Hogwarts.
 * 
 * @author Isidoro Nevares Martín - IES Virgen de la Paloma
 * @date 03 marzo 2026
 * 
 * 
 */
public class Casa {
    private int identificador;
    private String nombre;

    /**
     * @param identificador
     * @param nombre
     */
    public Casa(int identificador, String nombre) {
        super();
        this.identificador = identificador;
        this.nombre = nombre;
    }

    public Casa() {
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Casa [identificador=" + identificador + ", nombre=" + nombre + "]";
    }

}
