/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebacanvas;

/**
 *
 * @author antoniogarcia
 */
public class PruebaCanvas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
        VentanaConCanvas vcc=new VentanaConCanvas();
        int tamanoHorizontal=10;
        int tamanoVertical=10;
        String paraPoner=""
                + "AIKDKISLOL" 
                + "KISKDKIKSL"
                + "AIKDKISLOL" 
                + "KISKDKIKSL"    
                + "AIKDKISLOL" 
                + "KISKDKIKSL"
                + "AIKDKISLOL" 
                + "KISKDKIKSL"
                + "AIKDKISLOL" 
                + "KISKDKIKSL";
        vcc.setTablero(paraPoner, tamanoHorizontal, tamanoVertical);
        vcc.setVisible(true);
    }
    
}
