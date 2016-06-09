package com.github.geoand.jkong.proxy.registry

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
            final sut = new JsonConfigProxyEntryRegistry(mapper, "{}")

        expect:
            assertThat(sut.proxyEntries).isEmpty()

        where:
            configuration << ["", "{entries}"]
    }

    def "empty contents"() {
        given:
            final sut = new JsonConfigProxyEntryRegistry(mapper, "{}")

        expect:
            assertThat(sut.proxyEntries).isEmpty()
    }

    def "empty entries array"() {
        given:
            final sut = new JsonConfigProxyEntryRegistry(mapper, "{\"entries\": []}")

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
                        "path": "/first",
                        "value": "http://first.api.example.com"
                    },
                    {
                        "path": "/second",
                        "value": "http://second.api.example.com"
                    },
                    {
                        "host": "http://third.com",
                        "value": "http://third.api.example.com"
                    }
                ]
            }
            '''
        and:
            final sut = new JsonConfigProxyEntryRegistry(mapper, jsonContent)

        when:
            final entries = sut.proxyEntries

        then:
            entries.size() == 3

        and:
            entries.first().requestPath == '/first'

        and:
            entries.last().requestHost == "http://third.com"

        and:
            assertThat(entries*.targetValue).containsOnly("http://first.api.example.com", "http://second.api.example.com", "http://third.api.example.com")
    }
}
