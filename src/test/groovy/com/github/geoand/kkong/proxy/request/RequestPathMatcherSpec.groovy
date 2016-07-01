package com.github.geoand.kkong.proxy.request

import com.github.geoand.kkong.proxy.Options
import com.github.geoand.kkong.proxy.RequestHostAndPath
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by gandrianakis on 13/6/2016.
 */
class RequestPathMatcherSpec extends Specification {

    @Unroll
    def "path matches irrelevant of the leading slash and the request params"(String entryPath, String requestPath) {
        given:
            final entry = new RequestPathMatcher(entryPath)

        expect:
            entry.check(new RequestHostAndPath('whatever', requestPath), new Options(false, false)).matches

        where:
            entryPath | requestPath
            "path" | "path"
            "path" | "path/subpath"
            "path" | "path/subpath?val=123"
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
            "/path/" | "/path/subpath?val=123"
            "path/path2" | "path/path2"
            "path/path2" | "/path/path2"
            "/path/path2" | "path/path2"
            "path/path2" | "/path/path2"
            "path/path2" | "/path/path2?q=a"
    }

    @Unroll
    def "path does not match"(String entryPath, String requestPath) {
        given:
            final entry = new RequestPathMatcher(entryPath)

        expect:
            !(entry.check(new RequestHostAndPath('whatever', requestPath), new Options(false, false)).matches)

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
}
