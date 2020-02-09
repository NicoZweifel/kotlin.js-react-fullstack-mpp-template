# kotlin.js-react-fullstack-template


- Since all the samples and templates i could find were using npm-create-kotlin-react-app or using outdated/obsolete libraries,
  i created one from scratch.

- This is a sample/template for a multi-platform project targeting Jvm for the server and Js for the frontend.
- There is a common module for sharing code between the 2 targets + tests for each target.

- There is no packages-config.json or webpack-config.js, everything is done with gradle.

- Task that runs the packaged .jar:

> ./gradlew run


dependencies used:

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


React Example is in App.kt:

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

Im sorry if there's anything wrong with it, i am new to react. :)
