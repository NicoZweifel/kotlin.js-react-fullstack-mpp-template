import org.jetbrains.kotlin.gradle.targets.js.webpack.*


plugins {
    kotlin("multiplatform") version "1.3.61"
}

val kotlinVersion = "1.3.61"
val ktorVersion = "1.3.1"
val logbackVersion = "1.2.3"
val kotlinxHtmlVersion = "0.7.1"
val wrapperVersion = "1.0.0-pre.91-kotlin-$kotlinVersion"
val reactVersion = "^16.9.0"
val reactKtVersion = "16.9.0-pre.91-kotlin-$kotlinVersion"
val reactDomVersion = "^16.9.0"
val reactDomKtVersion = "16.9.0-pre.91-kotlin-$kotlinVersion"
val materialVersion = "^4.9.1"
val serializationVersion = "0.14.0"
val coroutineVersion = "1.3.3"
val kotlinExtensionsVersion = "1.0.1-pre.91-kotlin-$kotlinVersion"
val inlinePrefixerVersion = "5.1.1"
val styledComponentsVersion = "^5.0.1"

version = "template-1.0.0"

repositories {
    jcenter()
    mavenCentral()
    maven(url = "https://dl.bintray.com/kotlin/ktor")
    maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
    maven(url = "http://dl.bintray.com/kotlinx/kotlinx")
    maven(url = "http://dl.bintray.com/kotlin/kotlin-js-wrappers")
}

kotlin {
    jvm()
    js {
        browser {}
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$serializationVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                implementation("io.ktor:ktor-server-netty:$ktorVersion")
                implementation("io.ktor:ktor-html-builder:$ktorVersion")
                implementation("ch.qos.logback:logback-classic:$logbackVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:$serializationVersion")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
            }
        }
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
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}
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