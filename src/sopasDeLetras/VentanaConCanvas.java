/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopasDeLetras;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import oracle.net.aso.e;

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
    private String temaElegido;
    private int numPalabras;
    private ArrayList<String> listadoDePalabras;
    private JLabel[] lblPalabra;
    
    MiCanvas unCanvas = null;
    Tablero miTablero;
    Diccionario miDiccionario;
    
    VentanaConCanvas() {
        tamanoX = 10;
        tamanoY = 10;
        miTablero = new Tablero(tamanoX, tamanoY, this);
        miDiccionario = new Diccionario();
        temaElegido = "TODAS";
        numPalabras = 10;
        listadoDePalabras = new ArrayList<>();
        lblPalabra = new JLabel[40];
        iniciarControles();
    }
    
    private void iniciarControles() {
        this.setBounds(100, 100, 1020, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        addTematicaControl();
        addTamanoControl();
        addNumPalabrasControl();
        addGenerarControl();
        addListaDePalabras();
        unCanvas = new MiCanvas(this);
        unCanvas.setBounds(500, 0, 500, 500);
        add(unCanvas);
        //unCanvas.addMouseMotionListener(new ProcRaton());
        unCanvas.addMouseListener(new ProcRaton());
    }
    
    public void setArchivo(String archivo) {
        miDiccionario.setArchivo(new File(archivo));
        for (String categoria : miDiccionario.getCategorias()) {
            cmbTematica.addItem(categoria);
        }
    }
    
    public ArrayList<String> buscarPalabras(String patron) {
        ArrayList<String> paraDevolver = new ArrayList<>();
        if (temaElegido.equals("TODAS")) {
            paraDevolver = miDiccionario.getCoincidencias(patron);
        } else {
            paraDevolver = miDiccionario.getCoincidencias(temaElegido, patron);
        }
        return paraDevolver;
    }
    
    private void addNumPalabrasControl() {
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
        cmbNumPalabras.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                numPalabras = Integer.parseInt(cmbNumPalabras.getSelectedItem().toString());
            }
            
        });
    }
    
    private void addTamanoControl() {
        lblTamano = new JLabel("Tamaño");
        lblTamano.setBounds(20, 110, 100, 20);
        add(lblTamano);
        cmbTamano = new JComboBox();
        cmbTamano.addItem("10X10");
        cmbTamano.addItem("20x20");
        cmbTamano.setBounds(20, 140, 100, 20);
        cmbTamano.setSelectedIndex(0);
        add(cmbTamano);
        cmbTamano.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // cmbTamanoItemStateChanged(e);
                if (e.getSource() == cmbTamano) {
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
        });
    }
    
    private void addTematicaControl() {
        lblTematica = new JLabel("Temática");
        lblTematica.setBounds(20, 50, 100, 20);
        add(lblTematica);
        cmbTematica = new JComboBox();
        cmbTematica.addItem("Todas");
        cmbTematica.setBounds(20, 80, 100, 20);
        cmbTematica.setSelectedIndex(0);
        add(cmbTematica);
        cmbTematica.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                temaElegido = cmbTematica.getSelectedItem().toString().toUpperCase();
            }
            
        });
    }

    private void addListaDePalabras() {
        for (int i = 0; i < 40; i++) {
            lblPalabra[i] = new JLabel();
            
            lblPalabra[i].setBounds(200 + (i%2*100), 10 + ((i/2) * 20), 100, 20);
          
            add(lblPalabra[i]);
        }
    }

    private void cargarListaDePalabras() {
        
        for (int i = 0; i < 40; i++) {
            lblPalabra[i].setText("");
        }
        System.out.println(listadoDePalabras.size());
        for (int i = 0; i < listadoDePalabras.size(); i++) {
            lblPalabra[i].setText(listadoDePalabras.get(i));
        }
    }
    
    private void addGenerarControl() {
        btnGenerar = new JButton("Generar Sopa de Letras");
        btnGenerar.setBounds(20, 300, 150, 20);
        add(btnGenerar);
        btnGenerar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                
                miTablero.setAncho(tamanoX);
                miTablero.setAlto(tamanoY);
                miTablero.setNumeroDePalabras(numPalabras);
                listadoDePalabras.clear();
                listadoDePalabras = miTablero.getListaDePalabras();
                cargarListaDePalabras();
                unCanvas.setTablero(miTablero.getTablero(), listadoDePalabras, tamanoX, tamanoX);
            }
            
        });
        
    }

    public void tacharEtiqueta(String palabra) {
       /* if (listadoDePalabras.contains(palabra)){
            
            int i=listadoDePalabras.indexOf(palabra);
            System.out.println(i);
            lblPalabra[i].setFont(new Font(this.getFont().getName(),this.getFont().getSize(), 3));
            lblPalabra[i].setBackground(Color.red);
      }*/
        for (int i=0;i<listadoDePalabras.size();i++){
            lblPalabra[i].setFont(new Font(this.getFont().getName(),i, this.getFont().getSize()));
        }
        
    }
    
}
