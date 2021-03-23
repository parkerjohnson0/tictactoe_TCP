
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TTT_Server_TCP {

    public static void main(String[] args){
        try {
           new TTT_Server_TCP(); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
        

    }

    public TTT_Server_TCP() throws IOException {
        int port = 4445;
        ServerSocket servSock = new ServerSocket(port);
        Socket csock;

        System.out.println("You are player 1\n\nReady to connect to player 2...");

       
        csock = servSock.accept();
        
         
        System.out.println("Connected to player 2");
        BufferedReader clientInput = new BufferedReader(new InputStreamReader(csock.getInputStream()));
        PrintWriter out = new PrintWriter(csock.getOutputStream(), true);

        Scanner input = new Scanner(System.in);
        String[] players = new String[2];
        int[] b = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        String[] cbrd = {"-", "-", "-", "-", "-", "-", "-", "-", "-"};
        String[] ch = {"X", "O"};
        int cp = 1, moves = 0, winner = 0;
        System.out.print("\nPlayer 1, please enter your name : ");
        players[0] = input.nextLine();
        out.println(players[0]);
        System.out.print("Waiting on player 2 to enter name...... ");
        players[1] = clientInput.readLine();
        System.out.println(players[1]);

        while (moves < 9 && winner == 0) {
            int cell = 0;
            System.out.print(players[cp - 1] + "'s turn\n     Enter a cell number from 0 to 8? ");

            if (cp - 1 == 0) {
                cell = Integer.parseInt(input.nextLine());
                out.println(cell);
            } else {
                cell = Integer.parseInt(clientInput.readLine());
            }

            while (cell < 0 || cell > 8) {
                System.out.print(players[cp - 1] + "'s turn\n     Enter a cell number from 0 to 8? ");

                if (cp - 1 == 0) {
                    cell = Integer.parseInt(input.nextLine());
                    out.println(cell);

                } else {
                    cell = Integer.parseInt(clientInput.readLine());
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
