package template

import components.App
import kotlin.browser.document
import react.dom.render


actual object Platform {
    actual val name: String = "JS"
}

fun main() {
    document.addEventListener("DOMContentLoaded", {
        render(document.getElementById("root")) {
            child(App::class) {}
        }
    })
}                