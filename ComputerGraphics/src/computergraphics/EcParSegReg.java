package computergraphics;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author mmarulandc & ynarvaezp
 */
public class EcParSegReg {

    int x1;
    int x2;
    int y1;
    int y2;

    int u1;
    int u2;

    EcParSegReg(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.y2 = x1;

        this.x2 = x1;
        this.y2 = x1;
    }

    /*
    Ejemplo de entrada:
    epsr1: x = x1 + (x2 - x1)t, [ x = -2 + ( 2 + 2 ) t ], X = -2 + 4t 
    epsr2: y = y1 + (y2 - y1)t, [ y = 0 + ( 2 - 0 ) t ], Y = 2t
     */
    public static String solve(String epsr1, String epsr2) {
        EcParSegReg ec = new EcParSegReg(0, 0, 0, 0);
        String respuesta = "u1 = ";

        StringTokenizer ecuacion1 = new StringTokenizer(epsr1);
        StringTokenizer ecuacion2 = new StringTokenizer(epsr2);

        int cont = 0;
        while (ecuacion1.hasMoreTokens()) {
            String temp = ecuacion1.nextToken();
            if (!temp.equals("x")) {
                if (!temp.equals("=")) {
                    if (!temp.equals("(")) {
                        if (!temp.equals(")")) {
                            if (!temp.equals("+")) {
                                if (!temp.equals("t")) {
                                    if (!temp.equals("-")) {
                                        if (cont == 1) {
                                            ec.setX2(Integer.parseInt(temp));
                                            cont++;
                                        }
                                        if (cont == 0) {
                                            ec.setX1(Integer.parseInt(temp));
                                            cont++;
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        ec.setX2((ec.getX2() - ec.getX1()));

        if (ec.getX2() >= 0) {
            respuesta += ec.getX1() + " + " + ec.getX2() + "t, u2 = ";
        } else {
            respuesta += ec.getX1() + " " + ec.getX2() + "t, u2 = ";
        }

        cont = 0;

        while (ecuacion2.hasMoreTokens()) {
            String temp = ecuacion2.nextToken();
            if (!temp.equals("y")) {
                if (!temp.equals("=")) {
                    if (!temp.equals("(")) {
                        if (!temp.equals(")")) {
                            if (!temp.equals("+")) {
                                if (!temp.equals("t")) {
                                    if (!temp.equals("-")) {
                                        if (cont == 1) {
                                            ec.setY2(Integer.parseInt(temp));
                                            cont++;
                                        }
                                        if (cont == 0) {
                                            ec.setY1(Integer.parseInt(temp));
                                            cont++;
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
        ec.setY2((ec.getY2() - ec.getY1()));

        if (ec.getY2() >= 0) {
            respuesta += ec.getY1() + " + " + ec.getY2() + "t";
        } else {
            respuesta += ec.getY1() + " " + ec.getY2() + "t";
        }
        System.out.println(respuesta);
        return respuesta;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getU1() {
        return u1;
    }

    public void setU1(int u1) {
        this.u1 = u1;
    }

    public int getU2() {
        return u2;
    }

    public void setU2(int u2) {
        this.u2 = u2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese las ecuaciones");
        solve(sc.nextLine(), sc.nextLine());

    }

}
