package Grid;

public class Tile {
    private int id;
    private Location location;
    private int value;

    public Tile(int count, Location location, int value){
        this.id = count;
        this.location = location;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public Location getLocation() {
        return location;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void replaceValueWith(Tile newValue){
        this.value = newValue.getValue();
    }

    public void increaseValueBy(Tile newValue){
        this.value += newValue.getValue();
    }

    public boolean compareValueTo(Tile tileToCompare){
        return this.value == tileToCompare.getValue();
    }


    @Override
    public String toString(){
        return String.format("Tile [%d]: X: %d, Y: %d, Value: %d", getId(), getLocation().getX(), getLocation().getY(), getValue());
    }
}
