import java.util.Scanner;

public class Main {

    public final static String EXIT = "EXIT";

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        String cmd;

        do{
            cmd = getCommand(in);
            switch (cmd){
                case "teste" -> getCommand(in);
            }
        } while (!cmd.equals(EXIT));
    }

    private static String getCommand(Scanner in){
        String cmd = in.next().toUpperCase();
        return cmd;
    }


}