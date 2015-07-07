/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopasDeLetras;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author antoniogarcia
 */
public class ProcRaton extends MouseAdapter{
    public void mousePressed(MouseEvent evt){
        //capturamos x e y y lo pasamos al Canvas para saber donde se ha pulsado
        ((MiCanvas) evt.getComponent()).setxRaton(evt.getX());
        ((MiCanvas) evt.getComponent()).setyRaton(evt.getY());
        //le decimos al canvas que ha habido una elección
        ((MiCanvas) evt.getComponent()).setElegido();
        //repintamos para que aparezca (o desaparezca) el cuadro que marca la casilla seleccionada
        evt.getComponent().repaint();
    }
 /*  public void mouseMoved(MouseEvent evt){
        ((MiCanvas) evt.getComponent()).setxRaton(evt.getX());
        ((MiCanvas) evt.getComponent()).setyRaton(evt.getY());
        evt.getComponent().repaint();
    }*/
  
}
