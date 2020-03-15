package computergraphics;

import java.util.LinkedList;
import java.util.List;


public class Object3D {
    private List<int[]> listaAristas = new LinkedList<int[]>();
    private List<Point4> listaPuntos = new LinkedList<Point4>();

    private double xMenor;
    private double yMenor;
    private double zMenor;

    private double xMayor;
    private double yMayor;
    private double zMayor;

    public Object3D(List<int[]> aristas, List <Point4> puntos) {
        listaAristas = aristas;
        listaPuntos = puntos;


        if(puntos.size()==2) {
            //puntos tapa de arriba
            listaPuntos.add(new Point4(listaPuntos.get(1).getX(), listaPuntos.get(0).getY(), listaPuntos.get(0).getZ()));
            listaPuntos.add(new Point4(listaPuntos.get(1).getX(), listaPuntos.get(0).getY(), listaPuntos.get(1).getZ()));
            listaPuntos.add(new Point4(listaPuntos.get(0).getX(), listaPuntos.get(0).getY(), listaPuntos.get(1).getZ()));

            //puntos tapa de abajo
            listaPuntos.add(new Point4(listaPuntos.get(0).getX(), listaPuntos.get(1).getY(), listaPuntos.get(1).getZ()));
            listaPuntos.add(new Point4(listaPuntos.get(0).getX(), listaPuntos.get(1).getY(), listaPuntos.get(0).getZ()));
            listaPuntos.add(new Point4(listaPuntos.get(1).getX(), listaPuntos.get(1).getY(), listaPuntos.get(0).getZ()));

            //aristas
            int[] a1 = {0, 2};
            int[] a2 = {2, 3};
            int[] a3 = {3, 4};
            int[] a4 = {4, 0};
            int[] a5 = {6, 7};
            int[] a6 = {7, 1};
            int[] a7 = {1, 5};
            int[] a8 = {5, 6};
            int[] a9 = {0, 6};
            int[] a10 = {2, 7};
            int[] a11 = {3, 1};
            int[] a12 = {4, 5};
            listaAristas.add(a1);
            listaAristas.add(a2);
            listaAristas.add(a3);
            listaAristas.add(a4);
            listaAristas.add(a5);
            listaAristas.add(a6);
            listaAristas.add(a7);
            listaAristas.add(a8);
            listaAristas.add(a9);
            listaAristas.add(a10);
            listaAristas.add(a11);
            listaAristas.add(a12);
        }


        xMenor = puntos.get(0).getX();
        yMenor = puntos.get(2).getY();
        zMenor = puntos.get(0).getZ();

        xMayor = puntos.get(0).getX();
        yMayor = puntos.get(0).getY();
        zMayor = puntos.get(0).getZ();

        //Determinamos los minimos puntos

        for(Point4 punto : puntos) {
            if (punto.getX() < xMenor) {
                xMenor = punto.getX();
            }
            if (punto.getY() < yMenor) {
                yMenor = punto.getY();
            }
            if (punto.getZ() < zMenor) {
                zMenor = punto.getZ();
            }
        }

        //Determinamos puntos maximos

        for(Point4 punto : puntos) {
            if (punto.getX() > xMayor) {
                xMayor = punto.getX();
            }
            if (punto.getY() > yMayor) {
                yMayor = punto.getY();
            }
            if (punto.getZ() > zMayor) {
                zMayor = punto.getZ();
            }
        }
    }

    public List<Point4> getPuntos() {
        return listaPuntos;
    }

    public List<int[]> getAristas() {
        return listaAristas;
    }

    public double getxMenor() {
        return xMenor;
    }

    public double getyMenor() {
        return yMenor;
    }

    public double getzMenor() {
        return zMenor;
    }

    public double getxMayor() {
        return xMayor;
    }

    public double getyMayor() {
        return yMayor;
    }

    public double getzMayor() {
        return zMayor;
    }
}