import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.5.0-M2"
	kotlin("jvm") version "1.4.30"
	kotlin("plugin.spring") version "1.4.30"
}

group = "de.imedia24"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
	implementation {
		exclude(module = "spring-boot-starter-logging")
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-log4j2")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.flywaydb:flyway-core")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.h2database:h2:1.4.200")
	implementation("org.springframework.boot:spring-boot-starter-test:2.4.3")
	implementation ("org.projectlombok:lombok:1.18.2")
	implementation("org.springframework.data:spring-data-jpa:2.4.7")
	implementation ("org.hibernate:hibernate-validator:7.0.1.Final")
	implementation ("org.springframework.boot:spring-boot-starter-validation:2.4.4")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation ("org.hibernate:hibernate-gradle-plugin:5.4.30.Final")
	implementation ("org.springframework.boot:spring-boot-starter-web")
	implementation ("org.springframework.boot:spring-boot-starter-test" )
	implementation ("org.junit.jupiter:junit-jupiter-api")
	implementation ("org.junit.jupiter:junit-jupiter-engine")
	implementation ("org.junit.platform:junit-platform-launcher")
	implementation ( "org.mockito:mockito-junit-jupiter")
	compile( "io.springfox:springfox-swagger2:2.9.2")
	compile ("io.springfox:springfox-swagger-ui:2.9.2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}


tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
