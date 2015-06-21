/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopasDeLetras;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author antoniogarcia
 */
public class Controlador {
    File archivo;
    Diccionario miDiccionario;
    Tablero miTablero;
    
    Controlador(){
        miDiccionario=new Diccionario();
    }
    public void setArchivo(String archivo){
        this.archivo=new File(archivo); 
        miDiccionario.setArchivo(this.archivo);
    }
    public ArrayList<String> buscarPalabras(String patron){
    	ArrayList<String> paraDevolver=new ArrayList<>();
    	paraDevolver=miDiccionario.getCoincidencias(patron);
    	return paraDevolver;
    }
    public void crearTablero(){
        miTablero=new Tablero();
    }
    public void crearTablero(int ancho,int alto,int numeroDePalabras){
        miTablero=new Tablero(ancho,alto,this);
        miTablero.setNumeroDePalabras(numeroDePalabras);
    }
    public String getTablero(){
        return miTablero.toString();
    }
    public String getTramo(int xInicio,int yInicio,int xFin,int yFin){
    	return miTablero.getTramo(xInicio, yInicio, xFin, yFin);
    }

    public void setNumeroPalabras(int numeroPalabras){
    	miTablero.setNumeroDePalabras(numeroPalabras);
    }
    public String getListaDePalabras(){
        return miTablero.getListaDePalabras();
    }
    
       
}
