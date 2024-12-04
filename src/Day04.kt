data class Pair(val x: Int, val y: Int)

fun countOccurrences(grid: List<String>, word: String): Int {
    val rows = grid.size
    val cols = grid[0].length
    val directions = listOf(
        0 to 1, 1 to 0, 1 to 1, 1 to -1, 0 to -1, -1 to 0, -1 to -1, -1 to 1
    )

    fun isValid(x: Int, y: Int) = x in 0 until rows && y in 0 until cols

    var count = 0
    for (row in 0 until rows) {
        for (col in 0 until cols) {
            for ((dx, dy) in directions) {
                var found = true
                for (k in word.indices) {
                    val newRow = row + dx * k
                    val newCol = col + dy * k
                    if (!isValid(newRow, newCol) || grid[newRow][newCol] != word[k]) {
                        found = false
                        break
                    }
                }
                if (found) count++
            }
        }
    }
    return count
}


fun findNext(input: List<String>, target: Char, pos: Pair, dir: Pair): Pair? {
    val newPos = Pair(pos.x + dir.x, pos.y + dir.y)
    if (newPos.x in input[0].indices && newPos.y in input.indices) {
        return if (input[newPos.y][newPos.x] == target) newPos else null
    }
    return null
}

fun countXMasPatterns(input: List<String>): Int {
    return input.indices.sumOf { y ->
        input[0].indices.count { x ->
            if (input[y][x] == 'A') {
                val v = Pair(x, y)
                val left = (findNext(input, 'M', v, Pair(-1, -1)) != null && findNext(
                    input, 'S', v, Pair(1, 1)
                ) != null) || (findNext(input, 'S', v, Pair(-1, -1)) != null && findNext(
                    input, 'M', v, Pair(1, 1)
                ) != null)
                val right = (findNext(input, 'M', v, Pair(-1, 1)) != null && findNext(
                    input, 'S', v, Pair(1, -1)
                ) != null) || (findNext(input, 'S', v, Pair(-1, 1)) != null && findNext(
                    input, 'M', v, Pair(1, -1)
                ) != null)
                left && right
            } else false
        }
    }
}


fun part1(input: List<String>): Int {
    val word = "XMAS"
    return countOccurrences(input, word)
}

fun part2(input: List<String>): Int {
    return countXMasPatterns(input)
}


fun main() {
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    val input = readInput("Day04")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")

}