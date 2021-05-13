import java.util.HashMap;

public class Room {
    private String description;
    private HashMap<String, Room> exits;

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public void setExits(Room north, Room east, Room south, Room west) {
        if (north != null) exits.put("north", north);
        if (east != null) exits.put("east", east);
        if (south != null) exits.put("south", south);
        if (west != null) exits.put("west", west);
    }

    public String getDescription() { return description; }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public String getExitString() {
        String returnString = "Exits: ";
        for (String direction : exits.keySet()) {
            returnString += " " + direction;
        }
        return returnString;
    }
}