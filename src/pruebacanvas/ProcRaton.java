/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebacanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author antoniogarcia
 */
public class ProcRaton extends MouseAdapter{
   /* public void mousePressed(MouseEvent evt){
        ((MiCanvas) evt.getComponent()).xRaton=evt.getX();
        ((MiCanvas) evt.getComponent()).yRaton=evt.getY();
        evt.getComponent().repaint();
    }
 */   public void mouseMoved(MouseEvent evt){
        ((MiCanvas) evt.getComponent()).setxRaton(evt.getX());
        ((MiCanvas) evt.getComponent()).setyRaton(evt.getY());
        evt.getComponent().repaint();
    }
  
}
