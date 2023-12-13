plugins {
    kotlin("jvm") version "1.9.20"
    application
}

group = "net.speciesm.draget"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val koin_version = "3.5.1"
dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:4.15.0")
    implementation("com.github.ajalt.clikt:clikt:4.2.1")
    implementation("org.reflections:reflections:0.9.12")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.20")

    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.24.2")
    //testImplementation("io.insert-koin:koin-test-junit5:$koin_version")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}
