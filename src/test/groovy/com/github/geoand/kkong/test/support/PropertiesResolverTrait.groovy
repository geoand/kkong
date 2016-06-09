package com.github.geoand.kkong.test.support

import groovy.transform.Memoized

/**
 * Created by gandrianakis on 3/6/2016.
 */
trait PropertiesResolverTrait {

    String resolveProperty(String property) {
        return loadProperties().get(property)
    }

    @Memoized
    private Properties loadProperties() {
        final props = new Properties()
        new File(this.getClass().getResource( '/application.properties' ).toURI()).withInputStream {
            stream -> props.load(stream)
        }

        return props
    }

}