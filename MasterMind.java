import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MasterMind {
    public int[] mastermind(String solution, String guess) {
        int[] ans = new int[2];
        int c0 = 0, c1 = 0;

        Map<Character,Integer> smap = new HashMap<>();
        Map<Character,Integer> gmap = new HashMap<>();

        for (int i = 0; i < solution.length(); ++i){
            if (solution.charAt(i) == guess.charAt(i)){  // if the solution[i] == guess[i], the hit time + 1
                c0 ++;
            }else{  // if not equal, store the character (as a key) and the times it presenting (as a value) in hash map
                smap.put(solution.charAt(i), smap.getOrDefault(solution.charAt(i),0)+1);
                gmap.put(guess.charAt(i), gmap.getOrDefault(guess.charAt(i),0)+1);
            }
        }

        for (char k : smap.keySet()){  // chose the smaller value of the keys that exist in both two maps as the pseudo-hit
            if(gmap.containsKey(k)){
                c1 += Math.min(smap.get(k), gmap.get(k));
            }
        }

        ans[0] = c0;
        ans[1] = c1;
        return ans;
    }

    public static void main(String[] args){
        MasterMind game = new MasterMind();
        int count = 0;
        int[] res = new int[2];

        //count the guess time
        while (count < 4 && res[0] < 4){
        Scanner scanner = new Scanner(System.in);
        String S = "RGBY";
        System.out.println("The solution is "+S);
        System.out.println("Please enter the guess with R,G,B,Y (Red-R,Green-G,Blue-B,Yellow-Y):");
        String G = scanner.next();
        res = game.mastermind(S,G);
        System.out.println("[hit times,pseudo-hit times] is :"+ Arrays.toString(res));
        count ++;
        }

        //if the user guesses less than 4 times and hit all the color, he wins; else, he loses.
        if (count <= 4 && res[0] == 4){
            System.out.println("You win!");
        }else{
            System.out.println("You lose.");
        }

    }
}
