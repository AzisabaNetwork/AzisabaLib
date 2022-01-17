plugins {
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

dependencies {
    api(project(":common"))
    api(project(":spigot-legacy"))
    api(project(":spigot-modern"))
    api(project(":paper"))
    api(project(":velocity"))
}

tasks {
    withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
        relocate("net.kyori", "net.azisaba.library.libs.net.kyori")
        archiveFileName.set("AzisabaLib-${parent!!.version}.jar")
    }
}
