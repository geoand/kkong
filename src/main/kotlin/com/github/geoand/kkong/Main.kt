package com.github.geoand.kkong

import com.github.geoand.kkong.config.AllConfigLoader
import com.github.geoand.kkong.dsl.serverOf
import com.github.geoand.kkong.modules.BaseProxyModule
import org.slf4j.LoggerFactory.getLogger

object Main {
    private val log = getLogger(Main::class.java)

    @JvmStatic fun main(args: Array<String>) {
        try {
            createGatewayServer().start()
        }
        catch (e: Exception) {
            log.error("Unable to start application", e)
            System.exit(1)
        }
    }

    fun createGatewayServer() = serverOf {
        serverConfig {
            AllConfigLoader.load(this).build()
        }

        guiceRegistry {
            module(BaseProxyModule())
        }

        handlers {
            all(BaseProxyModule.proxyHandler())
        }
    }
}
