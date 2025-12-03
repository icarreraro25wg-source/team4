public class TicketPurchase {

    static boolean[][] seats = {
            {true,  true,  true,  true},
            {true,  true,  false, false},
            {false, false, false, false},
            {false, false, false, false},
            {false, false, false, false}
    };

    static double[][] priceBetweenStations = {
            {0.0, 0.5, 1.0, 1.5, 2.0},
            {0.5, 0.0, 0.5, 1.0, 1.5},
            {1.0, 0.5, 0.0, 0.5, 1.0},
            {1.5, 1.0, 0.5, 0.0, 0.5},
            {2.0, 1.5, 1.0, 0.5, 0.0}
    };

    static String abcd = "ABCD";

    static void ticketPurchase(int amount) {
        showAskedSeats(amount);
        System.out.println("Price: " + (calculatePrice(1, 4) * amount) + "€");
        showSeats();
    }

    static int lastSeatRow() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (!seats[i][j]) return i;
            }
        }
        return -1;
    }

    static int lastSeatColumn() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < 4; j++) {
                if (!seats[i][j]) return j;
            }
        }
        return -1;
    }

    static void showSeats() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(seats[i][j] + "\t");
            }
            System.out.println();
        }
    }

    static void showAskedSeats(int seatAmount) {
        System.out.print("Tickets: ");

        for (int i = 1; i <= seatAmount; i++) {
            int row = lastSeatRow();
            int col = lastSeatColumn();
            char letter = abcd.charAt(col);

            System.out.print((row + 1) + "" + letter + " ");
            seats[row][col] = true;
        }

        System.out.println();
    }

    static double calculatePrice(int firstStation, int secondStation) {
        return priceBetweenStations[firstStation][secondStation];
    }
}
