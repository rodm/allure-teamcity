
plugins {
    id ("io.github.rodm.teamcity-base")
}

teamcity {
    version = rootProject.extra["teamcityVersion"] as String
}
