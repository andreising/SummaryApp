package com.andreising.summaryapp.data.game_process.source

import com.andreising.summaryapp.data.game_process.model.GameSettings
import com.andreising.summaryapp.data.utils.ReactiveValue
import com.andreising.summaryapp.domain.models.GameProgressStats
import com.andreising.summaryapp.domain.models.GameResult
import com.andreising.summaryapp.domain.models.Level
import com.andreising.summaryapp.domain.models.Question

class GameManager(
    private val gameTimer: GameTimer
) {

    private var gameSettings: GameSettings? = null
    private val questionState = ReactiveValue(Question.getInitial())
    private val progressState = ReactiveValue(GameProgressStats.getInitial())
    private var gameEnd = ReactiveValue(GameResult.getInitial())
    private var currentQuestionIndex = 0

    fun initGame(level: Level) {
        gameSettings = generateGameSettings(level)
        progressState.update(
            GameProgressStats(
                timeRemainInSeconds = level.totalTimeSec,
                timeProgressRemain = 1f,
                correctAnswersCount = 0,
                totalAnswersCount = level.requiredCorrectAnswer
            )
        )
        setQuestion(currentQuestionIndex)
        setTimer(level.totalTimeSec)
    }

    fun startGame() {
        gameTimer.startTimer()
    }

    fun cancelGame() {
        gameTimer.stopTimer()
        gameSettings = null
        questionState.update(Question.getInitial())
        progressState.update(GameProgressStats.getInitial())
        currentQuestionIndex = 0
        gameEnd.update(GameResult.getInitial())
    }

    fun getGameCurrentStatsObserver() = progressState.observe()
    fun getQuestionObserver() = questionState.observe()
    fun getGameEndObserver() = gameEnd.observe()

    private fun generateGameSettings(level: Level): GameSettings =
        GameSettings(level, generateQuestionList(level.maxValue, level.totalQuestion))


    private fun setTimer(time: Int) {
        gameTimer.setTimer(
            time = time,
            onTickCallBack = { remaining ->
                val current = progressState.get()
                val floatRemaining =
                    remaining.toFloat() / requireGameSettings().currentLevel.totalTimeSec
                progressState.update(
                    current.copy(
                        timeRemainInSeconds = remaining,
                        timeProgressRemain = floatRemaining
                    )
                )
            },
            onFinishCallback = { endGame() }
        )
    }


    private fun setQuestion(index: Int) {
        val questions = requireGameSettings().questionList
        if (index == questions.size) endGame()
        else questionState.update(questions[index])
    }


    private fun requireGameSettings() =
        gameSettings ?: throw RuntimeException("Game settings are not initialized")

    private fun endGame() {
        gameEnd.update(generateGameResult())
        cancelGame()
    }

    private fun generateGameResult(): GameResult {
        val progress = progressState.get()
        val gameSettings = requireGameSettings()
        return GameResult(
            totalAnswers = gameSettings.currentLevel.totalQuestion,
            correctAnswers = progress.correctAnswersCount,
            requiredCorrectAnswers = gameSettings.currentLevel.requiredCorrectAnswer,
            totalTimeSec = gameSettings.currentLevel.totalTimeSec - progress.timeRemainInSeconds
        )
    }


    private fun generateQuestionList(maxSumValue: Int, totalQuestions: Int): List<Question> {
        return List(totalQuestions) {
            generateQuestion(maxSumValue)
        }
    }

    private fun generateQuestion(maxSumValue: Int): Question {
        val sum = (MIN_SUM_VALUE..maxSumValue).random()
        val firstOperand = (MIN_ANSWER_VALUE until sum).random()
        val correctAnswer = sum - firstOperand

        val answers = mutableSetOf(correctAnswer)
        while (answers.size < COUNT_OF_VARIATIONS) {
            answers.add((MIN_ANSWER_VALUE..sum).random())
        }

        return Question(
            sum = sum,
            firstOperand = firstOperand,
            answersList = answers.toList()
        )
    }

    fun answerTheQuestion(answer: Int) {
        val current = questionState.get()
        val isCorrect = current.answersList.first() == answer

        if (isCorrect) {
            val progress = progressState.get()
            progressState.update(progress.copy(correctAnswersCount = progress.correctAnswersCount + 1))
        }

        currentQuestionIndex++
        setQuestion(currentQuestionIndex)
    }

    companion object {
        private const val MIN_SUM_VALUE = 6
        private const val MIN_ANSWER_VALUE = 1
        private const val COUNT_OF_VARIATIONS = 6
    }
}