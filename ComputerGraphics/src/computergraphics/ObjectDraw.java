package computergraphics;

/**
 *
 * @author mmarulandc yanarvaezp
 */
import java.awt.*;
import java.util.List;

/**
 * Created by nekothecat on 2/25/16.
 */
public class ObjectDraw {

    FileReader fr;
    public ObjectDraw (FileReader f) {
        this.fr = f;
    }


    public void dibujarObjeto(Graphics2D g2d) {
        List<Point3> puntos = fr.getPuntos();
        List<int[]> aristas = fr.getAristas();

        int x0;
        int y0;
        int x1;
        int y1;

        for (int[] arista : aristas) {
            x0 = (int) puntos.get(arista[0]).getX();
            y0 = (int) puntos.get(arista[0]).getY();
            x1 = (int) puntos.get(arista[1]).getX();
            y1 = (int) puntos.get(arista[1]).getY();
            g2d.drawLine(x0, y0, x1, y1);
        }

    }
}