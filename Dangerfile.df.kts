@file:Repository("https://jcenter.bintray.com/")
@file:DependsOn("com.beust:klaxon:5.0.1")

import com.beust.klaxon.Klaxon
import systems.danger.kotlin.*

val prefix = """$""""
val sufix = """""""
val rawCmnd = "ktlint -a --color --reporter=json"
val ktlintCMD = "${prefix}$rawCmnd"

class Error(
    val line: Int,
    val column: Int,
    val message: String,
    val rules: String
)

fun checkDetekt(danger: DangerDSL, git: Git) {
    git.modifiedFiles.forEach { filePath ->
        val cmndToRun = """$ktlintCMD $filePath$sufix"""
        // val cmndToRun = rawCmnd
        val result = danger.utils.exec(cmndToRun, emptyList())

        // val cmndToRun = """ktlint "$it""""
        // val cmnd = danger.utils.exec(cmndToRun)
        println("run: $rawCmnd $filePath")

        if (result.isNullOrEmpty()) {
            message("Modified file - $filePath")
        } else {
            Klaxon()
                .parse<List<Error>>(result)
                ?.let { findings ->
                    findings.forEach {
                        warn(it.rules, filePath, it.line)
                    }
                } ?: run {
                        warn("Modified file - $filePath; $result", filePath, 0)
                     }

            message("Command $cmndToRun")
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
        message(
            "onGit done modifiedFiles: ${
                this.modifiedFiles.size
            }, createdFiles: ${
                this.createdFiles.size
            }, deletedFiles: ${
                this.deletedFiles.size
            }"
        )
    }
}
