import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;

public class Hyrule {
    private static BufferedReader br;
    private static Link link = null;
    private static Battlefield battlefield = null;

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        battlefield = new Battlefield();
        System.out.println("Enter the character name (Link by default):");
        try {
            command(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void command(String string) throws IOException {

        if (link == null) {
            if (string.equals(""))
                string = "Link";
            link = new Link(
                    string,
                    0,
                    0,
                    100,
                    20,
                    20
            );
            System.out.println(String.format("%s, it's dangerous to go alone! Take this sword!", link.getName()));
            printNavigation();
            command(br.readLine());
        }

        switch (string) {
            case "1": {
                Merchant merchant = new Merchant();
                if (link.getGold() >= 10) {
                    link.potions.add(merchant.sell(Merchant.Goods.POTION));
                    link.setGold(link.getGold() - 10);
                } else {
                    System.out.println("You have not enough money, you need at least 10 gold");
                    command(br.readLine());
                }

                System.out.println(String.format("Now %s has %d potions", link.getName(), link.potions.size()));
                printNavigation();
                command(br.readLine());
            }
            break;
            case "2": {
                commitFight();
            }
            break;
            case "3":
                System.exit(1);
                break;
            case "y":
                command("2");
                break;
            case "n": {
                printNavigation();
                command(br.readLine());
            }
            default:
                System.out.println("Wrong input. Please try again");
        }
        command(br.readLine());
    }

    private static void printNavigation() {
        System.out.println("What's next?");
        System.out.println("1. Buy potions");
        System.out.println("2. Go rescue Zelda");
        System.out.println("3. Quit");
    }

    private static void commitFight() {
        battlefield.fight(link, createEnemy(), new IFightCallBack() {
            @Override
            public void fightWin() {
                System.out.println(String.format("%s won! You have %d XP, %d GOLD, and %d HP.", link.getName(), link.getXp(), link.getGold(), link.getHp()));
                System.out.println("Let's go further? (y/n)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {

            }
        });
    }

    interface FightCallback {

    }

    public static Persona createEnemy() {
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0) {
            return new Bokoblin(
                    "Bokoblin",
                    10,
                    20,
                    50,
                    10,
                    100
            );
        } else {
            return new Guardian(
                    "Guardian",
                    50,
                    10,
                    60,
                    20,
                    20
            );
        }

    }
}

