public class Battlefield {

    public void fight(Persona link, Persona enemy, IFightCallBack fightCallback) {
        Runnable runnable = () -> {
            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println("----Ход: " + turn + "----");
                if (turn++ % 2 != 0) {
                    isFightEnded = makeHit(enemy, link, fightCallback);
                } else {
                    if (link.getHp() <= 50 && ((Link)link).potions.size() > 0 ){
                        System.out.println(String.format("%s took a potion! It heals for 50 HP", link.getName()));
                        ((Link)link).potions.remove(0);
                        link.setHp(link.getHp() + 50);
                    }
                    isFightEnded = makeHit(link, enemy, fightCallback);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        }

    private Boolean makeHit(Persona defender, Persona attacker, IFightCallBack fightCallback) {
        int hit = attacker.attack();
        int defenderHealth = defender.getHp() - hit;
        if (hit != 0) {
            System.out.println(String.format("%s caused %d points of damage!", attacker.getName(), hit));
            System.out.println(String.format("%s has %d HP...", defender.getName(), defenderHealth));
        } else {
            System.out.println(String.format("%s missed!", attacker.getName()));
        }
        if (defenderHealth <= 0 && defender instanceof Link) {
            System.out.println("YOU DIED");
            fightCallback.fightLost();
            return true;
        } else if(defenderHealth <= 0) {
            System.out.println(String.format("You won and get %d XP points and %d GOLD", defender.getXp(), defender.getGold()));
            attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.setGold(attacker.getGold() + defender.getGold());
            if (attacker.getXp() % 100 == 0){
                attacker.levelUp();
                System.out.println(String.format("%s LEVELED UP! lv%d -> lv%d",attacker.getName(), attacker.getLevel()-1,attacker.getLevel() ));
            }
            fightCallback.fightWin();
            return true;
        } else {
            defender.setHp(defenderHealth);
            return false;
        }
    }

}
