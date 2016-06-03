package com.github.geoand.jkong.config

import ratpack.server.ServerConfigBuilder

/**
 * Created by gandrianakis on 3/6/2016.
 */
object AllConfigLoader {

    @JvmStatic fun load(configDataBuilder: ServerConfigBuilder): ServerConfigBuilder {
        FileConfigLoader
                .load(configDataBuilder)
                .env()
                .sysProps()

        return configDataBuilder
    }
}