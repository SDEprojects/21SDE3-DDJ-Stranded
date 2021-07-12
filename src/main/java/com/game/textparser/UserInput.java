package com.game.textparser;


import com.game.items.Item;
import com.game.player.Player;
import com.game.world.gameWorld;
import com.game.world.location;
import jdk.jshell.Snippet;

import java.util.Scanner;

public class UserInput {
    private static String astronautName = "";
    private static String playerControlString = "";
    private static Scanner input = new Scanner(System.in);
    private static String moveDirection = "nowhere";
    private static String itemGrabbed = "nothing";
    private static String useItemGrabbed = "nothing";
    private static String locationToSearch = "here";
    private static String itemDropped = "none";

    public static String setPlayerName(){
        /*Takes user input and returns it so it can be stored as the name in the Player class. If the name is blank,
        the player's name will be assigned as Neil Armstrong */

        System.out.println("Enter your astronaut's name: ");
        astronautName = input.nextLine();

        if(astronautName.isBlank()){
            astronautName = "Neil Armstrong";
        }

        return astronautName;
    }

    public static String[] action(){
        /*Takes user input and it processes what type of action you are trying to take */


        System.out.println("Perform an action: ");
        playerControlString = input.nextLine().toLowerCase();
        String[] inputStringArray = playerControlString.split(" ", 3);
        String[] invalidArray = {"NOT", "VALID"};

        if(inputStringArray.length != 2){
            return invalidArray;
        }

        if(inputStringArray[0].equals("go")){
            inputStringArray[1] = move(inputStringArray);
        } else if (inputStringArray[0].equals("grab") || inputStringArray[0].equals("get")){
            inputStringArray[1] = grabItem(inputStringArray);
        }else if (inputStringArray[0].equals("use")){
            inputStringArray[1] = useItem(inputStringArray);
        } else if (inputStringArray[0].equals("search")) {
            inputStringArray[1] = search(inputStringArray);
        } else if (inputStringArray[0].equals("drop")){
            inputStringArray[1] = dropItem(inputStringArray);
        } else {
            return invalidArray;
        }

        return inputStringArray;
    }

    public static String move(String[] inputStringArrayArg){
        String directionString = inputStringArrayArg[1].toUpperCase();
        if(!directionString.equals("NORTH") && !directionString.equals("SOUTH") && !directionString.equals("EAST") && !directionString.equals("WEST")){
            return moveDirection;
        }

        for(Directions direction: Directions.values()){
            if(direction.equals(Directions.valueOf(directionString))){
                moveDirection = inputStringArrayArg[1];
            }
        }
        return moveDirection;
    }


    public static String grabItem(String[] inputStringArrayArg){
        itemGrabbed = inputStringArrayArg[1];
        return itemGrabbed;
    }

    //Need to update on commands engine
    public static String useItem(String[] inputStringArrayArg){
        String inventoryString = Player.viewInventory().toString();
        if(inventoryString.contains(inputStringArrayArg[1])){
            useItemGrabbed = inputStringArrayArg[1];
        } else {
            useItemGrabbed = "";
        }

        return useItemGrabbed;

    }

    //needs validation?
    public static String search(String[] inputStringArrayArg){
        return locationToSearch;
    }

    //Validation handled by status
    public static String dropItem(String[] inputStringArray) {
        String inventoryString = Player.viewInventory().toString();
        if (inventoryString.contains(inputStringArray[1])) {
            itemDropped = inputStringArray[1];
        } else {
            itemDropped = "";
        }
        return itemDropped;
    }

    public static boolean playAgain(){
        boolean askForReplay = true;
        int userNum = 0;
        while (askForReplay){
            System.out.println("Would you like to play Stranded again?");
            System.out.println("Please enter the number for your choice");
            System.out.println("1) Yes\n2) No");

            try {
                String userString = input.nextLine();
                userNum = Integer.parseInt(userString);

                if(userNum > 0 && userNum < 3){
                    askForReplay = false;
                    break;
                } else {
                    System.out.println("Invalid Choice");
                    System.out.println("====================");
                }

            } catch (Exception e){
                System.out.println("Invalid Choice");
                System.out.println("====================");
            }
        }

        return userNum == 1;


    }


}