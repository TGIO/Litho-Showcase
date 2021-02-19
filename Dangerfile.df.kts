@file:DependsOn("com.gianluz:danger-kotlin-android-lint-plugin:0.1.0")

import com.gianluz.dangerkotlin.androidlint.AndroidLint
import systems.danger.kotlin.*

val ktlintCMD = "ktlint -a --color --reporter=plain?group_by_file"

fun checkDetekt(danger: DangerDSL, git: Git) {
    git.modifiedFiles.forEach {
        val cmnd = danger.utils.exec(ktlintCMD, listOf("\"${it}\""))
        message("Modified file - ${it}; cmnd $cmnd; ktlintCMD $ktlintCMD ${"\"${it}\""}", it, 0)
    }
    git.createdFiles.forEach {
        message("Created file", it, 0)
    }
}

register plugin AndroidLint

danger(args) {
    val danger = this

    onGitHub {
        // val github = this
        message("onGithub done")

        // AndroidLint.report("app/build/reports/lint-results.xml")
        message("androidLint done")
    }

    onGit {
        checkDetekt(danger, this)
    }
}
