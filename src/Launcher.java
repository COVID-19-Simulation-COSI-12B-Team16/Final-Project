import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        String[] paths = new String[] { null, "data/glider.txt", "data/hwss.txt", "data/lwss.txt", "data/mwss.txt",
                "data/beacon_blinker_toad_pulsar.txt" };
        Scanner sc = new Scanner(System.in);
        Game prevGame = null;
        while (true) {
            try {
                System.out.println("======================================================");
                System.out.println("Welcome to the Game of Life");
                System.out.println("------------------------------------------------------");
                System.out.println("Please choose a pattern:\n");
                System.out.printf("1.%s\n2.%s\n3.%s\n4.%s5.%s\n6.%s\n", 
                        "Random Pattern", "Glider",
                        "Heavy-weight Spaceship", 
                        "Light-weight Spaceship", 
                        "Middle-weight Spaceship",
                        "beacon_blinker_toad_pulsar"
                    );
                    System.out.println("------------------------------------------------------");
                System.out.println("Press Q to quit");
                System.out.print("Your Choice: ");
                String input = sc.nextLine();
                if (input.toLowerCase().equals("q")) {
                    if (prevGame != null) prevGame.stop();
                    break;
                }
                int pathIndex = Integer.parseInt(input) - 1;
                if (prevGame != null) {
                    prevGame.stop();
                }
                prevGame = new Game(paths[pathIndex]);
                prevGame.start();
            } catch (Exception e) {
                System.out.println("------------------------------------------------------");
                System.out.println("Invalid Input");
            }
        }
        sc.close();
    }
}
