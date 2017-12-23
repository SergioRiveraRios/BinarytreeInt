/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Graphics;
import javax.swing.JComponent;
import static javax.swing.JOptionPane.*;
//import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Sergio
 */
public class Arbol {

    private Nodo raiz;
    int mayor;

    public Arbol() {
    }

    public Arbol(Nodo raiz) {
        this.raiz = raiz;
    }

    public void Añadir(int dato) {
        if (raiz == null) {
            raiz = new Nodo(dato);
        } else {
            Añadir(raiz, dato);
        }
    }

    public void Añadir(Nodo raiz, int dato) {
        if (dato < raiz.getDato()) {
            if (raiz.getIzq() != null) {
                Añadir(raiz.getIzq(), dato);
            } else {
                raiz.setIzq(new Nodo(dato));
            }
        } else {
            if (raiz.getDer() != null) {
                Añadir(raiz.getDer(), dato);
            } else {
                raiz.setDer(new Nodo(dato));
            }
        }
    }
    
    public boolean Eliminar(int dato) {
        Nodo aux = raiz;
        Nodo padre = raiz;
        boolean HijoIzq = true;
        while (aux.getDato() != dato) {
            padre = aux;
            if (dato < aux.getDato()) {
                HijoIzq = true;
                aux = aux.getIzq();
            } else {
                HijoIzq = false;
                aux = aux.getDer();
            }
            if (aux == null) {
                HijoIzq = false;
            }
        }//Fin while
        if (aux.getIzq() == null && aux.getDer() == null) {
            if (aux == raiz) {
                aux = padre = raiz = null;
            } else if (HijoIzq) {
                padre.setIzq(null);
            } else {
                padre.setDer(null);
            }

        } else if (aux.getDer()==null) {
            if (aux == raiz) {
                raiz = aux.getIzq();
            } else {
                if (HijoIzq) {
                    padre.setIzq(aux.getIzq());
                } else {
                    padre.setDer(aux.getIzq());
                }
            }
        } else if (aux.getIzq()==null) {
            if (aux == raiz) {
                raiz = aux.getDer();
            } else {
                if (HijoIzq) {
                    padre.setIzq(aux.getIzq());
                } else {
                    padre.setDer(aux.getDer());
                }
            }
        } else {
            Nodo remp = ObtenerRemp(aux);
            if (aux == raiz) {
                raiz=remp;
                System.out.println("Nodo remplazado:"+aux.getDato());
            } else if (HijoIzq) {
                padre.setIzq(remp);
            } else {
                padre.setDer(remp);
            }
             remp.setIzq(aux.getIzq());
            
        }
        return true;
    }
    public Nodo ObtenerRemp(Nodo Remp) {
        Nodo rempPadre = Remp;
        Nodo Remplazo = Remp;
        Nodo aux = Remp.getDer();
        while (aux != null) {
            rempPadre = Remplazo;
            Remplazo = aux;
            aux = aux.getIzq();
            if (Remplazo != Remp.getDer()) {
                if(Remplazo.getDer()==null && Remplazo.getIzq()!=null){
                    rempPadre.setIzq(Remplazo.getIzq());
                }
                rempPadre.setDer(Remplazo);
                Remplazo.setDer(Remp.getDer());
            }
        }
        System.out.println("Nodo remplazo" + Remplazo.getDato());
        return Remplazo;
    }
    
    public void mostrar(int forma){
        switch(forma){
            case 0:
                showMessageDialog(null,"Selecciona opcion valida");
            break;
            case 1:
                imprimirPre(raiz);
                System.out.println();
            break;
            case 2:
                imprimirEntre(raiz);
                System.out.println();
            break;
            case 3:
                imprimirPost(raiz);
                System.out.println();
            break;
            default:
                showMessageDialog(null,"aaaa");
            break;
        }
    }
    
    public void imprimirPre (Nodo raiz){
          if (raiz != null){
              System.out.print(raiz.getDato()+ " ");
              imprimirPre (raiz.getIzq());
              imprimirPre (raiz.getDer());
          }
      }
    public void imprimirEntre (Nodo raiz){
          if (raiz != null)
          {    
              imprimirEntre (raiz.getIzq());
              System.out.print(raiz.getDato()+ " ");
              imprimirEntre (raiz.getDer());
          }
      }
    public void imprimirPost(Nodo raiz) {
        if (raiz != null) {
            imprimirPost(raiz.getIzq());
            imprimirPost(raiz.getDer());
            System.out.print(raiz.getDato() + " ");
        }
    }

    public void paint(Graphics g, JComponent panel) {
        giveLocation(panel, raiz);
        paint(g, panel, raiz);
        paint(g, getRaiz());
    }

    public void paint(Graphics g, Nodo arb) {
        if (arb != null) {
            Posicion padre = arb.getLocacion();
            int radio = arb.getRadio();
            if (arb.getIzq() != null) {
                Posicion hijo = arb.getIzq().getLocacion();
                g.drawLine(padre.getX() + radio, padre.getY() + radio, hijo.getX() + radio, hijo.getY() + radio);
                paint(g, arb.getIzq());

            }
            if (arb.getDer() != null) {
                Posicion hijo = arb.getDer().getLocacion();
                g.drawLine(padre.getX() + radio, padre.getY() + radio, hijo.getX() + radio, hijo.getY() + radio);
                paint(g, arb.getDer());
            }
        }
    }

    public void paint(Graphics g, JComponent panel, Nodo arb) {
        if (arb != null) {
            arb.paint(g);
            if (arb.getIzq() != null) {
                paint(g, panel, arb.getIzq());
            }
            if (arb.getDer() != null) {
                paint(g, panel, arb.getDer());
            }
        }
    }

    public int giveMeTheLevel(int dat) {
        return enquenivel(raiz, 0, dat)-1;
    }

    private int enquenivel(Nodo nodo, int suma, int dato) {
        if (nodo != null) {
            suma++;
            if (nodo.getDato() == dato) {
                return suma;
            } else {
                if (contains(nodo.getIzq(), dato)) {
                    suma = enquenivel(nodo.getIzq(), suma, dato);
                } else if (contains(nodo.getDer(), dato)) {
                    suma = enquenivel(nodo.getDer(), suma, dato);
                }
            }
        }
        return suma;
    }

    public boolean contains(Nodo raiz, int dato) {
        if (raiz == null) {
            return false;
        } else {
            return raiz.getDato() == dato || contains(raiz.getIzq(), dato) || contains(raiz.getDer(), dato);

        }
    }

    public void altura(Nodo nodo, int cont) {
        if(nodo==null){return;}else{ 
            cont++;
            if(nodo.getIzq()==null && nodo.getDer()==null){
                if(cont>mayor){
                    mayor=cont;
                }
            }
                altura(nodo.getIzq(),cont);
                altura(nodo.getDer(), cont);
        }
    }

    public int altura() {
        altura(raiz, 0);
        return mayor;
    }

    public int getXincrement(Nodo arb, int width) {
        int n = giveMeTheLevel(arb.getDato());
        int dos_n = (int) Math.pow(2, n);
        return (width / dos_n) / 2;
    }

    public void giveLocation(JComponent panel, Nodo tree) {
        if (tree != null) {
            int n = giveMeTheLevel(tree.getDato());
            int quadrant = panel.getHeight() / this.altura();
            int margen = 100;
            int pos_y = quadrant * n;
            int pos_x = 0;
            if (tree.equals(raiz)) {
                pos_x = getXincrement(tree, panel.getWidth());
                tree.getLocacion().setX(pos_x);
            }

            if (tree.getIzq() != null) {
                pos_x = tree.getLocacion().getX() - getXincrement(tree.getIzq(), panel.getWidth());
                tree.getIzq().getLocacion().setX(pos_x);
            }
            if (tree.getDer() != null) {
                pos_x = tree.getLocacion().getX() + getXincrement(tree.getDer(), panel.getWidth());
                tree.getDer().getLocacion().setX(pos_x);
            }
            Posicion p = new Posicion(tree.getLocacion().getX(), pos_y);
            if (tree.getIzq() != null) {
                giveLocation(panel, tree.getIzq());
            }
            if (tree.getDer() != null) {
                giveLocation(panel, tree.getDer());
            }
            tree.setLocacion(p);
        }
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
}
