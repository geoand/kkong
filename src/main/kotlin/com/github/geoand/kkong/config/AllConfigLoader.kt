package com.github.geoand.kkong.config

import ratpack.server.ServerConfigBuilder

/**
 * Created by gandrianakis on 3/6/2016.
 */
object AllConfigLoader {

    @JvmStatic fun load(configDataBuilder: ServerConfigBuilder, prefix: String = ""): ServerConfigBuilder {
        FileConfigLoader.load(configDataBuilder, prefix) {
            if(prefix.isEmpty()) {
                env()
                sysProps()
            }
            else {
                env(prefix.toUpperCase())
                sysProps(prefix)
            }

        }

        return configDataBuilder
    }
}