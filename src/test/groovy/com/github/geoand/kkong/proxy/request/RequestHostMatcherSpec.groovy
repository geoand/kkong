package com.github.geoand.kkong.proxy.request

import com.github.geoand.kkong.proxy.Options
import com.github.geoand.kkong.proxy.RequestHostAndPath
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by gandrianakis on 13/6/2016.
 */
class RequestHostMatcherSpec extends Specification {

    @Unroll
    def "host matches"(String entryHost, String requestHost) {
        given:
            final entry = new RequestHostMatcher(entryHost)

        expect:
            entry.check(new RequestHostAndPath(requestHost, 'whatever'), new Options(false, false)).matches

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
    def "host does not match"(String entryHost, String requestHost) {
        given:
            final entry = new RequestHostMatcher(entryHost)

        expect:
            !(entry.check(new RequestHostAndPath(requestHost, 'whatever'), new Options(false, false)).matches)

        where:
            entryHost | requestHost
            'someapi' | 'https://someapi'
            'someapi' | 'someapi2'
            'http://someapi' | 'https://someapi'
    }
}
