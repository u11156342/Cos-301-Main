package Interface.BuyProperty;

import java.util.Random;

public class Generator {

    public int[][] genStates;
    double[] probability = new double[4];
    int tileRange = 4;
    int mapsize;

    public Generator(int size) {
        genStates = new int[size][size];
        mapsize = size;

    }

    public int[][] ExspandGenerate(String duche, int quality, int size, int[][] tiles) {

        System.out.println("begin");
        int workingsize = size;
        if (needToUpSize(tiles)) {
            System.out.println("upsize");
            tiles = ArrayCopy(tiles, size, size + 3);

            workingsize = tiles.length;
        }

        printArray(tiles);

        for (int a = 0; a < workingsize; a++) {

            if (tiles[a][workingsize - 1] == -1) {

                for (int c = a; c < a + 3; c++) {
                    for (int b = workingsize; b > workingsize - 3; b--) {
                        if (tiles[c][b - 1] != -1) {
                            System.out.println("error");
                        } else {
                            tiles[c][b - 1] = getRandom(duche, quality);
                        }
                    }

                }
                System.out.println("m1");
                printArray(tiles);
                return tiles;

            }
        }

        for (int a = 0; a < workingsize; a++) {

            if (tiles[workingsize - 1][a] == -1) {


                for (int b = workingsize; b > workingsize - 3; b--) {
                    for (int c = a; c < a + 3; c++) {
                        tiles[b - 1][c] = getRandom(duche, quality);
                    }
                }
                System.out.println("m2");
                printArray(tiles);
                return tiles;
            }
        }
        System.out.println("m3");
        printArray(tiles);
        return tiles;
    }

    public boolean needToUpSize(int[][] arr) {

        for (int a = 0; a < arr.length; a++) {
            for (int b = 0; b < arr[0].length; b++) {
                if (arr[a][b] == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[][] ArrayCopy(int[][] other, int oldsize, int newsize) {

        int[][] temp = new int[newsize][newsize];

        for (int a = 0; a < newsize; a++) {
            for (int b = 0; b < newsize; b++) {
                temp[a][b] = -1;
            }
        }


        for (int a = 0; a < oldsize; a++) {
            for (int b = 0; b < oldsize; b++) {
                temp[a][b] = other[a][b];
            }
        }

        return temp;

    }

    public int[][] generate(String duche, String abbay, int quality) {

        for (int i = 0; i < mapsize; i++) {
            for (int j = 0; j < mapsize; j++) {
                genStates[i][j] = getRandom(duche, quality);
            }
        }
        return genStates;

    }

    public void printArray(int[][] arr) {
        for (int a = 0; a < arr.length; a++) {
            for (int b = 0; b < arr[0].length; b++) {
                System.out.print(arr[a][b] + "   ");
            }
            System.out.println("");
        }
    }

    public int getRandom(String duche, int quality) {
        //0 Dirt
        //1 semi_fertile
        //2 fertile
        //3 water

        //poor
        if (quality == 1) {
            probability[0] = 0.70;
            probability[1] = 0.70;
            probability[2] = 0.70;
            probability[3] = 1.00;
        } else if (quality == 2) {
            probability[0] = 0.0;
            probability[1] = 0.65;
            probability[2] = 0.65;
            probability[3] = 1.00;
        } else if (quality == 3) {
            probability[0] = 0.0;
            probability[1] = 0.0;
            probability[2] = 0.80;
            probability[3] = 1.00;
        }

        Random rand = new Random();
        double r;

        r = rand.nextDouble();

        if (r > 0 && r < probability[0]) {
            return 0;
        } else {
            for (int k = 0; k < tileRange - 1; k++) {
                if (r > probability[k] && r < probability[k + 1]) {
                    return k + 1;
                }
            }
        }
        return -1;
    }
}
