public abstract class Persona implements IFighter {
    private String name;
    private int xp;
    private int gold;

    private int hp;
    private int strength;
    private int dex;
    private int level = 1;

    public Persona(String name, int xp, int gold, int hp, int strength, int dex) {
        this.name = name;
        this.xp = xp;
        this.gold = gold;
        this.hp = hp;
        this.strength = strength;
        this.dex = dex;
    }

    @Override
    public int attack(){
        if (dex * 3 > getRandomValue())
            return strength;
        else
            return 0;
    }

    private int getRandomValue(){
        return (int) (Math.random() * 100);
    }

    public String getName() {
        return name;
    }

    public int getXp() {
        return xp;
    }

    public int getGold() {
        return gold;
    }

    public int getHp() {
        return hp;
    }

    public int getStrength() {
        return strength;
    }

    public int getDex() {
        return dex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDex(int dex) {
        this.dex = dex;
    }

    @Override
    public String toString() {
        return name + ", HP: " + hp;
    }

    public void levelUp(){
        this.level++;
        this.hp += 10;
        this.dex += 2;
        this.strength += 2;
    }
}
