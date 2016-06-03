package com.github.geoand.jkong.proxy.basic

import com.github.geoand.jkong.modules.BaseProxyModule
import ratpack.groovy.test.embed.GroovyEmbeddedApp
import ratpack.guice.Guice
import ratpack.test.embed.EmbeddedApp
import ratpack.test.http.TestHttpClient
import spock.lang.Shared
import spock.lang.Specification

import static com.github.geoand.jkong.proxy.basic.EmbeddedProxyConsts.EMBEDDED_APP_PORT
import static com.github.geoand.jkong.proxy.basic.EmbeddedProxyConsts.PROXY_PATH

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

    def "get request to ratpack is proxied to the embedded app"(String extraPath) {
		expect:
		    getText("$PROXY_PATH/$extraPath") == "rendered $PROXY_PATH/${extraPath}".toString()

		where:
		    extraPath << [""]
	}
}
