
import java.nio.charset.StandardCharsets.UTF_8

plugins {
    id ("com.diffplug.spotless")
}

val root = rootProject.projectDir
val gradleScriptDir by extra("$root/gradle")
val qualityConfigsDir by extra("$gradleScriptDir/quality-configs")
val spotlessDtr by extra("$qualityConfigsDir/spotless")

spotless {
    java {
        target("src/**/*.java")
        removeUnusedImports()
        importOrder("", "jakarta", "javax", "java", "\\#")
        licenseHeader(file("$spotlessDtr/allure.java.license").readText(UTF_8))
        endWithNewline()
        replaceRegex("one blank line after package line", "(package .+;)\n+import", "$1\n\nimport")
        replaceRegex("one blank line after import lists", "(import .+;\n\n)\n+", "$1")
        replaceRegex("no blank line between jakarta & javax", "(import jakarta.+;\n)\n+(import javax.+;\n)", "$1$2")
        replaceRegex("no blank line between javax & java", "(import javax.+;\n)\n+(import java.+;\n)", "$1$2")
        replaceRegex("no blank line between jakarta & java", "(import jakarta.+;\n)\n+(import java.+;\n)", "$1$2")
    }
    format("misc") {
        target(
                "*.gradle",
                "*.gitignore",
                "README.md",
                "CONTRIBUTING.md",
                "config/**/*.xml",
                "src/**/*.xml"
        )
        trimTrailingWhitespace()
        endWithNewline()
    }

    encoding("UTF-8")
}
