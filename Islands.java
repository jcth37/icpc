import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Islands {
    private static char[][] world;
    private static MyStack<MyPair> stac = new MyStack<>();
    private static int n, m;
    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("islInput.txt");
        Scanner s = new Scanner(f);
        n = s.nextInt();
        m = s.nextInt();
        s.nextLine(); //get rid of current line so next isn't a blank
        //
        world = new char[n][m];
        String line;
        for(int i = 0 ; i < n ; i++){//this loop takes input -> array
            line = s.nextLine();
            for(int j = 0 ; j < m ; j++){
                world[i][j] = line.charAt(j);
            }
        }
        int ans = 0;
        MyPair curr;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(world[i][j] == 'L'){
                    addToStac(i, j); //is a method that will look at all surrounding blocks and addToStac all "L"s
                    //which repeat. This method is recursive until it finds all lands.
                    //resolve the stack;
                    while(stac.size() > 0){
                        curr = stac.pop();
                        addToStac(curr.x, curr.y);
                    }
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }

    private static void addToStac(int i, int j){
        if(i > 0){
            if(world[i-1][j] != 'W'){
                world[i-1][j] = 'W'; //marking it as seen
                stac.push(new MyPair(i-1, j));
            }
        }
        if(i < n-1){
            if(world[i+1][j] != 'W'){
                world[i+1][j] = 'W';
                stac.push(new MyPair(i+1, j));
            }
        }
        if(j > 0){
            if(world[i][j-1] != 'W'){
                world[i][j-1] = 'W';
                stac.push(new MyPair(i, j-1));
            }
        }
        if(j < m-1){
            if(world[i][j+1] != 'W'){
                world[i][j+1] = 'W';
                stac.push(new MyPair(i, j + 1));
            }
        }
        world[i][j] = 'W';
    }
}
