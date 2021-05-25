public class Check extends Command{

    public Check(CommandWord firstWord, String secondWord) {
        super(firstWord, secondWord);
    }

    @Override
    public boolean execute(Player player) {

        String omschrijvingkamer;
        omschrijvingkamer = player.getCurrentRoom().getDescription();

        if (omschrijvingkamer.equals("in de slaapkamer")
                && player.getCurrentRoom().hasItem("kousen")
                && player.getCurrentRoom().hasItem("jas")
                && player.getCurrentRoom().hasItem("pyjama")
                && player.getCurrentRoom().hasItem("hemdje")
                && player.getCurrentRoom().hasItem("pantoffels")) {
            System.out.println("Het is je gelukt! Goed gedaan! Opgeruimd staat netjes.");
            return true;
        } else {
            System.out.println("Er zijn nog steeds items die rondslingeren. Verzamel ze snel! De poetsvrouw komt eraan ...");
            return false;
        }
    }
}
