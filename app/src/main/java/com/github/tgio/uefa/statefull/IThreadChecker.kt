package com.github.tgio.uefa.statefull

interface IThreadChecker {
    fun isMainThread(): Boolean
}