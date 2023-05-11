
plugins {
    id ("teamcity.agent-plugin")
}

description = "allure-teamcity-plugin-agent"

teamcity {
    agent {
        archiveName = project.name
        descriptor {
            pluginDeployment {
                useSeparateClassloader = true
            }
        }
    }
}

dependencies {
    annotationProcessor("org.projectlombok:lombok")
    compileOnly("org.projectlombok:lombok")

    implementation(project(":allure-teamcity-common"))
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("commons-io:commons-io")
    implementation("commons-logging:commons-logging")
    implementation("org.apache.commons:commons-compress")
    implementation("org.apache.commons:commons-lang3")

    provided("org.jetbrains.teamcity.internal:agent")
}
