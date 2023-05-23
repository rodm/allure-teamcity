
plugins {
    id ("teamcity.server-plugin")
    id ("teamcity.environments")
}

description = "allure-teamcity-plugin-server"

val teamcityDir = "${rootDir}/.teamcity"
val serverOpts = "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=50055"

teamcity {
    server {
        archiveName = project.parent?.name
        descriptor {
            name = rootProject.name
            displayName = "Allure Report"
            version = rootProject.version as String?

            description = "Plugin adds support for generate Allure report based on tests results"

            vendorName = "qameta"
            vendorUrl = "https://qameta.io"
            email = "team@qameta.io"

            useSeparateClassloader = true
            nodeResponsibilitiesAware = true
        }
        publish {
            token = System.getenv("JETBRAINS_TOKEN")
        }
    }

    environments {
        create("Teamcity2022.04") {
            version = "2022.04"
            homeDir = "$teamcityDir/TeamCity-$version"
            dataDir = "$teamcityDir/data/$version"
            serverOptions (serverOpts)
        }
    }
}

dependencies {
    annotationProcessor (libs.lombok)
    compileOnly (libs.lombok)

    agent (project(path = ":allure-teamcity-agent", configuration = "plugin"))

    implementation (project(":allure-teamcity-common"))
    implementation (libs.jackson.databind)
    implementation (libs.commons.io)
    implementation (libs.commons.lang3)

    provided ("org.jetbrains.teamcity.internal:server:${teamcity.version}")
    provided ("org.jetbrains.teamcity.internal:server-tools:${teamcity.version}")
}
