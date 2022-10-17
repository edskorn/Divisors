import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //Read L and R
        System.out.print("Input L <space> R (1 ≤ L < R ≤ 300 000): ");
        Scanner sc = new Scanner(System.in);
        int left, right;
        try {
            left = sc.nextInt();
            right = sc.nextInt();
        } catch (Exception e) {
            System.out.print("Inputted L R out of range");
            return;
        }
        if (left>=right || left<1 || left>300000 || right>300000) {
            System.out.print("1 ≤ L < R ≤ 300 000 - out of range");
            return;
        }

        long startTime = System.currentTimeMillis();

        //Create an array:
        //index is amount of divisors
        //value contains all numbers with corresponding amount of divisors (as string with space delimiter)
        //size selected by experience
        StringBuilder[] index = new StringBuilder[200];
        for (int i=left; i<=right; i++){
            int countDivisors = countDivisors(i);
            if (index[countDivisors]==null) index[countDivisors] = new StringBuilder("" + i);
            else index[countDivisors] = index[countDivisors].append(" ").append(i);
        }

        //Output result of sorted numbers order by amount of divisors
        FileWriter output = new FileWriter("output.txt");
        for (StringBuilder str:index) {
            if (str!=null) output.write(str + " ");//System.out.print(str + " ");
        }
        output.close();

        //Output performance statistics
        System.out.println("\n==========\nSTATISTICS\n==========");
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Time Result: " + estimatedTime + " milliseconds");
        long usedBytes = (Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/1048576;
        System.out.println("Used memory: " + usedBytes + "Mb");
    }
    public static int countDivisors(int number){
        if (number == 1) return 1;
        int count = 1;
        double sqrt = Math.sqrt(number);
        //Important optimization - the end of cycle is sqrt of number!
        for (int i=1; i<=sqrt; i++){
            if (number%i==0) {
                //If Divisor is sqrt of number count++, because pair is the same
                if (number/i==i) count++;
                    //else count +2, because we have a pair of divisors
                else count = count + 2;
            }
        }
        return count;
    }
}