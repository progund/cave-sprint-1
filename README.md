# cave-sprint-1
Skeleton code and give-aways for cave assignment in TIG059

Your task is to write the Room and Player classes according to the UML diagram.

Put both classes in the se.itu.game.cave package.

The corresponding directory is

`se/itu/game/cave/`

# Classes you should write
## R1 The Room class
The Room class is used by the CaveInitializer which reads from the database and creates the Room graph (the map of connected Room:s).

So you'll need some methods in place which we'll provide for you here.

Package statement:
```Java
package se.itu.game.cave;
```

## R2 The constructor and methods we'll give you straight-up:

### R2.1 The inner class of type enum
```Java
  public static enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;
  } 
```

Put that somewhere inside the class, e.g. in the beginning, after the instance variable list.

### R2.2 Constructor
Comment: This horrible constructor is here to give you motivation to learn about Builder in coming sprints.

```Java
  /**
   * Constructor for Room. A direction (north, east, south or west)
   * with the value null signifies that the room in that direction
   * is either not present or accessible.
   * @param description a String decribing this Room
   * @param north the Room to the north
   * @param east the Room to the east
   * @param south the Room to the south
   * @param west the Room to the west     
   * @param things a List&lt;Thing&gt; of Things in the Room
   * @throws NullPointerException - if things or description is null
   */
  public Room(String description,
              Room north,        
              Room east,
              Room south,        
              Room west,
              List<Thing> things) {
    if ( description == null || things == null) {
      throw new NullPointerException("things or description can't be null.");
    }
    this.description=description;
    this.north=north;
    this.east=east;
    this.south=south;
    this.west=west;
    this.things=things;
  }
```

### R2.3 setConnectingRoom - used when building the graph

```Java
  /**
   * @param direction The direction to Room
   * @param room The Room in direction
   */
  public void setConnectingRoom(Direction direction, Room room) {
    switch (direction) {
      case NORTH:
        north = room;
        break;
      case EAST:
        east = room;
        break;
      case SOUTH:
        south = room;
        break;
      case WEST:
        west = room;
        break;
      default: // This is actually a case where we can skip the default label!
        throw new IllegalArgumentException("Direction not corect, can't happen ;)");
    }
  }
```

## R3 Instance variables needed for the Room class

Each Room should, according to the UML and the lectures, keep track of a few things:
* List of things
* Four connecting Room:s (which could be null to signify no such exit)
  * north
  * nouth
  * east
  * west
* A text description ("You are in a boring classroom. Nothing can save you now")

We suggest the follwing instance variable declarations:

```Java
  private String description;
  private Room north;        
  private Room east;        
  private Room south;        
  private Room west;        
  private List<Thing> things;
```

You'll need to import stuff:
```Java
import java.util.List;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
```

## R4 Instance methods
Let's again talk about sending messages to a Room (calling a method), or what behavior a Room exhibits (according to our analysis in class).

The UML specifies a few methods.

### R4.1 things() : Thing[*]

```Java
  /**
   * Returns a reference to an umodifiable version of the list of Things.
   * @return an umodifiable version of the list of Things in this room.
   */
  public List<Thing> things() {
    // Return an unmodifialble view of the things list
  }
```

Hint: Collections has a utility method for making an unmodifiable view of some list object. If we have a reference to e.g. a list, we can do:
```Java
 list = getSomeList();
 List unmodifiableList = Collections.unmodifiableList(list);
```

The call on the second line creates an unmodifiableList from the variable list.

### R4.2 removeThing(thing : Thing) : Thing
Comment:

There are some checks to do here and some exceptions to throw if the checks fails.

Why all the checks and exceptions?

We don't want it to be possible to try to remove a thing which is not present in the room. And we certainly don't want it to be possible to remove a thing which is null from the room.

All of the above are errors in the game logic, so we decided it is better to crash the game than to permit it.

```Java
  /**
   * Remove a Thing from the Room. For convenience reasons this
   * method returns the Thing to remove.
   * @param thing the Thing to remove.
   * @return the Thing to remove.
   * @throws IllegalArgumentException if the Thing to remove is not present in the Room.
   * @throws NullPointerException - if thing is null.
   */
  public Thing removeThing(Thing thing) {
    // Check if thing is null, and if so,
    //   throw a new NullPointerException with a message
    // If we can remove the thing from our things-list,
    //   remove it and return the thing.
    // Otherwise (we couldn't remove it), throw
    // a new IllegalArgumentException with a message.
  }
```

### R4.3 putThing(thing : Thing) : void

Comment: Same as above. We don't want to permit stupid things like adding null to the room, or adding a thing which already exists in the Room. Better to crash the program so that people learn to do the right thing ;-)

```Java
  /**
   * Add a Thing to the Room. For convenience reasons this method
   * returns the Thing added.
   * @param thing the Thing to add.
   * @throws NullPointerException - if thing is null.
   * @throws IllegalArgumentException - if thing already exists in list of things
   */
  public void putThing(Thing thing) {
    // IF the thing is null, throw a new NullPointerException
    //   with a message.
    // If we already have the Thing in the room, throw a new
    //   IllegalArgumentException with a message.
    // Otherwise, add the thing to the rooms list of things.
  }
```

### R4.4 getRoom(direction : Room.Direction) : Room

```Java
  /**
   * Returns the connecting Room in the given direction 
   * @param direction the direction of the room we want.
   * @return connecting Room in the given direction.
   */
  public Room getRoom(Direction direction) {
    // Use a switch-case or an if-else-if statement
    // to decide what room to return.
    // You know the direction from the parameter,
    // so you should simply decide which of your
    // instance variables of connecting Room:s to
    // return.
  }
```

### R4.5 description() : String

```Java
  /**
   * Returns the Room's description
   * @return the Room's description
   */
  public String description() {
    // Return a String with the description for this Room
    // e.g. the instance variable holding this information.
  }    
```

### R4.6 toString() : String

```Java
  /**
   * Returns a String representation of the Room
   * @return a String representation of the Room
   */
  public String toString() {
    // Return a String describing this room.
    // Perhaps, the description and the list of things?
  }
```

## P1. The Player class
Package statement:
```Java
package se.itu.game.cave;
```
The Player class should be a Singleton (there should only ever be one Player instance in the Game).

In order to achieve that, you need to take some actions:

### P1.1 Create a private static Player instance called player:
```Java
  private static Player player;
```

### P1.2. Make the constructor of Player private, so that no one can create instances using "new":
```Java
  /**
   * private constructor to prevent instantiation.
   */
  private Player(Room room) {
    inventory = new ArrayList<Thing>();
    currentRoom = room;
  }
```
### P1.3. Create a public static method called getInstance which returns the reference to the only player:
```Java
  /**
   * @return the one and only instance of Player.
   */
  public static Player getInstance() {
    if (player == null) {
      player = new Player(CaveInitializer.getInstance().getFirstRoom());
    }
    return player;
  }
```

Now, Player is a "singleton" class. In order for e.g. the GUI to get hold of the player, the syntax is:

```Java
Player player = Player.getInstance();
```

Whenever and wherever that code is executed (in a single-threaded program), you will always get the same, one and only, player.

## P2. What instance variables does the player class need?
The player class needs to keep track of the current room in one variable (as shown by the lectures and the UML). And it needs
to keep track of the inventory with things. We suggest you use the following instance variables:
```Java
  private List<Thing> inventory;
  private Room currentRoom;
```
You'll need to import java.util.List and java.util.ArrayList. Look at the constructor again, to see why.

## P3. What public instance methods (what behavior should the player have, or what messages should we be able to send to the player) should the Player class have?

Follow the UML diagram!

Here they are (You'll need to figure out the code in the methods yourselves):
### P3.1 takeThing(thing : Thing) : void
```Java
  /**
   * Takes a thing (i e in a room) and puts in the inventory.
   * @param thing The Thing to take (pick up)
   */
  public void takeThing(Thing thing) {
    // Remove this things from the current room
    // Add this thing to the inventory
  }
```

### P3.2 dropThing(thing : Thing) : void
```Java
  /**
   * Drop down a thing in the current room and remove it from the inventory.
   * @param thing the Thing to drop down.
   * @throws IllegalArgumentException if the Thing to remove is not present in the inventory.
   */
  public void dropThing(Thing thing) {
    // Check that we can drop this Thing, or
    //   throw a new IllegalArgumentException with messge
    // Remove this thing from the inventory
  }
```

### P3.3 inventory() : Thing[*]
```Java
  /**
   * Return the player's inventory
   * @return the inventory
   */
  public List<Thing> inventory() {
    // return a reference to the player's inventory
    
    // (or if you are fancy, a copy of it or a
    //  view of it which cannot be modified)
    // The last thing is "extra for the ambitious"
  }
```

### P3.4 currentRoom() : Room
```Java
  /**
   * Returns the current Room.
   * @return the current room.
   */
  public Room currentRoom() {
    // return a reference to the Player's current Room
  }
```
### P3.5 go(direction : Room.Direction) : void

Comment: For this one, you need to have a working Room class, so that you know how to ask a Room for the room in some direction.
```Java
  /**
   * Moves the player in given direction.
   * @param direction the direction in which to move the player.
   * @throws IllegalArgumentException - if the room in direction does not exist.
   */
  public void go(Direction direction) {
    // Ask the current Room for the Room in "direction" and save it
    // Check if the Room in that direction is null, and if so
    //  throw a new IllegalArgumentException with a message
    // If it wasn't null,
    //  change the Player's current Room to the Room above
  }
```

### P3.6 toString() : String
```Java
  /**
   * Returns a String representation of the player<br>
   * on the form currentRoom: [the room to String] inventory: [the inventory]
   * @return a String representation of the player
   */
  public String toString() {
    // return a string with information about e.g.
    // the current room and the whole inventory.
  }
```

Hint: If you convert a `java.util.List toString()`, either explictly or by using it together with a `String + theList`, you will get a nice String which could look something like this:

`[Bird, Cage, The Egg of Mantumbi]`

