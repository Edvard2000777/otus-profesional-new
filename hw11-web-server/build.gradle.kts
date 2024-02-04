plugins {
    id("java")
}

group = "ru.otus.pro.Yaremchuk"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.springframework.boot:spring-boot-starter-web:3.1.2")
    implementation ("org.springframework.boot:spring-boot-starter-thymeleaf:3.1.2")

    implementation("org.springframework:spring-context:6.0.13")
    implementation("javax.annotation:javax.annotation-api:1.3.2")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}