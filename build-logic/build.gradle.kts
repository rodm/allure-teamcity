
plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation ("io.github.rodm:gradle-teamcity-plugin:1.5")
    implementation ("ru.vyarus:gradle-quality-plugin:4.7.0")
}
