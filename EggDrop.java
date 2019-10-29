import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class EggDrop {
    public static void main(String[] args) throws FileNotFoundException{
        File f = new File("eggInp.txt");
        Scanner s = new Scanner(f);
        int tests = s.nextInt();
        int highestBroken = s.nextInt();
        int lowestSafe = 1;
        int floor;
        String string;
        for(int i = 0 ; i < tests ; i++){
            floor = s.nextInt();
            string = s.next();
            if(floor > lowestSafe && string.equals("SAFE")){
                lowestSafe = floor;
            }else if(floor < highestBroken && string.equals("BROKEN")){
                highestBroken = floor;
            }
        }
        System.out.println((lowestSafe + 1) + " " + (highestBroken - 1));
    }
}
