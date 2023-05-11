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

val teamcityVersion by extra("2020.2")
val teamcityDir by extra("$root/.teamcity")
val serverOpts by extra("-DTC.res.disableAll=true " +
        "-Dteamcity.development.mode=true " +
        "-Dteamcity.development.shadowCopyClasses=true " +
        "-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=50055")

tasks.wrapper {
    gradleVersion = "7.5.1"
}

plugins {
    java
    signing
    `java-library`
    id("com.jfrog.bintray") version "1.8.5"
    id("com.gorylenko.gradle-git-properties") version "2.4.1"
    id("io.spring.dependency-management") version "1.1.0"
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


configure(subprojects) {

    apply(plugin = "maven-publish")
    apply(plugin = "io.spring.dependency-management")

    dependencyManagement {
        imports {
            mavenBom("com.fasterxml.jackson:jackson-bom:2.14.1")
            mavenBom("org.junit:junit-bom:5.9.2")
        }
        dependencies {
            dependency("commons-logging:commons-logging:1.2")
            dependency("org.apache.commons:commons-compress:1.21")
            dependency("org.apache.commons:commons-lang3:3.12.0")
            dependency("commons-io:commons-io:2.11.0")

            dependency("org.jetbrains.teamcity.internal:agent:$teamcityVersion")
            dependency("org.jetbrains.teamcity.internal:server:$teamcityVersion")

            dependency("org.projectlombok:lombok:1.18.24")

            dependencySet("org.slf4j:2.0.3") {
                entry("slf4j-api")
                entry("slf4j-nop")
                entry("slf4j-simple")
            }
        }
    }
}
