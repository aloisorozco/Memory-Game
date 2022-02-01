package test3;
/**
 *
 * @author matt
 */
public class Highscore {

    private int level;

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore(){
        return (int)Math.pow(2,this.level) * 75;
    }

    @Override
    public String toString() {
        return "" + this.getScore();
    }
}
