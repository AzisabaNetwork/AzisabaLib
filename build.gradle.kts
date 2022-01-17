plugins {
    java
    `java-library`
    `maven-publish`
}

group = "net.azisaba"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

subprojects {
    group = parent!!.group
    version = parent!!.version

    apply {
        plugin("java")
        plugin("java-library")
        plugin("maven-publish")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        compileOnly("net.kyori:adventure-api:4.9.3")
        compileOnly("org.jetbrains:annotations:23.0.0")
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }

    tasks {
        getByName<Test>("test") {
            useJUnitPlatform()
        }
    }
}

allprojects {
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(8))
        }

        withSourcesJar()
        withJavadocJar()
    }

    val javaComponent = components["java"] as AdhocComponentWithVariants
    javaComponent.withVariantsFromConfiguration(configurations["sourcesElements"]) {
        skip()
    }

    publishing {
        repositories {
            maven {
                name = "repo"
                credentials(PasswordCredentials::class)
                url = uri(
                    if (project.version.toString().endsWith("SNAPSHOT"))
                        project.findProperty("deploySnapshotURL") ?: System.getProperty("deploySnapshotURL", "")
                    else
                        project.findProperty("deployReleasesURL") ?: System.getProperty("deployReleasesURL", "")
                )
            }
        }

        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
                artifact(tasks.getByName("sourcesJar"))
            }
        }
    }
}
