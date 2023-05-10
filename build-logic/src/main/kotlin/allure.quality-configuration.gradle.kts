
import org.gradle.api.plugins.quality.Checkstyle

plugins {
    id ("java")
    id ("ru.vyarus.quality")
}

val root = rootProject.projectDir
val gradleScriptDir by extra("$root/gradle")

quality {
    configDir = "$gradleScriptDir/quality-configs"
    excludeSources = fileTree("build/generated-sources")
    exclude("**/*.json")
    checkstyleVersion = "8.36.1"
    pmdVersion = "6.28.0"
    spotbugsVersion = "4.1.2"
    codenarcVersion = "1.6"
    spotbugs = true
    codenarc = true
    pmd = true
    checkstyle = true
    htmlReports = false

    afterEvaluate {
        val spotbugs = configurations.findByName("spotbugs")
        if (spotbugs != null) {
            dependencies {
                spotbugs("org.slf4j:slf4j-simple")
                spotbugs("com.github.spotbugs:spotbugs:4.7.3")
            }
        }
    }
}

tasks {
    withType(Checkstyle::class).configureEach {
        configDirectory.set(file("$gradleScriptDir/quality-configs/checkstyle"))
    }
}
