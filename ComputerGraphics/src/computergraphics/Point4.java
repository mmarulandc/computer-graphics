package computergraphics;

/**
 *
 * @author mmarulandc && yanarvaezp
 */
public class Point4 {

    private double x;
    private double y;
    private double z;
    private double w;

    public Point4(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Point4(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1;
    }

    public Point4 transf(Matrix4x4 m1, Point4 p) {
        double[][] m = m1.getMatrix();
        double[] point = {p.getX(), p.getY(), p.getZ(), p.getW()};
        double[] res = {0, 0, 0, 0};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res[i] += m[i][j] * point[j];
            }
        }
        return new Point4(res[0], res[1], res[2]);
    }
    
    public void normalize(){
        if(w == 1){
            return;
        }else{
            x = x/w;
            y = y/w;
            z = z/w;
            w = 1;
        }

    }
    
    public Point4() {
        //: void contructor
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

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

}
