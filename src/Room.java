import java.util.HashMap;
import java.util.ArrayList;

public class Room {
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Item> items;

    public Room(String description) {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new ArrayList<Item>();
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getDescription() { return description; }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public String toString() {

        String output = "You are " + description + "\n" + "which contains item(s): \n ";
        for(Item item : items){
            output = output + item.toString() + "\n";}
        output = output + "\n" + getExitString();
        return output;
    }

    public String getExitString() {
        String returnString = "Exits: ";
        for (String direction : exits.keySet()) {
            returnString += " " + direction;
        }
        return returnString;
    }
}