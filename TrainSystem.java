import java.util.Scanner;
import java.util.ArrayList;

public class TrainSystem {


    class Route {
        String name;
        String[] stations;

        Route(int number) {
            String[][] allStations = {
                    {"Figueras","Valencia","Barcelona","Lleida","Zaragoza","Madrid"},
                    {"Irun","Donostia","Pamplona","Logroño","Burgos","Madrid"},
                    {"Murcia","Alicante","Valencia","Cuenca","Aranjuez","Madrid"},
                    {"Cádiz","Sevilla","Huelva","Mérida","Toledo","Madrid"},
                    {"Málaga","Granada","Linares","Manzanares","Ciudad Real","Madrid"},
                    {"A Coruña","Santiago","Ourense","León","Valladolid","Madrid"}
            };
            this.name = "Route " + (number + 1);
            this.stations = allStations[number];
        }
    }

    class Train {
        String code;
        Route route;

        boolean[][] seats = new boolean[6][4];

        Train(String code, Route route) {
            this.code = code;
            this.route = route;
        }

        boolean isFree(int r, int c) {
            return !seats[r][c];
        }

        void occupy(int r, int c) {
            seats[r][c] = true;
        }

        void free(int r, int c) {
            seats[r][c] = false;
        }
    }

    class Reservation {
        String code;
        Train train;
        int row, col;
        String origin, dest;

        Reservation(String code, Train train, int row, int col, String origin, String dest) {
            this.code = code;
            this.train = train;
            this.row = row;
            this.col = col;
            this.origin = origin;
            this.dest = dest;
        }
    }

    // Storage

    Route[] routes = new Route[6];
    Train[] trains = new Train[6];
    ArrayList<Reservation> reservations = new ArrayList<>();

    // Selected origin-destination
    String selectedOrigin = null;
    String selectedDestination = null;

    public TrainSystem() {
        for (int i = 0; i < 6; i++) {
            routes[i] = new Route(i);
            trains[i] = new Train("T" + (i + 1), routes[i]);
        }
    }


    // Prepare search
    public void prepareSearch(Scanner sc) {
        System.out.println("\n--- Prepare train search ---");

        System.out.println("Choose route:");
        for (int i = 0; i < routes.length; i++) {
            System.out.println((i+1) + ". " + routes[i].name);
        }
        System.out.print("Route number: ");
        int r = Integer.parseInt(sc.nextLine()) - 1;

        System.out.println("Available stations:");
        for (int i = 0; i < routes[r].stations.length; i++) {
            System.out.println((i+1) + ". " + routes[r].stations[i]);
        }

        System.out.print("Origin: ");
        selectedOrigin = routes[r].stations[Integer.parseInt(sc.nextLine()) - 1];

        System.out.print("Destination: ");
        selectedDestination = routes[r].stations[Integer.parseInt(sc.nextLine()) - 1];

        System.out.println("Search parameters saved.");
    }

    // Search trains
    public void searchTrains() {
        System.out.println("\n--- Search results ---");

        if (selectedOrigin == null || selectedDestination == null) {
            System.out.println("You must prepare search first (option 1).");
            return;
        }

        System.out.println("Trains for: " + selectedOrigin + " → " + selectedDestination);

        for (Train t : trains) {
            if (containsStation(t.route.stations, selectedOrigin) &&
                    containsStation(t.route.stations, selectedDestination)) {
                System.out.println("- " + t.code + " (" + t.route.name + ")");
            }
        }
    }

    // Check if a city is in the route
    boolean containsStation(String[] arr, String name) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(name)) {
                return true;
            }
        }
        return false;
    }

    // Purchase ticket
    public void purchaseTicket(Scanner sc) {

        if (selectedOrigin == null) {
            System.out.println("Prepare search first.");
            return;
        }

        System.out.println("\n--- Purchase ticket ---");
        System.out.println("Choose train:");

        int count = 0;
        for (int i = 0; i < trains.length; i++) {
            if (containsStation(trains[i].route.stations, selectedOrigin)) {
                System.out.println((i+1) + ". " + trains[i].code);
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No trains available.");
            return;
        }

        int trainIndex = Integer.parseInt(sc.nextLine()) - 1;
        Train train = trains[trainIndex];

        int row = -1, col = -1;
        boolean found = false;

        for (int r = 0; r < 6 && !found; r++) {
            for (int c = 0; c < 4 && !found; c++) {
                if (!train.seats[r][c]) {
                    row = r;
                    col = c;
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Train is full.");
            return;
        }

        train.occupy(row, col);

        String code = "R" + (reservations.size() + 1);

        Reservation res = new Reservation(code, train, row, col,
                selectedOrigin, selectedDestination);

        reservations.add(res);

        System.out.println("Ticket purchased!");
        System.out.println("Reservation code: " + code);
        System.out.println("Assigned seat: row " + (row+1) + ", col " + (col+1));
    }

    // Change seat
    public void changeSeat(Scanner sc) {
        System.out.print("Reservation code: ");
        String code = sc.nextLine();

        Reservation res = getReservation(code);
        if (res == null) {
            System.out.println("Not found.");
            return;
        }

        printSeats(res.train);

        System.out.print("New row: ");
        int r = Integer.parseInt(sc.nextLine()) - 1;

        System.out.print("New col: ");
        int c = Integer.parseInt(sc.nextLine()) - 1;

        if (!res.train.isFree(r, c)) {
            System.out.println("Seat taken.");
            return;
        }

        res.train.free(res.row, res.col);
        res.train.occupy(r, c);

        res.row = r;
        res.col = c;

        System.out.println("Seat updated.");
    }

    //Modify reservation
    public void modifyReservation(Scanner sc) {
        System.out.print("Reservation code: ");
        String code = sc.nextLine();

        Reservation res = getReservation(code);
        if (res == null) {
            System.out.println("Not found.");
            return;
        }

        System.out.println("1. Change seat");
        System.out.println("2. Change destination");
        System.out.print("Option: ");
        int op = Integer.parseInt(sc.nextLine());

        if (op == 1) {
            changeSeat(sc);
        } else if (op == 2) {
            System.out.println("Stations:");
            for (int i = 0; i < res.train.route.stations.length; i++) {
                System.out.println((i+1) + ". " + res.train.route.stations[i]);
            }
            System.out.print("New destination: ");
            res.dest = res.train.route.stations[Integer.parseInt(sc.nextLine()) - 1];

            System.out.println("Destination updated.");
        }
    }

    // Cancel reservation
    public void removeReservation(Scanner sc) {
        System.out.print("Reservation code: ");
        String code = sc.nextLine();

        Reservation res = getReservation(code);
        if (res == null) {
            System.out.println("Not found.");
            return;
        }

        res.train.free(res.row, res.col);

        reservations.remove(res);

        System.out.println("Reservation removed.");
    }

    //
    Reservation getReservation(String code) {
        for (int i = 0; i < reservations.size(); i++) {
            Reservation r = reservations.get(i);

            if (r.code.equals(code)) {
                return r;
            }
        }
        return null;
    }

   // Shows seats in a visual way
    void printSeats(Train t) {
        System.out.println("Seat map:");
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 4; c++) {
                System.out.print(t.seats[r][c] ? "X " : "0 ");
            }
            System.out.println();
        }
    }
}
