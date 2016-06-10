package com.github.geoand.kkong

import com.github.geoand.kkong.config.AllConfigLoader
import com.github.geoand.kkong.dsl.serverOf
import com.github.geoand.kkong.modules.BaseProxyModule
import org.slf4j.LoggerFactory.getLogger
import ratpack.server.RatpackServer
import ratpack.server.ServerConfigBuilder

object Main {
    private val log = getLogger(Main::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val proxyServer = createProxyServer()
            proxyServer.start()
            proxyServer.logComponentStart("Proxy")

            val managementServer = createManagementServer()
            managementServer.start()
            managementServer.logComponentStart("Management")
        }
        catch (e: Exception) {
            log.error("Unable to start application", e)
            System.exit(1)
        }
    }

    private fun createProxyServer() = serverOf {
        serverConfig {
            createServerConfig("proxy.")
        }

        guiceRegistry {
            module(BaseProxyModule())
        }

        handlers {
            all(BaseProxyModule.proxyHandler())
        }
    }

    private fun createManagementServer() = serverOf {
        serverConfig {
            createServerConfig("management.")
        }

        handlers {
            all {
                render("this is the management component")
            }
        }
    }

    private fun ServerConfigBuilder.createServerConfig(propsNamePrefix: String) {
        AllConfigLoader.load(this, propsNamePrefix).build()
    }

    private fun RatpackServer.logComponentStart(componentName: String) = log.info("Component '$componentName' listening on $scheme://$bindHost:$bindPort")
}
