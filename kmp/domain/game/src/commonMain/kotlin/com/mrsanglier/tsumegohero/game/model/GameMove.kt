package com.mrsanglier.tsumegohero.game.model

interface GameMove

data object Pass : GameMove

data class Cell(
    val x: Int,
    val y: Int,
)
