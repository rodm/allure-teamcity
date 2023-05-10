
plugins {
    id ("java")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withJavadocJar()
    withSourcesJar()
}

tasks {
    compileTestJava {
        options.compilerArgs.add("-parameters")
    }

    jar {
        manifest {
            attributes(mapOf(
                    "Implementation-Title" to project.name,
                    "Implementation-Version" to project.version
            ))
        }
    }

    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
    }

    withType<Javadoc>().configureEach {
        (options as StandardJavadocDocletOptions).addStringOption("Xdoclint:none", "-quiet")
    }

    withType<GenerateModuleMetadata>().configureEach {
        enabled = false
    }
}
