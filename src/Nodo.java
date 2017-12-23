/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import javax.swing.Icon;
import javax.swing.ImageIcon;
/**
 *
 * @author Sergio
 */


public class Nodo {
    private int dato;
    private Nodo izq,der,nod;
    private int largo;
    private Posicion locacion;
    
    public Nodo() {
    }
    public Nodo(int dato) {
        this.dato = dato;
        this.largo=10;
        this.locacion=new Posicion();
        }
    public Nodo(int dato, Nodo izq, Nodo der) {
        this.dato = dato;
        this.izq = izq;
        this.der = der;
        this.locacion=new Posicion();
    }
    public void paint(Graphics g){
        Icon imagen=new ImageIcon(getClass().getResource("circulo.png"));
        ImageIcon img=(ImageIcon) imagen;
        Graphics2D g2=(Graphics2D) g;
        g2.setPaint(Color.WHITE);
        g.drawImage(img.getImage(),locacion.getX()-7,locacion.getY(), 40,40, (ImageObserver)nod);
        g2.setPaint(Color.WHITE);
        g.drawString(this.getDato()+"", locacion.getX()+6, locacion.getY()+23);
        g2.setPaint(Color.WHITE);
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public Nodo getIzq() {
        return izq;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public Posicion getLocacion() {
        return locacion;
    }

    public void setLocacion(Posicion locacion) {
        this.locacion = locacion;
    }
    public int getRadio(){
        return largo;
    }
    public void setRadio(int radio){
        this.largo=radio;
    }

    public Nodo getNod() {
        return nod;
    }

    public void setNod(Nodo nod) {
        this.nod = nod;
    }
    
}
