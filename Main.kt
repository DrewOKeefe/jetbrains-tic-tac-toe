package tictactoe

var board = mutableListOf(
    mutableListOf(' ', ' ', ' '),
    mutableListOf(' ', ' ', ' '),
    mutableListOf(' ', ' ', ' '))

fun boardPrint() {
    println(
        """
        ---------
        | ${board[0].joinToString(" ")} |
        | ${board[1].joinToString(" ")} |
        | ${board[2].joinToString(" ")} |
        ---------
        """.trimIndent()
    )
}
var count = 0
var coordinate = listOf<Int>()
var holder = 'X'
fun playerInput() {
    try {
        val range = 1..3
        coordinate = (readln().split(" ").map { it.toInt() })
        if (coordinate[0] !in range || coordinate[1] !in range) {
            println("Coordinates should be from 1 to 3!")
            playerInput()
        } else if (board[coordinate[0] - 1][coordinate[1] - 1] != ' ') {
            println("This cell is occupied! Choose another one!")
            playerInput()
        } else {
            board[coordinate[0] - 1][coordinate[1] - 1] = holder
            count++
            boardPrint()
            checkForWin()
            return
        }
    } catch (ex: NumberFormatException)
    {
        println("You should enter numbers!")
        playerInput()
    }
}

fun checkForWin() {
    val row1 = mutableListOf(board[0][0], board[0][1], board[0][2])
    val row2 = mutableListOf(board[1][0], board[1][1], board[1][2])
    val row3 = mutableListOf(board[2][0], board[2][1], board[2][2])
    val col1 = mutableListOf(board[0][0], board[1][0], board[2][0])
    val col2 = mutableListOf(board[0][1], board[1][1], board[2][1])
    val col3 = mutableListOf(board[0][2], board[1][2], board[2][2])
    val diag1 = mutableListOf(board[0][0], board[1][1], board[2][2])
    val diag2 = mutableListOf(board[0][2], board[1][1], board[2][0])
    val wins: MutableList<MutableList<Char>> = mutableListOf(row1, row2, row3, col1, col2, col3, diag1, diag2)
    when {
            listOf(holder, holder, holder) in wins -> {
                println("$holder wins")
                return
            }
            count == 9 -> {
                println("Draw")
                return
            }
            else -> {
                holder = if (holder == 'X') 'O' else 'X'
                playerInput()
            }
        }
    }
 
fun main() {
    boardPrint()
    playerInput()
}