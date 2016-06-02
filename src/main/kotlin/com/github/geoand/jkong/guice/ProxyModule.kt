package com.github.geoand.jkong.guice

import com.github.geoand.jkong.proxy.registry.HardCodedProxyEntryRegistry
import com.github.geoand.jkong.proxy.registry.ProxyEntryRegistry
import com.github.geoand.jkong.proxy.registry.ProxyEntryRegistryHandler
import com.google.inject.AbstractModule

/**
 * Created by gandrianakis on 2/6/2016.
 */
class ProxyModule : AbstractModule(){

    override fun configure() {
        bind(ProxyEntryRegistry::class.java).to(HardCodedProxyEntryRegistry::class.java)
        bind(ProxyEntryRegistryHandler::class.java)
    }
}