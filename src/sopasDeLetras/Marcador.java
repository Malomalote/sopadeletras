/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopasDeLetras;

import java.awt.geom.Point2D;

/**
 *
 * @author antoniogarcia
 */
public class Marcador {

    private int xInicio;
    private int yInicio;
    private int xFin;
    private int yFin;

    public void setInicio(int xInicio, int yInicio) {
        this.xInicio = xInicio;
        this.yInicio = yInicio;
    }

    public boolean setFin(int xFin, int yFin) {
        if (xInicio == xFin || yInicio == yFin || Math.abs(xInicio - xFin) == Math.abs(yInicio - yFin)) {
            this.xFin = xFin;
            this.yFin = yFin;
            return true;
        } else {
            return false;
        }
    }

    public int getxInicio() {
        return xInicio;
    }

    public int getyInicio() {
        return yInicio;
    }

    public int getxFin() {
        return xFin;
    }

    public int getyFin() {
        return yFin;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("xinicio = ").append(xInicio).append(" ...  yinicio = ").append(yInicio).append('\n');
        sb.append("xfin = ").append(xFin).append(" ...  yfin = ").append(yFin).append('\n');
        return sb.toString();
    }

}
