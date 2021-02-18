@file:DependsOn("com.gianluz:danger-kotlin-android-lint-plugin:0.1.0")

import com.gianluz.dangerkotlin.androidlint.AndroidLint
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

    onGitHub {
        // val github = this
        checkDetekt(danger)
        message("onGithub done")

//         AndroidLint.report("app/build/reports/lint-results.xml")
        message("androidLint done")
    }
}
