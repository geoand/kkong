package com.github.geoand.jkong

import com.github.geoand.jkong.dsl.serverOf
import org.slf4j.LoggerFactory.getLogger
import ratpack.handling.Context

object Main {
    private val log = getLogger(Main::class.java)

    @JvmStatic fun main(args: Array<String>) {
        try {
            createServer().start()
        }
        catch (e: Exception) {
            log.error("Unable to start application", e)
            System.exit(1)
        }
    }

    fun createServer() = serverOf {
        handlers {
            path("yo") { render("from the yo handler") }

            path("method", ::methodHandler)

            all { render("root handler!") }
        }
    }
}

/** A handler as a method */
fun methodHandler(context: Context) = context.render("from the method handler")
