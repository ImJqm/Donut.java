public class Test {
  public static void main(String[] args) {
    for (int i = 0; i < 256; i++) {
      for (int j = 0; j < 256; j++) {
        for (int k = 0; k < 256; k++) {
          System.out.println("\033[48;2;" + i + ";" + j + ";" + k + "m" + "test" + "r:" + i + " g:" + j + " b:" + k);
        }
      }
    }
  }
}
