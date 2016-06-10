package com.github.geoand.kkong.config

import com.github.geoand.kkong.dsl.serverOf
import org.jetbrains.spek.api.Spek
import kotlin.test.assertEquals

/**
 * Created by gandrianakis on 10/6/2016.
 */
class ServerConfigIntegrationSpec : Spek({

    describe("server port of Application should be populated using the following hierarchy application-properties < environment < system-properties") {

        it("should use port defined in 'server-props.application.properties'") {
            //given
            val app = createRatpackServerForTest()

            //when
            app.start()

            //then
            assertEquals(9997, app.bindPort)

            //cleanup
            app.stop()
        }

        it("should use port defined in system properties") {
            //given
            val portFromSystemProperties = 9999
            val propertyName = "server-props.server.port"
            System.setProperty(propertyName, portFromSystemProperties.toString())

            //and
            val app = createRatpackServerForTest()

            //when
            app.start()

            //then
            assertEquals(9999, app.bindPort)

            //cleanup
            System.clearProperty(propertyName)
            app.stop()
        }

    }


})

fun createRatpackServerForTest() = serverOf {
    serverConfig {
        AllConfigLoader.load(this, "server-props.").build()
    }

    handlers {
        all {
            render("text")
        }
    }
}