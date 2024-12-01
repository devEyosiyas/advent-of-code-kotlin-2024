import kotlin.math.abs

fun main() {
    fun sort(input: List<String>): Pair<List<Int>, List<Int>> {
        val pairs = input.mapNotNull {
            it.split("   ").takeIf { parts -> parts.size == 2 }?.let { (left, right) -> left.toInt() to right.toInt() }
        }

        return pairs.unzip().let { (left, right) ->
            left.sorted() to right.sorted()
        }
    }

    fun part1(input: List<String>): Int {
        val (sortedLeft, sortedRight) = sort(input)
        val totalDistance = sortedLeft.indices.sumOf { i -> abs(sortedLeft[i] - sortedRight[i]) }

        return totalDistance
    }

    fun part2(input: List<String>): Int {
        val (sortedLeft, sortedRight) = sort(input)
        val rightFrequency = sortedRight.groupingBy { it }.eachCount()
        val similarityScore = sortedLeft.sumOf { it * (rightFrequency[it] ?: 0) }

        return similarityScore
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("test_input")) == 0)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    println("Result Test 1: ${part1(testInput)}")
    println("Result Test 2: ${part2(testInput)}")
    check(part1(testInput) == 11)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    println("Solution Part 1: ${part1(input)}")
    println("Solution Part 2: ${part2(input)}")
}
