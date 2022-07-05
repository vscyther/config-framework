plugins {
    kotlin("jvm") version "1.7.0"
}

group = "com.github.vscyther.config"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = "https://maven.elmakers.com/repository/")
}

dependencies {
    api(kotlin("stdlib-jdk8"))
    compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")
}