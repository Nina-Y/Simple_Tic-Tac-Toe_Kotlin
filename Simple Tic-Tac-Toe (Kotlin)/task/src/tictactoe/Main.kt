package tictactoe

var xCounts = 0
var oCounts = 0
var xWins = 0
var oWins = 0
var isPlayerX = true   

fun main() {
    var board = mutableListOf(
        mutableListOf<Char>(' ', ' ', ' '),   
        mutableListOf<Char>(' ', ' ', ' '),  
        mutableListOf<Char>(' ', ' ', ' ')     
    )  
    printBoard(board) 
    checkCoords(board)  
}

fun printBoard(board: List<List<Char>>) {
    println("""---------
| ${board[0][0]} ${board[0][1]} ${board[0][2]} |
| ${board[1][0]} ${board[1][1]} ${board[1][2]} |
| ${board[2][0]} ${board[2][1]} ${board[2][2]} |
---------""")
}

fun checkCoords(board: MutableList<MutableList<Char>>) {
    while (true) {
        val coords = readln().split(" ")
        val c1 = coords[0].toString().toInt() - 1
        val c2 = coords[1].toString().toInt() - 1
        if (c1 < 0 || c1 > 2 || c2 < 0 || c2 > 2) {
            println("Coordinates should be from 1 to 3!")
        } else if (board[c1][c2] != ' ') {
            println("This cell is occupied! Choose another one!")
        } else {
            when {
                isPlayerX -> {
                    board[c1][c2] = 'X'
                    xCounts++
                    isPlayerX = false
                }
                else -> {
                    board[c1][c2] = 'O'
                    oCounts++
                    isPlayerX = true
                }
            }           
        printBoard(board)
        checkRows(board)
        checkColumns(board)
        checkDiagonals(board)
        checkGameState(board)       
        }   
    }
}

fun checkRows(board: List<List<Char>>) {
     for (i in 0 until 3) {
        if ("${board[i][0]}${board[i][1]}${board[i][2]}" == "XXX") {
            xWins++
        } else if ("${board[i][0]}${board[i][1]}${board[i][2]}" == "OOO") {
            oWins++
        }
    }
}     

fun checkColumns(board: List<List<Char>>) {
    for (j in 0..2) {
        if ("${board[0][j]}${board[1][j]}${board[2][j]}" == "XXX") {
            xWins++
        } else if ("${board[0][j]}${board[1][j]}${board[2][j]}" == "OOO") {
            oWins++
        }
    }
}    
        
fun checkDiagonals(board: List<List<Char>>) {
    if ("${board[0][0]}${board[1][1]}${board[2][2]}" == "XXX" || 
        "${board[0][2]}${board[1][1]}${board[2][0]}" == "XXX") {
        xWins++
    } else if ("${board[0][0]}${board[1][1]}${board[2][2]}" == "OOO" || 
        "${board[0][2]}${board[1][1]}${board[2][0]}" == "OOO") {
        oWins++
    }
}

fun checkGameState(board: List<List<Char>>) {
    if (xWins == 1 && oWins == 0) {
        println("X wins")
        System.exit(0)
    } else if (oWins == 1 && xWins == 0) {
        println("O wins")
        System.exit(0)        
    } else if (xWins + oWins == 0 && !board[0].contains(' ') && !board[1].contains(' ') 
               && !board[2].contains(' ')) {
        println("Draw")
        System.exit(0)     
    } 
}   
