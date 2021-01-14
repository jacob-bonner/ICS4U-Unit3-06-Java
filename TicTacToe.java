/*
* This program is a tic tac toe game against the computer.
*
* @author  Jacob Bonner
* @version 1.0
* @since   2021-01-11
*/

import java.util.Arrays;  // import the Array class
import java.util.Scanner;  // Import the Scanner class

public class TicTacToe {
  /**
   * This function prints the current iteration of the game board.
   */
  static void printBoard(char[][] gameList) {
    // Setting parameters for the character array
    int stopper = 0;
    int adder = 0;

    // Printing the current iteration of the board
    for (char[] listCounter : gameList) {
      for (int printCounter : listCounter) {
        char listValue = (char) (printCounter);
        System.out.print(listValue);
        adder += 1;

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
   * This function figures out if the array passed in has a winning condition.
   */
  static int winCondition(char[][] winBoard, char playerToken, char cpuToken) {
    // Checking for horizontal wins
    for (int horizontal = 0; horizontal < 3; horizontal++) {
      if (winBoard[horizontal][0] == winBoard[horizontal][1] 
          && winBoard[horizontal][0] == winBoard[horizontal][2]
          && winBoard[horizontal][1] == winBoard[horizontal][2]) {
        if (winBoard[horizontal][0] == playerToken) {
          return -10;
        } else if (winBoard[horizontal][0] == cpuToken) {
          return 10;
        } else {
          continue;
        }
      }
    }

    // Checking for vertical wins
    for (int vertical = 0; vertical < 3; vertical++) {
      if (winBoard[0][vertical] == winBoard[1][vertical] 
          && winBoard[0][vertical] == winBoard[2][vertical]
          && winBoard[1][vertical] == winBoard[2][vertical]) {
        if (winBoard[0][vertical] == playerToken) {
          return -10;
        } else if (winBoard[0][vertical] == cpuToken) {
          return 10;
        } else {
          continue;
        }
      }
    }

    // Checking for diagonal wins
    if (winBoard[0][0] == winBoard[1][1] && winBoard[0][0] == winBoard[2][2] 
        && winBoard[1][1] == winBoard[2][2]) {
      if (winBoard[0][0] == playerToken) {
        return -10;
      } else if (winBoard[0][0] == cpuToken) {
        return 10;
      }
    } else if (winBoard[0][2] == winBoard[1][1] 
               && winBoard[0][2] == winBoard[2][0] 
               && winBoard[1][1] == winBoard[2][0]) {
      if (winBoard[2][0] == playerToken) {
        return -10;
      } else if (winBoard[2][0] == cpuToken) {
        return 10;
      }
    }

    // Returning 0 if no winner has been determined yet
    return 0;
  }

  /**
   * This function finds if there are any open spaces left on the board.
   */
  static Boolean openSpaces(char[][] tieBoard, char tokenX, char tokenO) {
    // Looping through all indexes to find if there are empty spaces
    for (int drawCounter = 0; drawCounter < tieBoard.length; drawCounter++) {
      for (int tieCounter = 0; tieCounter < tieBoard.length; tieCounter++) {
        if (tieBoard[drawCounter][tieCounter] != tokenX 
            && tieBoard[drawCounter][tieCounter] != tokenO) {
          return true;
        }
      }
    }

    // Returning false if there are still empty spaces
    return false;
  }

  /**
   * This function finds the best possible score or outcome of a move through
   * recursion for the computer.
   */
  static int moveScore(char[][] gameBoard, int length, Boolean maxValue) {
    // Defining user and computer tokens
    char user = 'X';
    char cpu = 'O';

    // Checking to see if someone has won or tied the game
    int score = winCondition(gameBoard, user, cpu);
    if (score == 10) {
      return score;
    } else if (score == -10) {
      return score;
    } else if (openSpaces(gameBoard, user, cpu) == false) {
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
          if (gameBoard[rowCounter][columnCounter] != user 
              && gameBoard[rowCounter][columnCounter] != cpu) {
            // Filling the empty spot for now
            char tempValue = gameBoard[rowCounter][columnCounter];
            gameBoard[rowCounter][columnCounter] = cpu;

            // Best possible score for the player
            best = Math.max(best, moveScore(gameBoard, length + 1, !maxValue));

            // Undoing the filled spaces
            gameBoard[rowCounter][columnCounter] = tempValue;
          }
        }
      }
      return best;
    } else {
      // Defining a variable that holds the best possible score
      int best = 1000;

      // Iterating through all possible moves
      for (int rowCounter = 0; rowCounter < 3; rowCounter++) {
        for (int columnCounter = 0; columnCounter < 3; columnCounter++) {
          // Checking to see what spaces are open
          if (gameBoard[rowCounter][columnCounter] != user 
              && gameBoard[rowCounter][columnCounter] != cpu) {
            // Filling the empty spot for now
            char tempValue = gameBoard[rowCounter][columnCounter];
            gameBoard[rowCounter][columnCounter] = user;

            // Best possible score for the player
            best = Math.min(best, moveScore(gameBoard, length + 1, !maxValue));

            // Undoing the filled spaces
            gameBoard[rowCounter][columnCounter] = tempValue;
          }
        }
      }
      return best;
    }
  }

  /**
   * This function controls the computer's moves.
   */
  static int computerMove(char[][] moveBoard) {
    // Initializing some base coordinates to be used in determining the move
    int moveRow = -1;
    int moveColumn = -1;

    // Setting up the user and player tokens
    char userSymbol = 'X';
    char cpuSymbol = 'O';

    // Setting up a variable to keep track of the move's score
    int bestScore = -1000;

    // Iterating through the board to find the best move
    for (int bestRow = 0; bestRow < 3; bestRow++) {
      for (int bestColumn = 0; bestColumn < 3; bestColumn++) {
        if (moveBoard[bestRow][bestColumn] != userSymbol 
            && moveBoard[bestRow][bestColumn] != cpuSymbol) {
          // Temporarily filling the empty space
          char placeholdValue = moveBoard[bestRow][bestColumn];
          moveBoard[bestRow][bestColumn] = cpuSymbol;

          // Calculating the score of the computer's move
          int moveTotal = moveScore(moveBoard, 0, false);

          // Removing the placeholder
          moveBoard[bestRow][bestColumn] = placeholdValue;

          // Finding if the current or previously tested score is higher
          if (moveTotal > bestScore) {
            moveRow = bestRow;
            moveColumn = bestColumn;
            bestScore = moveTotal;
          }
        }
      }
    }

    // Finding the optimal move based on the score and returning it
    if (moveRow == 0 && moveColumn == 0) {
      return 1;
    } else if (moveRow == 0 && moveColumn == 1) {
      return 2;
    } else if (moveRow == 0 && moveColumn == 2) {
      return 3;
    } else if (moveRow == 1 && moveColumn == 0) {
      return 4;
    } else if (moveRow == 1 && moveColumn == 1) {
      return 5;
    } else if (moveRow == 1 && moveColumn == 2) {
      return 6;
    } else if (moveRow == 2 && moveColumn == 0) {
      return 7;
    } else if (moveRow == 2 && moveColumn == 1) {
      return 8;
    } else if (moveRow == 2 && moveColumn == 2) {
      return 9;
    } else {
      throw null;
    }
  }

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
        newInfo = (char) (49 + infoAsNumber + newCounter);
        userList[infoCounter][newCounter] = newInfo;
      }
    }

    // Creating an array to hold used spots
    int[] usedSpots = new int[9];

    // Printing the current iteration of the board
    System.out.println("Welcome to Tic Tac Toe!");
    printBoard(userList);

    // Stating the tokens for each player
    System.out.println("");
    System.out.println("Your token is X, the computer is O");

    // This allows for user input
    Scanner userInput = new Scanner(System.in);

    try {
      // Setting up the game counter
      int gameCounter = 0;

      // Looping through the game
      while (gameCounter < 9) {
        // Getting input for the user's move
        System.out.println("");
        System.out.print("Enter a move you would like to make (1 to 9): ");
        int userNumber = userInput.nextInt();
      
        // Ensuring the user played a number that has not been used
        for (int usedCounter = 0; usedCounter < usedSpots.length - 1;
             usedCounter++) {
          if (userNumber > 9 || userNumber < 1) {
            throw null;
          } else if (usedSpots[usedCounter] == userNumber) {
            throw null;
          } else {
            continue;
          }
        }

        // Marking the user's move as filling a spot
        usedSpots[gameCounter] = userNumber;

        // This switch statement determines which spot the player put an X
        switch (userNumber) {
          // Changing first spot to X
          case 1:
            userList[0][0] = 'X';
            break;

          // Changing second spot to X
          case 2:
            userList[0][1] = 'X';
            break;

          // Changing third spot to X
          case 3:
            userList[0][2] = 'X';
            break;

          // Changing fourth spot to X
          case 4:
            userList[1][0] = 'X';
            break;

          // Changing fifth spot to X
          case 5:
            userList[1][1] = 'X';
            break;

          // Changing sixth spot to X
          case 6:
            userList[1][2] = 'X';
            break;

          // Changing seventh spot to X
          case 7:
            userList[2][0] = 'X';
            break;

          // Changing eigth spot to X
          case 8:
            userList[2][1] = 'X';
            break;

          // Changing ninth spot to X
          case 9:
            userList[2][2] = 'X';
            break;

          // If the switch case reaches default it throws an error
          default:
            throw null;
        }

        // Printing the board now that the user has gone
        System.out.println("");
        printBoard(userList);

        // Checking to see if a win occured
        if (winCondition(userList, 'X', 'O') == -10) {
          System.out.println("");
          System.out.println("You Win!");
          break;
        } else if (winCondition(userList, 'X', 'O') == 10) {
          System.out.println("");
          System.out.println("The Computer Wins!");
          break;
        }

        // Increasing game counter and checking to see if the board is full
        gameCounter += 1;
        if (gameCounter == 9) {
          System.out.println("");
          System.out.println("Tied Game");
          break;
        }

        // Determining where the computer will put its token
        System.out.println("");
        int computerChoice = computerMove(userList);
        System.out.println("Computer's Move: " + computerChoice);
        usedSpots[gameCounter] = computerChoice;

        // This switch statement determines which spot the player put an X
        switch (computerChoice) {
          // Changing first spot to O
          case 1:
            userList[0][0] = 'O';
            break;

          // Changing second spot to O
          case 2:
            userList[0][1] = 'O';
            break;

          // Changing third spot to O
          case 3:
            userList[0][2] = 'O';
            break;

          // Changing fourth spot to O
          case 4:
            userList[1][0] = 'O';
            break;

          // Changing fifth spot to O
          case 5:
            userList[1][1] = 'O';
            break;

          // Changing sixth spot to O
          case 6:
            userList[1][2] = 'O';
            break;

          // Changing seventh spot to O
          case 7:
            userList[2][0] = 'O';
            break;

          // Changing eigth spot to O
          case 8:
            userList[2][1] = 'O';
            break;

          // Changing ninth spot to O
          case 9:
            userList[2][2] = 'O';
            break;

          // If the switch case reaches default it throws an error
          default:
            throw null;
        }

        // Printing the board
        printBoard(userList);

        // Increasing the game counter
        gameCounter += 1;

        // Checking to see if a win occured
        if (winCondition(userList, 'X', 'O') == -10) {
          System.out.println("");
          System.out.println("You Win!");
          break;
        } else if (winCondition(userList, 'X', 'O') == 10) {
          System.out.println("");
          System.out.println("The Computer Wins!");
          break;
        }
      }

      // Catches and tells the user what error occurred
    } catch (NullPointerException e) {
      System.out.println("");
      System.out.println("ERROR: Invalid Input");
    } catch (Exception e) {
      System.out.println("");
      System.out.println("ERROR: Invalid Input");
    }
  }
}
