package sgfparsing

import com.mrsanglier.tsumegohero.game.model.Cell
import com.mrsanglier.tsumegohero.game.utils.SgfParser
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class SgfParserTest : FunSpec({
    SgfParser.apply {
        context("parseCell") {
            test("valid cells") {
                parseCell("bb") shouldBe Cell(1, 1)
                parseCell("ej") shouldBe Cell(4, 9)
                parseCell("ac") shouldBe Cell(0, 2)
            }
        }
    }
})
