package computergraphics;

/**
 *
 * @author mmarulandc && yanarvaezp
 */
public class Point3 {

    private double x;
    private double y;
    private double w;

    public Point3(double x, double y, double w) {
        this.x = x;
        this.y = y;
        this.w = w;
    }

    public Point3(double x, double y) {
        this.x = x;
        this.y = y;
        this.w = 1;
    }

    public Point3() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public Point3 timePoint(Matrix3x3 m1, Point3 p) {
        double[][] m = m1.getMatrix();
        double[] point = {p.getX(), p.getY(), p.getW()};
        double[] res = {0, 0, 0};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res[i] += m[i][j] * point[j];
            }
        }
        return new Point3(res[0], res[1]);
    }
}
