package com.github.geoand.jkong

import ratpack.test.ApplicationUnderTest
import ratpack.test.MainClassApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by gandrianakis on 2/6/2016.
 */
class SimpleHandlerSpec extends Specification {

    @Shared
    ApplicationUnderTest aut = new MainClassApplicationUnderTest(Main)

    @Delegate
    TestHttpClient testClient = aut.httpClient

    def "/ path"() {
        expect:
            body("/").contains('root')
    }

    def "some ad-hoc path"() {
        expect:
            body("/adhoc").contains('root')
    }

    def "standard paths"(String path) {
        expect:
            body("/$path").contains(path)

        where:
            path | _
            "yo" | _
            "method" | _
    }

    private String body(String path) {
        return get(path).body.text
    }
}
