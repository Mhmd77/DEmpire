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
        while (flag) {
            try {
                sleep(500);
                if ((enemy = getEnemyInArea() )!= null) {
                    person.attack(enemy);
                    Main.getGame().getServerListener().sendCommand("attack", Main.getGame().getThisPlayer().getID(), person.getPersonID(), enemy.getTeam(), enemy.getPersonID());
                }
            } catch (Exception ignore) {
                System.out.println(Thread.currentThread().getName() + " " + ignore.toString());
            }
        }
    }

    public Person getEnemyInArea() {
        for (Person p :
                Main.getGame().getEnemyPersons()) {
            if ((p.getI() < person.getI() + 10) && (p.getI() > person.getI() - 10) && (p.getJ() < person.getJ() + 10) && (p.getJ() > person.getJ() - 10))
                return p;
        }
        return null;
    }

    public void cancel() {
        System.out.println("CANCELED");
        flag = false;
    }
}
