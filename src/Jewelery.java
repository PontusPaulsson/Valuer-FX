//Gold jewelry is worth 2000 and silver is worth 700 and the jewelery is worth 500 extra per gemstone.
public class Jewelery extends Valuables {
    private boolean isGold = false;
    private int gemStones = 0;
    public String type;

    public Jewelery(String name, boolean isGold, int gemStones) {
        this.isGold = isGold;
        this.gemStones = gemStones;
        this.setName(name);
        calculateWorth();
    }

    public boolean isGold() {
        return isGold;
    }

    public void setGold(boolean gold) {
        isGold = gold;
    }

    public int getGemStones() {
        return gemStones;
    }

    public void setGemStones(int gemStones) {
        this.gemStones = gemStones;
    }

    public void calculateWorth(){
        if(isGold){
            worth = 2000 + (500 * gemStones);
        }
        else
        {
            worth = 700 + (500 * gemStones);
        }
    }
    public String getType(){
        if(isGold)
            return "Gold";
        else
            return "Silver";

    }

}
