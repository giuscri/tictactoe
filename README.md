# TicTacToe (with Java)

This project consists of a simple environment for playing the famous game of *Tic-Tac-Toe*.

It has been designed and written using the object oriented paradigm.

## Design note:

Follows a list of classes written, such that design choices
might be clearer.

+ `Figure.java` represents the figures that can be
drawn in the TicTacToe table.
+ `Circle.java` and `Cross.java` inherit from `Figure`
adding no functionality. They've been written
only for a question of naming objects for what they really
represent. They're made of just few lines indeed.
+ `Pair.java` is a *naif* implementation of a
size 2-tuple in Java.
+ `Coordinate.java` inherit from `Pair<Integer>`.
It will be used for representing, **informally**
speaking, the latitude and longitude of the `Grid`.
+ `Cell.java` is the sigle unit of `Grid` -- that is
a 3x3 matrix of `Cell`'s.
+ `Grid.java` is a 3x3 matrix of `Cell`'s. Few notes
about the choice of using `HashTable` are required:
main reason was that I wanted to use a *`Coordinate` to `Cell`
map*, that I found more object-oriented-like; by the way
I know it might sound a bit *overkill*: I could have
used a double dimensional array of `Cell`'s, hence built a
function for getting `Cell`'s by providing a pair
of `int`'s. I need to choose only one solution and I went
for `HashMap`s.
+ `Player.java` represents the user who's playing
the game against the computer (the *cpu* handled by `Engine`).
+ `Engine.java` is the *core* of the game. It contains
reference to
  + the `Grid` that's used
  + the `Player` who's playing the match.
`Engine` takes care of keeping
update the `Grid`, parsing the input provided from `Player`,
answering to it with a proper move,
and checking `Grid` such that it knows if the match
has a winner.
+ `Main.java` wraps only the `main()` method.

## Sample:

    $ git clone https://github.com/giuscri/tictactoe.git
    $ cd tictactoe
    $ chmod +x go
    $ ./go
    ## Verbose output of javadoc here ...
    ##...
    Engine@df503 goes first.

     O |   |   
    -----------
       |   |   
    -----------
       |   |   


    Type coordinate of cell you want to set (row[1-3] col[1-3]):
    >>> 2,1

     O |   | O 
    -----------
     X |   |   
    -----------
       |   |   


    Type coordinate of cell you want to set (row[1-3] col[1-3]):
    >>> 2,1
    <<< Cell at coordinate (2,1) is already set.
    Type coordinate of cell you want to set (row[1-3] col[1-3]):
    >>> 1,1
    <<< Cell at coordinate (1,1) is already set.
    Type coordinate of cell you want to set (row[1-3] col[1-3]):
    >>> 1,2

     O | X | O 
    -----------
     X |   |   
    -----------
     O |   |   

    Type coordinate of cell you want to set (row[1-3] col[1-3]):
    >>> 3,3

     O | X | O 
    -----------
     X | O |   
    -----------
     O |   | X 


    Engine@df503 has won.


### TODO


+ Improving documentation and writing
*javadoc* features -- be accurate
about design choices (*why you chose
to write a `Cross` class?, why using
a HashMap instead of a double-d array?,
...*)

#### DOTO

+ <del>Improving `play()` such it won't crash
if user provides bad input.</del>
