package components

import react.*
import styled.css
import styled.styledDiv
import styles.ComponentStyles

class App: RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        greet()
    }
}

//Example
interface GreetProps: RProps {
    var name: String
}

class Greet: RComponent<GreetProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                +ComponentStyles.primary
            }
            +"HELLO, ${props.name}"
        }
    }
}

fun RBuilder.greet(name: String = "REACT WORLD!!!") = child(Greet::class) {
    attrs.name = name
}