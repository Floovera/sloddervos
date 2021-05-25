public class Check extends Command{

    public Check(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {
        System.out.println("Hier moet het aantal kamers getoond worden: xxx");
        return false;
    }
}
