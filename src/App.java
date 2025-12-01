import java.util.Scanner;
public class App {

    static void showInterface(){
        System.out.println("********** Renfe **********");
        System.out.println("1. Search for train");
        System.out.println("2. Buy ticket");                        // Our part
        System.out.println("3. Modify reservation");
        System.out.println("4. Cancel reservation");
        System.out.println("5. Exit");
        System.out.println("***************************");
        System.out.print("Selected: ");
    }


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        Train train1 = new Train();                                 // This train should be created with the search option

        showInterface();

        int option = sc.nextInt();

        switch (option) {
            case 1:
                break;
            case 2:
                Train.checkTrainSearch();                          // This should trigger train search

                System.out.println("How many tickets?");
                int amount = sc.nextInt();

                train1.showAskedSeats(amount);                      // Calculates position of tickets based on amount

                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }
}