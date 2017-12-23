/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Graphics;
import java.awt.TextField;
import javax.swing.JComponent;
/**
 *
 * @author Sergio
 */
public class ComunicacionJpanel extends JComponent {
    private Arbol Nod;
    private String ruta;
    public ComunicacionJpanel(JComponent Dibujo) {
        super();
        this.setBounds(0,0,Dibujo.getWidth(),Dibujo.getHeight());
        Dibujo.add(this);
        Nod=new Arbol();
        this.ruta = "/Imagenes/fondo.jpg";
    }
    public void update(JComponent tablero){
        this.setBounds(0,0,tablero.getWidth(),tablero.getHeight());
     
    }
    public void addChild(int num){
        Nod.Añadir(num);
    }
    public void DeleteChild(int num){
        Nod.Eliminar(num);
    }
    public void Mostrar(int op){
        Nod.mostrar(op);
    }
    public void paint(Graphics g){
        super.paint(g);
        Nod.paint(g,this);

    }
}
