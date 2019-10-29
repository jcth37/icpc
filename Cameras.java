import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Cameras {
    public static void main(String[] args) throws FileNotFoundException{
        File f = new File("camInput.txt");
        Scanner s = new Scanner(f);
        int houses = s.nextInt();
        int cams = s.nextInt();
        int r = s.nextInt();

        boolean[] hasCam = new boolean[houses];
        for (int i = 0; i < hasCam.length; i++) {
            hasCam[i] = false;
        }
        for (int i = 0; i < cams; i++) {
            hasCam[s.nextInt() - 1] = true; //must subtract 1 because houses are labelled 1 to n, array starts at 0
        }
        //printArray(hasCam);
        int finalAns = 0;
        int numCams = 0;
        int a, b;
        for (a = 0; a <= houses - r; a++) {
            for (b = a; b < a + r; b++) {
                if (hasCam[b] == true) {
                    numCams++;
                }
            }
            if (a == 0 && numCams == 0) {//special case at the beginning
                hasCam[b - 2] = true;
                hasCam[b - 1] = true;
                finalAns += 2;
            } else if (numCams < 2) {
                hasCam[b-1] = true;
                finalAns++;
            }
            numCams = 0;
        }
        //printArray(hasCam);
        System.out.println(finalAns);
    }

    public static void printArray(boolean[] bs){
        for(int i = 0 ; i < bs.length ; i++){
            if(bs[i]){
                System.out.print("T, ");
            }else{
                System.out.print("F, ");
            }
        }
        System.out.println();
    }
}