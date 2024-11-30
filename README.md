# adi_kann_guessing_game
## Project Problem Statement

The project is to develop a Guess the Number Game, a simple, interactive, and fun console-based application. The goal is to create an engaging user experience where players try to guess a randomly generated number between 1 and 100, receiving feedback and hints to improve their guesses

### Given objectives:
  - [x] Create a class named Console and move all the methods that retrieve and validate user input to that class. These methods can remain static. 
  - [x] Create a class named Game and move all the methods that display messages and handle user guesses to that class. Adjust these methods so they aren’t static and use instance variables of the Game class to keep track of numbers, guesses, and so on.
  - [x] Update the application to use these classes and their methods. Make sure the application functions the same as it did before.
### Given guidelines:
  - [x] You should think of the user interfaces that are shown for the projects as starting points. If you can improve on them, especially to make them more user-friendly, by all means do so.
  - [x] You should think of the specifications that are given for the projects as starting points. If you have the time to enhance the applications by improving on the starting specifications, by all means do so.
### Implemented improvements:
  - Structure: code refactored to be modular and easier to maintain, with a clear separation between user input and game logic.
  - Performance: getIntWithinRange() method refactored to reduce redundant calls to getInt()
  - Added a feature to allow users to type "exit" to quit at any time, avoiding an infinite loop in case of repeated errors
  - Implementation of hints after each incorrect guess, such as whether the number is even/odd or a multiple of 5.
  - A detailed and colored summary of the game is displayed at the end of each round
  - Input validation, handling invalid data properly.
  
**The project aims to learn basic programming concepts: it helps practice and demonstrate core programming skills such as loops, conditionals and user input validation.**

## Pseudocode

### GuessNumberApp.java

```
CLASS GuessNumberApp
    METHOD main()
        DECLARE scanner AS Scanner
        DECLARE choice AS STRING
        SET choice TO "y"

        WHILE choice IS EQUAL TO "y"
            DECLARE game AS Game
            CALL game.displayWelcomeMessage()
            CALL game.displayPleaseGuessMessage()
            CALL game.playGame(scanner)
            CALL game.displayGameSummary()
            SET choice TO Console.getChoiceString(scanner, "Try again? (y/n): ", "y", "n")
        END WHILE

        PRINT "Bye - Come back soon!"
    END METHOD
END CLASS
```

### Game.java

```
CLASS Game
    METHOD CONSTRUCTOR()
        SET number TO RANDOM(1 TO 100)
        SET guessNumber TO 0
        SET counter TO 1
        SET providedHints TO EMPTY SET
    END METHOD

    METHOD displayWelcomeMessage()
        PRINT "----------------------------------------"
        PRINT "Welcome to the Guess the Number Game"
        PRINT "++++++++++++++++++++++++++++++++++++"
        PRINT "You can type 'exit' at any time to quit."
    END METHOD

    METHOD getRandomNumber()
        RETURN RANDOM(1 TO 100)
    END METHOD

    METHOD displayPleaseGuessMessage()
        PRINT "I'm thinking of a number from 1 to 100."
        PRINT "Try to guess it."
    END METHOD

    METHOD displayCorrectGuessMessage()
        PRINT "You got it in " + counter + " tries."
        IF counter <= 3 THEN
            PRINT "Great work! You are a mathematical wizard."
        ELSE IF counter <= 7 THEN
            PRINT "Not too bad! You've got some potential."
        ELSE
            PRINT "What took you so long? Maybe you should take some lessons."
        END IF
    END METHOD

    METHOD displayGuessAgainMessage()
        DECLARE difference AS INTEGER
        SET difference TO guessNumber - number

        IF guessNumber > number THEN
            IF difference > 10 THEN
                PRINT "Way too high! Guess again."
            ELSE
                PRINT "Too high! Guess again."
            END IF
        ELSE
            IF difference < -10 THEN
                PRINT "Way too low! Guess again."
            ELSE
                PRINT "Too low! Guess again."
            END IF
        END IF

        CALL provideHint()
    END METHOD

    METHOD provideHint()
        IF number IS BETWEEN 10 AND 99 AND "two-digit number" IS NOT IN providedHints THEN
            PRINT "Hint: The correct number is a two-digit number."
            ADD "two-digit number" TO providedHints
        ELSE IF number MOD 2 IS 0 AND "even" IS NOT IN providedHints THEN
            PRINT "Hint: The correct number is even."
            ADD "even" TO providedHints
        ELSE IF number MOD 2 IS NOT 0 AND "odd" IS NOT IN providedHints THEN
            PRINT "Hint: The correct number is odd."
            ADD "odd" TO providedHints
        ELSE IF number MOD 5 IS 0 AND "multiple of 5" IS NOT IN providedHints THEN
            PRINT "Hint: The number is a multiple of 5."
            ADD "multiple of 5" TO providedHints
        END IF
    END METHOD

    METHOD playGame(scanner)
        WHILE guessNumber IS NOT EQUAL TO number
            SET guessNumber TO Console.getIntWithinRange(scanner, "Enter number: ", 0, 101)
            IF guessNumber IS EQUAL TO number THEN
                CALL displayCorrectGuessMessage()
            ELSE
                CALL displayGuessAgainMessage()
            END IF
            INCREMENT counter
        END WHILE
    END METHOD

    METHOD displayGameSummary()
        PRINT "----------------------------------------"
        PRINT "Game Summary"
        PRINT "The correct number was: " + number
        PRINT "Number of attempts: " + counter
        IF counter <= 3 THEN
            PRINT "Excellent job! You guessed it very quickly."
        ELSE IF counter <= 7 THEN
            PRINT "Good job! You did well."
        ELSE
            PRINT "You eventually got it! Keep practicing to improve."
        END IF
    END METHOD
END CLASS
```

### Console.java

```
CLASS Console
    METHOD getIntWithinRange(scanner, prompt, min, max)
        DECLARE isValid AS BOOLEAN
        SET isValid TO FALSE
        DECLARE i AS INTEGER

        WHILE NOT isValid
            TRY
                PRINT prompt
                SET input TO scanner.nextLine().trim()
                IF input IS EQUAL TO "exit" THEN
                    PRINT "You chose to exit. Goodbye!"
                    EXIT PROGRAM
                END IF

                SET i TO PARSE input AS INTEGER
                IF i <= min THEN
                    PRINT "Error! Number must be greater than " + min
                ELSE IF i >= max THEN
                    PRINT "Error! Number must be less than " + max
                ELSE
                    SET isValid TO TRUE
                END IF
            CATCH Exception
                PRINT "Error! Invalid integer value. Try again."
            END TRY
        END WHILE

        RETURN i
    END METHOD

    METHOD getRequiredString(scanner, prompt)
        DECLARE isValid AS BOOLEAN
        SET isValid TO FALSE
        DECLARE s AS STRING

        WHILE NOT isValid
            PRINT prompt
            SET s TO scanner.nextLine().trim()
            IF s IS EMPTY THEN
                PRINT "Error! This entry is required. Try again."
            ELSE
                SET isValid TO TRUE
            END IF
        END WHILE

        RETURN s
    END METHOD

    METHOD getChoiceString(scanner, prompt, s1, s2)
        DECLARE isValid AS BOOLEAN
        SET isValid TO FALSE
        DECLARE s AS STRING

        WHILE NOT isValid
            SET s TO getRequiredString(scanner, prompt)
            IF s IS NOT EQUAL TO s1 AND s IS NOT EQUAL TO s2 THEN
                PRINT "Error! Entry must be '" + s1 + "' or '" + s2 + "'. Try again."
            ELSE
                SET isValid TO TRUE
            END IF
        END WHILE

        RETURN s
    END METHOD
END CLASS
```

## Flowchart

![flowchart](https://github.com/user-attachments/assets/e5fd92ba-c549-4bf2-9981-e8524402466e)


## How to run

### Option 1:
``` java -jar adi_kann_guessing_game.jar ```

A JAR file is available on [GitHub](https://github.com/xeofus/adi_kann_guessing_game/releases/download/main/adi_kann_guessing_game.jar)

Compiled using Java OpenJDK 1.8.0_432

Linux (WSL)
```
openjdk version "1.8.0_432"
OpenJDK Runtime Environment (build 1.8.0_432-8u432-ga~us1-0ubuntu2~22.04-ga)
OpenJDK 64-Bit Server VM (build 25.432-bga, mixed mode)
```

![image](https://github.com/user-attachments/assets/a8e04744-be14-4556-aff7-2654af86032d)

Windows:
```
openjdk version "1.8.0_432-432"
OpenJDK Runtime Environment (build 1.8.0_432-432-b06)
OpenJDK 64-Bit Server VM (build 25.432-b06, mixed mode)
```

![image](https://github.com/user-attachments/assets/c0123c3a-fe9d-440e-822b-099e58ac1aa5)

### Option 2:

Download [ZIP](https://github.com/xeofus/adi_kann_guessing_game/archive/refs/heads/main.zip) or clone using the [GitHub web URL](https://github.com/xeofus/adi_kann_guessing_game.git)
Import as a project and run main method of GuessNumberApp.java:

Game flow:

![image](https://github.com/user-attachments/assets/5d779764-30ff-4bb8-8443-277a1127db9e)


Input validation:

![image](https://github.com/user-attachments/assets/6b4740b4-36e5-4436-bc26-880a58400040)




