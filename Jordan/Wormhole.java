import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Wormhole {
    public static void main(String[] args) throws FileNotFoundException {
        final int inf = Integer.MAX_VALUE;
        File f = new File("wormInput.txt");
        Scanner s = new Scanner(f);
        int cases = s.nextInt();
        for (int i = 0; i < cases; i++) {
            int numPlanets = s.nextInt();
            ArrayList<Planet> listPlanets = new ArrayList<Planet>();

            for (int j = 0; j < numPlanets; j++) {
                listPlanets.add(new Planet(s.next(), s.nextInt(), s.nextInt(), s.nextInt()));
                //s.nextLine();
            }
            //init all direct connections
            int[][] table = new int[numPlanets][numPlanets];
            Planet p, q;
            for (int j = 0; j < numPlanets; j++) {
                p = listPlanets.get(j);
                for (int k = 0; k < numPlanets; k++) {
                    if (j == k) {
                        table[j][k] = 0;
                    } else {
                        q = listPlanets.get(k);
                        table[j][k] = (int) Math.sqrt((q.x - p.x) * (q.x - p.x) +
                                (q.y - p.y) * (q.y - p.y) +
                                (q.z - p.z) * (q.z - p.z));
                    }
                }
            }
            //deal with wormholes
            int wormholes = s.nextInt();
            int tableI = -1;
            int tableJ = -1;
            String start, end;
            for (int j = 0; j < wormholes; j++) {
                start = s.next();
                end = s.next();
                for (int k = 0; k < listPlanets.size(); k++) {
                    if (listPlanets.get(k).name.equals(start)) {
                        tableI = k;
                    } else if (listPlanets.get(k).name.equals(end)) {
                        tableJ = k;
                    }
                }
                table[tableI][tableJ] = 0;
            }
            //go through floyd-worshall
            for (int v = 0; v < numPlanets; v++) {//v for vertex
                for(tableI = 0 ; tableI < numPlanets ; tableI++){
                    for(tableJ = 0 ; tableJ < numPlanets ; tableJ++){
                        table[tableI][tableJ] = Math.min(table[tableI][v] + table[v][tableJ], table[tableI][tableJ]);
                    }
                }
            }
            //print outputs
            System.out.println("Case " + (i+1) + ": ");
            int outputs = s.nextInt();
            for (int j = 0; j < outputs; j++) {
                start = s.next();
                end = s.next();
                for (int k = 0; k < listPlanets.size(); k++) {
                    if (listPlanets.get(k).name.equals(start)) {
                        tableI = k;
                    } else if (listPlanets.get(k).name.equals(end)) {
                        tableJ = k;
                    }
                }
                System.out.println("The distance from " + start + " to " + end + " is " + table[tableI][tableJ] +
                        " parsecs. ");
            }
        }
    }
}
