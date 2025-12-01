public class Ticket {

    static boolean[][] seats = {{true,true,true,true},{true,true,false,false},{false,false,false,false},{false,false,false,false},{false,false,false,false}};
    static double[][] priceBetweenStations = {{0.0,0.5,1.0,1.5,2.0},{0.5,0.0,0.5,1.0,1.5},{1.0,0.5,0.0,0.5,1.0},{1.5,1.0,0.5,0.0,0.5},{2.0,1.5,1.0,0.5,0.0}};
    // example arrays


    // copy from here ↓
    static String abcd = "ABCD";

    static void ticketPurchase(int amount){
        showAskedSeats(amount);
        System.out.println("Price: "+(calculatePrice(1,4)*amount)+"€"); // The values of calculatePrice have to be changed for the first and last stations
        Ticket.showSeats();
    }

    // Returns the last free seat's row
    static int lastSeatRow(){
        for (int i = 0; i < seats.length; i++){
            for (int j = 0; j < 4; j++){
                if (!seats[i][j]){
                    return i;
                }
            }
        }
        return -1;
    }

    // Returns the last free seat's column
    static int lastSeatColumn(){
        for (int i = 0; i < seats.length; i++){
            for (int j = 0; j < 4; j++){
                if (!seats[i][j]){
                    return j;
                }
            }
        }
        return -1;
    }

    // Shows all the seats of a train and if they are free or not
    static void showSeats(){
        for (int i = 0; i < seats.length; i++){
            for (int j = 0; j < 4; j++){
                System.out.print(seats[i][j]+"\t");
            }
            System.out.println();
        }
    }

    // Shows the row (number) and column (A,B,C,D) of the seats that were asked
    static void showAskedSeats(int seatAmount){
        System.out.print("Tickets: ");
        for (int i = 1; i <= seatAmount; i++){

            char columnLetter = abcd.charAt(lastSeatColumn());

            System.out.print((lastSeatRow()+1)+""+columnLetter+" ");

            seats[lastSeatRow()][lastSeatColumn()] = true;
        }
        System.out.println();
    }

    // Calculates the price depending on ticket amount and stations crossed, using priceBetweenStations array
    static double calculatePrice(int firstStation,int secondStation){
        return priceBetweenStations[firstStation][secondStation];
    }

}