import java.util.Scanner;

public class priceBetweenStations {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("How many stations?");
        int stationQuant = sc.nextInt();
        System.out.println("Price between stations?");
        double price = sc.nextDouble();
        sc.close();

        double[][] stations = new double[stationQuant][stationQuant];

        for (int i = 0; i < stationQuant; i++) {

            int kposition = 0;
            double jnumber = 0;

            for (int k = i; k > 0; k--) {
                double kprice = price * k;
                stations[i][kposition] = kprice;
                kposition++;
            }

            for (int j = i; j < stationQuant; j++) {
                stations[i][j] = jnumber;
                jnumber += price;
            }
        }
        for (int i = 0; i < stationQuant; i++) {
            for (int j = 0; j < stationQuant; j++) {
                System.out.print(stations[i][j] + " ");
            }
            System.out.println();
        }
    }
}
