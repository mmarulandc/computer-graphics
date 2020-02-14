package computergraphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;
import computergraphics.Point;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mmarulandc & ynarvaezp
 */
public class Edge extends JPanel {

    static ArrayList<Point> puntos = new ArrayList<>();
    public static int[] array;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.blue);

        Dimension size = getSize();
        Insets insets = getInsets();

        int w = size.width - insets.left - insets.right;
        int h = size.height - insets.top - insets.bottom;

        for (int i = 0; i < puntos.size(); i++) {
            int tempX = puntos.get(i).getX();
            int tempY = puntos.get(i).getY();
            g2d.drawLine(tempX, tempY, tempX, tempY);
        }
        int aristas[] = getArray();

        for (int i = 0; i < aristas.length; i = i + 2) {
            int p1 = aristas[i];
            int p2 = aristas[i + 1];

            int x1 = puntos.get(p1).getX();
            int y1 = puntos.get(p1).getY();
            int x2 = puntos.get(p2).getX();
            int y2 = puntos.get(p2).getY();

            g2d.drawLine(x1, y1, x2, y2);
        }

    }

    public static void main(String[] args) {

        System.out.println("¿Cuántos puntos desea hacer?");

        Scanner sc = new Scanner(System.in);
        int nPuntos = sc.nextInt();

        System.out.println("Inserte los puntos");
        for (int i = 0; i < nPuntos; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            Point punto = new Point(x, y);

            puntos.add(punto);
        }

        System.out.println("¿Cuántos Aristas a dibujar?");
        int nAristas = sc.nextInt();
        setArray(nAristas);

        System.out.println("Inserte las aristas con un espacio. Ejemplo: 0 1 (asi se une el punto 0 con el punto 1)");

        int[] arrTemp = new int[nAristas * 2];
        for (int i = 0; i < nAristas * 2; i = i + 2) {
            int a1 = sc.nextInt();
            int a2 = sc.nextInt();

            arrTemp[i] = a1;
            arrTemp[i + 1] = a2;
        }

        setArray(arrTemp);

        JFrame frame = new JFrame("Dibujo");
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Edge());
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void setArray(int i) {
        Edge.array = new int[i * 2];
    }

    public static void setArray(int[] array) {
        Edge.array = array;
    }

    public static int[] getArray() {
        return Edge.array;
    }
}
