import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

//takes too long?
public class Gravity{
    public static void main(String[] args) throws FileNotFoundException{
        File f = new File("gravInput.txt");
        Scanner s = new Scanner(f);
        int n = s.nextInt(); //no. of rows
        int m = s.nextInt(); //no. of cols
        char[][] world = new char[n][m];
        s.nextLine();//this is because the end of line char has not been read yet, so the next time this is used
        //a blank line would be read.
        String line;
        for(int i = 0 ; i < n ; i++){//this loop takes input -> array
            line = s.nextLine();
            for(int j = 0 ; j < m ; j++){
                world[i][j] = line.charAt(j);
            }
        }
        int free;
        for(int j = 0 ; j < m ; j++){//starts at first column
            free = n-1;
            for(int i = n-1 ; i >= 0 ; i--){//starts at last row
                if(world[i][j] == 'o'){
                    if(free > i) {//if free == i, then we don't have to change anything.
                        world[free][j] = 'o';//this does nothing if i == free, which is good.
                        world[i][j] = '.';
                    }
                    free--;
                }else if(world[i][j] == '#'){
                    free = i-1;
                }
            }
        }
        for(int i = 0 ; i < n ; i++){
            for(int j = 0; j < m ; j++){
                System.out.print(world[i][j]);
            }
            System.out.println();
        }
    }
}
