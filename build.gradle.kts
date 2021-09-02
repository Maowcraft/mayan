plugins {
    idea
    `java-library`
}

group = "xyz.maow"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {

}

tasks {
    compileJava {
        options.compilerArgs.add("-XDenableSunApiLintControl")
    }
}