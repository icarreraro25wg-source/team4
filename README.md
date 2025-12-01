# Team 4

> Ticket.java contains the methods for ticket-purchase.

> Two arrays are also created in Ticket.java for testing (seats and priceBetweenStations).

> Feel free to use our code.

>Recommended use of ticketPurchase() ↓
>````
>switch (option) {
>            case 2:
>                // Ticket.checkTrainSearch();  this should be taken from the requirement 3
>
>                System.out.println("How many tickets?");
>                int amount = sc.nextInt();
>
>                Ticket.ticketPurchase(amount);
>
>                break;
>        }