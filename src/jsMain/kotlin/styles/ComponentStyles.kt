package styles

import kotlinx.css.*
import styled.StyleSheet

object ComponentStyles : StyleSheet("ComponentStyles", isStatic = true) {
    val primary by css{
        applyMargin()
        applyPadding()
        color = Colors.DarkMain.textOnPrimary
        backgroundColor = Colors.DarkMain.primary
    }

    private fun CSSBuilder.applyMargin(){
        margin = "10px"
    }
    private fun CSSBuilder.applyPadding(){
        padding = "10px"
    }
}