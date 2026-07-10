/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Juan Porras
 */
public class ClsPulsaciones {
//Declaracion de variables

    private String nombre;
    private String apellido;
    private byte edad;
    private float pulsaciones;

//Encapsulacion
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public float getPulsaciones() {
        return pulsaciones;
    }

    public void setPulsaciones(float pulsaciones) {
        this.pulsaciones = pulsaciones;
    }

//Proceso
    public void PulsacionesMasculino() {
        pulsaciones = ((210 - edad) / 10);
    }

    public void PulsacionesFemenino() {
        pulsaciones = ((220 - edad) / 10);
    }
}
