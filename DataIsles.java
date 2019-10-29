import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class DataIsles {
    public static void main(String[] args) throws FileNotFoundException{
        File f = new File("datiInput.txt");
        Scanner s = new Scanner(f);
        int lines = s.nextInt();
        s.nextLine();
        int ans;

        int curr;
        int next;
        for(int i = 0 ; i < lines ; i++){
            ans = 0;
            s.nextInt(); //first number is useless
            curr = s.nextInt();
            next = s.nextInt();
            for(int j = 2 ; j < 15 ; j++){
                if(next > curr){
                    ans++;
                }
                curr = next;
                next = s.nextInt();
            }
            System.out.println(i+1 + " " + ans);
        }
    }
}
