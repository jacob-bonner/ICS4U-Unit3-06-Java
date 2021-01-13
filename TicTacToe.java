/*
* This program is a tic tac toe game against the computer.
*
* @author  Jacob Bonner
* @version 1.0
* @since   2021-01-11
*/

import java.util.Arrays;  // import the Array class
import java.util.ArrayList;  // import the ArrayList class
import java.util.Scanner;  // Import the Scanner class

public class TicTacToe {
  /**
   * This function prints the current iteration of the game board.
   */
  static void printBoard(char[][] gameList) {
    // Setting parameters for the character array
    int adder = 0;
    int stopper = 0;

    // Printing the current iteration of the board
    for (char[] listCounter : gameList) {
      for (int printCounter : listCounter) {
        char listValue = (char) printCounter + adder
        System.out.print(listValue);
        adder = adder + 1;
        // Checking to see if the counter is on the last element of the array
        if (printCounter != 3) {
          if (adder % 3 == 0) {
            // Printing a seperation of rows
            System.out.println("");
            adder = 0;
            stopper = stopper + 1;
            if (stopper != 3) {
              System.out.println("---------");
            }
          } else {
            // Printing a seperation of columns
            System.out.print(" | ");
          }
        } else {
          continue;
        }
      }
    }
  }


  /**
   * This function finds empty spots on the board and returns them in an array.
   */
  /*
  static ArrayList<String> openIndex(ArrayList<String> indexBoard) {
    // Creating a list to hold the empty indexes
    ArrayList<String> emptyIndex = new ArrayList<String>();
    int addIndex;

    // Finding empty indexes and adding them to the empty array
    for (int indexCounter = 0; indexCounter < indexBoard.size();
         indexCounter++) {
      // Checking if an index is empty or not and adding empty ones to the array
      if (indexBoard.get(indexCounter) != "X" && indexBoard.get(indexCounter) != "O") {
        addIndex = Integer.valueOf(indexBoard.get(indexCounter));
        emptyIndex.add(Integer.toString(addIndex));
      } else {
        continue;
      }
    }

    // Returning the array list
    return emptyIndex;
  }
  */

  /**
   * This function figures out if the array passed in has a winning condition.
   */
  /*
  static Boolean winCondition(ArrayList<String> winBoard, String playerToken) {
    // Adding elements to array to ensure comparisons can be made
    for(int addCounter = 0; addCounter < 9 - winBoard.size(); addCounter++) {
      winBoard.add("0");
    }

    // Checking all possible win conditions
    if ((winBoard.get(0) == playerToken && winBoard.get(1) == playerToken && winBoard.get(2) == playerToken) ||
        (winBoard.get(3) == playerToken && winBoard.get(4) == playerToken && winBoard.get(5) == playerToken) ||
        (winBoard.get(6) == playerToken && winBoard.get(7) == playerToken && winBoard.get(8) == playerToken) ||
        (winBoard.get(0) == playerToken && winBoard.get(3) == playerToken && winBoard.get(6) == playerToken) ||
        (winBoard.get(1) == playerToken && winBoard.get(4) == playerToken && winBoard.get(7) == playerToken) ||
        (winBoard.get(2) == playerToken && winBoard.get(5) == playerToken && winBoard.get(8) == playerToken) ||
        (winBoard.get(0) == playerToken && winBoard.get(4) == playerToken && winBoard.get(8) == playerToken) ||
        (winBoard.get(2) == playerToken && winBoard.get(4) == playerToken && winBoard.get(6) == playerToken)) {
      return true;
    } else {
      return false;
    }
  }
  */

  /**
   * This function controls the computer's moves through using conversion.
   */
  /*
  static int computerMove(ArrayList<String> gameBoard, int length, Boolean maxValue) {
    // Defining user and computer tokens
    String user = "X";
    String cpu = "O";

    // Checking to see if someone has won or tied the game
    int score;
    if (winCondition(gameBoard, user)) {
      score = 10;
      return score;
    } else if (winCondition(gameBoard, cpu)) {
      score = -10;
      return score;
    } else if (9 - length == 0) {
      return 0;
    }

    // Checking to see if the max value still needs calculating
    if (maxValue == true) {
      // Defining a variable that holds the best possible score
      int best = -1000;

      // Iterating through all possible moves
      for (int rowCounter = 0; rowCounter < 3; rowCounter++) {
        for (int columnCounter = 0; columnCounter < 3; columnCounter++) {
          // Checking to see what spaces are open
          if (gameBoard.get(rowCounter + columnCounter) != user && gameBoard.get(rowCounter + columnCounter) != cpu) {
            // Filling the empty spot for now
            gameBoard.set(rowCounter + columnCounter, user);

            // Best possible score for the player
            best = Math.max(best, computerMove(gameBoard, length + 1, !maxValue));

            // Undoing the filled spaces
            gameBoard.set((rowCounter + columnCounter), Integer.toString(rowCounter + columnCounter + 1));
          }
        }
      }
      return best;
    } else {
      // Defining a variable that holds the best possible score
      int best = -1000;

      // Iterating through all possible moves
      for (int rowCounter = 0; rowCounter < 3; rowCounter++) {
        for (int columnCounter = 0; columnCounter < 3; columnCounter++) {
          // Checking to see what spaces are open
          if (gameBoard.get(rowCounter + columnCounter) != user && gameBoard.get(rowCounter + columnCounter) != cpu) {
            // Filling the empty spot for now
            gameBoard.set(rowCounter + columnCounter, cpu);

            // Best possible score for the player
            int moveValue = computerMove(gameBoard, length + 1, !maxValue);
            if(moveValue < best) {
              best = (rowCounter + columnCounter);
            }

            // Undoing the filled spaces
            gameBoard.set((rowCounter + columnCounter), Integer.toString(rowCounter + columnCounter + 1));
          }
        }
      }
      return best;
    }
  }
  */

  /**
   * This function allows the user to play against the computer in a game of
   * tic tac toe.
   */
  public static void main(String[] args) {
    // Creating an array and adding information about the board to it
    char[][] userList = new char[3][3];
    int infoAsNumber = -3;
    char newInfo;
    for (int infoCounter = 0; infoCounter < 3; infoCounter++) {
      infoAsNumber = infoAsNumber + 3;
      for (int newCounter = 0; newCounter < 3; newCounter++) {
        newInfo = (char) (infoAsNumber + 1);
        userList[infoCounter][newCounter] = newInfo;
      }
    }

    // Printing the current iteration of the board
    System.out.println("Welcome to Tic Tac Toe!");
    printBoard(userList);

    // Stating the tokens for each player
    System.out.println("");
    System.out.println("");
    System.out.println("Your token is X, the computer is O");

    // This allows for user input
    System.out.println("");
    Scanner userInput = new Scanner(System.in);

    // Looping through the game
    for (int gameCounter = 0; gameCounter < 9; gameCounter++) {
      System.out.println("");
      System.out.print("Enter a move you would like to make (1 to 9): ");
      int userNumber = userInput.nextInt();

      for (int appendCounter = 0; appendCounter < userList.length; appendCounter++) {
        for (int nextCounter = 0; nextCounter < userList[appendCounter].length; nextCounter++) {
          userList[appendCounter][nextCounter] = 'X';
        }
      }
      System.out.println("");
      printBoard(userList);
    }
  }
}
