import java.util.Scanner;
import java.util.Random;

public class memoryMatching 
{
    public static void main(String[] args) 
	{
		
		Scanner scnr = new Scanner(System.in);
		
		//All the variables:
		int gameModality = 0;
		int game = 0;
		
		welcome(); //As it says, it just welcomes the user and explains the game.
		
		//The board length/size
		int[] board = setupBoard(); //This will be the board with the solutions.
		int[] userBoard = new int[board.length]; //This is the player's board, in 0's.
		
		
		int numberPairs = 2; //It already gives you the +2 attempts
		
		//To find the pairs:
		for (int i = 0; i < board.length ; i++)
		{
			for (int j = (i + 1) ; j < board.length ; j++)
			{
				if(board[i] == board[j])
				{
					numberPairs++;
				}
				
			}
			
		}
		
		
		//Let the user choose how many attempts or to exit:
		displayMainMenu(numberPairs);
		
		
		gameModality = scnr.nextInt(); //This is just to choose option 1, 2 or 3 of menu.
		
		
		//The user selected an invalid option:
		while (gameModality <= 0 || 3 < gameModality)
		{
			System.out.println("Invalid input, please choose option 1, 2 or 3.");
			gameModality = scnr.nextInt();
		}
		
		
		//The game starts:
		if (gameModality == 1) //When the user chooses n attempts.
		{
			int attempts = numberPairs;
			
			while (game == 0 && attempts != 0)
			{
				System.out.println("-------");
				System.out.println();
				System.out.println("Number of attempts left: " + attempts);
				
				//displayBoard(userBoard);
				game = runGame(userBoard , board);
				
				attempts--;	
			}
			
			
			
			System.out.println("-------");
			
			if (game == 3)
			{
				System.out.println("You have found a pair!");
			}
			else
			{
				System.out.println("You lost...");
			}
			
			System.out.println();
			System.out.println("Game Over.");
			
		}
		
		else if (gameModality == 2) //When the user chooses unlimited attempts.
		{
			while (game == 0)
			{
				//displayBoard(userBoard);
				game = runGame(userBoard , board);
				
			}
			
			System.out.println("You have found a pair!");
			System.out.println();
			System.out.println("Game Over.");
			
		}
		
		else //Exit.
		{
			System.out.println();
			System.out.println("... Exiting game!");
			
		}
		
		System.out.println();
		System.out.println("See you next time :)");
		
    }

    /**
     * This method will request the user to select two cells in the board to
     * discover the numbers in them
     * 
     * @param playingBoard
     * @param board
     * @return 3 if all the pairs have been found, 0 otherwise
     */
    private static int runGame(int[] playingBoard, int[] board) 
	{
		Scanner scn = new Scanner(System.in);
		int celln1 = 0;
		int celln2 = 0;
		
		displayBoard(playingBoard); //To print the board and play.
		
		
		System.out.println("Enter cell number: ");
		celln1 = scn.nextInt();
		//if the input for the cell is invalid:
		while (isWithinBounds(playingBoard.length, celln1) != true || isCellSelected(playingBoard, celln1))
		{
			System.out.println("Enter cell number: ");
			celln1 = scn.nextInt();
		}
		
		//This will discover the selected cell:
		System.out.println("You have discovered: " + board[celln1]);
		System.out.println("Where is the matching pair? ");
		
		//To replace the 0 to the value of the selected cell:
		clearCell(playingBoard, celln1, board[celln1]);
		
		
		displayBoard(playingBoard); //To print the board again.
		
		
		System.out.println("Enter cell number: ");
		celln2 = scn.nextInt();
		//if the input for the cell is invalid:
		while (isWithinBounds(playingBoard.length, celln2) != true || isCellSelected(playingBoard, celln2))
		{
			System.out.println("Enter cell number: ");
			celln2 = scn.nextInt();
		}
		
		//This will discover the selected cell:
		System.out.println("You have discovered: " + board[celln2]);
		
		//To replace the 0 to the value of the selected cell:
		clearCell(playingBoard, celln2, board[celln2]);
		
		
		displayBoard(playingBoard); //To print the board once again.
		
		
		//This is to make the board again into 0's if no pair was found:
		if (board[celln1] != board[celln2])
		{
			playingBoard[celln1] = 0;
			//and
			playingBoard[celln2] = 0;
		}
        
		
		//This is to see if the board is cleared or not:
		if (isBoardCleared(playingBoard, 0))
		{
			return 3;
		}
		else
		{
			return 0;
		}
		
    }


	/**
	* I added this method.
	* This is just to welcome the user :)
	*/
	private static void welcome()
	{
		
		System.out.println();
		System.out.println("Welcome player! :D");
		System.out.println();
		System.out.println("We'll be playing a number-matching game.");
		System.out.println("First, we'll ask how long you'd like your board to be. (The longer, the harder)");
		System.out.println("Then, you'll select the position of the numbers you think match.");
		System.out.println("You win if the board is cleared, and you lose otherwise. Easy to understand, right? ;)");
		System.out.println();
		System.out.println("Let's start!");
		System.out.println();
		System.out.println("-------");
		System.out.println();
		
	}


    /**
     * Checks if a selected cell is within the bounds of the board
     * 
     * @param boardLength
     * @param cell
     * @return true if the index of the cell is within the bounds of the board
     */
    private static boolean isWithinBounds(int boardLength, int cell) 
	{
        if (cell >= boardLength || cell < 0)
		{
			return false;
		}
		
		return true;
    }


    /**
     * This method will ask the user for the size of the board, check that the size
     * is even and request the method "place pairs" to randomly place pairs of numbers
     * 
     * @return An integer array representing a 1D board
     */
    private static int[] setupBoard() 
	{
		Scanner scnr = new Scanner(System.in);
		
		//All the variables:
		int boardLength = 0;
		
		
		//The progam asks size of board:
		System.out.println("Enter the size of the board (enter an even number):");
		boardLength = scnr.nextInt();
        
		
		//Invalid size. Ask again:
		while (boardLength  <= 0 || (boardLength % 2) != 0)
		{
			System.out.println("-------");
			System.out.println("Invalid input...");
			System.out.println("Enter the size of the board (enter an even number):");
			boardLength = scnr.nextInt();
		}
		
		
		int[] theBoard = new int[boardLength]; //The array for the board is initialized
		placePairs(theBoard);
		
		
        return theBoard; 
    }


    /**
     * This method will display the main menu
     * @param attempts
     */
    private static void displayMainMenu(int attempts) 
	{
		//Let the user choose how many attempts or to exit:
		System.out.println("-------");
		System.out.println("Do you wish to play for (enter number 1, 2, or 3):");
		System.out.println("1. " + attempts + " attempts");
		System.out.println("2. Unlimited number of attempts");
		System.out.println("3. Exit game");
    }


    /**
     * 
     * This method will display the board. Question marks are pairs to be found
     *   0   1   2   3   4   5
     * _________________________
     * | 2 | 3 | ? | 2 | 3 | ? |
     * 
     * @param board The board to be shown. Question marks are pairs to be found
     * @return nothing
     */
    private static void displayBoard(int[] board) 
	{
        System.out.println();
		
		//This is to print the index of the board:
		for (int i = 0; i < board.length ; i++)
		{
			System.out.print("  " + i + " ");
		}
		System.out.println();
		
		//This is just put the long line part:
		for (int i = 0; i < board.length ; i++)
		{
			System.out.print("____");
		}
		System.out.print("_"); //I wanted a _ at the end, ignore this.
		System.out.println();
		
		//To print the playing board:
		for (int i = 0; i < board.length ; i++)
		{
			//This is to not let the user see the values:
			if (board[i] == 0)
			{
				System.out.print("| ? ");
			}
			else
			{
				System.out.print("| " + board[i] + " ");
			}
			
		}
		System.out.print("|"); //I wanted a | at the end, ignore this.
		System.out.println();
		System.out.println();
    }


    /**
     * This method will return true if the cell to be selected has already been
     * cleared
     * @param board
     * @param cell
     * @return
     */
    private static boolean isCellSelected(int[] board, int cell) 
	{
        if (board[cell] != 0)
		{
			return true;
		}
			
		return false;
    }


    /**
     * This method will set a value into a specific cell
     * @param board
     * @param cell
     * @param value
     */
    private static void clearCell(int[] board, int cell, int value) 
	{
        board[cell] = value;
    }


    /**
     * This method will return true if the board has been cleared thus the end
     * of the game. False otherwise.
     * @param board
     * @return
     */
    private static boolean isBoardCleared(int[] board, int index) //I made this one recursive
	{
        if (index == board.length) 
		{
			return true;
			
		} 
		else if (board[index] == 0) 
		{
			return false;
			
		} 
		else 
		{
			return isBoardCleared(board, index+1);
			
		}
    }


    /**
     * This method will randomly place pairs of numbers. It is guaranteed that the pairs of numbers will be greater than zero
     * DO NOT MODIFY
     * @param board
     */
    private static void placePairs(int[] board) 
	{

        Random rand = new Random();

        // place the values in the array in order

        for (int i = 0; i < board.length; i++)
            board[i] = i / 2 + 1;
        // perfect array shuffle
        // swap the ith value with a random value from index i to end of array
        for (int i = 0; i < board.length - 1; i++) {
            int j = rand.nextInt(board.length - i);
            int temp = board[i];
            board[i] = board[j];
            board[j] = temp;
        }

    }
}

