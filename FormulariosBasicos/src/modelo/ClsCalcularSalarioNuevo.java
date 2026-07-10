/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Juan Porras
 */
public class ClsCalcularSalarioNuevo {
//Declaracion de variables 

    private int salario;
    private int porcentajeIncremento;
    private int nuevoSalario;

//Encapsulacion
    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public int getPorcentajeIncremento() {
        return porcentajeIncremento;
    }

    public void setPorcentajeIncremento(int porcentajeIncremento) {
        this.porcentajeIncremento = porcentajeIncremento;
    }

    public int getNuevoSalario() {
        return nuevoSalario;
    }

    public void setNuevoSalario(int nuevoSalario) {
        this.nuevoSalario = nuevoSalario;
    }

//Proesos
    public void CalcularSalario() {
        nuevoSalario = ((salario * porcentajeIncremento) / 100) + salario;
    }
}
