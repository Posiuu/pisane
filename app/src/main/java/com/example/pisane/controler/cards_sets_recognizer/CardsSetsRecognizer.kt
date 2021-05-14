package com.example.pisane.controler.cards_sets_recognizer

import com.example.pisane.model.card.*

class CardsSetsRecognizer {
    fun getAllGamesScores(cards: List<Card>): List<Int> {
        return listOf(
                getGame2Score(cards),
                getGame3Score(cards),
                getGame4Score(cards),
                getGame5Score(cards),
                getGame6Score(cards),
                getGame7Score(cards),
                getGame8Score(cards),
                getGame9Score(cards),
                getGame10Score(cards),
                getGameJScore(cards),
                getGameQScore(cards),
                getGameKScore(cards),
                getGameAScore(cards),
                getGameParaScore(cards),
                getGame2ParyScore(cards),
                getGame3RowneScore(cards),
                getGame4RowneScore(cards),
                getGame5RownychScore(cards),
                getGameCzerwoneScore(cards),
                getGameCzarneScore(cards),
                getGameParzysteScore(cards),
                getGameNieparzysteScore(cards),
                getGameSNScore(cards),
                getGameSPScore(cards),
                getGameSzansaScore(cards),
                getGameRozneScore(cards),
                getGameStritScore(cards),
                getGameKolorScore(cards),
                getGame2x3Score(cards),
                getGamePokerScore(cards),
                getGameKaroScore(cards),
                getGameKierScore(cards),
                getGamePikScore(cards),
                getGameTreflScore(cards),
                getGameJokerScore(cards),
                getGamePierwszeScore(cards),
                getGame25Score(cards),
                getGameNumeryScore(cards),
                getGameFiguryScore(cards),
        )
    }

    private fun getGame2Score(cards: List<Card>): Int {
        return cards.sumBy {
            when (it.figure) {
                CardFigure.F2 -> it.value
                CardFigure.JOKER -> 2
                else -> 0
            }
        }
    }

    private fun getGame3Score(cards: List<Card>): Int {
        return cards.sumBy {
            when (it.figure) {
                CardFigure.F3 -> it.value
                CardFigure.JOKER -> 3
                else -> 0
            }
        }
    }

    private fun getGame4Score(cards: List<Card>): Int {
        return cards.sumBy {
            when (it.figure) {
                CardFigure.F4 -> it.value
                CardFigure.JOKER -> 4
                else -> 0
            }
        }
    }

    private fun getGame5Score(cards: List<Card>): Int {
        return cards.sumBy {
            when (it.figure) {
                CardFigure.F5 -> it.value
                CardFigure.JOKER -> 5
                else -> 0
            }
        }
    }

    private fun getGame6Score(cards: List<Card>): Int {
        return cards.sumBy {
            when (it.figure) {
                CardFigure.F6 -> it.value
                CardFigure.JOKER -> 6
                else -> 0
            }
        }
    }

    private fun getGame7Score(cards: List<Card>): Int {
        return cards.sumBy {
            when (it.figure) {
                CardFigure.F7 -> it.value
                CardFigure.JOKER -> 7
                else -> 0
            }
        }
    }

    private fun getGame8Score(cards: List<Card>): Int {
        return cards.sumBy {
            when (it.figure) {
                CardFigure.F8 -> it.value
                CardFigure.JOKER -> 8
                else -> 0
            }
        }
    }

    private fun getGame9Score(cards: List<Card>): Int {
        return cards.sumBy {
            when (it.figure) {
                CardFigure.F9 -> it.value
                CardFigure.JOKER -> 9
                else -> 0
            }
        }
    }

    private fun getGame10Score(cards: List<Card>): Int {
        return cards.sumBy {
            when (it.figure) {
                CardFigure.F10 -> it.value
                CardFigure.JOKER -> 10
                else -> 0
            }
        }
    }

    private fun getGameJScore(cards: List<Card>): Int {
        return cards.sumBy {
            when (it.figure) {
                CardFigure.FJ -> it.value
                CardFigure.JOKER -> 2
                else -> 0
            }
        }
    }

    private fun getGameQScore(cards: List<Card>): Int {
        return cards.sumBy {
            when (it.figure) {
                CardFigure.FQ -> it.value
                CardFigure.JOKER -> 3
                else -> 0
            }
        }
    }

    private fun getGameKScore(cards: List<Card>): Int {
        return cards.sumBy {
            when (it.figure) {
                CardFigure.FK -> it.value
                CardFigure.JOKER -> 4
                else -> 0
            }
        }
    }

    private fun getGameAScore(cards: List<Card>): Int {
        return cards.sumBy {
            when (it.figure) {
                CardFigure.FA -> it.value
                CardFigure.JOKER -> 11
                else -> 0
            }
        }
    }

    private fun getGameParaScore(cards: List<Card>): Int {
        val jokerCount = cards.count{it.figure == CardFigure.JOKER}
        val cardsWithoutJoker = cards.filter{it.figure != CardFigure.JOKER}
        val pairedCardsWithoutJoker = cardsWithoutJoker.filter{ card ->
            1 < cardsWithoutJoker.count{it.figure == card.figure}
        }
        val distinctPairedCardsWithoutJoker = pairedCardsWithoutJoker.distinctBy{it.figure}

        return when (jokerCount) {
            0 -> {
                val isOnePair = 2 <= pairedCardsWithoutJoker.size
                if (isOnePair) {
                    distinctPairedCardsWithoutJoker.maxOf{it.value} * 2
                }
                else 0
            }
            1 -> cardsWithoutJoker.maxOf{it.value} * 2
            else -> 22
        }
    }

    private fun getGame2ParyScore(cards: List<Card>): Int {
        val jokerCount = cards.count{it.figure == CardFigure.JOKER}
        val cardsWithoutJoker = cards.filter{it.figure != CardFigure.JOKER}
        val pairedCardsWithoutJoker = cardsWithoutJoker.filter{ card ->
            1 < cardsWithoutJoker.count{it.figure == card.figure}
        }
        var unpairedCardsWithoutJoker = cardsWithoutJoker - pairedCardsWithoutJoker
        unpairedCardsWithoutJoker = unpairedCardsWithoutJoker.sortedByDescending{it.value}
        val distinctPairedCardsWithoutJoker = pairedCardsWithoutJoker.distinctBy{it.figure}

        when (jokerCount) {
            0 -> {
                val is2Pairs = distinctPairedCardsWithoutJoker.size == 2

                return if (is2Pairs) {
                    getSum(distinctPairedCardsWithoutJoker) * 2
                }
                else 0
            }
            1 -> {
                //check cards without joker
                val isOnePair = pairedCardsWithoutJoker.size == 2
                val is3OfAKind = pairedCardsWithoutJoker.size == 3
                val is2Pairs = distinctPairedCardsWithoutJoker.size == 2

                return when {
                    isOnePair -> {
                        getSum(pairedCardsWithoutJoker) + (unpairedCardsWithoutJoker.maxOf{it.value} * 2)
                    }
                    is3OfAKind -> {
                        (pairedCardsWithoutJoker[0].value * 2) + (unpairedCardsWithoutJoker.maxOf{it.value} * 2)
                    }
                    is2Pairs -> {
                        getSum(pairedCardsWithoutJoker)
                    }
                    else -> 0
                }
            }
            2 -> {
                //check cards without joker
                val isNoPair = pairedCardsWithoutJoker.isEmpty()
                val isOnePair = pairedCardsWithoutJoker.size == 2
                val isOnePairOfNotAces = isOnePair
                        && distinctPairedCardsWithoutJoker[0].figure != CardFigure.FA
                val is3OfAKind = pairedCardsWithoutJoker.size == 3
                val is3OfAKindOfNotAces = is3OfAKind
                        && distinctPairedCardsWithoutJoker[0].figure != CardFigure.FA

                return when {
                    isNoPair -> {
                        (unpairedCardsWithoutJoker[0].value * 2) + (unpairedCardsWithoutJoker[1].value * 2)
                    }
                    isOnePairOfNotAces -> {
                        getSum(pairedCardsWithoutJoker) + 22
                    }
                    is3OfAKindOfNotAces -> {
                        (pairedCardsWithoutJoker[0].value * 2) + 22
                    }
                    else -> 42
                }
            }
            3 -> {
                //check cards without joker
                val isNoPair = pairedCardsWithoutJoker.isEmpty()
                val isNoPairWithoutAce = isNoPair
                        && cardsWithoutJoker.all{it.figure != CardFigure.FA}
                val isOnePair = pairedCardsWithoutJoker.size == 2
                val isOnePairOfNotAces = isOnePair
                        && distinctPairedCardsWithoutJoker[0].figure != CardFigure.FA

                return when {
                    isNoPairWithoutAce -> {
                        (unpairedCardsWithoutJoker.maxOf{it.value} * 2) + 22
                    }
                    isOnePairOfNotAces -> {
                        getSum(pairedCardsWithoutJoker) + 22
                    }
                    else -> 42
                }
            }
            else -> return 42
        }
    }

    private fun getGame3RowneScore(cards: List<Card>): Int {
        val jokerCount = cards.count{it.figure == CardFigure.JOKER}
        val cardsWithoutJoker = cards.filter{it.figure != CardFigure.JOKER}
        val pairedCardsWithoutJoker = cardsWithoutJoker.filter{ card ->
            1 < cardsWithoutJoker.count{it.figure == card.figure}
        }
        val distinctPairedCardsWithoutJoker = pairedCardsWithoutJoker.distinctBy{it.figure}

        when (jokerCount) {
            0 -> {
                for (card in distinctPairedCardsWithoutJoker) {
                    if (3 <= pairedCardsWithoutJoker.count{it.figure == card.figure}) {
                        return card.value * 3
                    }
                }
                return 0
            }
            1 -> {
                //check cards without joker
                val isOnePair = pairedCardsWithoutJoker.size == 2
                val is3OfAKind = pairedCardsWithoutJoker.size == 3
                val is2Pairs = distinctPairedCardsWithoutJoker.size == 2
                val is4OfAKind = pairedCardsWithoutJoker.size == 4
                        && !is2Pairs

                return when {
                    isOnePair -> {
                        distinctPairedCardsWithoutJoker[0].value * 3
                    }
                    is3OfAKind -> {
                        distinctPairedCardsWithoutJoker[0].value * 3
                    }
                    is2Pairs -> {
                        distinctPairedCardsWithoutJoker.maxOf{it.value} * 3
                    }
                    is4OfAKind -> {
                        distinctPairedCardsWithoutJoker[0].value * 3
                    }
                    else -> 0
                }
            }
            2 -> {
                return cardsWithoutJoker.maxOf{it.value} * 3
            }
            else -> return 33
        }
    }

    private fun getGame4RowneScore(cards: List<Card>): Int {
        val jokerCount = cards.count{it.figure == CardFigure.JOKER}
        val cardsWithoutJoker = cards.filter{it.figure != CardFigure.JOKER}
        val pairedCardsWithoutJoker = cardsWithoutJoker.filter{ card ->
            1 < cardsWithoutJoker.count{it.figure == card.figure}
        }
        val distinctPairedCardsWithoutJoker = pairedCardsWithoutJoker.distinctBy{it.figure}

        when (jokerCount) {
            0 -> {
                val is2Pairs = distinctPairedCardsWithoutJoker.size == 2
                val is4OfAKind = pairedCardsWithoutJoker.size == 4
                        && !is2Pairs
                return if (is4OfAKind) {
                    getSum(pairedCardsWithoutJoker)
                }
                else 0
            }
            1 -> {
                val is3OfAKind = pairedCardsWithoutJoker.size == 3
                val is2Pairs = distinctPairedCardsWithoutJoker.size == 2
                val is4OfAKind = pairedCardsWithoutJoker.size == 4
                        && !is2Pairs
                return if (is3OfAKind || is4OfAKind) {
                    pairedCardsWithoutJoker[0].value * 4
                }
                else 0
            }
            2 -> {
                val isOnePair = pairedCardsWithoutJoker.size == 2
                val is3OfAKind = pairedCardsWithoutJoker.size == 3
                return if (isOnePair || is3OfAKind) {
                    pairedCardsWithoutJoker[0].value * 4
                }
                else 0
            }
            3 -> {
                return cardsWithoutJoker.maxOf{it.value} * 4
            }
            else -> return 44
        }
    }

    private fun getGame5RownychScore(cards: List<Card>): Int {
        val jokerCount = cards.count{it.figure == CardFigure.JOKER}
        val cardsWithoutJoker = cards.filter{it.figure != CardFigure.JOKER}
        val pairedCardsWithoutJoker = cardsWithoutJoker.filter{ card ->
            1 < cardsWithoutJoker.count{it.figure == card.figure}
        }
        val unpairedCardsWithoutJoker = cardsWithoutJoker - pairedCardsWithoutJoker

        return when (jokerCount) {
            0 -> 0
            else -> {
                if (unpairedCardsWithoutJoker.isEmpty()) {
                    cardsWithoutJoker[0].value * 5
                }
                else 0
            }
        }
    }

    private fun getGameCzerwoneScore(cards: List<Card>): Int {
        return if (cards.all(isRed)) {
            getSum(cards)
        }
        else 0
    }

    private fun getGameCzarneScore(cards: List<Card>): Int {
        return if (cards.all(isBlack)) {
            getSum(cards)
        }
        else 0
    }

    private fun getGameParzysteScore(cards: List<Card>): Int {
        return if (cards.all(isEven)) {
            cards.sumBy{
                when(it.figure) {
                    CardFigure.JOKER -> 10
                    else -> it.value
                }
            }
        }
        else 0
    }

    private fun getGameNieparzysteScore(cards: List<Card>): Int {
        return if (cards.all(isOdd)) {
            getSum(cards)
        }
        else 0
    }

    private fun getGameSNScore(cards: List<Card>): Int {
        val score = getSum(cards)

        val isScoreOdd = score % 2 == 1
        val isJokerInHand = cards.any{it.figure == CardFigure.JOKER}

        return when {
            isScoreOdd -> score
            isJokerInHand -> score - 1
            else -> 0
        }
    }

    private fun getGameSPScore(cards: List<Card>): Int {
        val score = getSum(cards)

        val isScoreEven = score % 2 == 0
        val isJokerInHand = cards.any{it.figure == CardFigure.JOKER}

        return when {
            isScoreEven -> score
            isJokerInHand -> score - 1
            else -> 0
        }
    }

    private fun getGameSzansaScore(cards: List<Card>): Int {
        return getSum(cards)
    }

    private fun getGameRozneScore(cards: List<Card>): Int {
        val jokerCount = cards.count{it.figure == CardFigure.JOKER}
        val cardsWithoutJoker = cards.filter{it.figure != CardFigure.JOKER}
        val distinctCardsWithoutJoker = cardsWithoutJoker.distinctBy{it.figure}
        val isAllCardsDifferent = distinctCardsWithoutJoker.size == cardsWithoutJoker.size
        if (!isAllCardsDifferent) {
            return 0
        }

        val bestScoreValuesList = listOf(11, 10, 9, 8, 7)
        val cardsValuesList = mutableListOf<Int>()
        for (card in cards) {
            cardsValuesList.add(card.value)
        }
        var notUsedBestScoreValuesList = bestScoreValuesList - cardsValuesList
        notUsedBestScoreValuesList = notUsedBestScoreValuesList.sortedDescending()

        return when (jokerCount) {
            1 -> getSum(cardsWithoutJoker) + notUsedBestScoreValuesList[0]
            2 -> getSum(cardsWithoutJoker) + notUsedBestScoreValuesList[0] + notUsedBestScoreValuesList[1]
            3 -> getSum(cardsWithoutJoker) + notUsedBestScoreValuesList[0] + notUsedBestScoreValuesList[1] + notUsedBestScoreValuesList[2]
            else -> return getSum(cards)
        }
    }

    private fun getGameStritScore(cards: List<Card>): Int {
        val cardsWithoutJoker = cards.filter{it.figure != CardFigure.JOKER}
        val pairedCardsWithoutJoker = cardsWithoutJoker.filter{ card ->
            1 < cardsWithoutJoker.count{it.figure == card.figure}
        }

        if (pairedCardsWithoutJoker.isNotEmpty()) {
            return 0
        }

        val cardsFiguresList = mutableListOf<Int>()
        for (card in cardsWithoutJoker) {
            cardsFiguresList.add(card.figure)
        }

        //We have to make sure that all cards in our hand
        //belong to a possible straight. Cards that are missing
        //will be replaced with jokers
        val possibleStraightsWithThisHand = possibleStraights.filter {
            it.cardFigures.containsAll(cardsFiguresList)
        }

        return if (possibleStraightsWithThisHand.isNotEmpty()) {
            possibleStraightsWithThisHand.maxOf{it.score}
        }
        else 0
    }

    private fun getGameKolorScore(cards: List<Card>): Int {
        return if (cards.all(isDiamond)
                || cards.all(isHeart)
                || cards.all(isSpade)
                || cards.all(isClub)) {
            getSum(cards)
        }
        else 0
    }

    private fun getGame2x3Score(cards: List<Card>): Int {
        val jokerCount = cards.count{it.figure == CardFigure.JOKER}
        val cardsWithoutJoker = cards.filter{it.figure != CardFigure.JOKER}
        val pairedCardsWithoutJoker = cardsWithoutJoker.filter{ card ->
            1 < cardsWithoutJoker.count{it.figure == card.figure}
        }
        var unpairedCardsWithoutJoker = cardsWithoutJoker - pairedCardsWithoutJoker
        unpairedCardsWithoutJoker = unpairedCardsWithoutJoker.sortedByDescending{it.value}
        val distinctPairedCardsWithoutJoker = pairedCardsWithoutJoker.distinctBy{it.figure}

        when (jokerCount) {
            0 -> {
                val is2x3 = pairedCardsWithoutJoker.size == 5
                        && distinctPairedCardsWithoutJoker.size == 2

                return if (is2x3) {
                    getSum(cards)
                }
                else 0
            }
            1 -> {
                //check cards without joker
                val is2Pairs = distinctPairedCardsWithoutJoker.size == 2
                val is3OfAKind = pairedCardsWithoutJoker.size == 3

                return when {
                    is2Pairs -> {
                        getSum(pairedCardsWithoutJoker) + pairedCardsWithoutJoker.maxOf{it.value}
                    }
                    is3OfAKind -> {
                        getSum(pairedCardsWithoutJoker) + (unpairedCardsWithoutJoker[0].value * 2)
                    }
                    else -> 0
                }
            }
            2 -> {
                //check cards without joker
                val isOnePair = pairedCardsWithoutJoker.size == 2
                val isOnePairWithBiggerKicker = isOnePair
                        && distinctPairedCardsWithoutJoker[0].value < unpairedCardsWithoutJoker[0].value
                val isOnePairWithSmallerKicker = isOnePair
                        && unpairedCardsWithoutJoker[0].value < distinctPairedCardsWithoutJoker[0].value
                val is3OfAKind = pairedCardsWithoutJoker.size == 3
                val is3OfAKindOfAces = is3OfAKind
                        && distinctPairedCardsWithoutJoker[0].figure == CardFigure.FA
                val is3OfAKindOfNotAces = is3OfAKind
                        && distinctPairedCardsWithoutJoker[0].figure != CardFigure.FA

                return when {
                    isOnePairWithBiggerKicker -> {
                        (pairedCardsWithoutJoker[0].value * 2) + (unpairedCardsWithoutJoker[0].value * 3)
                    }
                    isOnePairWithSmallerKicker -> {
                        (pairedCardsWithoutJoker[0].value * 3) + (unpairedCardsWithoutJoker[0].value * 2)
                    }
                    is3OfAKindOfAces -> {
                        getSum(pairedCardsWithoutJoker) + 10 + 10
                    }
                    is3OfAKindOfNotAces -> {
                        getSum(pairedCardsWithoutJoker) + 11 + 11
                    }
                    else -> 0
                }
            }
            3 -> {
                //check cards without joker
                val isNoPair = pairedCardsWithoutJoker.isEmpty()
                val isPair = pairedCardsWithoutJoker.size == 2
                val isOnePairOfNotAces = isPair
                        && distinctPairedCardsWithoutJoker[0].figure != CardFigure.FA

                return when {
                    isNoPair -> {
                        (unpairedCardsWithoutJoker[0].value * 3) + (unpairedCardsWithoutJoker[0].value * 2)
                    }
                    isOnePairOfNotAces -> {
                        getSum(cardsWithoutJoker) + 11 + 11 + 11
                    }
                    else -> 53
                }
            }
            else -> return 42
        }
    }

    private fun getGamePokerScore(cards: List<Card>): Int {
        val isColor = getGameKolorScore(cards) != 0
        val isStraight = getGameStritScore(cards) != 0
        val isPoker = isColor && isStraight

        return if (isPoker) {
            getGameKolorScore(cards)
        }
        else 0
    }

    private fun getGameKaroScore(cards: List<Card>): Int {
        return cards.sumBy {
            when (it.color) {
                CardColor.DIAMOND -> it.value
                CardColor.JOKER -> 11
                else -> 0
            }
        }
    }

    private fun getGameKierScore(cards: List<Card>): Int {
        return cards.sumBy {
            when (it.color) {
                CardColor.HEART -> it.value
                CardColor.JOKER -> 11
                else -> 0
            }
        }
    }

    private fun getGamePikScore(cards: List<Card>): Int {
        return cards.sumBy {
            when (it.color) {
                CardColor.SPADE -> it.value
                CardColor.JOKER -> 11
                else -> 0
            }
        }
    }

    private fun getGameTreflScore(cards: List<Card>): Int {
        return cards.sumBy {
            when (it.color) {
                CardColor.CLUB -> it.value
                CardColor.JOKER -> 11
                else -> 0
            }
        }
    }

    private fun getGameJokerScore(cards: List<Card>): Int {
        return cards.sumBy {
            when (it.figure) {
                CardFigure.JOKER -> it.value
                else -> 0
            }
        }
    }

    private fun getGamePierwszeScore(cards: List<Card>): Int {
        return if (cards.all(isPrime)) {
            getSum(cards)
        }
        else 0
    }

    private fun getGame25Score(cards: List<Card>): Int {
        if (cards.any{it.figure == CardFigure.JOKER}) {
            return 0
        }

        val score = getSum(cards)
        return if (score == 25) {
            score
        }
        else 0
    }

    private fun getGameNumeryScore(cards: List<Card>): Int {
        return if (cards.all(isNumber)) {
            cards.sumBy{
                when(it.figure) {
                    CardFigure.JOKER -> 10
                    else -> it.value
                }
            }
        }
        else 0
    }

    private fun getGameFiguryScore(cards: List<Card>): Int {
        return if (cards.all(isFigure)) {
            getSum(cards)
        }
        else 0
    }

    private val isDiamond: (Card) -> Boolean = {
        (it.color == CardColor.DIAMOND
                || it.figure == CardFigure.JOKER)
    }

    private val isHeart: (Card) -> Boolean = {
        (it.color == CardColor.HEART
                || it.figure == CardFigure.JOKER)
    }

    private val isSpade: (Card) -> Boolean = {
        (it.color == CardColor.SPADE
                || it.figure == CardFigure.JOKER)
    }

    private val isClub: (Card) -> Boolean = {
        (it.color == CardColor.CLUB
                || it.figure == CardFigure.JOKER)
    }

    private val isRed: (Card) -> Boolean = {
        isDiamond(it) || isHeart(it)
    }

    private val isBlack: (Card) -> Boolean = {
        isSpade(it) || isClub(it)
    }

    private val isEven: (Card) -> Boolean = {
        it.value % 2 == 0 || it.figure == CardFigure.JOKER
    }

    private val isOdd: (Card) -> Boolean = {
        !isEven(it) || it.figure == CardFigure.JOKER
    }

    private val isPrime: (Card) -> Boolean = {
        it.value in listOf(2, 3, 5, 7, 11) || it.figure == CardFigure.JOKER
    }

    private val isFigure: (Card) -> Boolean = {
        it.figure in listOf(
                CardFigure.FJ,
                CardFigure.FQ,
                CardFigure.FK,
                CardFigure.FA,
                CardFigure.JOKER
        )
    }

    private val isNumber: (Card) -> Boolean = {
        !isFigure(it) || it.figure == CardFigure.JOKER
    }

    private fun getSum(_cards: List<Card>): Int {
        return _cards.sumBy {
            when(it.figure) {
                CardFigure.JOKER -> 11
                else -> it.value
            }
        }
    }

    inner class Straight(val cardFigures: List<Int>, val score: Int)

    private val possibleStraights = listOf(
            Straight(listOf(CardFigure.FA, CardFigure.F2, CardFigure.F3, CardFigure.F4, CardFigure.F5), 25),
            Straight(listOf(CardFigure.F2, CardFigure.F3, CardFigure.F4, CardFigure.F5, CardFigure.F6), 20),
            Straight(listOf(CardFigure.F3, CardFigure.F4, CardFigure.F5, CardFigure.F6, CardFigure.F7), 25),
            Straight(listOf(CardFigure.F4, CardFigure.F5, CardFigure.F6, CardFigure.F7, CardFigure.F8), 30),
            Straight(listOf(CardFigure.F5, CardFigure.F6, CardFigure.F7, CardFigure.F8, CardFigure.F9), 35),
            Straight(listOf(CardFigure.F6, CardFigure.F7, CardFigure.F8, CardFigure.F9, CardFigure.F10), 40),
            Straight(listOf(CardFigure.F7, CardFigure.F8, CardFigure.F9, CardFigure.F10, CardFigure.FJ), 36),
            Straight(listOf(CardFigure.F8, CardFigure.F9, CardFigure.F10, CardFigure.FJ, CardFigure.FQ), 32),
            Straight(listOf(CardFigure.F9, CardFigure.F10, CardFigure.FJ, CardFigure.FQ, CardFigure.FK), 28),
            Straight(listOf(CardFigure.F10, CardFigure.FJ, CardFigure.FQ, CardFigure.FK, CardFigure.FA), 30)
    )
}