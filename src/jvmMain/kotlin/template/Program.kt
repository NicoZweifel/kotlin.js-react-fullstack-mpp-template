package template

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.ContentType
import io.ktor.http.content.*
import io.ktor.response.respondText
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.html.*


actual object Platform {
    actual val name: String = "JVM"
}

fun main() {
    embeddedServer(Netty, port = 80, host = "127.0.0.1") {
        routing {
            get("/") {
                call.respondHtml {
                    head {
                        title("Kotlin.Js-React")
                    }
                    body {
                        div {
                            id = "root"
                        }
                        script(src = "/static/KotlinJs-React-Fullstack-Template.js") {}
                    }
                }
            }
            static("/static") {
                resource("KotlinJs-React-Fullstack-Template.js")
            }
        }
    }.start(wait = true)
}