import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {

    private String name;
    private Room currentRoom;
    private Room previousRoom;
    private ArrayList<Item> items;

    public Player(String name, Room currentRoom) {
        this.name = name;
        this.currentRoom = currentRoom;
        items = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void setPreviousRoom(Room previousRoom){
        this.previousRoom = previousRoom;
    }

    public Room getPreviousRoom(){
        return previousRoom;
    }

    private boolean hasItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) return true;
        }
        return false;
    }

    public boolean take(String itemName) {
        if (currentRoom.hasItem(itemName)) {
            items.add(currentRoom.getItem(itemName));
            return true;
        }
        return false;
    }

    public boolean drop(String itemName) {
        if (this.hasItem(itemName)) {
            for (Item item : items) {
                if (item.getName().equals(itemName)) {
                    if (items.remove(item)) {
                        currentRoom.addItem(item);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String getLongDescription() {
        if (!items.isEmpty()) {
            String returnString = "Kledingstukken verzameld:\n";
            for (Item item : items) {
                returnString += "   " + item.toString() + "\n";
            }
            return returnString;
        }
        return "Niets (meer) in je handen.";
    }

    public void printLocationInfo() {
        System.out.println(this.getCurrentRoom().getLongDescription());
        System.out.println(this.getLongDescription());
        System.out.println();
    }



}
