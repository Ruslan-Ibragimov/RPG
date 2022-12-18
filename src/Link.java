import java.util.ArrayList;

public class Link extends Persona{

    ArrayList<String> potions = new ArrayList<>();

    public Link(String name, int xp, int gold, int hp, int strength, int dex) {
        super(name, xp, gold, hp, strength, dex);
    }

}
