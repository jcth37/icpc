import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Squawk {
    public static void main(String[] args){
        Scanner s;
        try {
            s = new Scanner(new File("squawkInput.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return;
        }

        while(s.hasNext()) {
            Node[] users = new Node[s.nextInt()];
            int links = s.nextInt();
            int infect = s.nextInt();
            int minutes = s.nextInt();
            //init users
            for (int i = 0; i < users.length; i++) {
                users[i] = new Node();
            }
            //init connects
            for (int i = 0; i < links; i++) {
                int u1 = s.nextInt();
                int u2 = s.nextInt();
                users[u1].addConnect(users[u2]);
                users[u2].addConnect(users[u1]);
            }

            int ans = 0;
            //at time t = 0
            users[infect].outSquawks += 1;
            users[infect].wasSqakdAtPrev = true;

            for (int t = 1; t <= minutes; t++) {
                //at the end of the loop, ans is the number of times a squawk has been sent
                ans = 0;
                for (int i = 0; i < users.length; i++) {
                    //current node is users[i]
                    if (users[i].wasSqakdAtPrev) {
                        for (int j = 0; j < users[i].connects.size(); j++) {
                            //each node in the list users[i].connects is a neighbour of the current node
                            //send squawk to each neighbour for each squawk that was sent to the current node
                            //receive(n) method makes node receive n squawks
                            //the number of squawks received by current node is equal to the node's inSquawks (a member variable)
                            //see one second passes method in node class
                            users[i].connects.get(j).receive(users[i].outSquawks);
                            ans += users[i].outSquawks;
                            //next line lets the connected nodes/users know that they have been squawked at
                            users[i].connects.get(j).wasSqakdAtNow = true;
                            //so they now must squawk in the next loop
                        }
                    }
                }
                for (int j = 0; j < users.length; j++) {
                    users[j].oneSecondPasses(); //modifies the wasSqakdAtNow/Prev, incoming/outgoingSquawks
                }
                //uncomment following loop to see progression
                /*
                for(int j = 0 ; j < users.length ; j++){
                    System.out.print(users[j].outSquawks + ", ");
                }
                System.out.println();
                 */
            }
            System.out.println(ans);
        }
    }
}
