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

        System.out.println("size : " + size);
        System.out.println("working size " + workingsize);
        System.out.println("tiles l " + tiles.length);

        //  printArray(tiles);

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


        for (int a = 0; a < other.length; a++) {
            for (int b = 0; b < other.length; b++) {
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
        if (quality == 1) {
            return 0;
        } else if (quality == 2) {
            return 1;
        } else if (quality == 3) {
            return 2;
        }

        //poor
        if (quality == 1) {
            probability[0] = 1.00;
            probability[1] = 1.00;
            probability[2] = 1.00;
            probability[3] = 1.00;
        } else if (quality == 2) {
            probability[0] = 0.0;
            probability[1] = 1.00;
            probability[2] = 1.00;
            probability[3] = 1.00;
        } else if (quality == 3) {
            probability[0] = 0.0;
            probability[1] = 0.0;
            probability[2] = 1.00;
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

    public int[][] ExspandGenerate(String get, String picked, int size, int[][] tiles) {

        int temp = 0;
        switch (picked) {
            case "Poor":
                temp = 1;
                break;
            case "Fine":
                temp = 2;
                break;
            default:
                temp = 3;
                break;
        }

        return this.ExspandGenerate(get, temp, size, tiles);
    }
}
