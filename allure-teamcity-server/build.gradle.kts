
plugins {
    id ("teamcity.server-plugin")
    id ("teamcity.environments")
}

description = "allure-teamcity-plugin-server"

val teamcityDir = (rootProject.extra["teamcityDir"] as String)

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
            serverOptions = rootProject.extra["serverOpts"] as String
        }
    }
}

dependencies {
    annotationProcessor("org.projectlombok:lombok")
    compileOnly("org.projectlombok:lombok")

    agent(project(path = ":allure-teamcity-agent", configuration = "plugin"))

    implementation(project(":allure-teamcity-common"))
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("commons-io:commons-io")
    implementation("org.apache.commons:commons-lang3")

    provided("org.jetbrains.teamcity.internal:server")
    provided("org.jetbrains.teamcity.internal:server-tools")
}
