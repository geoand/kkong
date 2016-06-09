package com.github.geoand.kkong.proxy.entry

import spock.lang.Specification

/**
 * Created by gandrianakis on 2/6/2016.
 */
class ProxyEntryCreateSpec extends Specification {

    def "missing both requestHost and requestPath"() {
        when:
            new ProxyEntry(null, null, ProxyTargetType.UPSTREAM_URL, true, "http://internal-api:8080/")

        then:
            thrown(IllegalArgumentException)
    }

    def "requestPath supplied"() {
        expect:
            new ProxyEntry(null, "/somepath", ProxyTargetType.UPSTREAM_URL, true, "http://internal-api:8080/")
    }

    def "requestHost supplied"() {
        expect:
            new ProxyEntry("http://external-api/path", null, ProxyTargetType.UPSTREAM_URL, true, "http://internal-api:8080/")
    }

    def "requestPath and requestHost supplied"() {
        expect:
            new ProxyEntry("http://external-api/path", "/somepath", ProxyTargetType.UPSTREAM_URL, true, "http://internal-api:8080/")
    }
}
