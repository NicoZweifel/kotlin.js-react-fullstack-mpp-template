# kotlin.js-react-fullstack-template


- Since all the samples and templates i could find were using npm-create-kotlin-react-app or using outdated/obsolete libraries,
  i created one from scratch and now i thought why not share it.

- This is a sample/template for a multi-platform project targeting jvm backend and js frontend.

Includes:

- Common module for sharing code between the 2 targets.

- There is no packages-config.json or webpack-config.js in the project folder, everything is built using gradle.

- Task that runs the packaged .jar:

> ./gradlew run

- Dependencies used for js target:
```kt
val jsMain by getting {
    dependencies {
        implementation(kotlin("stdlib-js"))
        implementation("org.jetbrains.kotlinx:kotlinx-html-js:$kotlinxHtmlVersion")
        implementation("org.jetbrains:kotlin-react:$reactKtVersion")
        implementation("org.jetbrains:kotlin-react-dom:$reactDomKtVersion")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$serializationVersion")
        implementation("org.jetbrains:kotlin-css:$wrapperVersion")
        implementation("org.jetbrains:kotlin-css-js:$wrapperVersion")
        implementation("org.jetbrains:kotlin-styled:$wrapperVersion")
        implementation("org.jetbrains:kotlin-extensions:$kotlinExtensionsVersion")
        //npm
        implementation(npm("react", reactVersion))
        implementation(npm("react-dom", reactDomVersion))
        implementation(npm("react-is",reactVersion))
        implementation(npm("styled-components",styledComponentsVersion))
        implementation(npm("inline-style-prefixer",inlinePrefixerVersion ))
    }
}
```

- WebPack:
```kt
val jsBrowserWebpack by tasks.named<KotlinWebpack>("jsBrowserWebpack")

val jvmJar by tasks.named<Jar>("jvmJar") {
    dependsOn(jsBrowserWebpack)
    from(jsBrowserWebpack.entry.name,jsBrowserWebpack.destinationDirectory)
}

val run by tasks.register<JavaExec>("run") {
    dependsOn("jvmJar")
    group = "application"
    main = "template.ProgramKt"
    classpath(configurations.named("jvmRuntimeClasspath"), jvmJar )
}
```

- React Example is in App.kt:
```kt
class App: RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        welcome()
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

fun RBuilder.welcome(name: String = "REACT WORLD!!!") = child(Greet::class) {
    attrs.name = name
}
```

Im sorry if there's anything wrong with it, i am new to react :)
