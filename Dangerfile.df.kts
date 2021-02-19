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

danger(args) {
    val danger = this

    onGitHub {
        // val github = this
        message("onGithub done")

    }

    onGit {
        checkDetekt(danger, this)
    }
}
