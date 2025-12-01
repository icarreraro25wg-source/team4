public class Train {
    int trainNumber;

    boolean[][] seats = {{true,true,true,true},{true,true,false,false},{false,false,false,false},{false,false,false,false},{false,false,false,false},};
    // tren hutsa adibiderako

    static String abcd = "ABCD";

    Train(){

    }

    // Returns the last free seat's row
    int lastSeatRow(){
        for (int i = 0; i < this.seats.length; i++){
            for (int j = 0; j < 4; j++){
                if (!this.seats[i][j]){
                    return i;
                }
            }
        }
        return -1;
    }

    // Returns the last free seat's column
    int lastSeatColumn(){
        for (int i = 0; i < this.seats.length; i++){
            for (int j = 0; j < 4; j++){
                if (!this.seats[i][j]){
                    return j;
                }
            }
        }
        return -1;
    }

    // Shows all the seats of a train and if they are free or not
    void showSeats(){
        for (int i = 0; i < this.seats.length; i++){
            for (int j = 0; j < 4; j++){
                System.out.print(this.seats[i][j]+"\t");
            }
            System.out.println();
        }
    }

    // Shows the row (number) and column (A,B,C,D) of the seats that were asked
    void showAskedSeats(int seatAmount){
        System.out.print("Tickets: ");
        for (int i = 1; i <= seatAmount; i++){

            char columnLetter = abcd.charAt(lastSeatColumn());

            System.out.print((lastSeatRow()+1)+""+columnLetter+" ");

            this.seats[lastSeatRow()][lastSeatColumn()] = true;
        }
        System.out.println();
        showSeats();
    }

    static void checkTrainSearch(){
        // searchTrain(); if it has not been performed
    }

    // This method uses the previous two to check if there's a seat available and displays it
    void showLastSpareSeat(){
        if (lastSeatRow() == -1){
            System.out.println("No seats available in this train");
        }
        else {
            System.out.println(lastSeatRow() + "," + lastSeatColumn());
        }
    }

    // Returns seat like array - not used yet
    int[] lastSpareSeat(){
        for (int i = 0; i < this.seats.length; i++){
            for (int j = 0; j < 4; j++){
                if (!this.seats[i][j]){
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{};
    }

    // Changes the state of the seats from false to true according to what was asked
    void assignSeats(int seatAmount){
        for (int i = 1; i <= seatAmount; i++){
            int row = lastSeatRow();
            int column = lastSeatColumn();

            this.seats[row][column] = true;
        }
        showSeats();
    }

}