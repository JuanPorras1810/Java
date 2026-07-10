/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Juan Porras
 */
public class ClsAreaRectangulo {
//Declaracion de variables

    private int lado1;
    private int lado2;
    private int areaRectangulo;

//Encapsulacion 
    public int getLado1() {
        return lado1;
    }

    public void setLado1(int lado1) {
        this.lado1 = lado1;
    }

    public int getLado2() {
        return lado2;
    }

    public void setLado2(int lado2) {
        this.lado2 = lado2;
    }

    public int getAreaRectangulo() {
        return areaRectangulo;
    }

    public void setAreaRectangulo(int areaRectangulo) {
        this.areaRectangulo = areaRectangulo;
    }

//Proceso
    public void calcularAreaRectangulo() {
        areaRectangulo = lado1 * lado2;
    }

}
