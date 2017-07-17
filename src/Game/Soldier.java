package Game;

public class Soldier extends Person {
    private int life = 1000;
    private int attackPower = 140;
    private int foodAmount = 2;

    Soldier(int personID, int j, int team, int i) {
        super(personID, j, team, i);
    }

    @Override
    protected void setInfo() {
        foodAmount = 2;
        attackPower = 70;
        life = 1000;
    }
}
