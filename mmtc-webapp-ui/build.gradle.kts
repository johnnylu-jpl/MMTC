plugins {
    base
}

val checkPnpm = tasks.register<Exec>("checkPnpm") {
    description = "Verify pnpm is installed"
    commandLine("pnpm", "--version")

    doFirst {
        try {
            val process = ProcessBuilder("command", "-v", "pnpm")
                .redirectErrorStream(true)
                .start()
            process.waitFor()
            if (process.exitValue() != 0) {
                throw GradleException("pnpm is not installed. Please install pnpm: npm install -g pnpm@latest")
            }
        } catch (e: Exception) {
            throw GradleException("pnpm is not installed. Please install pnpm: npm install -g pnpm@latest", e)
        }
    }

    // Don't fail the task if pnpm --version itself fails, doFirst already checked
    isIgnoreExitValue = false
}

val nuxtBuild = tasks.register<Exec>("nuxtBuild") {
    dependsOn(checkPnpm)
    inputs.dir(projectDir.toPath().resolve("mmtc-webapp-ui/app"))
    inputs.dir(projectDir.toPath().resolve("mmtc-webapp-ui/public"))

    inputs.file(projectDir.toPath().resolve("mmtc-webapp-ui/nuxt.config.ts"))
    inputs.file(projectDir.toPath().resolve("mmtc-webapp-ui/pnpm-lock.yaml"))
    inputs.file(projectDir.toPath().resolve("mmtc-webapp-ui/tsconfig.json"))

    workingDir("mmtc-webapp-ui")
    environment("NODE_OPTIONS", "--max-old-space-size=6144")
    executable("pnpm")
    args("exec", "nuxt", "generate")

    outputs.dir("mmtc-webapp-ui/.output/public/")
}

tasks {
    build {
        dependsOn(nuxtBuild)
    }

    clean {
        dependsOn("cleanNuxtBuild")
    }
}
