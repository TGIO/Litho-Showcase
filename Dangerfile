warn("Big PR") if git.lines_of_code > 500

kotlin_detekt.skip_gradle_task = true
kotlin_detekt.detekt(inline_mode: true)

android_lint.skip_gradle_task = true
android_lint.lint(inline_mode: true)

github.dismiss_out_of_range_messages
