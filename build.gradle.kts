import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm") version "1.9.22"
}

repositories {
    mavenCentral()
    maven("https://repo.tabooproject.org/repository/releases")
    maven("https://repo.papermc.io/repository/maven-public/")
}

val taboolibVersion: String by project
dependencies {
    compileOnly("ink.ptms:nms-all:1.0.0")
    compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")
    compileOnly(kotlin("stdlib"))
    compileOnly(fileTree("libs"))

    compileOnly("com.mojang:authlib:1.5.25")

    compileOnly("io.izzel.taboolib:common:${taboolibVersion}")
    compileOnly("io.izzel.taboolib:common-legacy-api:${taboolibVersion}")
    compileOnly("io.izzel.taboolib:common-env:${taboolibVersion}")
    compileOnly("io.izzel.taboolib:common-platform-api:${taboolibVersion}")
    compileOnly("io.izzel.taboolib:common-reflex:${taboolibVersion}")
    compileOnly("io.izzel.taboolib:common-util:${taboolibVersion}")
    compileOnly("io.izzel.taboolib:module-bukkit-util:${taboolibVersion}")
    compileOnly("io.izzel.taboolib:module-bukkit-xseries:${taboolibVersion}")
    compileOnly("io.izzel.taboolib:module-kether:${taboolibVersion}")
    compileOnly("io.izzel.taboolib:module-configuration:${taboolibVersion}")
    compileOnly("io.izzel.taboolib:module-database:${taboolibVersion}")
    compileOnly("io.izzel.taboolib:module-ui:${taboolibVersion}")
    compileOnly("io.izzel.taboolib:module-chat:${taboolibVersion}")
    compileOnly("io.izzel.taboolib:module-lang:${taboolibVersion}")
    compileOnly("io.izzel.taboolib:module-nms:${taboolibVersion}")
    compileOnly("io.izzel.taboolib:platform-bukkit:${taboolibVersion}")
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf(
            "-Xjvm-default=all",
            // "-opt-in=kotlin.RequiresOptIn",
            // "-Xuse-experimental=kotlin.contracts.ExperimentalContracts"
        )
    }
}

publishing {
    repositories {
        maven {
            url = uri("https://repo.tabooproject.org/repository/releases")
            credentials {
                username = project.findProperty("taboolibUsername").toString()
                password = project.findProperty("taboolibPassword").toString()
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
        }
    }
}