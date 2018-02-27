package Grid;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Map {
    private static List<Tile> grid = new ArrayList<>();
    private static int size = 1;
    private static int width;
    private static int score = 0;

    public static void generateMap(int height){
        for (int x = 0; x <= height-1; x++){
            for (int y = 0; y <= height-1; y++){
                Location l = new Location(x, y);
                int val = 0;
                Tile t = new Tile(Map.size, l, val);
                grid.add(t);
                Map.size++;
            }
        }
        getTile(0).setValue(4);
        Map.width = height;
    }

    public static void addRandomTile(){
        int val = (new Random().nextInt(2) == 1 ? 2 : 4);
        try {
            int tile = new Random().nextInt(size) - 1;

            if (getTile(tile).getValue() == 0) {
                getTile(tile).setValue(val);
            } else {
                addRandomTile();
            }
        }
        catch (IndexOutOfBoundsException e){
            addRandomTile();
        }
    }

    public static void displayMap(){
        addRandomTile();
        String line = "";
        System.out.println(String.format("Score: %d", score));
        for (Tile t : grid){
            line += String.format("%-4s", t.getValue());
            if (t.getId() % width == 0 && t.getId() != 0){
                System.out.println(line);
                line = "";
            }
        }
    }

    private static Tile getTile(int id){
        return Map.grid.get(id);
    }

    private static List<Tile> getColumn(int y){
        List<Tile> tiles = new ArrayList<>();
        int count = 0;
        for (Tile t : Map.grid) {
            if (count == width){
                return tiles;
            }
            else
            {
                if (t.getLocation().getY() == y){
                    tiles.add(t);
                    count++;
                }
            }
        }
        return tiles;
    }

    private static List<Tile> getRow(int x){
        List<Tile> tiles = new ArrayList<>();
        int count = 0;
        for (Tile t : Map.grid) {
            if (count == width){
                return tiles;
            }
            else
            {
                if (t.getLocation().getX() == x){
                    tiles.add(t);
                    count++;
                }
            }
        }
        return tiles;
    }

    private static void setColumn(List<Tile> tiles){
        int column = tiles.get(0).getLocation().getY();
        List<Tile> toRemove = new ArrayList<>();
        for (Tile t : Map.grid) {
            if (t.getLocation().getY() == column) {
                toRemove.add(t);
            }
        }
        Map.grid.removeAll(toRemove);
        Map.grid.addAll(tiles);
        Map.grid.sort(Comparator.comparing(Tile::getId));
    }

    private static void setRow(List<Tile> tiles){
        int row = tiles.get(0).getLocation().getX();
        List<Tile> toRemove = new ArrayList<>();
        for (Tile t : Map.grid) {
            if (t.getLocation().getX() == row) {
                toRemove.add(t);
            }
        }
        Map.grid.removeAll(toRemove);
        Map.grid.addAll(tiles);
        Map.grid.sort(Comparator.comparing(Tile::getId));
    }

    public static void shiftUp(){
        List<Tile> tiles = new ArrayList<>();
        for (int y = 0; y <= width-1; y++){     //Go over each column
            tiles = getColumn(y);               //Find current column
            for (int x = 0; x <=width-1; x++) { //Go over each element width times, so everything shifts
                for (Tile t : tiles) {          //Go over each tile
                    for (Tile n : tiles) {      //Go over each tile (but technically -1)
                        if (t != n) {           //As long as they're not the same tile
                            if (n.getLocation().getX() == t.getLocation().getX() - 1) { //Check that they're -1 X apart
                                if (n.getValue() == 0 && t.getValue() != 0) {           //Is the above tile 0?
                                    n.replaceValueWith(t);                              //Replace 0 with current tile;
                                    t.setValue(0);                                      //Set current tile to 0
                                }
                                if (n.compareValueTo(t)) {                              //Are they the same value?
                                    n.increaseValueBy(t);                               //Increase above with current
                                    score+=t.getValue();
                                    t.setValue(0);                                      //Set current to 0
                                }
                            }
                        }
                    }
                }
            }
        }
        setColumn(tiles);
    }

    public static void shiftDown(){
        List<Tile> tiles = new ArrayList<>();
        for (int y = 0; y <= width-1; y++){     //Go over each column
            tiles = getColumn(y);               //Find current column
            for (int x = 0; x <=width-1; x++) { //Go over each element width times, so everything shifts
                for (Tile t : tiles) {          //Go over each tile
                    for (Tile n : tiles) {      //Go over each tile (but technically -1)
                        if (t != n) {           //As long as they're not the same tile
                            if (n.getLocation().getX() == t.getLocation().getX() + 1) { //Check that they're 1 X apart
                                if (n.getValue() == 0 && t.getValue() != 0) {           //Is the above tile 0?
                                    n.replaceValueWith(t);                              //Replace 0 with current tile
                                    t.setValue(0);                                      //Set current tile to 0
                                }
                                if (n.compareValueTo(t)) {                              //Are they the same value?
                                    n.increaseValueBy(t);                               //Increase above with current
                                    score+=t.getValue();
                                    t.setValue(0);                                      //Set current to 0
                                }
                            }
                        }
                    }
                }
            }
        }
        setColumn(tiles);
    }

    public static void shiftRight(){
        List<Tile> tiles = new ArrayList<>();
        for (int x = 0; x <= width-1; x++){     //Go over each column
            tiles = getRow(x);               //Find current column
            for (int y = 0; y <=width-1; y++) { //Go over each element width times, so everything shifts
                for (Tile t : tiles) {          //Go over each tile
                    for (Tile n : tiles) {      //Go over each tile (but technically -1)
                        if (t != n) {           //As long as they're not the same tile
                            if (n.getLocation().getY() == t.getLocation().getY() + 1) { //Check that they're -1 Y apart
                                if (n.getValue() == 0 && t.getValue() != 0) {           //Is the above tile 0?
                                    n.replaceValueWith(t);                              //Replace 0 with current tile
                                    t.setValue(0);                                      //Set current tile to 0
                                }
                                if (n.compareValueTo(t)) {                              //Are they the same value?
                                    n.increaseValueBy(t);                               //Increase above with current
                                    score+=t.getValue();
                                    t.setValue(0);                                      //Set current to 0
                                }
                            }
                        }
                    }
                }
            }
        }
        setRow(tiles);
    }

    public static void shiftLeft(){
        List<Tile> tiles = new ArrayList<>();
        for (int x = 0; x <= width-1; x++){     //Go over each column
            tiles = getRow(x);                  //Find current column
            for (int y = 0; y <=width-1; y++) { //Go over each element width times, so everything shifts
                for (Tile t : tiles) {          //Go over each tile
                    for (Tile n : tiles) {      //Go over each tile (but technically +1)
                        if (t != n) {           //As long as they're not the same tile
                            if (n.getLocation().getY() == t.getLocation().getY() - 1) { //Check that they're 1 Y apart
                                if (n.getValue() == 0 && t.getValue() != 0) {           //Is the above tile 0?
                                    n.replaceValueWith(t);                              //Replace 0 with current tile
                                    t.setValue(0);                                      //Set current tile to 0
                                }
                                if (n.compareValueTo(t)) {                              //Are they the same value?
                                    n.increaseValueBy(t);                               //Increase above with current
                                    score+=t.getValue();
                                    t.setValue(0);                                      //Set current to 0
                                }
                            }
                        }
                    }
                }
            }
        }
        setRow(tiles);
    }
}