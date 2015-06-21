/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopasDeLetras;

/**
 *
 * @author antoniogarcia
 */
public class Utilidades {
    
    /**
     * quita las tildes de una palabra(y dieresis)
     *
     * @param paraQuitarTildes
     * @return palabra sin tildes
     */
    public static String quitarTildes(String paraQuitarTildes) {
        if (paraQuitarTildes != null) {
            for (int i = 0; i < paraQuitarTildes.length(); i++) {
                if (paraQuitarTildes.charAt(i) == '\u00E1') {
                    paraQuitarTildes = paraQuitarTildes.replace("\u00E1", "a");
                } else {
                    if (paraQuitarTildes.charAt(i) == '\u00E9') {
                        paraQuitarTildes = paraQuitarTildes.replace('\u00E9', 'e');
                    } else {
                        if (paraQuitarTildes.charAt(i) == '\u00ED') {
                            paraQuitarTildes = paraQuitarTildes.replace('\u00ED',
                                    'i');
                        } else {
                            if (paraQuitarTildes.charAt(i) == '\u00F3') {
                                paraQuitarTildes = paraQuitarTildes.replace(
                                        '\u00F3', 'o');
                            } else {
                                if (paraQuitarTildes.charAt(i) == '\u00FA') {
                                    paraQuitarTildes = paraQuitarTildes
                                            .replace('\u00FA', 'u');
                                } else {
                                    if (paraQuitarTildes.charAt(i) == '\u00FC') {
                                        paraQuitarTildes = paraQuitarTildes
                                                .replace('\u00FC', 'u');
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return paraQuitarTildes;
    }
    
    /**
     * Devuelve un número entero aleatorio entre el valor mínimo y máximo que
     * recibe como parámetros
     *
     * @param minimo
     * @param maximo
     * @return
     */
    public static int dameUnNumeroEntre(int minimo, int maximo) {
        if (maximo < minimo) {
            int aux = maximo;
            maximo = minimo;
            minimo = aux;
        }
        return (int) ((Math.random() * (maximo - minimo + 1)) + minimo);
    }
}
