fun main() {
    fun cleanMultiply(input: String): List<Int> {
        val regex = Regex("""mul\((\d{1,3}),(\d{1,3})\)""")

        return regex.findAll(input).map { matchResult ->
            val (x, y) = matchResult.destructured
            x.toInt() * y.toInt()
        }.toList()
    }


    fun cleanMultiplyWithControl(input: List<String>): Int {
        val regex = Regex("""mul\((\d+),(\d+)\)|do\(\)|don't\(\)""")
        var enabled = true

        return input.flatMap { line ->
            regex.findAll(line).map { match ->
                when (match.value) {
                    "do()" -> {
                        enabled = true; 0
                    }

                    "don't()" -> {
                        enabled = false; 0
                    }

                    else -> if (enabled) {
                        val (x, y) = match.destructured
                        x.toInt() * y.toInt()
                    } else 0
                }
            }
        }.sum()
    }

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            cleanMultiply(line).sum()
        }
    }

    fun part2(input: List<String>): Int {
        return cleanMultiplyWithControl(input)
    }

    val testInput = readInput("Day03_test")
    println("Result Test 1: ${part1(testInput)}")
    println("Result Test 2: ${part2(testInput)}")
    check(part1(testInput) == 161)
    check(part2(testInput) == 48)

    val input = readInput("Day03")
    println("Solution Part 1: ${part1(input)}")
    println("Solution Part 2: ${part2(input)}")
}
