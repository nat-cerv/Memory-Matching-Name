# Memory-Matching-Name

1. Program Overview

    The game starts by welcoming the player and guiding them through the rules. Players specify the size of the board, with the program ensuring an even number to support matching pairs. The game offers different modes, including limited and unlimited attempts, to uncover pairs of matching numbers.

2. Core Functionalities
    - Welcome and Instructions: The program begins by greeting the player and explaining the objective: to clear the board by correctly matching pairs of numbers. Players are informed that the difficulty increases with the size of the board.
    - Board Setup: Players are prompted to choose the size of the board, which must be an even number to ensure proper pairing. The board is randomly populated with pairs of numbers using a shuffle algorithm.
    - Main Menu Options: The player is presented with three options:
        - Option 1: Play with a limited number of attempts (based on the number of pairs).
        - Option 2: Play with unlimited attempts.
        - Option 3: Exit the game.
    - Game Play: Players select two cells to reveal their contents. If the numbers in the selected cells match, they remain visible on the board. If they do not match, the cells are reset to a hidden state, represented by question marks.
    - Win/Loss Conditions: Players win if they uncover all pairs within the allowed attempts (or through persistence in the unlimited mode). If they run out of attempts, they lose.
    - Board Display: The board is displayed with indexed cells and hidden values (question marks) to guide players in selecting cells. After each selection, the board updates to show any revealed pairs.

3. Key Features
    - Randomized Pair Placement: The placePairs method ensures that pairs are randomly distributed across the board using an efficient shuffle algorithm.
    - User-Friendly Prompts: The game provides clear instructions, including input validation for board size and cell selection.
    - Multiple Game Modes: Players can choose between a challenging mode with limited attempts or a more relaxed mode with unlimited attempts.
    - Recursive Check for Win Condition: The game uses a recursive function to determine if the board has been fully cleared.

4. Technical Details
- Scanner for User Input: The program uses the Scanner class to gather inputs for board size, menu options, and cell selections.
- Random Class for Shuffle: The Random class ensures the initial placement of pairs is randomized.
- Error Handling and Validation: The program validates user inputs for board size, menu options, and cell selections to prevent crashes or incorrect behavior.
- Recursive and Iterative Methods: The program combines recursive and iterative logic to manage game states, validate conditions, and clear the board.
