
plugins {
    id ("io.github.rodm.teamcity-base")
}

teamcity {
    version = (project.findProperty("teamcityVersion") ?: "2022.04") as String
}
