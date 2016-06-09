package com.github.geoand.kkong.config

import com.github.geoand.kkong.test.support.PropertiesResolverTrait
import ratpack.groovy.test.embed.GroovyEmbeddedApp
import spock.lang.Specification

/**
 * Created by gandrianakis on 3/6/2016.
 */
class ConfigIntegrationSpec extends Specification implements PropertiesResolverTrait {

    def "configuration should be loaded and injected correctly into handler"() {

        given:
            final app = GroovyEmbeddedApp.of {
                serverConfig {
                    AllConfigLoader.load(it).require('/app', AppData)
                }

                handlers {
                    all { AppData appData ->
                        render appData.name
                    }
                }
            }

        and:
            final client = app.httpClient

        when:
            final response = client.get()

        then:
            response.body.text == resolveProperty('app.name')
    }
}
