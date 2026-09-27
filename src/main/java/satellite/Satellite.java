package satellite;

import java.io.File;
import java.util.Scanner;

public class Satellite {

    static int[][] oldImage, newImage;
    static int noOfRows, noOfCols;

    static int[][] readImage(Scanner input) {
        int[][] image = new int[noOfRows][noOfCols];
        for (int row = 0; row < noOfRows; row++)
            for (int col = 0; col < noOfCols; col++)
                image[row][col] = input.nextInt();
        return image;
    }

    static boolean equalLine(int index, boolean row) {
        int length = row ? noOfCols : noOfRows;
        for (int i = 0; i < length; i++)
            if (row ? oldImage[index][i] != newImage[index][i]
                    : oldImage[i][index] != newImage[i][index])
                return false;
        return true;
    }

    static int findBorder(boolean row, boolean fromStart) {
        int limit = row ? noOfRows : noOfCols;
        int i = fromStart ? 0 : limit - 1;
        int step = fromStart ? 1 : -1;
        while (i >= 0 && i < limit && equalLine(i, row)) i += step;
        return i;
    }

    static void printResult() {
        int x1 = findBorder(true, true), y1 = findBorder(false, true);
        int x2 = findBorder(true, false), y2 = findBorder(false, false);
        if (x1 > x2 || y1 > y2)
            System.out.println("The two images are the same");
        else
            System.out.println((x1 + 1) + " " + (y1 + 1) + " " + (x2 + 1) + " " + (y2 + 1));
    }

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(new File("input.txt"));
        noOfRows = input.nextInt();
        noOfCols = input.nextInt();
        oldImage = readImage(input);
        newImage = readImage(input);
        printResult();
        input.close();
    }
}