package sopasDeLetras;

import java.util.ArrayList;


public class SopaDeLetras {

    public static void main(String[] args) {
        Controlador miControlador=new Controlador();

       miControlador.setArchivo("palabras.txt");
        

      /*  String paraBuscar="......";
        ArrayList<String> coincidencias=miControlador.buscarPalabras(paraBuscar);
        
        for (String pala : coincidencias){
            System.out.println(pala);
        }*/
       
        miControlador.crearTablero(10,10,50);
     //   miControlador.setNumeroPalabras(1);
        System.out.println(miControlador.getTablero());
        System.out.println(miControlador.getListaDePalabras());
    }

}
