@file:Repository("https://jcenter.bintray.com/")
@file:DependsOn("com.beust:klaxon:5.0.1")

import com.beust.klaxon.JsonArray
import com.beust.klaxon.Klaxon
import systems.danger.kotlin.*

val q = "\""
val rawCmnd = "ktlint -a --color --reporter=json"

class Report(
    val file: String,
    val errors: ArrayList<Error>
)

class Error(
    val line: Int,
    val column: Int,
    val message: String,
    val rule: String
)

fun runKlint(files: Array<FilePath>): String {
    val paths = files.joinToString(prefix = q, postfix = q, separator = " ")
    val process = Runtime.getRuntime().exec(arrayOf("/bin/bash", "-c", "$q$rawCmnd $paths$q"))
    process.waitFor()
    val txt = process.inputStream.bufferedReader().readText()
    return txt
}

fun convertStringToErrorObjects(string: String): List<Report> {
    if (string.isEmpty()) return emptyList()
    println("convertStringToErrorObjects string = ${string}")
    return Klaxon()
        .parseArray(string) ?: emptyList()
}

fun checkDetekt(danger: DangerDSL, git: Git) {
    if (git.modifiedFiles.size < 0) return
    println("checkDetekt ${git.modifiedFiles.size} modified files")
    val outputString = runKlint(git.modifiedFiles)
    val report = convertStringToErrorObjects(outputString)

    report.forEach { report ->
        report.errors.forEach {
            warn(it.message, report.file, it.line)
        }
    }
}

danger(args) {
    val danger = this

    onGitHub {
        message("onGithub done")
    }

    onGit {
        message("Kltint version: '${danger.utils.exec("ktlint --version")}'")
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
