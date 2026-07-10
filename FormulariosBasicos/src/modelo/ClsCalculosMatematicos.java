/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Juan Porras
 */
public class ClsCalculosMatematicos {
//Declaracion de variables

    private int numero1;
    private int numero2;
    private int resultado;

//Encapsulacion 
    public int getNumero1() {
        return numero1;
    }

    public void setNumero1(int numero1) {
        this.numero1 = numero1;
    }

    public int getNumero2() {
        return numero2;
    }

    public void setNumero2(int numero2) {
        this.numero2 = numero2;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

//Procesos
    public void sumar() {
        resultado = numero1 + numero2;
    }

    public void restar() {
        resultado = numero1 - numero2;
    }

    public void multiplicar() {
        resultado = numero1 * numero2;
    }

    public void dividir() {
        if (numero2 == 0) {
            resultado = 0;
        } else {
            resultado = numero1 / numero2;
        }
    }
}
