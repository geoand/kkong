package com.github.geoand.jkong.modules

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.geoand.jkong.proxy.ProxyHandler
import com.github.geoand.jkong.proxy.entry.match.RequestToProxyEntryMatcher
import com.github.geoand.jkong.proxy.entry.match.SimpleRequestToProxyEntryMatcher
import com.github.geoand.jkong.proxy.entry.uri.CopyValueProxyUriCreator
import com.github.geoand.jkong.proxy.entry.uri.ProxyUriCreator
import com.github.geoand.jkong.proxy.registry.JsonConfigProxyEntryRegistry
import com.github.geoand.jkong.proxy.registry.ProxyEntryRegistry
import com.google.common.io.Resources
import com.google.inject.AbstractModule
import com.google.inject.name.Names

/**
 * Created by gandrianakis on 2/6/2016.
 */
open class BaseProxyModule : AbstractModule(){

    override fun configure() {
        bind(ProxyEntryRegistry::class.java).to(entryRegistryClass())
        bind(RequestToProxyEntryMatcher::class.java).to(SimpleRequestToProxyEntryMatcher::class.java)
        bind(ProxyUriCreator::class.java).to(CopyValueProxyUriCreator::class.java)

        bind(ProxyHandler::class.java)


        val mapper = ObjectMapper()
        bind(ObjectMapper::class.java).to(mapper)
        bindConstant()
                .annotatedWith(Names.named("JsonConfigContent"))
                .to(
                        Resources.toString(
                                BaseProxyModule::class.java.getResource("/proxy-config.json"),
                                Charsets.UTF_8
                        )
                )
    }

    open fun entryRegistryClass(): Class<out ProxyEntryRegistry> = JsonConfigProxyEntryRegistry::class.java

    companion object {
        @JvmStatic fun proxyHandler() : ProxyHandler = ProxyHandler()
    }

}