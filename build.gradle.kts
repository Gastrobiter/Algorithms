plugins {
    id("scala")
    id("idea")

    application
}

object Ext {
    const val scalaVersion = "2.11.12"
    const val scalaSuffix = "2.11"
    const val scalaTestJUnitRunner = "0.1.7"
    const val jUnitPlatform = "1.6.2"
}

repositories {
    mavenCentral()
}

fun scalaSuffix(packageName: String): String {
    return packageName + Ext.scalaSuffix
}

dependencies {
    // For running just with Spark Core
    implementation(group = "org.scala-lang", name = "scala-library", version = Ext.scalaVersion)

    //  Support libraries
    testImplementation(group = "org.scalatest", name = scalaSuffix("scalatest_"), version = "3.2.10")
    testRuntimeOnly(group = "co.helmethair", name = "scalatest-junit-runner", version = Ext.scalaTestJUnitRunner)
    testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-engine", version = Ext.jUnitPlatform)
    testRuntimeOnly(group = "org.junit.platform", name = "junit-platform-launcher", version = Ext.jUnitPlatform)
}

tasks {
    test {
        useJUnitPlatform()
    }
}