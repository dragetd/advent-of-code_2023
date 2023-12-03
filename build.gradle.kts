plugins {
    kotlin("jvm") version "1.9.20"
    application
}

group = "net.speciesm.draget"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.seleniumhq.selenium:selenium-java:4.15.0")
    implementation("com.github.ajalt.clikt:clikt:4.2.1")

    testImplementation(kotlin("test"))
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
