package net.speciesm.draget.utils

import java.net.HttpURLConnection
import java.net.URL

object AoCWeb {
    fun downloadInput(day: Int, session: String): String {
        return downloadURLContent("https://adventofcode.com/2023/day/$day/input", "session=$session")
    }

    private fun downloadURLContent(urlStr: String, cookie: String): String {
        val url = URL(urlStr)
        val connection = url.openConnection() as HttpURLConnection
        connection.setRequestProperty("Cookie", cookie)

        return connection.inputStream.bufferedReader().use {
            it.readText().trimEnd()
        }
    }
}
