package tarea3_arbol;

public class NodoB{
    int[] llaves;
    int t;
    NodoB[] hijos;
    boolean hoja;
    int n;

    public NodoB(int t, boolean hoja) {
        this.t = t;
        this.hoja = hoja;
        llaves = new int[2 * t - 1];
        hijos = new NodoB[2 * t];
        n = 0;
    }

    public void recorrer() {
        int i;
        for (i = 0; i < n; i++) {
            if (hoja == false) {
                hijos[i].recorrer();
            }
            System.out.print(" " + llaves[i]);
        }
        if (hoja == false) {
            hijos[i].recorrer();
        }
    }

    public NodoB buscar(int k) {
        int i = 0;
        while (i < n && k > llaves[i]) {
            i++;
        }
        if (llaves[i] == k) {
            return this;
        }
        if (hoja == true) {
            return null;
        }
        return hijos[i].buscar(k);
    }

    public void insertarNoLleno(int k) {
        int i = n - 1;
        if (hoja == true) {
            while (i >= 0 && llaves[i] > k) {
                llaves[i + 1] = llaves[i];
                i--;
            }
            llaves[i + 1] = k;
            n = n + 1;
        } else {
            while (i >= 0 && llaves[i] > k) {
                i--;
            }
            if (hijos[i + 1].n == 2 * t - 1) {
                dividirHijo(this, i + 1, hijos[i + 1]);
                if (llaves[i + 1] < k) {
                    i++;
                }
            }
            hijos[i + 1].insertarNoLleno(k);
        }
    }

    public void dividirHijo(NodoB nodoPadre, int i, NodoB nodoHijo) {
        NodoB nuevoNodoHijo = new NodoB(t, nodoHijo.hoja);
        nuevoNodoHijo.n = t - 1;
        for (int j = 0; j < t - 1; j++) {
            nuevoNodoHijo.llaves[j] = nodoHijo.llaves[j + t];
        }
        if (!nodoHijo.hoja) {
            for (int j = 0; j < t; j++) {
                nuevoNodoHijo.hijos[j] = nodoHijo.hijos[j + t];
            }
        }
        nodoHijo.n = t - 1;
        for (int j = nodoPadre.n; j >= i + 1; j--) {
            nodoPadre.hijos[j + 1] = nodoPadre.hijos[j];
        }
        nodoPadre.hijos[i + 1] = nuevoNodoHijo;
        for (int j = nodoPadre.n - 1; j >= i; j--) {
            nodoPadre.llaves[j + 1] = nodoPadre.llaves[j];
        }
        nodoPadre.llaves[i] = nodoHijo.llaves[t - 1];
        nodoPadre.n = nodoPadre.n + 1;
    }
}