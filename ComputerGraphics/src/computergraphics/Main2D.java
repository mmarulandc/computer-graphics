package computergraphics;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;


public class Main2D extends JPanel implements KeyListener{

    double sX = 1;
    double sY = 1;
    int dX = 0;
    int dY = 0;
    int r = 0;
    int xO = 50;
    int yO = 30;

    FileReader fileReader = new FileReader();

    List<Point3> puntosF = fileReader.getPuntos();
    List<int[]> aristasF = fileReader.getAristas();
    List<Point3> puntos = new LinkedList<>();

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        dibujarObjeto(g2d);
    }

    public void timePointormar(){
        for(int i = 0; i < puntosF.size();i++){

            Point3 p1 = puntosF.get(i);

            Matrix3x3 origen1 = new Matrix3x3();
            Matrix3x3 origen2 = new Matrix3x3();
            Matrix3x3 rotacion = new Matrix3x3();
            Matrix3x3 escala = new Matrix3x3();
            Matrix3x3 traslacion = new Matrix3x3();

            origen1.setMatrix(0,0,1);
            origen1.setMatrix(0,1,0);
            origen1.setMatrix(0,2,-xO);
            origen1.setMatrix(1,0,0);
            origen1.setMatrix(1,1,1);
            origen1.setMatrix(1,2,-yO);
            origen1.setMatrix(2,0,0);
            origen1.setMatrix(2,1,0);
            origen1.setMatrix(2,2,1);

            origen2.setMatrix(0,0,1);
            origen2.setMatrix(0,1,0);
            origen2.setMatrix(0,2,xO);
            origen2.setMatrix(1,0,0);
            origen2.setMatrix(1,1,1);
            origen2.setMatrix(1,2,yO);
            origen2.setMatrix(2,0,0);
            origen2.setMatrix(2,1,0);
            origen2.setMatrix(2,2,1);

            double angRad = Math.toRadians(r);
            double cos = Math.cos(angRad);
            double sin = Math.sin(angRad);

            rotacion.setMatrix(0,0,cos);
            rotacion.setMatrix(0,1,sin);
            rotacion.setMatrix(0,2,0);
            rotacion.setMatrix(1,0,-sin);
            rotacion.setMatrix(1,1,cos);
            rotacion.setMatrix(1,2,0);
            rotacion.setMatrix(2,0,0);
            rotacion.setMatrix(2,1,0);
            rotacion.setMatrix(2,2,1);

            escala.setMatrix(0,0,sX);
            escala.setMatrix(0,1,0);
            escala.setMatrix(0,2,0);
            escala.setMatrix(1,0,0);
            escala.setMatrix(1,1,sY);
            escala.setMatrix(1,2,0);
            escala.setMatrix(2,0,0);
            escala.setMatrix(2,1,0);
            escala.setMatrix(2,2,1);

            traslacion.setMatrix(0,0,1);
            traslacion.setMatrix(0,1,0);
            traslacion.setMatrix(0,2,dX);
            traslacion.setMatrix(1,0,0);
            traslacion.setMatrix(1,1,1);
            traslacion.setMatrix(1,2,dY);
            traslacion.setMatrix(2,0,0);
            traslacion.setMatrix(2,1,0);
            traslacion.setMatrix(2,2,1);

            p1 = p1.timePoint(origen1,p1);
            p1 = p1.timePoint(rotacion,p1);
            p1 = p1.timePoint(escala,p1);
            p1 = p1.timePoint(traslacion,p1);
            p1 = p1.timePoint(origen2,p1);

            puntos.add(i,p1);
        }
    }

    public void dibujarObjeto(Graphics2D g2d){
        int x0;
        int y0;
        int x1;
        int y1;

        timePointormar();

        for (int[] arista : aristasF) {


            x0 = (int) puntos.get(arista[0]).getX();
            y0 = (int) puntos.get(arista[0]).getY();
            x1 = (int) puntos.get(arista[1]).getX();
            y1 = (int) puntos.get(arista[1]).getY();

            g2d.drawLine(x0, y0, x1, y1);

        }

    }

    public void keyReleased(KeyEvent ke) {
        repaint();
    }

    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_W) {
            System.out.println("key pressed W");
            dY -= 5;
        }

        if(ke.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("key pressed A");
            dX -= 5;
        }

        if(ke.getKeyCode() == KeyEvent.VK_S) {
            System.out.println("key pressed S");
            dY += 5;
        }

        if(ke.getKeyCode() == KeyEvent.VK_D) {
            System.out.println("key pressed D");
            dX += 5;
        }

        if(ke.getKeyCode() == KeyEvent.VK_I) {
            System.out.println("key pressed I");
            r += 5;
            if(r == 360){ r = 0;}
            System.out.println("r = " + r);
        }

        if(ke.getKeyCode() == KeyEvent.VK_K) {
            System.out.println("key pressed K");
            if(r == 0){ r = 360;}
            r -= 5;
            System.out.println("r = " + r);
        }

        if(ke.getKeyCode() == KeyEvent.VK_L) {
            System.out.println("key pressed L");
            if(sX >= 0.2){
                sX -= 0.2;
                sY -= 0.2;
            }else{
                System.out.println("Escala Minima Alcanzada");
            }
        }

        if(ke.getKeyCode() == KeyEvent.VK_O) {
            System.out.println("key pressed O");
            sX += 0.2;
            sY += 0.2;

        }
    }

    public void keyTyped(KeyEvent ke) {
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lines");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main2D main = new Main2D();
        frame.add(main);
        frame.addKeyListener(main);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
