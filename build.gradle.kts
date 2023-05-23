import net.researchgate.release.ReleaseExtension

val linkHomepage by extra("https://qameta.io/allure")
val linkCi by extra("https://ci.qameta.io/job/allure-teamcity")
val linkScmUrl by extra("https://github.com/allure-framework/allure-teamcity")
val linkScmConnection by extra("scm:git:git://github.com/allure-framework/allure-teamcity.git")
val linkScmDevConnection by extra("scm:git:ssh://git@github.com:allure-framework/allure-teamcity.git")

tasks.wrapper {
    gradleVersion = "7.5.1"
}

plugins {
    id("org.owasp.dependencycheck") version "7.4.4"
    id("net.researchgate.release") version "3.0.2"
}

description = "Allure Teamcity"
group = "io.qameta.allure"

configurations.all {
    resolutionStrategy.eachDependency {
        when (requested.module.toString()) {
            "xml-apis:xml-apis" -> useVersion("1.4.01")
        }
    }
}

configure<ReleaseExtension> {
    tagTemplate.set("$version")
}

tasks.afterReleaseBuild {
    dependsOn(":allure-teamcity-server:publishPlugin")
}
