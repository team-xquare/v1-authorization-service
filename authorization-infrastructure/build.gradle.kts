plugins {
    id("org.springframework.boot") version PluginVersions.SPRING_BOOT_VERSION
    id("io.spring.dependency-management") version PluginVersions.DEPENDENCY_MANAGER_VERSION
    kotlin("plugin.spring") version PluginVersions.SPRING_PLUGIN_VERSION
}

dependencies {
    implementation(Dependencies.ACTUATOR)
    implementation(Dependencies.WEBFLUX)
    implementation(Dependencies.KOTLIN_JACKSON)
    implementation(Dependencies.COROUTINE_REACTOR)
    implementation(Dependencies.COROUTINE_REACTOR_EXTENSION)
    implementation(Dependencies.KOTLIN_STDLIB)
    implementation(Dependencies.KOTLIN_REFLECT)
    implementation(Dependencies.SPRING_VALIDATION)
    implementation(Dependencies.REACTIVE_JDSL)
    implementation(Dependencies.REACTIVE_MYSQL)
    implementation(Dependencies.REACTIVE_HIBERNATE)
    implementation(Dependencies.MUTINY_KOTLIN)
    implementation(Dependencies.MUTINY_REACTOR)
    kapt(Dependencies.CONFIGURATION_PROCESSOR)
    testImplementation(Dependencies.SPRING_BOOT_TEST)
    testImplementation(Dependencies.EMBEDDED_MYSQL)
}