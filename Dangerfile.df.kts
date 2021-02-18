@file:DependsOn("com.gianluz:danger-kotlin-android-lint-plugin:0.1.0")

import com.gianluz.dangerkotlin.androidlint.AndroidLint
import com.gianluz.dangerkotlin.androidlint.androidLint
import systems.danger.kotlin.*


fun checkDetekt(danger: DangerDSL) {
    danger.git.modifiedFiles.forEach {
        message("Modified file", it, 0)
    }
}

register plugin AndroidLint

danger(args) {
    val danger = this

    onGitHub {
        val github = this
        checkDetekt(danger)
        message("onGithub done")

        androidLint {
            // Fail for each Fatal in a single module
            val moduleLintFilePaths = find(
                "app",
                "lint-results-debug.xml",
                "lint-results-release.xml"
            ).toTypedArray()

            parseAllDistinct(*moduleLintFilePaths).forEach {
                if(it.severity == "Fatal")
                    fail(
                        "Danger lint check failed: ${it.message}",
                        it.location.file.replace(System.getProperty("user.dir"), ""),
                        Integer.parseInt(it.location.line)
                    )
            }
            message("androidLint done")
        }
    }
}