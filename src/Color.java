public class Color {

  private int r;
  private int g;
  private int b;

  public Color(int r, int g, int b) {
    this.r = r;
    this.b = b;
    this.g = g;
  }

  public int getR() {
    return r;
  }

  public int getG() {
    return g;
  }

  public int getB() {
    return b;
  }

  public String toString() {
    return (r + " " + g + " " + b);
  }
}
