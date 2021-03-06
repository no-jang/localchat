import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
    application
    idea

    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"

    id("com.google.protobuf") version "0.8.19"
    id("com.google.devtools.ksp") version "1.7.10-1.0.6"

    id("com.github.ben-manes.versions") version "0.42.0"
}

// General

group = "de.localchat"
version = "0.1.0-alpha.1"

// Source

sourceSets {
    main {
        java {
            srcDir(layout.buildDirectory.dir("generated/ksp/main/kotlin"))
        }
    }
}

// Compiler

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(18))
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.6.1"
    }
}

// Running

application {
    mainClass.set("de.localchat.Main")
}

// Dependencies

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // Web
    implementation("io.javalin:javalin:4.6.4")
    implementation("com.j2html:j2html:1.6.0")

    // Data
    implementation("com.google.protobuf:protobuf-java:3.21.1")
    implementation("org.json:json:20220320")

    // Dependency Injection
    implementation("io.insert-koin:koin-core:3.2.0")
    implementation("io.insert-koin:koin-annotations:1.0.1")
    implementation("io.insert-koin:koin-logger-slf4j:3.2.0")
    //TODO Remove if pull request accepted
    //ksp("io.insert-koin:koin-ksp-compiler:1.0.1")
    ksp("io.insert-koin:koin-annotations:1.0.1")
    ksp(fileTree("libs/ksp"))
    testImplementation("io.insert-koin:koin-test:3.2.0")

    // Network
    implementation("io.netty:netty5-handler:5.0.0.Alpha3")
    implementation("io.netty.contrib:netty-codec-extras:5.0.0.Alpha1")

    implementation("io.netty:netty5-transport-classes-epoll:5.0.0.Alpha3")
    implementation("io.netty:netty5-transport-classes-kqueue:5.0.0.Alpha3")
    runtimeOnly("io.netty:netty5-transport-native-epoll:5.0.0.Alpha3:linux-x86_64")
    runtimeOnly("io.netty:netty5-transport-native-kqueue:5.0.0.Alpha3:osx-x86_64")

    // Logger
    implementation("com.github.tinylog-org.tinylog:tinylog-api:v3.0-SNAPSHOT")
    implementation("com.github.tinylog-org.tinylog:tinylog-api-kotlin:v3.0-SNAPSHOT")
    implementation("com.github.tinylog-org.tinylog:tinylog-impl:v3.0-SNAPSHOT")
    implementation("com.github.tinylog-org.tinylog:slf4j-tinylog:v3.0-SNAPSHOT")

    // Testing
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.0-RC1")
}