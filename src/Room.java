import java.util.HashMap;

public class Room {
    private String description;
    private HashMap<String, Room> exits;

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getDescription() { return description; }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }

    public String getExitString() {
        String returnString = "Exits: ";
        for (String direction : exits.keySet()) {
            returnString += " " + direction;
        }
        return returnString;
    }
}