package Game;


public class AttackListener extends Thread {
    private Person person;//Who Is Moving
    private boolean flag;

    AttackListener(Person person) {
        this.person = person;
        flag = true;
    }

    @Override
    public void run() {
        Person enemy;
        Building building;
        while (flag) {
            try {
                sleep(500);
                if ((enemy = getEnemyInArea()) != null) {
                    person.attack(enemy);
                    Main.getGame().getServerListener().sendCommand("attack_person", Main.getGame().getThisPlayer().getID(), person.getPersonID(), enemy.getTeam(), enemy.getPersonID());
                } else if ((building = getBuildingInAre()) != null) {
                    person.attack(building);
                    Main.getGame().getServerListener().sendCommand("attack_building", Main.getGame().getThisPlayer().getID(), person.getPersonID(), building.getTeam(), building.getBuildingId());
                }
            } catch (Exception ignore) {
                System.out.println(Thread.currentThread().getName() + " " + ignore.toString());
            }
        }
    }

    private Person getEnemyInArea() {
        for (Person p :
                Main.getGame().getEnemyPersons()) {
            if ((p.getI() < person.getI() + 10) && (p.getI() > person.getI() - 10) && (p.getJ() < person.getJ() + 10) && (p.getJ() > person.getJ() - 10))
                return p;
        }
        return null;
    }

    void cancel() {
        System.out.println("CANCELED");
        flag = false;
    }

    private Building getBuildingInAre() {
        for (Building b :
                Main.getGame().getEnemyBuildings()) {
            if ((b.getPos().i < person.getI() + 10) && (b.getPos().i > person.getI() - 10) && (b.getPos().j < person.getJ() + 10) && (b.getPos().j > person.getJ() - 10))
                return b;
        }
        return null;
    }
}
