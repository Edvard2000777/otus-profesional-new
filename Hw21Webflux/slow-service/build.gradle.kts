plugins {
    id("java")
    id ("org.springframework.boot") version ("2.7.16")
    id ("io.spring.dependency-management") version ("1.0.15.RELEASE")
}

group = "ru.otus.pro.Yaremchuk"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.springframework.boot:spring-boot-starter-web")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    compileOnly ("org.projectlombok:lombok:1.18.20")
    annotationProcessor ("org.projectlombok:lombok:1.18.20")
}

tasks.test {
    useJUnitPlatform()
}