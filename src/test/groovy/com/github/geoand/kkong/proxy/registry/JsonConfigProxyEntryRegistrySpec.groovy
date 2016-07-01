package com.github.geoand.kkong.proxy.registry

import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import static org.assertj.core.api.Assertions.assertThat

/**
 * Created by gandrianakis on 9/6/2016.
 */
class JsonConfigProxyEntryRegistrySpec extends Specification {

    @Shared ObjectMapper mapper = new ObjectMapper()

    @Unroll
    def "invalid configuration"() {
        given:
            final sut = new JsonConfigProxyActionsRegistry(mapper, "{}")

        expect:
            assertThat(sut.proxyEntries).isEmpty()

        where:
            configuration << ["", "{entries}"]
    }

    def "empty contents"() {
        given:
            final sut = new JsonConfigProxyActionsRegistry(mapper, "{}")

        expect:
            assertThat(sut.proxyEntries).isEmpty()
    }

    def "empty entries array"() {
        given:
            final sut = new JsonConfigProxyActionsRegistry(mapper, "{\"entries\": []}")

        expect:
            assertThat(sut.proxyEntries).isEmpty()
    }

    def "valid entries"() {
        given:
            final jsonContent =
            '''
            {
                "key": "value",
                "entries": [
                    {
                        "name": "first",
                        "path": "/first",
                        "value": "http://first.api.example.com"
                    },
                    {
                        "name": "second",
                        "path": "/second",
                        "value": "http://second.api.example.com"
                    }
                ]
            }
            '''
        and:
            final sut = new JsonConfigProxyActionsRegistry(mapper, jsonContent)

        when:
            final entries = sut.proxyEntries

        then:
            entries.size() == 2
    }
}
