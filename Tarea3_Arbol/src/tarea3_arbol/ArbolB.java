package tarea3_arbol;
import java.util.Arrays;

public class ArbolB {
    public NodoB raiz;
    public final int t;

    // Constructor del Arbol B
    public ArbolB(int t) {
        this.t = t;
        raiz = new NodoB(t, true);
    }

    // Metodo para buscar una llave en el arbol B
    public NodoB buscar(int llave) {
        return buscar(raiz, llave);
    }

    private NodoB buscar(NodoB nodo, int llave) {
        int i = 0;
        while (i < nodo.n && llave > nodo.llaves[i]) {
            i++;
        }
        if (i < nodo.n && llave == nodo.llaves[i]) {
            return nodo;
        }
        if (nodo.hoja) {
            return null;
        }
        return buscar(nodo.hijos[i], llave);
    }

    // Metodo para insertar una llave en el arbol B
    public void insertar(int llave) {
        NodoB r = raiz;
        if (r.n == 2 * t - 1) {
            NodoB s = new NodoB(t, false);
            raiz = s;
            s.hijos[0] = r;
            dividirHijo(s, 0, r);
            insertarNoLleno(s, llave);
        } else {
            insertarNoLleno(r, llave);
        }
    }

    private void insertarNoLleno(NodoB nodo, int llave) {
        int i = nodo.n - 1;
        if (nodo.hoja) {
            while (i >= 0 && llave < nodo.llaves[i]) {
                nodo.llaves[i + 1] = nodo.llaves[i];
                i--;
            }
            nodo.llaves[i + 1] = llave;
            nodo.n = nodo.n + 1;
            System.out.println("Insertando llave " + llave + " en nodo " + Arrays.toString(nodo.llaves));
        } else {
            while (i >= 0 && llave < nodo.llaves[i]) {
                i--;
            }
            i++;
            if (nodo.hijos[i].n == 2 * t - 1) {
                dividirHijo(nodo, i, nodo.hijos[i]);
                if (llave > nodo.llaves[i]) {
                    i++;
                }
            }
            insertarNoLleno(nodo.hijos[i], llave);
        }
    }


    public void dividirHijo(NodoB nodoPadre, int i, NodoB nodoHijo) {
        // Crear un nuevo nodo hijo
        NodoB nuevoNodoHijo = new NodoB(t, nodoHijo.hoja);
        nuevoNodoHijo.n = t - 1;
    
        // Copiar las llaves del nodo hijo al nuevo nodo hijo
        for (int j = 0; j < t - 1; j++) {
            nuevoNodoHijo.llaves[j] = nodoHijo.llaves[j + t];
        }
    
        // Si el nodo hijo no es una hoja, copiar también los hijos
        if (!nodoHijo.hoja) {
            for (int j = 0; j < t; j++) {
                nuevoNodoHijo.hijos[j] = nodoHijo.hijos[j + t];
            }
        }
    
        // Reducir la cantidad de llaves en el nodo hijo original
        nodoHijo.n = t - 1;
    
        // Hacer espacio para el nuevo nodo hijo en el nodo padre
        for (int j = nodoPadre.n; j >= i + 1; j--) {
            nodoPadre.hijos[j + 1] = nodoPadre.hijos[j];
        }
    
        // Conectar el nuevo nodo hijo con el nodo padre
        nodoPadre.hijos[i + 1] = nuevoNodoHijo;
    
        // Mover la llave del nodo hijo al nodo padre
        for (int j = nodoPadre.n - 1; j >= i; j--) {
            nodoPadre.llaves[j + 1] = nodoPadre.llaves[j];
        }
        nodoPadre.llaves[i] = nodoHijo.llaves[t - 1];
        nodoPadre.n = nodoPadre.n + 1;
    }
}