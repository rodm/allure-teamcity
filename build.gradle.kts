import net.researchgate.release.ReleaseExtension

val linkHomepage by extra("https://qameta.io/allure")
val linkCi by extra("https://ci.qameta.io/job/allure-teamcity")
val linkScmUrl by extra("https://github.com/allure-framework/allure-teamcity")
val linkScmConnection by extra("scm:git:git://github.com/allure-framework/allure-teamcity.git")
val linkScmDevConnection by extra("scm:git:ssh://git@github.com:allure-framework/allure-teamcity.git")

val root = rootProject.projectDir
val gradleScriptDir by extra("$root/gradle")
val qualityConfigsDir by extra("$gradleScriptDir/quality-configs")
val spotlessDtr by extra("$qualityConfigsDir/spotless")

val teamcityDir by extra("$root/.teamcity")
val serverOpts by extra("-DTC.res.disableAll=true " +
        "-Dteamcity.development.mode=true " +
        "-Dteamcity.development.shadowCopyClasses=true " +
        "-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=50055")

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
