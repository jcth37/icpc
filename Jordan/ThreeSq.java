import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ThreeSq {
    public static void main(String[] args) throws FileNotFoundException{
        File f = new File("thrInput.txt");
        Scanner s = new Scanner(f);
        ArrayList<MyPair> list = new ArrayList<>();
        while(s.hasNext()){
            list.add(new MyPair(s.nextInt(), s.nextInt()));
        }
        int lastSize = list.size();
        int index = 0;
        MyPair curr;
        while(list.size() > 1){
            curr = list.get(index);
            for(int i = 0 ; i < list.size() ; i++){
                if(index != i){
                    if(curr.x == list.get(i).x){
                        curr.y += list.get(i).y;
                        list.remove(i);
                    }else if(curr.x == list.get(i).y){
                        curr.y += list.get(i).x;
                        list.remove(i);
                    }else if(curr.y == list.get(i).x){
                        curr.x += list.get(i).y;
                        list.remove(i);
                    }else if(curr.y == list.get(i).y){
                        curr.x += list.get(i).x;
                        list.remove(i);
                    }
                }
            }
            if(index < list.size() - 1){
                index++;
            }else{
                if(list.size() == lastSize){
                    System.out.println("NO");
                    return;
                }
                lastSize = list.size();
                index = 0;
            }
        }
        System.out.println("YES");
    }
}
