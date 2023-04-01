package tarea3_arbol;
import tarea3_arbol.ArbolB;
import tarea3_arbol.NodoB;

public class Main {
    public static void main(String[] args) {
        ArbolB arbol = new ArbolB(2);
        arbol.insertar(15);
        arbol.insertar(85);
        arbol.insertar(6);
        arbol.insertar(25);
        arbol.insertar(38);
        arbol.insertar(64);
        arbol.insertar(1);
        arbol.insertar(22);
        
        NodoB nodo = arbol.buscar(6);
        if (nodo != null) {
            System.out.println("La llave 6 fue encontrada en el nodo con las siguientes llaves:");
            for (int i = 0; i < nodo.n; i++) {
                System.out.print(nodo.llaves[i] + " ");
            }
            System.out.println();
        } else {
            System.out.println("La llave 6 no se encontró en el árbol.");
        }
    }    
}