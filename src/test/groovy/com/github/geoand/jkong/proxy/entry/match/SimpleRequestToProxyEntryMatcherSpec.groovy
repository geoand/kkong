package com.github.geoand.jkong.proxy.entry.match

import com.github.geoand.jkong.proxy.RequestHostAndPath
import com.github.geoand.jkong.proxy.entry.ProxyEntry
import com.github.geoand.jkong.proxy.entry.ProxyTargetType
import com.github.geoand.jkong.proxy.registry.ProxyEntryRegistry
import spock.lang.Specification

/**
 * Created by gandrianakis on 3/6/2016.
 */
class SimpleRequestToProxyEntryMatcherSpec extends Specification {

    final ProxyEntryRegistry proxyEntryRegistry = Mock()
    final RequestToProxyEntryMatcher proxyEntryMatcher = new SimpleRequestToProxyEntryMatcher(proxyEntryRegistry)

    def "empty proxy entry list"() {
        given:
            proxyEntryRegistry.all() >> []

        expect:
            proxyEntryMatcher.match(new RequestHostAndPath('host', 'path')) == null

    }

    def "no matching entries"() {
        given:
            proxyEntryRegistry.all() >> [new ProxyEntry('host', null, ProxyTargetType.UPSTREAM_URL, '')]

        expect:
            proxyEntryMatcher.match(new RequestHostAndPath('host2', 'path')) == null
    }

    def "single matching entry"() {
        given:
            final matchingEntry = new ProxyEntry('host', null, ProxyTargetType.UPSTREAM_URL, '')

        and:
            proxyEntryRegistry.all() >> [new ProxyEntry(null, 'path', ProxyTargetType.UPSTREAM_URL, ''), matchingEntry]

        expect:
            proxyEntryMatcher.match(new RequestHostAndPath('host', 'path2')) == matchingEntry
    }

    def "multiple matching entries, first returned"() {
        given:
            final firstMatchingEntry = new ProxyEntry('host', null, ProxyTargetType.UPSTREAM_URL, '')

        and:
            final secondMatchingEntry = new ProxyEntry(null, 'path', ProxyTargetType.UPSTREAM_URL, '')

        and:
            proxyEntryRegistry.all() >> [firstMatchingEntry, new ProxyEntry(null, 'path', ProxyTargetType.UPSTREAM_URL, ''), secondMatchingEntry]

        expect:
            proxyEntryMatcher.match(new RequestHostAndPath('host', 'path2')) == firstMatchingEntry
    }
}
