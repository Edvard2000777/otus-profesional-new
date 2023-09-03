import org.gradle.internal.impldep.com.google.common.collect.FluentIterable.from


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
    implementation("com.google.guava:guava:32.0.1-jre")


}


tasks.shadowJar {
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