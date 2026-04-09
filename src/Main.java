import youVideo.YouVideoAppClass;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public final static String CMD_ADD_PUBLISHABLE = "createpublishable";
    
    public final static String MSG_ADD_PUBLISHABLE_LANG = "Invalid language type.";
    public final static String MSG_ADD_DURATION = "Invalid value.";
    public final static String MSG_ADD_ID = "Video with this ID already exists.";
    public final static String MSG_ADD_ADDED = "Video %s created successfully";
    public final static String EXIT = "EXIT";

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        YouVideoAppClass yv = new YouVideoAppClass();

        String cmd;

        do{
            cmd = getCommand(in);
            switch (cmd){
                case CMD_ADD_PUBLISHABLE -> addPublishable(in, yv);
            }
        } while (!cmd.equals(EXIT));
    }

    private static String getCommand(Scanner in){
        String cmd = in.next().toLowerCase();
        return cmd;
    }

    private static void addPublishable(Scanner in, YouVideoAppClass yv){
        String id = in.next();
        int duration = in.nextInt();
        String location = in.next();
        in.nextLine();
        String title = in.next();
        in.nextLine();
        String publisher = in.next();
        in.nextLine();
        String language = in.next();
        in.nextLine();
        if (!isValidLanguage(language)){
            System.out.println(MSG_ADD_PUBLISHABLE_LANG);
        } else if (duration <= 0) {
            System.out.println(MSG_ADD_DURATION);
        } else if (yv.isUnique(id)) {
            System.out.println(MSG_ADD_ID);
        } else {
            yv.addPublishable(id, duration, location, title, publisher, language);
            System.out.printf(MSG_ADD_ADDED, id);
        }

    }

    private static boolean isValidLanguage(String lang){
        if (lang.length() != 2 || lang == null){
            return false;
        }

        lang = lang.toLowerCase();
        String languages[] = Locale.getISOLanguages();

        for (int i = 0; i<languages.length; i++){
            if (languages[i].equals(lang)){
                return true;
            }
        }
        return false;
    }

}