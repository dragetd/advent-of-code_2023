package net.speciesm.draget

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option

class AoC2023 : CliktCommand() {
    val interactiveLogin by option("-i", "--interactive-login", help = "Start interactive login and store the AoC session.").flag()

    override fun run() {
        val aocSession = AoCSession().getSession(interactiveLogin);
        println("Session is: $aocSession")
    }
}

