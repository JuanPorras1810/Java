/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Juan Porras
 */
public class ClsMasaCorporal {
//Declaracion de variables

    private float peso;
    private float estatura;
    private float IMC;

//Encapsulacion

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getEstatura() {
        return estatura;
    }

    public void setEstatura(float estatura) {
        this.estatura = estatura;
    }

    public float getIMC() {
        return IMC;
    }

    public void setIMC(float IMC) {
        this.IMC = IMC;
    }


  

//Proceso
    public void CalcularMasaCorporal() {
        IMC = (peso / (estatura * estatura));
    }

}
