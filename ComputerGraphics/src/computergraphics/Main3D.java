package computergraphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Main3D extends JPanel implements KeyListener {

    double s = 1;
    int dX = 0;
    int dY = 0;
    int dZ = 0;
    int rX = 0;
    int rY = 0;
    int rZ = 0;
    int xO = 50;
    int yO = 30;
    int zO = -110;
    int d = 250;
    Color colorColl;

    static boolean coll = false;

    static FileReader3D fileReader = new FileReader3D();

    static Object3D[] objetos;





    /*
    List<Point4> puntosF = fileReader.getPuntos();
    List<int[]> aristasF = fileReader.getAristas();
    */



    public static void depthSort(Object3D[] obj)
    {
        int j;
        boolean flag = true;   // set flag to true to begin first pass
        Object3D temp;   //holding variable

        while ( flag )
        {
            flag= false;    //set flag to false awaiting a possible swap
            for( j=0;  j < obj.length -1;  j++ )
            {
                if ( obj[ j ].getzMenor() < obj[j+1].getzMenor() )   // change to > for ascending sort
                {
                    temp = obj[ j ];                //swap elements
                    obj[ j ] = obj[ j+1 ];
                    obj[ j+1 ] = temp;
                    flag = true;              //shows a swap occurred
                }
            }
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        this.setBackground(Color.BLACK);

        depthSort(objetos);


        for (int i = 0; i < objetos.length; i++) {
            dibujarObjeto(g2d, objetos[i]);
        }


    }

    void collision(Object3D obj1, Object3D obj2) {
        coll = false;

        System.out.println(obj1.getyMenor());

        for(Point4 pto : obj1.getPuntos()) {
            if(pto.getX() >= obj2.getxMenor() && pto.getX() <= obj2.getxMayor()) {
                if (pto.getY() >= obj2.getyMenor() && pto.getY() <= obj2.getyMayor()) {
                    if (pto.getZ() >= obj2.getzMenor() && pto.getZ() <= obj2.getzMayor()) {
                        coll = true;
                    }
                }
            }
        }

        for(Point4 pto : obj2.getPuntos()) {
            if(pto.getX() >= obj1.getxMenor() && pto.getX() <= obj1.getxMayor()) {
                if (pto.getY() >= obj1.getyMenor() && pto.getY() <= obj1.getyMayor()) {
                    if (pto.getZ() >= obj1.getzMenor() && pto.getZ() <= obj1.getzMayor()) {
                        coll = true;
                    }
                }
            }
        }

        System.out.println(coll);




    }

    Object3D transformar(Object3D obj){

        List<Point4> puntos = new LinkedList<Point4>();
        Matrix4x4 origen1 = new Matrix4x4();
        Matrix4x4 origen2 = new Matrix4x4();
        Matrix4x4 rotacionX = new Matrix4x4();
        Matrix4x4 rotacionY = new Matrix4x4();
        Matrix4x4 rotacionZ = new Matrix4x4();
        Matrix4x4 escala = new Matrix4x4();
        Matrix4x4 traslacion = new Matrix4x4();
        Matrix4x4 perspectiva = new Matrix4x4();

        double[][] origen1M ={{1,0,0,-xO},
                              {0,1,0,-yO},
                              {0,0,1,-zO},
                              {0,0,0,1}};
        origen1.setMatrix(origen1M);

        double[][] origen2M ={{1,0,0,xO},
                              {0,1,0,yO},
                              {0,0,1,zO},
                              {0,0,0,1}};
        origen2.setMatrix(origen2M);

        double angRadX = Math.toRadians(rX);
        double cosX = Math.cos(angRadX);
        double sinX = Math.sin(angRadX);
        double angRadY = Math.toRadians(rY);
        double cosY = Math.cos(angRadY);
        double sinY = Math.sin(angRadY);
        double angRadZ = Math.toRadians(rZ);
        double cosZ = Math.cos(angRadZ);
        double sinZ = Math.sin(angRadZ);

        double[][] rotacionXM ={{1,0,0,0},
                                {0,cosX,-sinX,0},
                                {0,sinX,cosX,0},
                                {0,0,0,1}};
        rotacionX.setMatrix(rotacionXM);

        double[][] rotacionYM ={{cosY,0,sinY,0},
                                {0,1,0,0},
                                {-sinY,0,cosY,0},
                                {0,0,0,1}};
        rotacionY.setMatrix(rotacionYM);

        double[][] rotacionZM ={{cosZ,-sinZ,0,0},
                                {sinZ,cosZ,0,0},
                                {0,0,1,0},
                                {0,0,0,1}};
        rotacionZ.setMatrix(rotacionZM);

        double[][] escalaM ={{s,0,0,0},
                             {0,s,0,0},
                             {0,0,s,0},
                             {0,0,0,1}};
        escala.setMatrix(escalaM);

        double[][] traslacionM ={{1,0,0,dX},
                                 {0,1,0,dY},
                                 {0,0,1,dZ},
                                 {0,0,0,1}};
        traslacion.setMatrix(traslacionM);

        double[][] perspectivaM ={{1,0,0,0},
                                  {0,1,0,0},
                                  {0,0,1,0},
                                  {0,0,1/d,0}};
        perspectiva.setMatrix(perspectivaM);

        for(int i = 0; i < obj.getPuntos().size();i++){
            Point4 p1 = obj.getPuntos().get(i);

            p1 = p1.transf(perspectiva,p1);
            p1.normalize();
            p1 = p1.transf(origen1,p1);
            p1 = p1.transf(rotacionX,p1);
            p1 = p1.transf(rotacionY,p1);
            p1 = p1.transf(rotacionZ,p1);
            p1 = p1.transf(traslacion,p1);
            p1 = p1.transf(escala,p1);
            p1 = p1.transf(origen2,p1);


            puntos.add(i,p1);
        }

        List<int[]> aristasNew = obj.getAristas();

        return new Object3D(aristasNew, puntos);
    }

    public void dibujarObjeto(Graphics2D g2d, Object3D obj){
        int x0;
        int y0;
        int x1;
        int y1;

        if (obj==objetos[0]) {
            obj = transformar(obj);
            coll = false;
            collision(obj, objetos[1]);
        }




        if (coll) {
            colorColl = Color.RED;
        } else {
            colorColl = Color.BLUE;
        }

        List<Point4> puntos = obj.getPuntos();
        List<int[]> aristasF = obj.getAristas();

        //ran = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));



        for(int[] arista : aristasF){
            x0 = (int) puntos.get(arista[0]).getX();
            y0 = (int) puntos.get(arista[0]).getY();
            x1 = (int) puntos.get(arista[1]).getX();
            y1 = (int) puntos.get(arista[1]).getY();

            g2d.setColor(colorColl);
            g2d.drawLine(x0,y0,x1,y1);
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

        if(ke.getKeyCode() == KeyEvent.VK_X) {
            System.out.println("key pressed X");
            dZ -= 5;
        }

        if(ke.getKeyCode() == KeyEvent.VK_C) {
            System.out.println("key pressed C");
            dZ += 5;
        }

        if(ke.getKeyCode() == KeyEvent.VK_Q) {
            System.out.println("key pressed Q");
            rX += 5;
            if(rX == 360){ rX = 0;}
            System.out.println("rX = " + rX);
        }

        if(ke.getKeyCode() == KeyEvent.VK_E) {
            System.out.println("key pressed E");
            if(rX == 0){ rX = 360;}
            rX -= 5;
            System.out.println("rX = " + rX);
        }

        if(ke.getKeyCode() == KeyEvent.VK_1) {
            System.out.println("key pressed 1");
            rY += 5;
            if(rY == 360){ rY = 0;}
            System.out.println("rY = " + rY);
        }

        if(ke.getKeyCode() == KeyEvent.VK_4) {
            System.out.println("key pressed 4");
            if(rY == 0){ rY = 360;}
            rY -= 5;
            System.out.println("rY = " + rY);
        }

        if(ke.getKeyCode() == KeyEvent.VK_2) {
            System.out.println("key pressed 2");
            rZ += 5;
            if(rZ == 360){ rZ = 0;}
            System.out.println("rZ = " + rZ);
        }

        if(ke.getKeyCode() == KeyEvent.VK_3) {
            System.out.println("key pressed 3");
            if(rZ == 0){ rZ = 360;}
            rZ -= 5;
            System.out.println("rZ = " + rZ);
        }

        if(ke.getKeyCode() == KeyEvent.VK_MINUS || ke.getKeyCode() == KeyEvent.VK_SUBTRACT) {
            System.out.println("key pressed -");
            if(s >= 0.2){
                s -= 0.2;
            }else{
                System.out.println("Escala Minima Alcanzada");
            }
        }

        if(ke.getKeyCode() == KeyEvent.VK_PLUS || ke.getKeyCode() == KeyEvent.VK_ADD) {
            System.out.println("key pressed +");
            s += 0.2;

        }

        if(ke.getKeyCode() == KeyEvent.VK_ENTER){
            //for(int i = 0 ; i<1000;i++){
                int r1 =(int)(Math.random()*4);
                switch (r1){
                    case 1:
                        rX +=10;
                        repaint();
                        break;

                    case 2:
                        rY += 10;
                        repaint();
                        break;

                    case 3:
                        rZ +=10;
                        repaint();
                        break;
                }
            }
        //}
    }

    public void keyTyped(KeyEvent ke) {
        //ran = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
        //System.out.println(ran.toString());
        repaint();
    }

    public static void main(String[] args) {
        try {
            objetos = fileReader.leerObjetos();
            System.out.println("Leidos objetos del archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Lines");
//      Lines frame = new Lines();
//      frame.addKeyListener(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main3D main = new Main3D();
        frame.add(main);
        frame.addKeyListener(main);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}