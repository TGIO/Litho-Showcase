import systems.danger.kotlin.*

val prefix = """$""""
val sufix = """""""
val ktlintCMD = "${prefix}ktlint -a --color --reporter=json?group_by_file"

fun checkDetekt(danger: DangerDSL, git: Git) {
    git.modifiedFiles.forEach {
        val cmndToRun = """${ktlintCMD} $it$sufix"""
        val cmnd = danger.utils.exec(cmndToRun, emptyList())
        if (cmnd.isNullOrEmpty()) {
            message("Modified file - ${it}")
        } else {
            warn("Modified file - ${it}; $cmnd", it, 0)
        }
    }
    git.createdFiles.forEach {
        message("Created file", it, 0)
    }
}

danger(args) {
    val danger = this

    onGitHub {
        message("onGithub done")
    }

    onGit {
        checkDetekt(danger, this)
        message("onGit done modifiedFiles: ${this.modifiedFiles.size}, createdFiles: ${this.createdFiles.size}, deletedFiles: ${this.deletedFiles.size}")
    }
}
