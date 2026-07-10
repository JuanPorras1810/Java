/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Juan Porras
 */
public class ClsCalculadoraCalorias {
//Declaracion de variables 

    private float peso;
    private float altura;
    private byte edad;
    private float ConsumoCalorico;

//Encapsulacion 
    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public byte getEdad() {
        return edad;
    }

    public void setEdad(byte edad) {
        this.edad = edad;
    }

    public float getConsumoCalorico() {
        return ConsumoCalorico;
    }

    public void setConsumoCalorico(float ConsumoCalorico) {
        this.ConsumoCalorico = ConsumoCalorico;
    }

//Proceso
    public void HombreInactivo() {
        ConsumoCalorico = (66.473f + (13.752f * peso) + (5.0033f * altura) - (6.755f * edad)) * 1;
    }

    public void HombreActividadLigera() {
        ConsumoCalorico = (66.473f + (13.752f * peso) + (5.0033f * altura) - (6.755f * edad)) * 1.2f;
    }

    public void HombreActividadMedia() {
        ConsumoCalorico = (66.473f + (13.752f * peso) + (5.0033f * altura) - (6.755f * edad)) * 1.4f;
    }

    public void HombreMuyActivo() {
        ConsumoCalorico = (66.473f + (13.752f * peso) + (5.0033f * altura) - (6.755f * edad)) * 1.6f;
    }

    public void HombreActividadExtrema() {
        ConsumoCalorico = (66.473f + (13.752f * peso) + (5.0033f * altura) - (6.755f * edad)) * 1.8f;
    }

    public void MujerInactiva() {
        ConsumoCalorico = (655.0955f + (9.463f * peso) + (1.8496f * altura) - (4.6756f * edad)) * 1;
    }

    public void MujerActividadligera() {
        ConsumoCalorico = (655.0955f + (9.463f * peso) + (1.8496f * altura) - (4.6756f * edad)) * 1.2f;
    }

    public void MujerActividadMedia() {
        ConsumoCalorico = (655.0955f + (9.463f * peso) + (1.8496f * altura) - (4.6756f * edad)) * 1.4f;
    }

    public void MujerMuyActiva() {
        ConsumoCalorico = (655.0955f + (9.463f * peso) + (1.8496f * altura) - (4.6756f * edad)) * 1.6f;
    }

    public void MujerActividadExtrema() {
        ConsumoCalorico = (655.0955f + (9.463f * peso) + (1.8496f * altura) - (4.6756f * edad)) * 1.8f;
    }
}
