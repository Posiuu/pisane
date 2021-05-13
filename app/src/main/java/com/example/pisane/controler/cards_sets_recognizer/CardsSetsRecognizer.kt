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
        return cards.sumBy{
            if (it.figure == CardFigure.F2) {
                it.value
            }
            else 0
        }
    }

    private fun getGame3Score(cards: List<Card>): Int {
        return cards.sumBy{
            if (it.figure == CardFigure.F3) {
                it.value
            }
            else 0
        }
    }

    private fun getGame4Score(cards: List<Card>): Int {
        return cards.sumBy{
            if (it.figure == CardFigure.F4) {
                it.value
            }
            else 0
        }
    }

    private fun getGame5Score(cards: List<Card>): Int {
        return cards.sumBy{
            if (it.figure == CardFigure.F5) {
                it.value
            }
            else 0
        }
    }

    private fun getGame6Score(cards: List<Card>): Int {
        return cards.sumBy{
            if (it.figure == CardFigure.F6) {
                it.value
            }
            else 0
        }
    }

    private fun getGame7Score(cards: List<Card>): Int {
        return cards.sumBy{
            if (it.figure == CardFigure.F7) {
                it.value
            }
            else 0
        }
    }

    private fun getGame8Score(cards: List<Card>): Int {
        return cards.sumBy{
            if (it.figure == CardFigure.F8) {
                it.value
            }
            else 0
        }
    }

    private fun getGame9Score(cards: List<Card>): Int {
        return cards.sumBy{
            if (it.figure == CardFigure.F9) {
                it.value
            }
            else 0
        }
    }

    private fun getGame10Score(cards: List<Card>): Int {
        return cards.sumBy{
            if (it.figure == CardFigure.F10) {
                it.value
            }
            else 0
        }
    }

    private fun getGameJScore(cards: List<Card>): Int {
        return cards.sumBy{
            if (it.figure == CardFigure.FJ) {
                it.value
            }
            else 0
        }
    }

    private fun getGameQScore(cards: List<Card>): Int {
        return cards.sumBy{
            if (it.figure == CardFigure.FQ) {
                it.value
            }
            else 0
        }
    }

    private fun getGameKScore(cards: List<Card>): Int {
        return cards.sumBy{
            if (it.figure == CardFigure.FK) {
                it.value
            }
            else 0
        }
    }

    private fun getGameAScore(cards: List<Card>): Int {
        return cards.sumBy{
            if (it.figure == CardFigure.FA) {
                it.value
            }
            else 0
        }
    }

    private fun getGameParaScore(cards: List<Card>): Int {
        val pairedCards = cards.filter{ card ->
            1 < cards.count{it.figure == card.figure}
        }

        return if (pairedCards.isNotEmpty()) {
            pairedCards.maxOf{it.value} * 2
        }
        else 0
    }

    private fun getGame2ParyScore(cards: List<Card>): Int {
        val pairedCards = cards.filter{ card ->
            1 < cards.count{it.figure == card.figure}
        }
        val distinctPairedCards = pairedCards.distinctBy{it.figure}

        return if (distinctPairedCards.size == 2) {
            distinctPairedCards.sumBy{it.value} * 2
        }
        else 0
    }

    private fun getGame3RowneScore(cards: List<Card>): Int {
        val pairedCards = cards.filter{ card ->
            1 < cards.count{it.figure == card.figure}
        }
        val distinctPairedCards = pairedCards.distinctBy{it.figure}

        for (card in distinctPairedCards) {
            if (3 <= pairedCards.count{it.figure == card.figure}) {
                return card.value * 3
            }
        }

        return 0
    }

    private fun getGame4RowneScore(cards: List<Card>): Int {
        val pairedCards = cards.filter{ card ->
            1 < cards.count{it.figure == card.figure}
        }
        val distinctPairedCards = pairedCards.distinctBy{it.figure}

        for (card in distinctPairedCards) {
            if (4 <= pairedCards.count{it.figure == card.figure}) {
                return card.value * 4
            }
        }

        return 0
    }

    private fun getGame5RownychScore(cards: List<Card>): Int {
        return 0
    }

    private fun getGameCzerwoneScore(cards: List<Card>): Int {
        return if (cards.all(isRed)) {
            cards.sumBy{it.value}
        }
        else 0
    }

    private fun getGameCzarneScore(cards: List<Card>): Int {
        return if (cards.all(isBlack)) {
            cards.sumBy{it.value}
        }
        else 0
    }

    private fun getGameParzysteScore(cards: List<Card>): Int {
        return if (cards.all(isEven)) {
            cards.sumBy{it.value}
        }
        else 0
    }

    private fun getGameNieparzysteScore(cards: List<Card>): Int {
        return if (cards.all(isOdd)) {
            cards.sumBy{it.value}
        }
        else 0
    }

    private fun getGameSNScore(cards: List<Card>): Int {
        val score = cards.sumBy{it.value}
        return if (score % 2 == 1) {
            score
        }
        else 0
    }

    private fun getGameSPScore(cards: List<Card>): Int {
        val score = cards.sumBy{it.value}
        return if (score % 2 == 0) {
            score
        }
        else 0
    }

    private fun getGameSzansaScore(cards: List<Card>): Int {
        return cards.sumBy{it.value}
    }

    private fun getGameRozneScore(cards: List<Card>): Int {
        val distinctCards = cards.distinctBy{it.figure}
        return if (distinctCards.size == 5) {
            cards.sumOf{it.value}
        }
        else 0
    }

    private fun getGameStritScore(cards: List<Card>): Int {
        val cardsFiguresList = mutableListOf<Int>()
        for (card in cards) {
            cardsFiguresList.add(card.figure)
        }

        val isStraight = possibleStraights.any { cardsFiguresList.containsAll(it) }

        return if (isStraight){
            cards.sumOf{it.value}
        }
        else 0
    }

    private fun getGameKolorScore(cards: List<Card>): Int {
        return if (cards.all{it.color == CardColor.DIAMOND}) {
            cards.sumBy{it.value}
        }
        else if (cards.all{it.color == CardColor.HEART}) {
            cards.sumBy{it.value}
        }
        else if (cards.all{it.color == CardColor.SPADE}) {
            cards.sumBy{it.value}
        }
        else if (cards.all{it.color == CardColor.CLUB}) {
            cards.sumBy{it.value}
        }
        else 0
    }

    private fun getGame2x3Score(cards: List<Card>): Int {
        val pairedCards = cards.filter{ card ->
            1 < cards.count{it.figure == card.figure}
        }
        val distinctPairedCards = pairedCards.distinctBy{it.figure}

        if (distinctPairedCards.size != 2) {
            return 0
        }

        val firstFigureCount = pairedCards.count{it.figure == distinctPairedCards[0].figure}
        val secondFigureCount = pairedCards.count{it.figure == distinctPairedCards[1].figure}
        val is2x3 = (firstFigureCount == 2 && secondFigureCount == 3)
                || (firstFigureCount == 3 && secondFigureCount == 2)

        return if (is2x3) {
            cards.sumOf{it.value}
        }
        else 0
    }

    private fun getGamePokerScore(cards: List<Card>): Int {
        val isColor = getGameKolorScore(cards) != 0
        val isStraight = getGameStritScore(cards) != 0
        val isPoker = isColor && isStraight

        return if (isPoker) {
            cards.sumOf{it.value}
        }
        else 0
    }

    private fun getGameKaroScore(cards: List<Card>): Int {
        return cards.sumBy {
            if (it.color == CardColor.DIAMOND) {
                it.value
            }
            else 0
        }
    }

    private fun getGameKierScore(cards: List<Card>): Int {
        return cards.sumBy {
            if (it.color == CardColor.HEART) {
                it.value
            }
            else 0
        }
    }

    private fun getGamePikScore(cards: List<Card>): Int {
        return cards.sumBy {
            if (it.color == CardColor.SPADE) {
                it.value
            }
            else 0
        }
    }

    private fun getGameTreflScore(cards: List<Card>): Int {
        return cards.sumBy {
            if (it.color == CardColor.CLUB) {
                it.value
            }
            else 0
        }
    }

    private fun getGameJokerScore(cards: List<Card>): Int {
        return 0
    }

    private fun getGamePierwszeScore(cards: List<Card>): Int {
        return if (cards.all(isPrime)) {
            cards.sumBy{it.value}
        }
        else 0
    }

    private fun getGame25Score(cards: List<Card>): Int {
        val score = cards.sumBy{it.value}
        return if (score == 25) {
            score
        }
        else 0
    }

    private fun getGameNumeryScore(cards: List<Card>): Int {
        return if (cards.all(isNumber)) {
            cards.sumBy{it.value}
        }
        else 0
    }

    private fun getGameFiguryScore(cards: List<Card>): Int {
        return if (cards.all(isFigure)) {
            cards.sumBy{it.value}
        }
        else 0
    }

    val isRed: (Card) -> Boolean = {
        (it.color == CardColor.DIAMOND
                || it.color == CardColor.HEART)
    }

    val isBlack: (Card) -> Boolean = {
        !isRed(it)
    }

    val isEven: (Card) -> Boolean = {
        it.value % 2 == 0
    }

    val isOdd: (Card) -> Boolean = {
        !isEven(it)
    }

    val isPrime: (Card) -> Boolean = {
        it.value in listOf(2, 3, 5, 7, 11)
    }

    val isFigure: (Card) -> Boolean = {
        it.figure in listOf(
                CardFigure.FJ,
                CardFigure.FQ,
                CardFigure.FK,
                CardFigure.FA
        )
    }

    val isNumber: (Card) -> Boolean = {
        !isFigure(it)
    }

    private val possibleStraights = listOf(
            listOf(CardFigure.FA, CardFigure.F2, CardFigure.F3, CardFigure.F4, CardFigure.F5),
            listOf(CardFigure.F2, CardFigure.F3, CardFigure.F4, CardFigure.F5, CardFigure.F6),
            listOf(CardFigure.F3, CardFigure.F4, CardFigure.F5, CardFigure.F6, CardFigure.F7),
            listOf(CardFigure.F4, CardFigure.F5, CardFigure.F6, CardFigure.F7, CardFigure.F8),
            listOf(CardFigure.F5, CardFigure.F6, CardFigure.F7, CardFigure.F8, CardFigure.F9),
            listOf(CardFigure.F6, CardFigure.F7, CardFigure.F8, CardFigure.F9, CardFigure.F10),
            listOf(CardFigure.F7, CardFigure.F8, CardFigure.F9, CardFigure.F10, CardFigure.FJ),
            listOf(CardFigure.F8, CardFigure.F9, CardFigure.F10, CardFigure.FJ, CardFigure.FQ),
            listOf(CardFigure.F9, CardFigure.F10, CardFigure.FJ, CardFigure.FQ, CardFigure.FK),
            listOf(CardFigure.F10, CardFigure.FJ, CardFigure.FQ, CardFigure.FK, CardFigure.FA)
    )
}