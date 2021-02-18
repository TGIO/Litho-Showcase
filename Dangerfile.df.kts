@file:DependsOn("com.gianluz:danger-kotlin-android-lint-plugin:0.1.0")

import com.gianluz.dangerkotlin.androidlint.AndroidLint
import com.pinterest.ktlint.KtlintCommandLine
import systems.danger.kotlin.*


fun checkDetekt(danger: DangerDSL) {
    danger.git.modifiedFiles.forEach {
        message("Modified file", it, 0)
    }
    danger.git.createdFiles.forEach {
        message("Created file", it, 0)
    }
}

register plugin AndroidLint

danger(args) {
    val danger = this

    KtlintCommandLine().run()
    onGitHub {
        // val github = this
        checkDetekt(danger)
        message("onGithub done")

        danger.utils.exec("./gradlew", listOf("lint"))
        AndroidLint.report("androidlint.xml")
        message("androidLint done")
    }
}
