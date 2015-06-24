/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopasDeLetras;

import sopasDeLetras.VentanaConCanvas;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author antoniogarcia
 */
public class MiCanvas extends Canvas {
    
    private int xRaton;
    private int yRaton;
    private int xElegido;
    private int yElegido;
    private String paraPoner;
    private String paraMostrar;
    private boolean elegido;
    private int tamanoHorizontal;
    private int tamanoVertical;
    private VentanaConCanvas vcc;
    private ArrayList<Image> todasLasImagenesAzules;
    private ArrayList<Image> todasLasImagenesRosas;
    private ArrayList<String> relacionPalabras;
    private Image fondoSeleccion;
    private int tamanoLetra;
    
    MiCanvas(VentanaConCanvas vcc) {
        this.setBackground(Color.CYAN);
        this.vcc = vcc;
        todasLasImagenesAzules = new ArrayList<>();
        todasLasImagenesRosas = new ArrayList<>();
        //creamos un primer tablero aleatorio para que nos muestre algo al empezar
        tamanoHorizontal = 10;
        tamanoVertical = 10;
        StringBuilder cadenaAleatoria = new StringBuilder();
        for (int i = 0; i < tamanoHorizontal; i++) {
            for (int j = 0; j < tamanoHorizontal; j++) {
                int numero = Utilidades.dameUnNumeroEntre(0, 26);
                if (numero == 26) {
                    cadenaAleatoria.append('ñ');
                } else {
                    cadenaAleatoria.append((char) (numero + 97));
                }
            }
        }
        //cadenaParaMostrar.setCharAt(20, 'r');
        paraPoner = cadenaAleatoria.toString();
        setParaMostrar();
        //fin creacion de tablero
        elegido = false;
        System.out.println("tamano Horizontal = " + tamanoHorizontal);
        setTamanoLetra();
        cargaTodasLasImagenes();
    }

    private void setTamanoLetra() {
        int haCambiado = tamanoLetra;
        if (tamanoHorizontal > tamanoVertical) {
            tamanoLetra = 500 / tamanoHorizontal;
        } else {
            tamanoLetra = 500 / tamanoVertical;
        }
        if (tamanoLetra != haCambiado) {
            System.out.println("dlasidflasdkf");
            cargaTodasLasImagenes();
        }
    }

    private void setParaMostrar() {
        StringBuilder cadenaParaMostrar = new StringBuilder();
        for (int i = 0; i < tamanoHorizontal; i++) {
            for (int j = 0; j < tamanoHorizontal; j++) {
                cadenaParaMostrar.append('a');
            }
        }
        paraMostrar = cadenaParaMostrar.toString();
    }

    public void update(Graphics g) {
        paint(g);
    }
    
    private Image cargarImagen(String archivo) {
        try {
            setTamanoLetra();
            Image img1 = ImageIO.read(getClass().getClassLoader().getResource(archivo));
            img1 = img1.getScaledInstance(tamanoLetra, tamanoLetra, 0);
            return img1;
        } catch (IOException ex) {
            Logger.getLogger(MiCanvas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void setTablero(String paraPoner, ArrayList<String> relacionPalabras, int tamanoHorizontal, int tamanoVertical) {
        
        this.relacionPalabras = relacionPalabras;
        this.tamanoHorizontal = tamanoHorizontal;
        this.tamanoVertical = tamanoVertical;
        setTamanoLetra();
        setParaMostrar();
        this.paraPoner = paraPoner.toLowerCase();
        this.repaint();
    }
    
    public void setxRaton(int xRaton) {
        this.xRaton = xRaton;
    }
    
    public void setyRaton(int yRaton) {
        this.yRaton = yRaton;
    }
    
    public void setElegido() {
        if (!elegido) {
            xElegido = (xRaton / tamanoLetra) * tamanoLetra;
            yElegido = (yRaton / tamanoLetra) * tamanoLetra;
            elegido = true;
        } else {
            elegido = false;
        }
    }
    
    private void cargaTodasLasImagenes() {
        String rutaImagen;
        setTamanoLetra();
        todasLasImagenesAzules.clear();
        todasLasImagenesRosas.clear();
        for (int i = 97; i <= 122; i++) {
            rutaImagen = "recursos/" + (char) i + "azul.png";
            todasLasImagenesAzules.add(cargarImagen(rutaImagen));
            rutaImagen = "recursos/" + (char) i + "rosa.png";
            todasLasImagenesRosas.add(cargarImagen(rutaImagen));
        }
        rutaImagen = "recursos/" + '1' + "azul.png";
        todasLasImagenesAzules.add(cargarImagen(rutaImagen));
        rutaImagen = "recursos/" + '1' + "rosa.png";
        todasLasImagenesRosas.add(cargarImagen(rutaImagen));
        fondoSeleccion = cargarImagen("recursos/fondoseleccion.png");
        
    }
    
    @Override
    public void paint(Graphics g) {
        // Se crea una imagen del mismo tamaño que el Canvas
        BufferedImage imagen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        g.clearRect(0, 0, 500, 500);
        int caracter;
        Image miImagen;
        for (int i = 0; i < tamanoHorizontal; i++) {
            for (int j = 0; j < tamanoVertical; j++) {
                
                caracter = (paraPoner.codePointAt((i * tamanoVertical) + j)) - 97;
                if (caracter > 26) {
                    caracter = 26;
                }
                if (paraMostrar.codePointAt((i * tamanoVertical) + j) == 'a') {
                    miImagen = todasLasImagenesAzules.get(caracter);
                } else {
                    miImagen = todasLasImagenesRosas.get(caracter);
                }
                
                imagen.getGraphics().drawImage(miImagen, j * tamanoLetra, i * tamanoLetra, null);
            }
        }
        if (elegido) {
            miImagen = fondoSeleccion;
            imagen.getGraphics().drawImage(miImagen, xElegido, yElegido, null);
        } else {
            g.clearRect(xElegido, yElegido, tamanoLetra, tamanoLetra);
        }
        // Se "pega" la imagen sobre el componente
        g.drawImage(imagen, 0, 0, this);
        
    }
    
}
