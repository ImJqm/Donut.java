//Imports arra list library to manage grid system
import java.util.ArrayList;


public class Donut
{
    //quick access for pi
    static double pi = Math.PI;
    
    //makes arrays to store each x, y, and luminance point
    static ArrayList<Double> gridx = new ArrayList<Double>();
    static ArrayList<Double> gridy = new ArrayList<Double>();
    static ArrayList<Double> gridz = new ArrayList<Double>();
    static ArrayList<Double> gridL = new ArrayList<Double>();
    //sets radius of torus
    static final double R1 = 1.0;
    //sets radius of center hole in donut
    static final double R2 = 2.0;
    //A and B are  values between 0 and 2pi to control the donut's pitch and yaw
    static double A = pi;
    static double B = 1.0;
    static double K2 = 5.0;
    //variables to be used to pre-compute sins and coss for easier typing
    static double pc1 = 0.0;
    //sets the width and height of the display
    static final int screenx = 50;
    static final int screeny = 50;
    //creates coordinate system to access everywhere
    static coordsystem cs;
    static final double pitch = 2.2;
    static final double yaw = 4.02;
    static final double i = 0.386;
    static final double j = 3.35;
    public static void main(String[] args)
    {
        
        
        //System.out.println("Z: "+(Math.cos(pitch)*(R2+R1*Math.cos(i))*Math.sin(j)+R1*Math.sin(pitch)*Math.sin(i)) + ", L: " + ((Math.cos(j))*(Math.cos(i))*(Math.sin(yaw))-((Math.cos(pitch))*(Math.cos(i))*(Math.sin(j)))-((Math.sin(pitch))*(Math.sin(i)))+(Math.cos(yaw))*(((Math.cos(pitch))*(Math.sin(i)))-((Math.cos(i))*(Math.sin(pitch))*(Math.sin(j))))));
        
        //sets score system
        cs = new coordsystem(screenx, screeny);
        //update loop
        while (true) {
            //clears screen
            System.out.print("\u001b[H");
            //clears lists
            gridx.clear();
            gridy.clear();
            gridz.clear();
            gridL.clear();
            //resets the coord system
            cs.resetSys();
            //calculates the needed values to draw a frame and updates the appropriate lists
            frame(A,B);
            //iterates through lists and add points to cs
            for (int i =0; i<gridx.size(); i++) {
                cs.addPoint(gridx.get(i) , gridy.get(i) , gridz.get(i) , gridL.get(i));
            }
            //prints screen
            cs.printScreen();
            //increments rotation variables
            A+=0.07;
            B+=0.02;
            //waits 20 ms
            wait(20);
        }
    }
    //function to wait ms
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    //function to calculate frames
    public static void frame(double pitch, double yaw) {
        //double for loop to account for the rotation to make a circle (i) and rotate said circle around the y axis to make donut (j)
        for (double i = 0; i<2.0*pi;i+=0.07) {
            for (double j = 0; j<2.0*pi; j+=0.02) {
                //precomputes commonly used expression
                double sini = Math.sin(i);
                double sinj = Math.sin(j);
                double sinA = Math.sin(pitch);
                double sinB = Math.sin(yaw);
                
                double cosi = Math.cos(i);
                double cosj = Math.cos(j);
                double cosA = Math.cos(pitch);
                double cosB = Math.cos(yaw);
                
                pc1 = (R2 +(R1*Math.cos(i)));
                
                gridx.add(((pc1*(cosB*cosj+sinA*sinB*sinj)-R1*cosA*sinB*sini)));
                
                gridy.add(((pc1*(cosj*sinB-cosB*sinA*sinj)+R1*cosA*cosB*sini)));
                
                gridz.add(K2+(cosA*(R2+R1*cosi)*sinj+R1*sinA*sini));
                
                gridL.add(cosj*cosi*sinB-cosA*cosi*sinj-sinA*sini+cosB*(cosA*sini-cosi*sinA*sinj));
                
                //adds x, y and Luminance values to their respective lists given pitch and yaw for every rotation of i and j
                /*gridx.add((int)((((pc1*((Math.cos(yaw))*(Math.cos(j))+(Math.sin(pitch)*(Math.sin(yaw))*(Math.sin(j))))-(R1*Math.cos(pitch)*Math.sin(yaw)*Math.sin(i)))*5))));
                gridy.add((int)((((pc1*((Math.cos(j))*(Math.sin(yaw))-(Math.cos(yaw))*(Math.sin(pitch))*(Math.sin(j)))+(R1*Math.cos(pitch)*Math.cos(yaw)*Math.sin(i)))*5))));
                gridz.add((Math.cos(pitch)*(R2+R1*Math.cos(i))*Math.sin(j)+R1*Math.sin(pitch)*Math.sin(i)));
                //gridL.add(Math.max(0,Math.cos(j)*Math.cos(i)));
                gridL.add(((Math.cos(j))*(Math.cos(i))*(Math.sin(yaw))-((Math.cos(pitch))*(Math.cos(i))*(Math.sin(j)))-((Math.sin(pitch))*(Math.sin(i)))+(Math.cos(yaw))*(((Math.cos(pitch))*(Math.sin(i)))-((Math.cos(i))*(Math.sin(pitch))*(Math.sin(j))))));
                */
                //System.out.println("A: " + pitch + ", B: " + yaw + " Z: " + gridz.get(gridz.size()-1) + ", L: " + gridL.get(gridL.size()-1));
            }
        }
    }
    
}
