public class coordsystem {
    //sets screen x and 2d array for coordinate grid
    int screenx;
    int screeny;
    //creates array of all possible ascii characters for different luminance values
    String[] LIndex = {"..",",,","--","~~","::",";;","!!","**","==","##","$$","@@"};
    String[][] grid = null;
    double[][] zgrid = null;
    //coordsystem constructor 
    public coordsystem(int x, int y){
        screenx = x;
        screeny = y;
        //makes grid array empty
        grid = new String[screenx][screeny];
        zgrid = new double[screenx][screeny];
        for (int i = 0; i<screenx; i++) {
            for (int j = 0; j<screeny;j++) {
                grid[i][j] = "  ";
                zgrid[i][j] = -0.0;
                
            }
        }
    }
    //function to reset array
    public void resetSys() {
        for (int i = 0; i<screenx; i++) {
            for (int j = 0; j<screeny;j++) {
                grid[i][j] = "  ";
                zgrid[i][j] = 0.0;
                //System.out.println(zgrid[i][j]);
            }
        }
    }
    //function to add point to coordinate grid from the center
    public void addPoint(double x, double y, double z, double L) {
        if (x>(screenx/2)||x>(screeny/2)) {
            return;
        }


        
        //System.out.println("z: " + z +", " + "zgrid: " + zgrid[((screeny/2)-2)][((screenx/2)+x)] + "L: " + L );
        if (L>-0.5) {
            if (1/z>zgrid[(int)((screeny/2)-5*y)][(int)((screenx/2)+5*x)]) {
                zgrid[(int)((screeny/2)-5*y)][(int)((screenx/2)+5*x)] = 1/z;
                //System.out.println((int)(Math.floor(L*8)));
                //grid[(screeny/2)-y][(screenx/2)+x] = "# ";
                grid[(int)((screeny/2)-5*y)][(int)((screenx/2)+5*x)] = LIndex[((int)(Math.abs(L*8)))];
            }
        }


        
    }
    //iterates through the 2d array grid system and prints it out
    public void printScreen() {
        for (int i = 0; i<screeny;i++) {
            System.out.println();
            for (int j = 0; j <screenx;j++) {
                System.out.print(grid[i][j]);
            }
        }
    }
}
