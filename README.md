# TicTacToe (with Java)

This project consists of a simple environment for playing the famous game of *Tic-Tac-Toe*.

It has been designed and written using the object oriented paradigm.

**Sample**:

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
*javadoc* features.

#### DOTO

+ <del>Improving `play()` such it won't crash
if user provides bad input.</del>
