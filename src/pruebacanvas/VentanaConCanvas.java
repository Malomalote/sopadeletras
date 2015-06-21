/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebacanvas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author antoniogarcia
 */
public class VentanaConCanvas extends JFrame{
    private JLabel labelx,labely;
    private JButton boton1;
    MiCanvas unCanvas;
    VentanaConCanvas(){
        iniciarControles();
    }
    private void iniciarControles(){
        this.setBounds(100, 100, 1000, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        labelx=new JLabel("aqui va la x");
        labelx.setBounds(50, 150, 100, 30);
        add(labelx);
        labely=new JLabel("aqui va la y");
        labely.setBounds(50, 200, 100, 30);
        add(labely);
        boton1=new JButton("mi boton");
        boton1.setBounds(50, 100,100, 30);
        add(boton1);
        unCanvas=new MiCanvas(this);
        unCanvas.setBounds(200, 0, 500, 500);
        add(unCanvas);
        unCanvas.addMouseMotionListener(new ProcRaton());
       // unCanvas.addMouseListener(new ProcRaton());   
    }
    public void setTablero(String paraPoner,int tamanoHorizontal,int tamanoVertical){
        unCanvas.setTablero(paraPoner,tamanoHorizontal,tamanoVertical);
    }
    public void setLabels(int x,int y){
        labelx.setText(Integer.toString(x));
        labely.setText(Integer.toString(y));
    }
}
