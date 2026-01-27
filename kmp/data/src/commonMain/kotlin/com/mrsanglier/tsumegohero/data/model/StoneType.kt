package com.mrsanglier.tsumegohero.data.model

enum class StoneType {
    BLACK, WHITE;

    companion object {
        fun StoneType.getOpponent(): StoneType =
            if (this == BLACK) WHITE else BLACK
    }
}