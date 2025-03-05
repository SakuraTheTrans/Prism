import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {

    // Input creation
    static Scanner input = new Scanner(System.in);

    static int strength;
    static int dexterity;
    static int constitution;
    static int intelligence;
    static int wisdom;
    static int charisma;

    private static String setName() {
        String newName;
        System.out.println("What would you like your username to be?");
        System.out.print("> ");
        newName = input.nextLine();
        System.out.println("Username updated!");
        return newName;
    }

    private static void playerInfo(String username, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        System.out.println("Name : " + username);
        System.out.println("Str  : " + strength);
        System.out.println("Dex  : " + dexterity);
        System.out.println("Con  : " + constitution);
        System.out.println("Int  : " + intelligence);
        System.out.println("Wis  : " + wisdom);
        System.out.println("Cha  : " + charisma);
    }

    private static void changeStat(String fileName, int stat) {
        try (FileWriter writer = new FileWriter(new File (fileName))){
            writer.write(String.valueOf(stat));
        } catch (Exception e) {
            System.out.println("Idk how this broke but changeStat threw an error!");
        }
    }

    private static void cheat() {
        String[] allStatFiles = {"strength.txt","dexterity.txt","constitution.txt","intelligence.txt","wisdom.txt","charisma.txt"};
        for (String stat : allStatFiles) {
            changeStat(stat, 99);
        }
        updateStat();
        System.out.println("All stats updated to 99");
    }

    private static void reset() {
        String[] allStatFiles = {"strength.txt","dexterity.txt","constitution.txt","intelligence.txt","wisdom.txt","charisma.txt"};
        for (String stat : allStatFiles) {
            changeStat(stat, 0);
        }
        updateStat();
        System.out.println("All stats updated to 0");
    }

    private static int readStat(String fileName) {
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
        } catch (Exception e) {
            System.out.println("File not found: " + fileName);
            System.out.println("This should never happen, if this did idk how you broke my code this bad");
        }
        return 0;
    }

    private static void help() {
        System.out.println("Command List :\nname\ninfo\nhelp\nreset\ncheat\nexit");
    }

    public static void updateStat() {
            strength = readStat("strength.txt");
            dexterity = readStat("dexterity.txt");
            constitution = readStat("constitution.txt");
            intelligence = readStat("intelligence.txt");
            wisdom = readStat("wisdom.txt");
            charisma = readStat("charisma.txt");
    }

    public static void main(String[] args) {

        // Creates the files for persistent stat storage!
        try {
            File strength = new File("strength.txt");
            File dexterity = new File("dexterity.txt");
            File constitution = new File("constitution.txt");
            File intelligence = new File("intelligence.txt");
            File wisdom = new File("wisdom.txt");
            File charisma = new File("charisma.txt");

            System.out.println("Initial Startup Processes :");
            if (strength.createNewFile()) System.out.println("File created: " + strength.getName());
            if (dexterity.createNewFile()) System.out.println("File created: " + dexterity.getName());
            if (constitution.createNewFile()) System.out.println("File created: " + constitution.getName());
            if (intelligence.createNewFile()) System.out.println("File created: " + intelligence.getName());
            if (wisdom.createNewFile()) System.out.println("File created: " + wisdom.getName());
            if (charisma.createNewFile()) System.out.println("File created: " + charisma.getName());

        } catch (Exception e) {
            System.out.println("Testing :3");
        }

        // Declaring variables
        String username = "Player";
        String userInput;
        updateStat();

        System.out.println("Welcome! To list commands please type 'help'.\nTo set your username (Which is recommended) please type 'name'");
        while (true) {
            System.out.print("> ");
            userInput = input.nextLine();
            switch (userInput) {
                case "name" -> username = setName();
                case "info" -> playerInfo(username, strength, dexterity, constitution, intelligence, wisdom, charisma);
                case "help" -> help();
                case "cheat" -> cheat();
                case "reset" -> reset();
                case "exit" -> {return;}

                default -> System.out.println("Not a command, try again!");
            }

        }
    }
}