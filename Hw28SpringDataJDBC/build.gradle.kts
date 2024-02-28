plugins {
    id("java")
    id("org.springframework.boot") version ("3.2.0")
    id("io.spring.dependency-management") version ("1.1.4")
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

  dependencies {
      testImplementation(platform("org.junit:junit-bom:5.9.1"))
      testImplementation("org.junit.jupiter:junit-jupiter")
      implementation ("org.springframework.boot:spring-boot-starter-data-jdbc")
      implementation ("org.springframework.boot:spring-boot-starter-web")
      implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
      runtimeOnly ("com.h2database:h2")
      implementation ("org.flywaydb:flyway-core")
      testImplementation ("org.springframework.boot:spring-boot-starter-test")
      compileOnly("org.projectlombok:lombok")

}

tasks.test {
    useJUnitPlatform()
}