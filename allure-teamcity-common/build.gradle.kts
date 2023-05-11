
plugins {
    id ("teamcity.common-library")
}

description = "allure-teamcity-plugin-common"

teamcity {
    version = rootProject.extra["teamcityVersion"] as String
}

dependencies {
    annotationProcessor("org.projectlombok:lombok")
    compileOnly("org.projectlombok:lombok")
    implementation("com.fasterxml.jackson.core:jackson-databind")
}
