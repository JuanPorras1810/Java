/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Juan Porras
 */
public class ClsAreaFiguras {

//Declaracion  de variables
    private float dato1;
    private float dato2;
    private float dato3;
    private float area;

//Encapsulacion
    public float getDato1() {
        return dato1;
    }

    public void setDato1(float dato1) {
        this.dato1 = dato1;
    }

    public float getDato2() {
        return dato2;
    }

    public void setDato2(float dato2) {
        this.dato2 = dato2;
    }

    public float getDato3() {
        return dato3;
    }

    public void setDato3(float dato3) {
        this.dato3 = dato3;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

//Procesos
    public void cuadrado() {
        area = dato1 * dato1;
    }

    public void triangulo() {
        area = ((dato1 * dato2) / 2);
    }

    public void romboide() {
        area = dato1 * dato2;
    }

    public void pentagono() {
        area = ((dato1  * dato2) / 2);
    }

    public void rectangulo() {
        area = dato1 * dato2;
    }

    public void rombo() {
        area = ((dato1 * dato2) / 2);
    }

    public void circulo() {
        area = (3.14f * (dato3 * dato3));
    }

    public void trapecio() {
        area = (((dato1 + dato3) * dato2) / 2);
    }
}
