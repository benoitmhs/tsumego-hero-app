package sgfparsing

internal const val sgf1: String = """
(;GM[1]FF[4]CA[UTF-8]AP[CGoban:3]ST[2]
RU[Japanese]SZ[19]KM[0.00]
PW[White]PB[Black]AW[ba][ea][ab][eb][ec][ed][ae][be][ce][de]AB[ca][da][db][dc][ad][bd][cd][dd]
(;B[bc]
;W[bb]
;B[cb]C[+])
(;B[bb]
;W[bc]
;B[cb]
;W[ac])
(;B[cb]
;W[bc]
;B[bb]
;W[ac])
(;B[cc]
;W[bb])
(;B[ac]
;W[bb]))
"""

internal const val sgf2: String = """
(;GM[1]FF[4]CA[UTF-8]AP[CGoban:3]ST[2]
RU[Japanese]SZ[19]KM[0.00]
PW[Weiß]PB[Schwarz]AW[db][eb][cc][fc]AB[cb][fb][dc][ec]
(;B[da]
;W[bb]
(;B[ea]C[+])
(;B[ca]C[+]))
(;B[ea]
;W[gb]
(;B[da]C[+])
(;B[fa]C[+]))
(;B[ca]
;W[gb])
(;B[fa]
;W[bb])
(;B[gb]
;W[bb])
(;B[bb]
;W[gb]))
"""