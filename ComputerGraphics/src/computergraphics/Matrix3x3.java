package computergraphics;

/**
 *
 * @author mmarulandc & yanarvaezp
 */
public class Matrix3x3 {

    private double[][] matrix;

    public Matrix3x3() {
        matrix[0][0] = 0;
        matrix[0][1] = 0;
        matrix[1][0] = 0;
        matrix[1][1] = 0;
        matrix[2][0] = 0;
        matrix[2][1] = 0;
        matrix[2][2] = 0;
        matrix[0][2] = 0;
        matrix[1][2] = 0;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int i, int j, double value) {
        matrix[i][j] = value;
    }

    public double[][] times(Matrix3x3 m1) {
        double[][] m = m1.getMatrix();
        double[][] mR = new double[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    mR[i][j] += this.matrix[i][k] * m[k][j];
                }
            }
        }
        return mR;
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
