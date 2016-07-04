package com.github.geoand.kkong.proxy.basic

import com.github.geoand.kkong.modules.BaseProxyModule
import ratpack.groovy.test.embed.GroovyEmbeddedApp
import ratpack.guice.Guice
import ratpack.test.embed.EmbeddedApp
import ratpack.test.http.TestHttpClient
import spock.lang.Shared
import spock.lang.Specification

import static com.github.geoand.kkong.proxy.basic.EmbeddedProxyConsts.*

class ProxyBasicIntegrationSpec extends Specification {

    @Shared
    EmbeddedApp aut = GroovyEmbeddedApp.of {
        registry Guice.registry {
            it.module EmbeddedProxyModule
        }

        handlers {
            all(BaseProxyModule.proxyHandler())
        }
    }

    @Delegate
    TestHttpClient testClient = aut.httpClient

	@Shared
	EmbeddedApp proxiedHost = GroovyEmbeddedApp.of {
        serverConfig {
            port(EMBEDDED_APP_PORT)
        }

		handlers {
			all {
				render "rendered ${request.rawUri}"
			}
		}
	}

    void setup() {
        final proxiedUri = proxiedHost.address //without referencing the proxiedHost, the embedded app seems to not be started at all
    }

    def "GET request to non-stripped path is correctly proxied"() {
		expect:
		    getText("$NON_STRIPPED_PROXY_PATH") == "rendered $NON_STRIPPED_PROXY_PATH".toString()
	}

    def "GET request to stripped path is correctly proxied"() {
        expect:
            getText("$STRIPPED_PROXY_PATH/p2") == "rendered /p2".toString()
    }
}
