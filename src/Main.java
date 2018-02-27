import Grid.Map;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        start();
    }

    public static void start(){
        Map.generateMap(4);
        Map.displayMap();
        tick();
    }

    public static void tick(){
        String input = "";
        while (!input.equals("q")){
            input = in();
            handleInput(input);
        }
    }

    public static void handleInput(String input){
        switch(input){
            case "w":
                Map.shiftUp();
                break;

            case "s":
                Map.shiftDown();
                break;

            case "a":
                Map.shiftLeft();
                break;

            case "d":
                Map.shiftRight();
                break;
        }
        Map.displayMap();
    }

    private static String in(){
        return scanner.nextLine();
    }

}
