
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
    annotationProcessor (libs.lombok)
    compileOnly (libs.lombok)

    implementation (project(":allure-teamcity-common"))
    implementation (libs.jackson.databind)
    implementation (libs.commons.io)
    implementation (libs.commons.logging)
    implementation (libs.commons.compress)
    implementation (libs.commons.lang3)

    provided ("org.jetbrains.teamcity.internal:agent:${teamcity.version}")
}
