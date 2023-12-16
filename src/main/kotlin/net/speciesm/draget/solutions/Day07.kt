package net.speciesm.draget.solutions

import net.speciesm.draget.DaySolution

class Day07 : DaySolution {

    val inputRegex = """(.....) (\d+)""".toRegex()

    private enum class CardType1(val representation: Char) {
        Card2('2'),
        Card3('3'),
        Card4('4'),
        Card5('5'),
        Card6('6'),
        Card7('7'),
        Card8('8'),
        Card9('9'),
        CardT('T'),
        CardJ('J'),
        CardQ('Q'),
        CardK('K'),
        CardA('A'),
        ;

        companion object {
            fun fromRepresentation(representation: Char): CardType1? {
                return entries.find { it.representation == representation }
            }
        }
    }

    private enum class HandType1(val check: (String) -> Boolean) {
        HIGH_CARD({ cards ->
            true
        }),
        ONE_PAIR({ cards ->
            cards.groupBy { it }.values.any { it.size == 2 }
        }),
        TWO_PAIR({ cards ->
            val groups = cards.groupBy { it }.values
            groups.count { it.size == 2 } == 2
        }),
        THREE_OF_A_KIND({ cards ->
            cards.groupBy { it }.values.any { it.size == 3 }
        }),
        FULL_HOUSE({ cards ->
            val groups = cards.groupBy { it }.values
            groups.any { it.size == 3 } && groups.any { it.size == 2 }
        }),
        FOUR_OF_A_KIND({ cards ->
            cards.groupBy { it }.values.any { it.size == 4 }
        }),
        FIVE_OF_A_KIND({ cards ->
            cards.all { it == cards.first() }
        }),
        ;
    }

    private data class Hand(val cards: String, val bid: Int, val handValue: Int, val cardsValue: Long)

    override fun solve1(input: String): String {
        val hands = input.toHands()
        return hands.sortedWith(compareBy({ it.handValue }, { it.cardsValue }))
            .mapIndexed { index, hand ->
                hand.bid * (index + 1)
            }
            .sum()
            .toString()
    }

    private fun String.toHands(): List<Hand> {
        return this.lines().map { line ->
            val (cards, bid) = requireNotNull(inputRegex.matchEntire(line)) { "Input not matching the rules of `$inputRegex`" }.destructured
            Hand(cards, bid.toInt(), cards.calculateHandValue(), cards.calculateCardsValue())
        }
    }

    private fun String.calculateHandValue(): Int {
        return HandType1.entries.reversed()
            .find { it.check(this) }?.ordinal ?: HandType1.HIGH_CARD.ordinal
    }

    private fun String.calculateCardsValue(): Long {
        return this.map { card ->
            CardType1.fromRepresentation(card)?.ordinal?.toString().orEmpty().padStart(2, '0')
        }.joinToString(separator = "").toLong()
    }


    private enum class CardType2(val representation: Char) {
        CardJ('J'),
        Card2('2'),
        Card3('3'),
        Card4('4'),
        Card5('5'),
        Card6('6'),
        Card7('7'),
        Card8('8'),
        Card9('9'),
        CardT('T'),
        CardQ('Q'),
        CardK('K'),
        CardA('A'),
        ;

        companion object {
            fun fromRepresentation(representation: Char): CardType2? {
                return entries.find { it.representation == representation }
            }
        }
    }

    private enum class HandType2(val check: (String) -> Boolean) {
        HIGH_CARD({ cards ->
            true
        }),
        ONE_PAIR({ cards ->
            val groups = cards.groupBy { it }.values
            (groups.any { it.size == 2 }) ||
                    (groups.any { it.size != 2 && it[0] == 'J' })
        }),
        TWO_PAIR({ cards ->
            val groups = cards.groupBy { it }.values
            (groups.count { it.size == 2 } == 2) ||
                    (groups.any { it.size == 2 } && groups.any { it.size != 2 && it[0] == 'J' })
        }),
        THREE_OF_A_KIND({ cards ->
            val groups = cards.groupBy { it }.values
            (groups.any { it.size == 2 && it[0] == 'J' }) ||
                    (groups.any { it.size == 3 }) ||
                    (groups.any { it.size == 2 } && groups.any { it.size == 1 && it[0] == 'J' })
        }),
        FULL_HOUSE({ cards ->
            val groups = cards.groupBy { it }.values
            (groups.any { it.size == 3 } && groups.any { it.size == 2 }) ||
                    (groups.any { it.size == 3 } && groups.any { it.size == 1 && it[0] == 'J' }) ||
                    (groups.count { it.size == 2 } == 2 && groups.any { it.size == 1 && it[0] == 'J' })
        }),
        FOUR_OF_A_KIND({ cards ->
            val groups = cards.groupBy { it }.values
            (groups.any { it.size == 3 && it[0] == 'J' }) ||
                    (groups.any { it.size == 4 }) ||
                    (groups.any { it.size == 3 } && groups.any { it.size == 1 && it[0] == 'J' }) ||
                    (groups.any { it[0] != 'J' && it.size == 2 } && groups.any { it.size == 2 && it[0] == 'J' })
        }),
        FIVE_OF_A_KIND({ cards ->
            val groups = cards.groupBy { it }.values
            (groups.any { it.size == 4 && it[0] == 'J' }) ||
                    (groups.any { it.size == 5 }) ||
                    (groups.any { it.size == 4 } && groups.any { it.size == 1 && it[0] == 'J' }) ||
                    (groups.any { it.size == 3 } && groups.any { it.size == 2 && it[0] == 'J' }) ||
                    (groups.any { it.size == 2 && it[0] != 'J' } && groups.any { it.size == 3 && it[0] == 'J' })
        }),
        ;
    }

    override fun solve2(input: String): String {
        val hands = input.toHands2()
        return hands.sortedWith(compareBy({ it.handValue }, { it.cardsValue }))
            .mapIndexed { index, hand ->
                hand.bid * (index + 1)
            }
            .sum()
            .toString()
    }

    private fun String.toHands2(): List<Hand> {
        return this.lines().map { line ->
            val (cards, bid) = requireNotNull(inputRegex.matchEntire(line)) { "Input not matching the rules of `$inputRegex`" }.destructured
            Hand(cards, bid.toInt(), cards.calculateHandValue2(), cards.calculateCardsValue2())
        }
    }

    private fun String.calculateHandValue2(): Int {
        return HandType2.entries.reversed()
            .find { it.check(this) }?.ordinal ?: HandType2.HIGH_CARD.ordinal
    }

    private fun String.calculateCardsValue2(): Long {
        return this.map { card ->
            CardType2.fromRepresentation(card)?.ordinal?.toString().orEmpty().padStart(2, '0')
        }.joinToString(separator = "").toLong()
    }
}

