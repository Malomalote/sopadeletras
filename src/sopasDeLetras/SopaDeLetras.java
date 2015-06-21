package sopasDeLetras;

import java.util.ArrayList;


public class SopaDeLetras {

    public static void main(String[] args) {
        Controlador miControlador=new Controlador();

       miControlador.setArchivo("palabras.txt");

        miControlador.crearTablero(10,10,50);
        System.out.println(miControlador.getTablero());
        VentanaConCanvas vcc=new VentanaConCanvas(miControlador);
        int tamanoHorizontal=10;
        int tamanoVertical=10;
        String paraPoner=""
                + "AIKDKIÑLOL" 
                + "BCDEFGHIJK"
                + "MNÑOPQRSTU" 
                + "KVWXYZABCD"    
                + "AIKDKISLOL" 
                + "KISKDKIKSL"
                + "AIKDKISLOL" 
                + "KISKDKIKSL"
                + "AIKDKISLOL" 
                + "KISKDKIKSL";
       // vcc.setTablero(paraPoner, tamanoHorizontal, tamanoVertical);
        vcc.setVisible(true);
    }

}
