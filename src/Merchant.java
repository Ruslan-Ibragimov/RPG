public class Merchant implements IDealer {

    public enum Goods {POTION}

    @Override
    public String sell(Goods goods) {
        String result = "";
        if (goods == Goods.POTION){
            result = "potion";
        }
        return result;
    }

}
