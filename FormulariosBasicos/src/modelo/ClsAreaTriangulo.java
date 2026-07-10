/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Juan Porras
 */
public class ClsAreaTriangulo {
//Declaracion de variables

    private int base;
    private int altura;
    private int areaTriangulo;

//Encapsulacion
    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAreaTriangulo() {
        return areaTriangulo;
    }

    public void setAreaTriangulo(int areaTriangulo) {
        this.areaTriangulo = areaTriangulo;
    }

//Proceso
    public void CalcularAreaTriangulo() {
        areaTriangulo = ((base * altura) / 2);
    }

}
