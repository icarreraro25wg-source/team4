import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TrainSystem system = new TrainSystem();

        int option = -1;

        while (option != 0) {

            System.out.println("\n====== RENFE TRAIN SYSTEM ======");
            System.out.println("1. Prepare train search request");
            System.out.println("2. Search trains");
            System.out.println("3. Purchase ticket");
            System.out.println("4. Change seat");
            System.out.println("5. Modify reservation");
            System.out.println("6. Remove reservation");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> system.prepareSearch(sc);
                case 2 -> system.searchTrains();
                case 3 -> system.purchaseTicket(sc);
                case 4 -> system.changeSeat(sc);
                case 5 -> system.modifyReservation(sc);
                case 6 -> system.removeReservation(sc);
                case 0 -> System.out.println("Exiting program...");
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
