/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopasDeLetras;

import java.util.ArrayList;

/**
 *
 * @author antoniogarcia
 */
public class Tablero {

    private int ancho;
    private int alto;

    private int palabraMasLarga;
    private int palabraMasCorta;
    private int tamanoPalabraMinimo;
    private char[][] contenido;
    ArrayList<String> palabrasIncluidas;
    private int numeroDePalabras;
    private VentanaConCanvas vcc;

    public void setAncho(int ancho) {
        this.ancho = ancho;
        nuevoContenido();
    }

    public void setAlto(int alto) {
        this.alto = alto;
        nuevoContenido();
    }

    /**
     * Establece el número de palabras que compondrá la sopa de letras una vez
     * establecido se llama al método de creación de la sopa
     *
     * @param numeroDePalabras
     */
    public void setNumeroDePalabras(int numeroDePalabras) {
        this.numeroDePalabras = numeroDePalabras;
        palabrasIncluidas = new ArrayList<>();
        for (int i = 0; i < this.numeroDePalabras; i++) {
            incluirPalabras();
        }
    }

    /**
     * constructor por defecto, crea un tablero de 10x10 llama al constructor
     * con parámetros
     */
    Tablero() {
        //  this(10, 10,);
    }

    /**
     * constructor con parámetros, crea un tablero de anchoxalto
     *
     * @param ancho
     * @param alto
     */
    Tablero(int ancho, int alto, VentanaConCanvas vcc) {
        this.ancho = ancho;
        this.alto = alto;
        if (ancho > alto) {
            this.palabraMasLarga = ancho;
            this.palabraMasCorta = alto;
        } else {
            this.palabraMasLarga = alto;
            this.palabraMasCorta = ancho;
        }
        nuevoContenido();
        tamanoPalabraMinimo = 4;
        this.vcc = vcc;
    }

    private void nuevoContenido() {
        contenido = new char[alto][ancho];
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                contenido[i][j] = '.';
            }
        }
    }

    /**
     * Devuelve el tablero como una cadena de caracteres
     *
     * @return el tablero completo
     */
    @Override
    public String toString() {
        StringBuilder cadena = new StringBuilder();
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                cadena.append(contenido[j][i]);
            }
            cadena.append('\n');
        }
        return cadena.toString();
    }

    public String getTablero() {
        StringBuilder paraDevolver = new StringBuilder();
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                if (contenido[j][i] == '.') {
                    int numero = Utilidades.dameUnNumeroEntre(0, 26);
                    if (numero == 26) {
                        paraDevolver.append('ñ');
                    } else {
                        paraDevolver.append((char) (numero + 97));
                    }
                } else {
                    paraDevolver.append(contenido[j][i]);
                }
            }
        }
        return paraDevolver.toString();
    }

    public ArrayList<String> getListaDePalabras() {
        return palabrasIncluidas;
    }

    private boolean incluirPalabras() {
        // número máximos de intentos de colocar una palabra
        int maximosIntentos = 10000;
        // si se encuentra una palabra adecuada salimos antes
        boolean encontrado = false;
        do {
            int direccion = Utilidades.dameUnNumeroEntre(1, 4);
            switch (direccion) {
                case 1:
                    encontrado = intentaNoreste();
                    break;
                case 2:
                    encontrado = intentaEste();
                    break;
                case 3:
                    encontrado = intentaSureste();
                    break;
                case 4:
                    encontrado = intentaSur();
            }
        } while (maximosIntentos-- > 0 && !encontrado);

        return encontrado;
    }

    private boolean intentaNoreste() {
        boolean exito = false;
        ArrayList<String> listaCandidatos = new ArrayList<>();
        int tamanoPalabra = Utilidades.dameUnNumeroEntre(tamanoPalabraMinimo,
                palabraMasCorta);
        int x = Utilidades.dameUnNumeroEntre(0, ancho - tamanoPalabra);
        int y = Utilidades.dameUnNumeroEntre(tamanoPalabra - 1, alto - 1);
        String patron = getTramo(x, y, x + tamanoPalabra - 1, y - tamanoPalabra + 1);
        /*   System.out.println("intenta noreste");
         System.out.println("xinicio-> " + x + " yinicio-> " + y);
         System.out.println("xfin-> " + (x + tamanoPalabra - 1) + " yfin-> " + (y + tamanoPalabra - 1));*/

        if (patron != "-1") {
            listaCandidatos = vcc.buscarPalabras(patron);

            if (!listaCandidatos.isEmpty()) {
                String nuevaPalabra;
                nuevaPalabra = listaCandidatos.get(Utilidades.dameUnNumeroEntre(0, listaCandidatos.size() - 1));
                if (!palabrasIncluidas.contains(nuevaPalabra)) {
                    palabrasIncluidas.add(nuevaPalabra);
                    setTramoNoreste(x, y, nuevaPalabra);
                    exito = true;
                }
            }
        }
        return exito;
    }

    private boolean intentaEste() {
        boolean exito = false;
        ArrayList<String> listaCandidatos = new ArrayList<>();
        int tamanoPalabra = Utilidades.dameUnNumeroEntre(tamanoPalabraMinimo,
                palabraMasCorta);
        int x = Utilidades.dameUnNumeroEntre(0, ancho - tamanoPalabra);
        int y = Utilidades.dameUnNumeroEntre(0, alto - 1);
        String patron = getTramo(x, y, x + tamanoPalabra - 1, y);
        /*  System.out.println("intenta este");
         System.out.println("xinicio-> " + x + " yinicio-> " + y);
         System.out.println("xfin-> " + (x + tamanoPalabra - 1) + " yfin-> " + (y + tamanoPalabra - 1));*/

        if (patron != "-1") {
            listaCandidatos = vcc.buscarPalabras(patron);

            if (!listaCandidatos.isEmpty()) {
                String nuevaPalabra;
                nuevaPalabra = listaCandidatos.get(Utilidades.dameUnNumeroEntre(0, listaCandidatos.size() - 1));
                if (!palabrasIncluidas.contains(nuevaPalabra)) {
                    palabrasIncluidas.add(nuevaPalabra);
                    setTramoEste(x, y, nuevaPalabra);
                    exito = true;
                }
            }
        }
        return exito;
    }

    private boolean intentaSureste() {
        boolean exito = false;
        ArrayList<String> listaCandidatos = new ArrayList<>();
        int tamanoPalabra = Utilidades.dameUnNumeroEntre(tamanoPalabraMinimo,
                palabraMasCorta);
        int x = Utilidades.dameUnNumeroEntre(0, ancho - tamanoPalabra);
        int y = Utilidades.dameUnNumeroEntre(0, alto - tamanoPalabra);
        String patron = getTramo(x, y, x + tamanoPalabra - 1, y + tamanoPalabra - 1);
        /*   System.out.println("intenta sureste");
         System.out.println("xinicio-> " + x + " yinicio-> " + y);
         System.out.println("xfin-> " + (x + tamanoPalabra - 1) + " yfin-> " + (y + tamanoPalabra - 1));*/

        if (patron != "-1") {
            listaCandidatos = vcc.buscarPalabras(patron);

            if (!listaCandidatos.isEmpty()) {
                String nuevaPalabra;
                nuevaPalabra = listaCandidatos.get(Utilidades.dameUnNumeroEntre(0, listaCandidatos.size() - 1));
                //  System.out.println(nuevaPalabra);
                if (!palabrasIncluidas.contains(nuevaPalabra)) {
                    palabrasIncluidas.add(nuevaPalabra);
                    setTramoSureste(x, y, nuevaPalabra);
                    exito = true;
                }
            }
        }
        return exito;
    }

    private boolean intentaSur() {
        boolean exito = false;
        ArrayList<String> listaCandidatos = new ArrayList<>();
        int tamanoPalabra = Utilidades.dameUnNumeroEntre(tamanoPalabraMinimo,
                palabraMasCorta);
        int x = Utilidades.dameUnNumeroEntre(0, ancho - 1);
        int y = Utilidades.dameUnNumeroEntre(0, alto - tamanoPalabra);
        String patron = getTramo(x, y, x, y + tamanoPalabra - 1);
        /*  System.out.println("intenta sur");
         System.out.println("xinicio-> " + x + " yinicio-> " + y);
         System.out.println("xfin-> " + (x + tamanoPalabra - 1) + " yfin-> " + (y + tamanoPalabra - 1));*/

        if (patron != "-1") {
            listaCandidatos = vcc.buscarPalabras(patron);

            if (!listaCandidatos.isEmpty()) {
                String nuevaPalabra;
                nuevaPalabra = listaCandidatos.get(Utilidades.dameUnNumeroEntre(0, listaCandidatos.size() - 1));
                if (!palabrasIncluidas.contains(nuevaPalabra)) {
                    palabrasIncluidas.add(nuevaPalabra);
                    setTramoSur(x, y, nuevaPalabra);
                    exito = true;
                }
            }
        }
        return exito;
    }

    private void setTramoNoreste(int x, int y, String nuevaPalabra) {
        for (int i = 0; i < nuevaPalabra.length(); i++) {
            contenido[x + i][y - i] = nuevaPalabra.charAt(i);
        }
    }

    private void setTramoEste(int x, int y, String nuevaPalabra) {
        for (int i = 0; i < nuevaPalabra.length(); i++) {
            contenido[x + i][y] = nuevaPalabra.charAt(i);
        }
    }

    private void setTramoSureste(int x, int y, String nuevaPalabra) {
        for (int i = 0; i < nuevaPalabra.length(); i++) {
            contenido[x + i][y + i] = nuevaPalabra.charAt(i);
        }
    }

    private void setTramoSur(int x, int y, String nuevaPalabra) {
        for (int i = 0; i < nuevaPalabra.length(); i++) {
            contenido[x][y + i] = nuevaPalabra.charAt(i);
        }
    }

    /**
     * Devuelve, en una cadena, el contenido de una línea del tablero. la línea
     * puede ser horizontal,vertical o diagonal. En caso de que los parámetros
     * sean erroneos devuelve -1 como cadena.
     *
     * @param xInicio
     * @param yInicio
     * @param xFin
     * @param yFin
     * @return el contenido del tramo o -1 en caso de error.
     */
    public String getTramo(int xInicio, int yInicio, int xFin, int yFin) {

        StringBuilder cadenaTemporal = new StringBuilder();
        String paraDevolver = "-1";
        if (xInicio >= ancho || xInicio < 0 || xFin >= ancho || xFin < 0
                || yInicio >= alto || yInicio < 0 || yFin >= alto || yFin < 0) {
            return "pedazo error";
        }
        if ((xInicio - yInicio) == (xFin - yFin)) { // diagonal
            paraDevolver = getTramoDiagonal(xInicio, yInicio, xFin, yFin);
        } else {
            if (xInicio == xFin) { // vertical
                paraDevolver = getTramoVertical(xInicio, yInicio, xFin, yFin);
            } else {
                if (yInicio == yFin) { // horizontal
                    paraDevolver = getTramoHorizontal(xInicio, yInicio, xFin, yFin);
                }
            }

        }
        return paraDevolver;
    }

    private String getTramoHorizontal(int xInicio, int yInicio, int xFin, int yFin) {
        StringBuilder cadenaTemporal = new StringBuilder();
        if (xInicio < xFin) {
            for (int i = xInicio; i <= xFin; i++) {
                cadenaTemporal.append(contenido[i][yFin]);
            }
        } else {
            for (int i = xInicio; i >= xFin; i--) {
                cadenaTemporal.append(contenido[i][yFin]);
            }
        }
        return cadenaTemporal.toString();
    }

    private String getTramoVertical(int xInicio, int yInicio, int xFin, int yFin) {
        StringBuilder cadenaTemporal = new StringBuilder();
        if (yInicio < yFin) {
            for (int i = yInicio; i <= yFin; i++) {
                cadenaTemporal.append(contenido[xInicio][i]);
            }
        } else {
            for (int i = yInicio; i >= yFin; i--) {
                cadenaTemporal.append(contenido[xInicio][i]);
            }
        }
        return cadenaTemporal.toString();
    }

    private String getTramoDiagonal(int xInicio, int yInicio, int xFin, int yFin) {
        StringBuilder cadenaTemporal = new StringBuilder();
        int difX = xFin - xInicio;
        int difY = yFin - yInicio;
        for (int i = 0; i <= Math.abs(difX); i++) {
            int incX = i, incY = i;
            if (difX < 0) {
                incX *= -1;
            }
            if (difY < 0) {
                incY *= -1;
            }
            cadenaTemporal.append(contenido[xInicio + incX][yInicio + incY]);
        }
        return cadenaTemporal.toString();
    }

}
