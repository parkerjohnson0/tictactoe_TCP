
import java.net.Socket;
import java.util.Scanner;
import java.io.*;

public class TTT_Client_TCP {

    public static void main(String[] args) throws IOException {
        new TTT_Client_TCP();

    }

    public TTT_Client_TCP() throws IOException {
        int port = 4445;
        Socket csock;
        BufferedReader serverInput = null;
        PrintWriter out = null;

        try {
            csock = new Socket("localhost", port);
            serverInput = new BufferedReader(new InputStreamReader(csock.getInputStream()));
            out = new PrintWriter(csock.getOutputStream(), true);

        } catch (Exception e) {
            System.out.println("Attempting to connect to server...");

            e.printStackTrace();
        }

    


        Scanner input = new Scanner(System.in);
        String[] players = new String[2];
        int[] b = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        String[] cbrd = {"-", "-", "-", "-", "-", "-", "-", "-", "-"};
        String[] ch = {"X", "O"};
        int cp = 1, moves = 0, winner = 0;
        System.out.print("You are player 2\n\nWaiting on player 1 to enter name.... ");
        players[0] = serverInput.readLine();
        System.out.println(players[0]);
        System.out.print("Player 2, please enter your name : ");
        players[1] = input.nextLine();
        out.println(players[1]);

        while (moves < 9 && winner == 0) {
            int cell = 0;
            System.out.print(players[cp - 1] + "'s turn\n    Enter a cell number from 0 to 8? ");

            if (cp - 1 == 0) {
                cell = Integer.parseInt(serverInput.readLine());
            } else {
                cell = Integer.parseInt(input.nextLine());
                out.println(cell);
            }

            while (cell < 0 || cell > 8) {
                System.out.print(players[cp - 1] + "'s turn\n    Enter a cell number from 0 to 8? ");

                if (cp - 1 == 0) {
                    cell = Integer.parseInt(serverInput.readLine());
                } else {
                    cell = Integer.parseInt(input.nextLine());
                    out.println(cell);
                }
            }
            if (b[cell] != 0) {
                System.out.println("The cell entered was " + cell);

                System.out.println("This cell is occupied, please try again");

            } else {
                System.out.println("\nThe cell entered was " + cell);

                b[cell] = cp;
                cbrd[cell] = ch[cp - 1];
                if (b[0] == cp && b[1] == cp && b[2] == cp) {
                    winner = cp;
                } else if (b[0] == cp && b[3] == cp && b[6] == cp) {
                    winner = cp;
                } else if (b[0] == cp && b[4] == cp && b[8] == cp) {
                    winner = cp;
                } else if (b[1] == cp && b[4] == cp && b[7] == cp) {
                    winner = cp;
                } else if (b[2] == cp && b[5] == cp && b[8] == cp) {
                    winner = cp;
                } else if (b[2] == cp && b[4] == cp && b[6] == cp) {
                    winner = cp;
                } else if (b[3] == cp && b[4] == cp && b[5] == cp) {
                    winner = cp;
                } else if (b[5] == cp && b[4] == cp && b[3] == cp) {
                    winner = cp;
                } else if (b[6] == cp && b[4] == cp && b[2] == cp) {
                    winner = cp;
                } else if (b[6] == cp && b[7] == cp && b[8] == cp) {
                    winner = cp;
                }
                if (winner == 0) {
                    cp = cp % 2 + 1;
                }
                System.out.println("\n" + cbrd[0] + "   " + cbrd[1] + "    " + cbrd[2]);
                System.out.println("\n" + cbrd[3] + "   " + cbrd[4] + "    " + cbrd[5]);
                System.out.println("\n" + cbrd[6] + "   " + cbrd[7] + "    " + cbrd[8] + "\n\n");

            }
        }
        if (winner > 0) {
            System.out.println(players[cp - 1] + " is the winner");

        } else {
            System.out.println("The game ended in a tie");

        }

    }
}
