package se.itu.game.test;

import se.itu.game.cave.Room;
import se.itu.game.cave.ThingRule;
import se.itu.game.cave.Player;
import se.itu.game.cave.Thing;
import se.itu.game.cave.NotAllowedException;

import java.util.List;
import java.util.ArrayList;

public class TestThingRule {

    static public void main(String []args) {

        List<Thing> things = new ArrayList<Thing>();
        Room startingRoom = new Room("Start vroom with a view",
                          null, null, null, null, things);

        Player player = Player.getInstance(startingRoom);
        Thing duke = new Thing("The thin white duke");
        Thing bird = new Thing("Bird");
        Thing cage = new Thing("Cage");

        /*
         * Setup a room
         */
        System.out.println("player: " + player);
        System.out.println("* room:      " + player.currentRoom());
        System.out.println("* inventory: " + player.inventory());
        player.currentRoom().putThing(new Thing("SnakeOil"));
        player.currentRoom().putThing(new Thing("Handkerchief"));
        player.currentRoom().putThing(new Thing("Hamster"));
        player.currentRoom().putThing(new Thing("Killer whale"));

        /*
         * print the room's inventory
         */
        System.out.println("* room:      " + player.currentRoom());

        /*
         * Add more Things
         */
        player.currentRoom().putThing(duke);
        player.currentRoom().putThing(bird);
        player.currentRoom().putThing(cage);

        /*
         * print the room's inventory
         */
        System.out.println("* room:      " + player.currentRoom());

        /*
         * Player take duke
         */
        try {
            player.currentRoom().removeThing(duke);
            player.takeThing(duke);
        } catch (Exception e) { ; }
        System.out.println("* room:      " + player.currentRoom());
        System.out.println("* inventory: " + player.inventory());

        /*
         * Player take Bird, should fail since no cage
         */
        try {
            player.currentRoom().removeThing(bird);
            player.takeThing(bird);
        } catch (Exception e) { System.out.println("Failed taking bird. This is correct since we have no cage"); }
        System.out.println("* room:      " + player.currentRoom());
        System.out.println("* inventory: " + player.inventory());
        
        /*
         * Player take cage
         */
        try {
            player.currentRoom().removeThing(cage);
            player.takeThing(cage);
        } catch (Exception e) { ; }
        System.out.println("* room:      " + player.currentRoom());
        System.out.println("* inventory: " + player.inventory());
        
        /*
         * Player take Bird, should work since plaer has cage
         */
        try {
            player.currentRoom().removeThing(bird);
            player.takeThing(bird);
        } catch (Exception e) { System.out.println("Failed taking bird"); }
        System.out.println("* room:      " + player.currentRoom());
        System.out.println("* inventory: " + player.inventory());
    }
    
}
