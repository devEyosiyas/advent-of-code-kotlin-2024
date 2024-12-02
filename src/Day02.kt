fun main() {
    fun isSafeReport(report: List<Int>): Boolean {
        val differences = report.zipWithNext { a, b -> b - a }
        val isIncreasing = differences.all { it in 1..3 }
        val isDecreasing = differences.all { it in -3..-1 }

        return isIncreasing || isDecreasing
    }

    fun isSafeWithDampener(report: List<Int>): Boolean {
        if (isSafeReport(report)) return true

        return report.indices.any { index ->
            val modifiedReport = report.toMutableList().apply { removeAt(index) }
            isSafeReport(modifiedReport)
        }
    }

    fun part1(input: List<String>): Int {
        val reports = input.map { line -> line.split(" ").map { it.toInt() } }
        return reports.count { isSafeReport(it) }
    }

    fun part2(input: List<String>): Int {
        val reports = input.map { line -> line.split(" ").map { it.toInt() } }
        return reports.count { isSafeWithDampener(it) }
    }

    val testInput = readInput("Day02_test")
    println("Result Test 1: ${part1(testInput)}")
    println("Result Test 2: ${part2(testInput)}")
    check(part1(testInput) == 2)

    val input = readInput("Day02")
    println("Solution Part 1: ${part1(input)}")
    println("Solution Part 2: ${part2(input)}")
}
