
plugins {
    id ("teamcity.common-library")
}

description = "allure-teamcity-plugin-common"

dependencies {
    annotationProcessor (libs.lombok)
    compileOnly (libs.lombok)
    implementation (libs.jackson.databind)
}
