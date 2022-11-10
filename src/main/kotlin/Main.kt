import java.util.*

const val N = 11

fun main() {
    val maze = Array<IntArray>(N) { IntArray(N) { 1 } }

    makeMaze(maze)

    printMaze(maze)
}

fun makeMaze(maze: Array<IntArray>) {
    val frontiers = LinkedList<Pair<Cell, Cell>>()
    val start = Cell(1, 1)
    frontiers.add(Pair(start, start))

    while (frontiers.isNotEmpty()) {
        val f = frontiers.random()
        frontiers.remove(f)

        val r = f.second.r
        val c = f.second.c

        val previousR = f.first.r
        val previousC = f.first.c

        val betweenR = (r + previousR) / 2
        val betweenC = (c + previousC) / 2

        if (maze[r][c] == 1) {
            maze[r][c] = 0
            maze[betweenR][betweenC] = 0

            if (r >= 2 && maze[r - 2][c] == 1) {
                frontiers.add(Pair( Cell(r, c), Cell(r - 2, c) ))
            }
            if (c >= 2 && maze[r][c - 2] == 1) {
                frontiers.add(Pair( Cell(r, c), Cell(r, c - 2) ))
            }
            if (r < N - 2 && maze[r + 2][c] == 1) {
                frontiers.add(Pair( Cell(r, c), Cell(r + 2, c) ))
            }
            if (c < N - 2 && maze[r][c + 2] == 1) {
                frontiers.add(Pair( Cell(r, c), Cell(r, c + 2) ))
            }
        }
    }
}

fun printMaze(maze: Array<IntArray>) {
    for (r in maze) {
        for (c in r) {
            print(if (c == 1) '■' else '□')
        }
        println()
    }
}

data class Cell(val r: Int, val c: Int)