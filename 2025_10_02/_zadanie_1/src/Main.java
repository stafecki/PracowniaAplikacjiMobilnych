import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ile kostek chcesz rzucić?(3 - 10)");
        int numDice = sc.nextInt();
        while(true){
            Dice[] dice = new Dice[numDice];
            for(int i = 0; i < numDice; i++){
                Dice newDice = new Dice();
                dice[i] = newDice;
                System.out.println("Kostka " + (i+1) + ": " + newDice.getVal());
            }
            System.out.println("Liczba uzyskanych punktów: " + sumDiceValues(dice));
            System.out.println("Jeszcze raz? (t/n)");
            char choice;
            do {
                choice = sc.next().toLowerCase().charAt(0);
            }while (choice != 't' && choice != 'n');
            if(choice == 'n'){
                break;
            }
        }

    }
    public static int sumDiceValues(Dice[] dice) {
        int[] counts = new int[7]; // ignoruje index 0
        for (Dice d : dice) {
            counts[d.getVal()]++;
        }
        int sum = 0;
        for (int i = 1; i <= 6; i++) {
            if (counts[i] >= 2) {
                sum += i * counts[i];
            }
        }
        return sum;
    }
    public static int getNumDice(){
        Scanner scanner = new Scanner(System.in);
        int numDice;
        while (true) {
            if (scanner.hasNextInt()) {
                numDice = scanner.nextInt();
                if (numDice >= 3 && numDice <= 10) {
                    return numDice;
                }
            } else {
                scanner.next();
            }
        }
    }

}
