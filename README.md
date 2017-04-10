# cave-sprint-1
Skeleton code and give-aways for cave assignment in TIG059

Your task is to write the Room and Player classes according to the UML diagram.

Put both classes in the se.itu.game.cave package.

The corresponding directory is

`se/itu/game/cave/`

# Classes you should write
## P1. The Player class
Package statement:
```Java
package se.itu.game.cave;
```
The Player class should be a Singelton (there should only ever be one Player instance in the Game).

In order to achieve that, you need to take some actions:

### P1.1 Create a private static Player instance called player:
```Java
  private static Player player;
```
