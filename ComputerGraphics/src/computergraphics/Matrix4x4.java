package computergraphics;

/**
 *
 * @author mmarulandc & ynarvaezp
 */
public class Matrix4x4 {

    private double[][] matrix;

    public Matrix4x4() {
        matrix = new double[4][4];
    }

    public void setMatrix(double[][] m) {
        matrix = m;
    }

    public void setMatrixVal(int i, int j, double val) {
        matrix[i][j] = val;
    }

    public double[][] times(Matrix4x4 m1) {
        double[][] m = m1.getMatrix();
        double[][] mR = new double[4][4];

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {

                for (int k = 0; k < 4; k++) {
                    mR[i][j] += this.matrix[i][k] * m[k][j];
                }
            }
        }

        return mR;
    }

    public Point4 timesPoint(Matrix4x4 m1, Point4 p) {
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

    public double[][] getMatrix() {
        return matrix;
    }
}
