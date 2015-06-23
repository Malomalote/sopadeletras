/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopasDeLetras;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author antoniogarcia
 */
public class VentanaConCanvas extends JFrame {

    private JLabel lblTematica, lblTamano, lblNumPalabras;
    private JButton btnGenerar;
    private JComboBox cmbTematica;
    private JComboBox cmbTamano;
    private JComboBox cmbNumPalabras;
    private int tamanoX, tamanoY;

    MiCanvas unCanvas;
    Tablero miTablero;
    Diccionario miDiccionario;

    VentanaConCanvas() {
        tamanoX = 10;
        tamanoY = 10;
        miTablero = new Tablero(tamanoX, tamanoY, this);
        miDiccionario = new Diccionario();
        iniciarControles();
    }

    private void iniciarControles() {
        this.setBounds(100, 100, 1020, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        lblTematica = new JLabel("Temática");
        lblTematica.setBounds(20, 50, 100, 20);
        add(lblTematica);
        cmbTematica = new JComboBox();
        cmbTematica.addItem("Todas");

        /* cmbTematica.addItem("Deportes");
         cmbTematica.addItem("Paises");
         cmbTematica.addItem("Flores");
         cmbTematica.addItem("Frutas");*/
        cmbTematica.setBounds(20, 80, 100, 20);
        cmbTematica.setSelectedIndex(0);
        add(cmbTematica);

        lblTamano = new JLabel("Tamaño");
        lblTamano.setBounds(20, 110, 100, 20);
        add(lblTamano);
        cmbTamano = new JComboBox();
        cmbTamano.addItem("10X10");
        cmbTamano.addItem("20x20");
        cmbTamano.setBounds(20, 140, 100, 20);
        cmbTamano.setSelectedIndex(0);
        add(cmbTamano);

        lblNumPalabras = new JLabel("Número de palabras");
        lblNumPalabras.setBounds(20, 170, 150, 20);
        add(lblNumPalabras);
        cmbNumPalabras = new JComboBox();
        cmbNumPalabras.addItem("10");
        cmbNumPalabras.addItem("20");
        cmbNumPalabras.addItem("30");
        cmbNumPalabras.addItem("40");
        cmbNumPalabras.setBounds(20, 200, 100, 20);
        cmbNumPalabras.setSelectedIndex(0);
        add(cmbNumPalabras);

        btnGenerar = new JButton("Generar Sopa de Letras");
        btnGenerar.setBounds(20, 300, 150, 20);
        add(btnGenerar);

        unCanvas = new MiCanvas(this);
        unCanvas.setBounds(500, 0, 500, 500);
        add(unCanvas);
        //unCanvas.addMouseMotionListener(new ProcRaton());
        unCanvas.addMouseListener(new ProcRaton());
    }

    public void setTablero(String paraPoner, int tamanoHorizontal, int tamanoVertical) {
        unCanvas.setTablero(paraPoner, tamanoHorizontal, tamanoVertical);
    }

    public void setArchivo(String archivo) {
        miDiccionario.setArchivo(new File(archivo));
        for (String categoria : miDiccionario.getCategorias()) {
            cmbTematica.addItem(categoria);
        }
    }

    public ArrayList<String> buscarPalabras(String patron) {
        ArrayList<String> paraDevolver = new ArrayList<>();
        paraDevolver = miDiccionario.getCoincidencias(patron);
        return paraDevolver;
    }

    private void cmbTamanoItemStateChanged(java.awt.event.ItemEvent evt) {
        if (evt.getSource() == cmbTamano) {
            String idioma;
            switch (cmbTamano.getSelectedIndex()) {
                case 0:
                    tamanoX = 10;
                    tamanoY = 10;
                    break;
                case 1:
                    tamanoX = 20;
                    tamanoY = 20;
                    break;

                default:
                    tamanoX = 10;
                    tamanoY = 10;
                    break;
            }
        }
    }
}
