/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Juan Porras
 */
public class ClsCantidadHombresMujeres {
//Declaracion de variables

    private float hombres;
    private float mujeres;
    private float total;
    private float porcentajeHombres;
    private float porcentajeMujeres;

//encapsulacion
    public float getHombres() {
        return hombres;
    }

    public void setHombres(float hombres) {
        this.hombres = hombres;
    }

    public float getMujeres() {
        return mujeres;
    }

    public void setMujeres(float mujeres) {
        this.mujeres = mujeres;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getPorcentajeHombres() {
        return porcentajeHombres;
    }

    public void setPorcentajeHombres(float porcentajeHombres) {
        this.porcentajeHombres = porcentajeHombres;
    }

    public float getPorcentajeMujeres() {
        return porcentajeMujeres;
    }

    public void setPorcentajeMujeres(float porcentajeMujeres) {
        this.porcentajeMujeres = porcentajeMujeres;
    }

//Proceso
    public void calcularTotal() {
        total = (hombres + mujeres);
    }

    public void porcentajeHombres() {
        porcentajeHombres = (hombres * 100) / total;
    }

    public void porcentajeMujeres() {
        porcentajeMujeres = (mujeres * 100) / total;
    }

}
