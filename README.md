# Tic Tac Toe

## Project concept

An application recreating the class Tic Tac Toe game. A great, quick game for two friends who want to play Tic Tac Toe without a piece of paper. I find this project interesting because I played Tic Tac Toe often in elementary school when I should have been doing my work.

## User stories

- As a user, I want to be able to place an X or an O tile on the board.
- As a user, I want to be able to undo placing a tile.
- As a user, I want to be able to clear the board and start a new game.
- As a user, I want to be able to see the current state of the board.
- As a user, I want to know when the game results in a win or draw.
- As a user, I want to be able to save my progress to a file.
- As a user, I want to be able to load from progress from a file.

## Phase 4: Task 2

```
Fri Apr 01 11:39:32 PDT 2022
Tile X placed at (0, 0)
Fri Apr 01 11:39:32 PDT 2022
Tile O placed at (2, 2)
Fri Apr 01 11:39:33 PDT 2022
Tile X placed at (0, 2)
Fri Apr 01 11:39:37 PDT 2022
Undid tile X from (0, 2)
Fri Apr 01 11:39:39 PDT 2022
Tile X placed at (2, 0)
Fri Apr 01 11:39:40 PDT 2022
Tile O placed at (1, 0)
Fri Apr 01 11:39:40 PDT 2022
Tile X placed at (0, 2)
Fri Apr 01 11:39:41 PDT 2022
Tile O placed at (1, 1)
Fri Apr 01 11:39:42 PDT 2022
Tile X placed at (0, 1)
```

## Phase 4: Task 3

The flow of my UML class diagram is logical for the most part. If I were to refacter a component of my project given more time, I would refactor the Button class so as to not include an association with the TicTacToe class. This would likely be done through the singleton pattern, as my game will never have more than one window and the TicTacToe class represents a window. By removing this association, a reference to the TicTacToe object would no longer need to be passed on to the Button by the Board during construction, and would make the Board class more cohesive since it no longer has to handle that responsibility which is unrelated to its own purpose.
