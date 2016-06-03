package com.github.geoand.jkong.modules

import com.github.geoand.jkong.proxy.ProxyHandler
import com.github.geoand.jkong.proxy.entry.match.RequestToProxyEntryMatcher
import com.github.geoand.jkong.proxy.entry.match.SimpleRequestToProxyEntryMatcher
import com.github.geoand.jkong.proxy.entry.uri.CopyValueProxyUriCreator
import com.github.geoand.jkong.proxy.entry.uri.ProxyUriCreator
import com.github.geoand.jkong.proxy.registry.HardCodedProxyEntryRegistry
import com.github.geoand.jkong.proxy.registry.ProxyEntryRegistry
import com.google.inject.AbstractModule

/**
 * Created by gandrianakis on 2/6/2016.
 */
open class BaseProxyModule : AbstractModule(){

    override fun configure() {
        bind(ProxyEntryRegistry::class.java).to(entryRegistryClass())
        bind(RequestToProxyEntryMatcher::class.java).to(SimpleRequestToProxyEntryMatcher::class.java)
        bind(ProxyUriCreator::class.java).to(CopyValueProxyUriCreator::class.java)

        bind(ProxyHandler::class.java)
    }

    open fun entryRegistryClass(): Class<out ProxyEntryRegistry> = HardCodedProxyEntryRegistry::class.java

    companion object {
        @JvmStatic fun proxyHandler() : ProxyHandler = ProxyHandler()
    }

}