package com.github.geoand.jkong.proxy.entry

import com.github.geoand.jkong.proxy.RequestHostAndPath
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by gandrianakis on 3/6/2016.
 */
class ProxyEntryMatchSpec extends Specification {

    @Unroll
    def "host is null, path matches irrelevant of the leading slash"(String entryPath, String requestPath) {
        given:
            final entry = new ProxyEntry(null, entryPath, ProxyTargetType.UPSTREAM_URL, "")

        expect:
            entry.match(new RequestHostAndPath('whatever', requestPath))

        where:
            entryPath | requestPath
            "path" | "path"
            "path" | "/path"
            "path" | "path/"
            "path" | "/path/"
            "/path" | "path"
            "/path" | "/path"
            "/path" | "path/"
            "/path" | "/path/"
            "path/" | "path"
            "path/" | "/path"
            "path/" | "path/"
            "path/" | "/path/"
            "path/" | "/path"
            "/path/" | "path"
            "/path/" | "/path"
            "/path/" | "path/"
            "/path/" | "/path/"
            "path/path2" | "path/path2"
            "path/path2" | "/path/path2"
            "/path/path2" | "path/path2"
            "path/path2" | "/path/path2"
    }

    @Unroll
    def "host is null, path does not match"(String entryPath, String requestPath) {
        given:
            final entry = new ProxyEntry(null, entryPath, ProxyTargetType.UPSTREAM_URL, "")

        expect:
            !entry.match(new RequestHostAndPath('whatever', requestPath))

        where:
            entryPath | requestPath
            "path" | "path2"
            "path" | "/path2"
            "path" | "/path2"
            "path/path2" | "path/path3"
            "path/path2" | "/path/path3"
            "/path/path2" | "path/path3"
            "path/path2" | "/path/path3"
    }

    @Unroll
    def "host is set and matches"(String entryHost, String requestHost) {
        given:
            final entry = new ProxyEntry(entryHost, null, ProxyTargetType.UPSTREAM_URL, "")

        expect:
            entry.match(new RequestHostAndPath(requestHost, 'whatever'))

        where:
            entryHost | requestHost
            'someapi' | 'someapi'
            'someapi' | 'http://someapi'
            'http://someapi' | 'someapi'
            'http://someapi' | 'http://someapi'
            'someapi:8000' | 'http://someapi:8000'
            'http://someapi:8000' | 'http://someapi:8000'
    }

    @Unroll
    def "host is set but does not match"(String entryHost, String requestHost) {
        given:
            final entry = new ProxyEntry(entryHost, null, ProxyTargetType.UPSTREAM_URL, "")

        expect:
            !entry.match(new RequestHostAndPath(requestHost, 'whatever'))

        where:
            entryHost | requestHost
            'someapi' | 'https://someapi'
            'someapi' | 'someapi2'
            'http://someapi' | 'https://someapi'
    }
}
