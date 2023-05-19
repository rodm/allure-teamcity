
rootProject.name = "allure-teamcity"

dependencyResolutionManagement {
    versionCatalogs {
        register("libs") {
            library ("lombok","org.projectlombok:lombok:1.18.24")

            library ("jackson.databind","com.fasterxml.jackson.core:jackson-databind:2.14.1")
            library ("commons.logging","commons-logging:commons-logging:1.2")
            library ("commons.compress","org.apache.commons:commons-compress:1.21")
            library ("commons.lang3","org.apache.commons:commons-lang3:3.12.0")
            library ("commons.io","commons-io:commons-io:2.11.0")
        }
    }
}

includeBuild ("build-logic")

include("allure-teamcity-common")
include("allure-teamcity-agent")
include("allure-teamcity-server")
