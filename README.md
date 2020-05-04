### This project was inspired by the idea of [the Game of life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).
-----
## How to Run

Use IDE to run `Launcher.java`

## Game of life Rules

Each cell occupies one spot on the 2D grid and each cell has **two status**: 

* Dead
* Alive

Given conditions (how many neighbors does a cell have), a cell can die or live in the next time unit. 

Rules are the following:

1. Any live cell with two or three live neighbors survives.
2. Any dead cell with three live neighbors becomes a live cell.
3. All other live cells die in the next generation. Similarly, all other dead cells stay dead.
