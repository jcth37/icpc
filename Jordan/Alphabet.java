import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Alphabet {
    public static void main(String[] args) throws FileNotFoundException{
        File f = new File("abcInp.txt");
        Scanner s = new Scanner(f);
        String input = s.next();
        String abc = "abcdefghijklmnopqrstuvwxyz";
        String bestSubseq = "";
        int[][] subseq = new int[input.length() + 1][abc.length() + 1];
        for(int i = 0 ; i < input.length() + 1 ; i++){
            subseq[i][0] = 0;
        }
        for(int j = 0 ; j < abc.length() + 1 ; j++){
            subseq[0][j] = 0;
        }
        int counter = 0;
        for(int i = 0 ; i < input.length(); i++){
            for(int j = 0 ; j < abc.length() ; j++){
                if(input.charAt(i) == abc.charAt(j)){
                    subseq[i+1][j+1] = subseq[i][j] + 1;
                    if(subseq[i+1][j+1] > counter) {
                        bestSubseq = bestSubseq.concat(Character.toString(abc.charAt(j)));
                        counter++;
                    }
                }else{
                    subseq[i+1][j+1] = Math.max(subseq[i][j+1], subseq[i+1][j]);
                }
            }
        }

        //System.out.println(bestSubseq);//for testing
        System.out.println(abc.length() - bestSubseq.length());
    }
}
