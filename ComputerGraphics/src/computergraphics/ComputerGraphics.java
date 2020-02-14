/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computergraphics;

/**
 *
 * @author mmarulandc yanarvaezp
 */
public class ComputerGraphics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Vector4 vector = new Vector4(new Point4(2,4,3), new Point4(1,2,4));
        System.out.println(vector.magnitude());
        vector.normilize();
    }
    
}
