import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Rings {
    public static void main(String[] args) {
        Scanner s;

        try {
            s = new Scanner(new File("ringsInput.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return;
        }
        while (s.hasNext()) {
            int rows = s.nextInt();
            int cols = s.nextInt();
            Integer[][] table = new Integer[rows][cols];
            char[][] ctable = new char[rows][cols];
            int rings = -1;
            //init ctable
            for (int i = 0; i < rows; i++) {
                String line = s.next();
                for (int j = 0; j < cols; j++) {
                    ctable[i][j] = line.charAt(j);
                }
            }
            //init table to max, needed for later inits
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    table[i][j] = Integer.MAX_VALUE;
                }
            }
            //
            for (int i = 0; i < rows; i++) {
                if (ctable[i][0] == '.') {
                    table[i][0] = 0;
                } else {
                    table[i][0] = 1;
                }
            }
            for (int i = 0; i < rows; i++) {
                if (ctable[i][cols - 1] == '.') {
                    table[i][cols - 1] = 0;
                } else {
                    table[i][cols - 1] = 1;
                }
            }
            for (int j = 0; j < cols; j++) {
                if (ctable[0][j] == '.') {
                    table[0][j] = 0;
                } else {
                    table[0][j] = 1;
                }
            }
            for (int j = 0; j < cols; j++) {
                if (ctable[rows - 1][j] == '.') {
                    table[rows - 1][j] = 0;
                } else {
                    table[rows - 1][j] = 1;
                }
            }

            //now fill out table? loop in a circle
            for (int j = 1, i = 1, n = 1; n < Math.max(rows / 2, cols / 2); n++) {
                //limit is how many circles
                for (; j < cols - n; j++) {
                    if (ctable[i][j] == '.') {
                        table[i][j] = 0;
                    } else {
                        table[i][j] = Math.min(Math.min(Math.min(table[i - 1][j], table[i][j - 1]), table[i + 1][j]), table[i][j + 1]) + 1;
                        if (table[i][j] > rings) {
                            rings = table[i][j];
                        }
                    }
                }
                j--; //increment still happens when exiting a for loop, we don't want it to.
                for (; i < rows - n; i++) {
                    if (ctable[i][j] == '.') {
                        table[i][j] = 0;
                    } else {
                        table[i][j] = Math.min(Math.min(Math.min(table[i - 1][j], table[i][j - 1]), table[i + 1][j]), table[i][j + 1]) + 1;
                        if (table[i][j] > rings) {
                            rings = table[i][j];
                        }
                    }
                }
                i--;
                for (; j > n - 1; j--) {
                    if (ctable[i][j] == '.') {
                        table[i][j] = 0;
                    } else {
                        table[i][j] = Math.min(Math.min(Math.min(table[i - 1][j], table[i][j - 1]), table[i + 1][j]), table[i][j + 1]) + 1;
                        if (table[i][j] > rings) {
                            rings = table[i][j];
                        }
                    }
                }
                j++;
                for (; i > n; i--) {//i > n here because we already did table[n][j], n++ happens when loop is reentered
                    if (ctable[i][j] == '.') {
                        table[i][j] = 0;
                    } else {
                        table[i][j] = Math.min(Math.min(Math.min(table[i - 1][j], table[i][j - 1]), table[i + 1][j]), table[i][j + 1]) + 1;
                        if (table[i][j] > rings) {
                            rings = table[i][j];
                        }
                    }
                }
                i++;
            }
            //end loop
            if (rings < 10) {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (table[i][j] == 0) {
                            System.out.print("..");
                        } else {
                            System.out.print("." + table[i][j]);
                        }
                    }
                    System.out.println();
                }
            } else {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (table[i][j] == 0) {
                            System.out.print("...");
                        } else {
                            System.out.print(pad(table[i][j].toString(), 3));
                        }
                    }
                    System.out.println();
                }
            }
        }
    }

    public static String pad(String s, int len) {
        String ans = "";
        int num = len - s.length();
        for (int i = 0; i < num; i++) {
            ans = ans.concat(".");
        }
        ans = ans.concat(s);
        return ans;
    }
}
