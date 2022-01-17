plugins {
    java
    `java-library`
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
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(8))
        }
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
