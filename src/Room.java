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

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getDescription() { return description; }

    public String getExitString() {
        String returnString = "Uitgangen: ";
        for (String direction : exits.keySet()) {
            returnString += " " + direction;
        }
        return returnString;
    }

    public String getItemsString() {
        if (!items.isEmpty()) {
            String returnString = "Kledingstukken in de kamer: \n";
            for (Item item : items) {
                returnString += "   " + item.toString() + "\n";
            }
            return returnString;
        }
        return "";
    }

    public boolean hasItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) return true;
        }
        return false;
    }

    public Item getItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                if (items.remove(item)) {
                    return item;
                }
            }
        }
        return null;
    }

    public String getLongDescription() {
        return "Je bent " + description + "\n" + getItemsString() + getExitString();
    }
}