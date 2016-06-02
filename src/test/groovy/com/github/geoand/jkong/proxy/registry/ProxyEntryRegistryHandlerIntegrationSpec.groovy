package com.github.geoand.jkong.proxy.registry

import com.github.geoand.jkong.proxy.entry.ProxyEntry
import com.github.geoand.jkong.proxy.entry.ProxyTargetType
import groovy.json.JsonSlurper
import org.assertj.core.api.Assertions
import ratpack.test.embed.EmbeddedApp
import spock.lang.Specification

/**
 * Created by gandrianakis on 2/6/2016.
 */
class ProxyEntryRegistryHandlerIntegrationSpec extends Specification {

    def "result is the expected json"() {
        given:
            final ProxyEntryRegistry registry = Mock()

        and:
            registry.all() >> [new ProxyEntry(null, 'path', ProxyTargetType.UPSTREAM_URL, 'target')]

        and:
            final client = EmbeddedApp.fromHandler(new ProxyEntryRegistryHandler(registry)).httpClient

        when:
            final List<Map<String, Object>> entries = new JsonSlurper().parse(client.get().body.bytes)

        then:
            Assertions.assertThat(entries).hasSize(1)
    }
}
