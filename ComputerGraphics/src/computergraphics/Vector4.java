package computergraphics;

/**
 *
 * @author mmarulandc & yanarvaezp
 */
public class Vector4 {

    private double x;
    private double y;
    private double z;

    public Vector4(Point4 p1, Point4 p2) {
        x = p2.getX() - p1.getX();
        y = p2.getY() - p1.getY();
        z = p2.getZ() - p1.getZ();
    }

    public Vector4 cruz(Vector4 v1) {
        Vector4 vR = v1;

        vR.setX(this.y * v1.getZ() - this.z * v1.getY());
        vR.setY(-1 * (this.x * v1.getZ() - this.z * v1.getX()));
        vR.setZ(this.x * v1.getY() - this.y * v1.getX());

        return vR;
    }

    public double dot(Vector4 v1) {
        return (this.x * v1.getX() + this.y * v1.getY() + this.z * v1.getZ());
    }
    
    public double magnitude() {
        return Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2));
    }

    public void  normilize() {
        double magnitude = this.magnitude();
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        this.setX(x/magnitude());
        this.setY(y/magnitude());
        this.setZ(z/magnitude());
        System.out.println("Vector normalizado ("+x+"/"+magnitude+", "+y+"/"+magnitude+", "+z+"/"+magnitude+"");
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
