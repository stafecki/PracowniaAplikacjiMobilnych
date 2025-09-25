import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
        List<Integer> liczby = new ArrayList<>();

        try (Scanner sc = new Scanner(file)) {
            String line;
            while (sc.hasNext()) {
                line = sc.nextLine();
                liczby.add(Integer.parseInt(line));
            }
        }catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku.");
            e.printStackTrace();
        }
        Collections.sort(liczby);

        int licznikTrojki = 0;
        System.out.println("Dobre trójki:");

        for (int i = 0; i < liczby.size(); i++) {
            for (int j = 0; j < liczby.size(); j++) {
                for (int k = 0; k < liczby.size(); k++) {
                    int x = liczby.get(i);
                    int y = liczby.get(j);
                    int z = liczby.get(k);
                    if (x != y && y != z && x != z &&
                            y % x == 0 && z % y == 0) {
                        System.out.println(x + " " + y + " " + z);
                        licznikTrojki++;
                    }
                }
            }
        }

        System.out.println("\nLiczba dobrych trójek: " + licznikTrojki);


        int licznikPiatki = 0;
        //System.out.println("\nDobre piątki:");

        for (int a = 0; a < liczby.size(); a++) {
            int u = liczby.get(a);
            for (int b = a + 1; b < liczby.size(); b++) {
                int w = liczby.get(b);
                if (w % u != 0) continue;

                for (int c = b + 1; c < liczby.size(); c++) {
                    int x = liczby.get(c);
                    if (x % w != 0) continue;

                    for (int d = c + 1; d < liczby.size(); d++) {
                        int y = liczby.get(d);
                        if (y % x != 0) continue;

                        for (int e = d + 1; e < liczby.size(); e++) {
                            int z = liczby.get(e);
                            if (z % y == 0) {
                                licznikPiatki++;
                                //System.out.println(u + " " + w + " " + x + " " + y + " " + z);
                            }
                        }
                    }
                }
            }
        }

        System.out.println("\nLiczba dobrych piątek: " + licznikPiatki);
    }


}
