/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopasDeLetras;

import java.awt.BasicStroke;
import sopasDeLetras.VentanaConCanvas;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
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
    private ArrayList<Marcador> marcadores;

    MiCanvas(VentanaConCanvas vcc) {
        this.setBackground(Color.CYAN);
        this.vcc = vcc;
        todasLasImagenesAzules = new ArrayList<>();
        todasLasImagenesRosas = new ArrayList<>();
        marcadores = new ArrayList<>();
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
        //limpiamos lista de marcadores para borrar los rectángulos que enmarcan
        //a las palabras de la anterior sopa de letrs
        this.marcadores.clear();
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
            Marcador m = new Marcador();
            m.setInicio(xElegido, yElegido);
            xElegido = (xRaton / tamanoLetra) * tamanoLetra;
            yElegido = (yRaton / tamanoLetra) * tamanoLetra;
            if (m.setFin(xElegido, yElegido)) {
                marcadores.add(m);
            }
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
        //TODO PINTAR LAS MARCAS DE LAS PALABRAS ELEGIDAS
        /*
         Marcador m = new Marcador();
         boolean sePuedePintar = false;
         m.setInicio(50, 100);
         sePuedePintar = m.setFin(200, 250);
         if (m.setFin(150, 200)) {
         pintarRectanguloRedondeado2(g, m);
         }
         m.setInicio(50, 50);
         if (m.setFin(200, 100)) {
         pintarRectanguloRedondeado2(g, m);
         }*/
        for (Marcador m : marcadores) {
            pintarRectanguloRedondeado2(g, m);
            System.out.println(m);
        }
    }

    void pintarRectanguloRedondeado2(Graphics g, Marcador m) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(Color.RED);
        g2.setStroke(new BasicStroke(3.0f));

        double x = m.getxInicio();
        double y = m.getyInicio();
        double w = tamanoLetra;
        double h = Math.abs(m.getyFin() - m.getyInicio());

        if (m.getxInicio() == m.getxFin()) { //vertical
            h = h + tamanoLetra;
            //si hemos elegido primero el de abajo tenemos que cambiarlo
            if (m.getyInicio() > m.getyFin()) {
                y = m.getyFin();
            }
            g2.draw(new RoundRectangle2D.Double(x, y, w, h, tamanoLetra, tamanoLetra));

        } else {
            if (m.getyInicio() == m.getyFin()) { //horizontal
                h = w;
                w = (Math.abs(m.getxFin() - m.getxInicio())) + tamanoLetra;
                //si hemos elegido primero el de la derecha tenemos que cambiarlo
                if (m.getxInicio() > m.getxFin()) {
                    x = m.getxFin();
                }
                g2.draw(new RoundRectangle2D.Double(x, y, w, h, tamanoLetra, tamanoLetra));
            } else {
                //TODO NECESITO PAEPEL Y LAPIZ
                if (Math.abs(m.getxInicio() - m.getxFin()) == Math.abs(m.getyInicio() - m.getyFin())) { //diagonal

                    double angulo = 0;
                    if (m.getxInicio() > m.getxFin() && m.getyInicio() > m.getyFin()) {
                        angulo = 135;
                       // x = m.getxFin();
                        // y = m.getyFin();
                    } else {
                        if (m.getxInicio() < m.getxFin() && m.getyInicio() < m.getyFin()) {
                            angulo = 315;
                            //  x = m.getxFin();
                            //y = m.getyFin();
                        } else {
                            if (m.getxInicio() > m.getxFin() && m.getyInicio() < m.getyFin()) {
                                angulo = 45;
                                //y = m.getyFin();
                            } else {
                                if (m.getxInicio() < m.getxFin() && m.getyInicio() > m.getyFin()) {
                                    angulo = 225;
                                    //  x = m.getxFin();
                                }
                            }

                        }
                    }
                    //(m.getxInicio() - m.getyInicio()) == (m.getxFin() - m.getyFin())) { //diagonal
                    AffineTransform oldXForm = g2.getTransform();

                    double incremento = Math.sqrt((tamanoLetra * tamanoLetra) + (tamanoLetra * tamanoLetra));
                    h = incremento * ((h / tamanoLetra) + (incremento / 100.0));

                    double r = Math.toRadians(angulo); //se convierte a radianes lo grados

                    AffineTransform at = new AffineTransform();

                    at.rotate(r, x + (tamanoLetra / 2), y + (tamanoLetra / 2)); //se asigna el angulo y centro de rotacion

                    g2.setTransform(at);
                    g2.draw(new RoundRectangle2D.Double(x, y, w, h, tamanoLetra, tamanoLetra));
                    g2.setTransform(oldXForm); // De esta forma elimino la rotación

                } ////HASTA AQUI EL TODO DEL PAPEL Y LAPIZ
            }

        }

    }
}
