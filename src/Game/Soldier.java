package Game;

/**
 * Created by ASUS on 7/8/2017.
 */
public class Soldier extends Person {
    private int life = 1000;
    private int attackPower = 140;
    private int foodAmount = 2;

    public void attack(int isrc, int jsrc, int igoal, int jgoal) {
        isrc = this.i;
        jsrc = this.j;


    }

    Soldier(int i, int j, int team, String loc) {
        super(i, j, team, loc);
    }

    @Override
    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    @Override
    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public void setLife(int life) {
        this.life = life;
    }
}
