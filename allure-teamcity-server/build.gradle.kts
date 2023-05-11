
plugins {
    id ("teamcity.server-plugin")
    id ("teamcity.environments")
}

description = "allure-teamcity-plugin-server"

val teamcityVersion = (rootProject.extra["teamcityVersion"] as String)
val teamcityDir = (rootProject.extra["teamcityDir"] as String)
val teamcityFullDir = teamcityDir + "/TeamCity-" + teamcityVersion

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
        create("Teamcity20202") {
            version = "2020.2"
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
    provided(files("$teamcityFullDir/webapps/ROOT/WEB-INF/lib/server-tools.jar"))
}
