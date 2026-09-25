
package Day7.assignment_problems;

interface Attackable {
    void attack();

    void attack(String weaponName);
}

interface Defendable {
    void defend();
}

abstract class GameCharacter {

    private static int counter = 1000;
    private final String characterId;

    public GameCharacter() {
        characterId = "CHAR-" + counter++;
    }

    public String getCharacterId() {
        return characterId;
    }

    public abstract String getSpecialMove();
}

class Warrior extends GameCharacter
        implements Attackable, Defendable {

    private String name;

    public Warrior(String name) {
        this.name = name;
    }

    @Override
    public void attack() {
        System.out.println(name + " attacks with fists.");
    }

    @Override
    public void attack(String weaponName) {
        System.out.println(name + " attacks using " + weaponName);
    }

    @Override
    public void defend() {
        System.out.println(name + " blocks the attack.");
    }

    @Override
    public String getSpecialMove() {
        return "Power Strike";
    }
}

class Trap implements Defendable {

    private String trapName;

    public Trap(String trapName) {
        this.trapName = trapName;
    }

    @Override
    public void defend() {
        System.out.println(trapName + " activates defensive mechanism.");
    }
}

public class ArenaBattleSimulator {

    public static void resolveDefense(Defendable[] combatants) {

        for (Defendable combatant : combatants) {
            combatant.defend();
        }
    }

    public static void main(String[] args) {

        Warrior warrior = new Warrior("Arjun");
        Trap trap = new Trap("Fire Trap");

        warrior.attack();
        warrior.attack("Sword");

        System.out.println(
                "Character ID: " + warrior.getCharacterId()
        );

        System.out.println(
                "Special Move: " + warrior.getSpecialMove()
        );

        Defendable[] combatants = {warrior, trap};

        resolveDefense(combatants);
    }
}