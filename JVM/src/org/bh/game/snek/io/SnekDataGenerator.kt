package org.bh.game.snek.io

import org.bh.game.snek.gui.swing.Keymap
import org.bh.game.snek.state.*
import org.bh.tools.base.abstraction.Integer
import org.bh.tools.base.math.geometry.*

/**
 * @author Ben Leggiero
 * @since 2017-02-23
 */
class SnekDataGenerator {
    fun generateDefaultData(): SnekData {
        val boardSize = defaultBoardSize
        val path = defaultSnekPath(boardSize)
        return SnekData(defaultBoardSize,
                path,
                defaultLeaderboard,
                defaultScreen,
                defaultApple(boardSize, path),
                defaultKeymap,
                defaultDebug)
    }


    companion object {
        fun generateApplePosition(boardSize: IntegerSize, snekPath: IntegerPath): IntegerPoint =
                boardSize.randomPointNotOnPath(snekPath).integerValue
    }
}

private val defaultBoardSize = IntegerSize(width = 32, height = 32)
private fun defaultSnekPath(boardSize: IntegerSize) = IntegerPath(boardSize.midXmidY.integerValue, boardSize.midXmidY.integerValue + IntegerPoint(0, 1), isClosed = false)
private val testSnekPath = IntegerPath(IntegerPoint(10, 10), IntegerPoint(15, 10), IntegerPoint(15, 7), isClosed = true)
private val defaultLeaderboard = Leaderboard<Leader, Integer>(mapOf())
private val defaultScreen = SnekScreen.ready
private fun defaultApple(boardSize: IntegerSize, snekPath: IntegerPath): IntegerPoint = SnekDataGenerator.generateApplePosition(boardSize, snekPath)
private val defaultKeymap = Keymap()
private val defaultDebug = false


private fun <
        NumberType : Number,
        PointType : ComputablePoint<NumberType>,
        SegmentType : ComputableLineSegment<NumberType, PointType>
        >
        ComputableSize<NumberType>.randomPointNotOnPath(snekPath: ComputablePath<NumberType, PointType, SegmentType>, maxAttempts: Integer = 100)
        : Point<NumberType> {
    var point = randomPoint()
    for (i in 1..maxAttempts) {
        if (snekPath.contains(point)) {
            point = randomPoint()
        } else {
            break
        }
    }
    return point
}
