import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args)
    {
        File file = new File("liczby.txt");

        //Zadanie 4.1
        System.out.println("Zadanie 4.1");
        int counter = 0;
        String firstMatched = null;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                String data = sc.next();
                char firstNumber = data.charAt(0);
                char lastNumber = data.charAt(data.length() - 1);
                if (firstNumber == lastNumber) {
                    counter++;
                    if(firstMatched == null) {
                        firstMatched = data;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku.");
            e.printStackTrace();
        }
        System.out.println(counter + " " + firstMatched);


        //Zadanie 4.2
        System.out.println("Zadanie 4.2");
        int maxAllCount = 0;
        int numberMaxAll = 0;
        int maxDistinctCount = 0;
        int numberMaxDistinct = 0;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                String data = sc.next();
                int n;
                try {
                    n = Integer.parseInt(data);
                }
                catch (NumberFormatException e) {
                    continue;
                }
                int temp = n;
                int totalFactors = 0;
                int distinctFactors = 0;
                if(temp%2==0){
                    distinctFactors++;
                    while (temp%2==0){
                        totalFactors++;
                        temp = temp/2;
                    }
                }
                for(int i = 3; i*i<=temp; i+=2){
                    if(temp%i==0){
                        distinctFactors++;
                        while (temp%i==0){
                            totalFactors++;
                            temp = temp/i;
                        }
                    }
                }
                if(temp>1){
                    distinctFactors++;
                    totalFactors++;
                }
                if(totalFactors>maxAllCount){
                    maxAllCount = totalFactors;
                    numberMaxAll = n;
                }
                if(distinctFactors>maxDistinctCount){
                    maxDistinctCount = distinctFactors;
                    numberMaxDistinct = n;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku.");
            e.printStackTrace();
        }

        System.out.println(numberMaxAll + " " + maxAllCount + " " + numberMaxDistinct + " " + maxDistinctCount);

        System.out.println("Zadanie 4.3");
        int x = 0;
        int y = 0;
        int z = 0;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                String data = sc.next();
                try {
                    x = Integer.parseInt(data);
                }
                catch (NumberFormatException e) {
                    continue;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku.");
            e.printStackTrace();
        }

    }
}