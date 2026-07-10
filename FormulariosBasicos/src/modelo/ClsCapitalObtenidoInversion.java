/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Juan Porras
 */
public class ClsCapitalObtenidoInversion {
//Declaracion de variables

    private float dinero;
    private float interesAnual;
    private int años;
    private double capitalObtenido;

//Encapsulacion
    public float getDinero() {
        return dinero;
    }

    public void setDinero(float dinero) {
        this.dinero = dinero;
    }

    public float getInteresAnual() {
        return interesAnual;
    }

    public void setInteresAnual(float interesAnual) {
        this.interesAnual = interesAnual;
    }

    public int getAños() {
        return años;
    }

    public void setAños(int años) {
        this.años = años;
    }

    public double getCapitalObtenido() {
        return capitalObtenido;
    }

    public void setCapitalObtenido(double capitalObtenido) {
        this.capitalObtenido = capitalObtenido;
    }

//Proceso
    public void CalcularInversion() {
        capitalObtenido = dinero * Math.pow((1 + (interesAnual / 100)), años);

    }

}
