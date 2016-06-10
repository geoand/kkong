package com.github.geoand.kkong.extensions

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek

/**
 * Created by gandrianakis on 3/6/2016.
 */
class StringWithIfMissingSpec : Spek({

    describe("string.withLeadingIfMissing") {
        it("should simply return the string if it already starts with the lead") {
            assertThat("/path".withLeadingIfMissing("/")).isEqualTo("/path")

            assertThat("http://host:port/path".withLeadingIfMissing("http")).isEqualTo("http://host:port/path")
        }

        it("should prepend the lead to the string when the string does not start with the lead") {
            assertThat("path".withLeadingIfMissing("/")).isEqualTo("/path")

            assertThat("host:port/path".withLeadingIfMissing("http://")).isEqualTo("http://host:port/path")
        }
    }

    describe("string.withTrailingIfMissing") {
        it("should simply return the string if it already ends with the trail") {
            assertThat("path/".withTrailingIfMissing("/")).isEqualTo("path/")
        }

        it("should append the trail to the string when the string does not end with the trail") {
            assertThat("path".withTrailingIfMissing("/")).isEqualTo("path/")
        }
    }

    describe("string.withBothIfMissing") {
        it("should simply return the string if it already starts and ends with the trail") {
            assertThat("/path/".withBothIfMissing("/")).isEqualTo("/path/")
        }

        it("should prepend the lead to the string when the string does not start with the lead") {
            assertThat("path/".withBothIfMissing("/")).isEqualTo("/path/")
        }

        it("should append the trail to the string when the string does not end with the trail") {
            assertThat("/path".withBothIfMissing("/")).isEqualTo("/path/")
        }

        it("should both append and prepend to the string when it does start or end with it") {
            assertThat("path".withBothIfMissing("/")).isEqualTo("/path/")
        }
    }
})