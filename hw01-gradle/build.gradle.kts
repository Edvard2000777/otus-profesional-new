plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/com.google.guava/guava
    implementation("com.google.guava:guava:32.0.1-jre")


}

tasks.register<Jar>("createJar") {
    archiveBaseName.set("my-app")
    archiveVersion.set("1.0")
    from(sourceSets.main.get().output)

    manifest {
        attributes["Main-Class"] = "org.example.HelloOtus"
    }
}
tasks.test {
    useJUnitPlatform()
}