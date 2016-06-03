package com.github.geoand.jkong.extensions

import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek

/**
 * Created by gandrianakis on 3/6/2016.
 */
class ListCombineSpec : Spek({
    describe("list.combine") {

        val first = listOf("Kotlin", "Groovy")
        val second = setOf("rocks", "kicks-ass", "is awesome")

        it("should return a list of Pair objects containing all combinations") {
            val combinations = first.combine(second)

            assertThat(combinations).containsOnly(
                    Pair("Kotlin", "rocks"),
                    Pair("Kotlin", "kicks-ass"),
                    Pair("Kotlin", "is awesome"),
                    Pair("Groovy", "rocks"),
                    Pair("Groovy", "kicks-ass"),
                    Pair("Groovy", "is awesome")
            )
        }

        it("should return a list of strings that represent the joint strings of all combinations") {
            val combinations = first.combine(second, {first, second -> "$first $second"})

            assertThat(combinations).containsOnly(
                    "Kotlin rocks",
                    "Kotlin kicks-ass",
                    "Kotlin is awesome",
                    "Groovy rocks",
                    "Groovy kicks-ass",
                    "Groovy is awesome"
            )
        }
    }
})