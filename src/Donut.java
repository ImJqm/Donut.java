//Desmos calculations linK:
//https://www.desmos.com/3d/9jiwwo81ia

//Imports arra list library to manage grid system
import java.util.ArrayList;

//to be added: calculate each frame once and store in a buffer: allows you to have better performance bc u calculate all the frames once and then never again --> high fps
public class Donut {
  // quick access for pi
  static double pi = Math.PI;

  // makes arrays to store each x, y, and luminance point
  static ArrayList<Double> gridx = new ArrayList<Double>();
  static ArrayList<Double> gridy = new ArrayList<Double>();
  static ArrayList<Double> gridz = new ArrayList<Double>();
  static ArrayList<Double> gridL = new ArrayList<Double>();

  // sets radius of torus
  static final double R1 = 1.0;

  // sets radius of center hole in donut
  static final double R2 = 2.0;

  // A and B are values between 0 and 2pi to control the donut's pitch and yaw
  static double A = 0.0;
  static double B = 1.0;

  // variables to be used to pre-compute sins and coss for easier typing
  static double pc1 = 0.0;

  // sets the width and height of the display
  static final int screenx = 50;
  static final int screeny = 50;

  // creates coordinate system to access everywhere
  static coordsystem cs;

  public static void main(String[] args) {

    int r1 = Integer.valueOf(args[0]);
    int g1 = Integer.valueOf(args[1]);
    int b1 = Integer.valueOf(args[2]);
    int r2 = Integer.valueOf(args[3]);
    int g2 = Integer.valueOf(args[4]);
    int b2 = Integer.valueOf(args[5]);

    int[] colors = { r1, g1, b1, r2, g2, b2 };

    // sets coordinate system system
    cs = new coordsystem(screenx, screeny, colors);
    // update loop
    while (true) {
      wait(1000);

      // clears screen
      System.out.print("\u001b[H");
      // clears lists
      gridx.clear();
      gridy.clear();
      gridz.clear();
      gridL.clear();
      // resets the coord system
      cs.resetSys();
      // calculates the needed values to draw a frame and updates the appropriate
      // lists
      frame(A, B);
      // iterates through lists and add points to coordinate system
      for (int i = 0; i < gridx.size(); i++) {
        cs.addPoint(gridx.get(i), gridy.get(i), gridz.get(i), gridL.get(i));
      }
      // prints screen
      cs.printScreen();
      // increments rotation variables
      A += 0.07;
      B += 0.04;
    }
  }

  // function to wait ms, needed to keep constant framerate which is currently
  // diabled
  public static void wait(int ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
  }

  // function to calculate frames
  public static void frame(double pitch, double yaw) {
    // double for loop to account for the rotation to make a circle (i) and rotate
    // said circle around the y axis to make donut (j)
    for (double i = 0; i < 2.0 * pi; i += 0.07) {
      for (double j = 0; j < 2.0 * pi; j += 0.02) {
        // precomputes commonly used expressions
        double sini = Math.sin(i);
        double sinj = Math.sin(j);
        double sinA = Math.sin(pitch);
        double sinB = Math.sin(yaw);

        double cosi = Math.cos(i);
        double cosj = Math.cos(j);
        double cosA = Math.cos(pitch);
        double cosB = Math.cos(yaw);

        // commonly used expression (pre-compute 1)
        pc1 = (R2 + (R1 * Math.cos(i)));

        // each point (x,y,z) is calculated by taking a circle, rotating it around the x
        // axis, and applying rotation matrices to change the pitch and yaw,
        // the following calculations are the result of distributed rotation matrices

        // adds the x coordinates to the array that stores each one
        gridx.add(((pc1 * (cosB * cosj + sinA * sinB * sinj) - R1 * cosA * sinB * sini)));

        // adds the y coordinates to the array that stores each one
        gridy.add(((pc1 * (cosj * sinB - cosB * sinA * sinj) + R1 * cosA * cosB * sini)));

        // adds tge z coordinates to the array that stores each one
        gridz.add((cosA * (R2 + R1 * cosi) * sinj + R1 * sinA * sini) + 5.0);

        // calculates the luminance by taking a point (x, y, z) taking the surface
        // normal of that point, and taking the dot product of that vector against the
        // vector of the light source to that point
        // in this case the light is lacked behind the camera and upwards, caclulated
        // using the following matrix: (0, 1, -1)
        // taking the dot product of both of these gives us the value of the cos of the
        // angle between the 2 vectors, this is a value between -sqrt2 and +sqrt2, if it
        // is positive, there is light hitting that point

        gridL.add(cosj * cosi * sinB - cosA * cosi * sinj - sinA * sini + cosB * (cosA * sini - cosi * sinA * sinj));

      }
    }
  }

}
