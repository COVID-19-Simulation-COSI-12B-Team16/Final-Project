public class Launcher {
    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        Game g = new Game(args.length == 0 ? null : args[0]);
        g.start();
    }
}
