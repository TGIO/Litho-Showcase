import systems.danger.kotlin.*

fun checkDetekt(danger: DangerDSL, git: Git) {
    git.modifiedFiles.forEach {
        warn("Modified file - $it", it, 0)
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
