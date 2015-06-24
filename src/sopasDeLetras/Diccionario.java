package sopasDeLetras;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que realiza la tokenizaciÃ³n de un archivo
 *
 * @author: Antonio Miguel Garcia Gomez
 * @version: 1.0
 *
 */
public class Diccionario {

    private File archivo;
    private TreeMap<String, TreeMap<Integer, ArrayList<String>>> listadoCompleto = new TreeMap<>();
    //TODO BORRAR SOLO PARA PRUEBAS
    private ArrayList<String> categorias = new ArrayList<>();

    /**
     * Metodo para establecer el archivo que contiene las palabras de mi
     * diccionario AdemÃ¡s crea la lista de palabras
     *
     * @param archivo Archivo al que se le aplica la tokenizaciÃ³n
     */
    public void setArchivo(File archivo) {
        this.archivo = archivo;
        crearDiccionario();
        /*
         //PARA COMPROBAR QUE SE HA CARGADO BIEN EL FICHERO
         for (String cate : listadoCompleto.keySet()) {
         for (Integer numer : listadoCompleto.get(cate).keySet()) {
         for (String pala : listadoCompleto.get(cate).get(numer)) {
         System.out.println(pala);
         }
         }
         }*/
    }

    /**
     * crea el diccionario y lo guarda en memoria
     */
    private void crearDiccionario() {
        String cadena;
        String categoria;
        String nuevaPalabra;
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "UTF8"));
            while ((cadena = br.readLine()) != null) {
                categoria = parsearPalabra(cadena);
                if ((cadena = br.readLine()) != null) {
                    int palabrasEnCategoria = Integer.parseInt(cadena);
                    for (int i = 0; i < palabrasEnCategoria; i++) {
                        if ((cadena = br.readLine()) != null) {
                            nuevaPalabra = parsearPalabra(cadena);
                            incluirPalabraEnDiccionario(categoria, nuevaPalabra);
                        }
                    }
                }
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Se ha encontrado un error en la lectura del fichero");
            System.out.println(e);
        }

    }

    /**
     * Elimina caracteres extraños de una palabra y la deja lista para incluir
     * en la estructura de datos
     *
     * @param palabraDeEntrada
     * @return palabra preparada para ser utilizada o null si no cumple los
     * requisitos
     */
    private String parsearPalabra(String palabraDeEntrada) {
        Pattern p = Pattern.compile("[a-zA-ZáéíóúüÁÉÍÓÚÜñÑ]+");
        Matcher m = p.matcher(palabraDeEntrada);
        while (m.find()) {
            return Utilidades.quitarTildes(palabraDeEntrada.substring(m.start(), m.end()).toLowerCase());
        }
        return null;
    }

    /**
     * incluye una palabra en el diccionario
     *
     * @param palabraParaIncluir
     */
    private void incluirPalabraEnDiccionario(String categoria, String palabraParaIncluir) {

        int tamano = palabraParaIncluir.length();

        if (!listadoCompleto.containsKey(categoria)) {
            //TODO BORRAR, SOLO PARA PRUEBAS
            categorias.add(categoria);
            ////////////////
            listadoCompleto.put(categoria, new TreeMap<Integer, ArrayList<String>>());
            listadoCompleto.get(categoria).put(tamano, new ArrayList<String>());
            listadoCompleto.get(categoria).get(tamano).add(palabraParaIncluir);
        } else {
            if (!listadoCompleto.get(categoria).containsKey(tamano)) {
                listadoCompleto.get(categoria).put(tamano, new ArrayList<String>());
                listadoCompleto.get(categoria).get(tamano).add(palabraParaIncluir);
            } else {
                if (!listadoCompleto.get(categoria).get(tamano).contains(palabraParaIncluir)) {
                    listadoCompleto.get(categoria).get(tamano).add(palabraParaIncluir);
                }
            }
        }
    }

    /**
     * Busca las palabras que coinciden con el patrÃ³n que se le pasa como
     * modelo
     *
     * @param patron
     * @return
     */
    public ArrayList<String> getCoincidencias(String patron) {
        ArrayList<String> paraDevolver = new ArrayList<>();
        int tamano = patron.length();
        for (String cate : listadoCompleto.keySet()) {
            if (listadoCompleto.get(cate).containsKey(tamano)) {
                for (String pala : listadoCompleto.get(cate).get(tamano)) {
                    Pattern p = Pattern.compile(patron);
                    Matcher m = p.matcher(pala);
                    if (m.find()) {
                        paraDevolver.add(pala);
                    }
                }
            }
        }
        return paraDevolver;
    }

    /**
     * Busca las palabras que coinciden con el patrón que se le pasa como modelo
     * dentro de la categoría elegida
     *
     * @param patron
     * @return
     */
    public ArrayList<String> getCoincidencias(String categoria, String patron) {
        ArrayList<String> paraDevolver = new ArrayList<>();
        int tamano = patron.length();
        if (listadoCompleto.get(categoria.toLowerCase()).containsKey(tamano)) {
            for (String pala : listadoCompleto.get(categoria.toLowerCase()).get(tamano)) {
                Pattern p = Pattern.compile(patron);
                Matcher m = p.matcher(pala);
                if (m.find()) {
                    paraDevolver.add(pala);
                }
            }

        }
        return paraDevolver;
    }

    /**
     * Devuelve un array con las categorias que se incluyen en el diccionario
     *
     * @return listado de categorias
     */
    public ArrayList<String> getCategorias() {
        ArrayList<String> paraDevolver = new ArrayList<>();
        for (String cate : listadoCompleto.keySet()) {
            paraDevolver.add(cate);
        }
        return paraDevolver;
    }

}
