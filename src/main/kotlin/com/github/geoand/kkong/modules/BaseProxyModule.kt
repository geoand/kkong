package com.github.geoand.kkong.modules

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.geoand.kkong.proxy.ProxyHandler
import com.github.geoand.kkong.proxy.registry.JsonConfigProxyActionsRegistry
import com.github.geoand.kkong.proxy.registry.ProxyActionsRegistry
import com.google.common.io.Resources
import com.google.inject.AbstractModule
import com.google.inject.name.Names

/**
 * Created by gandrianakis on 2/6/2016.
 */
open class BaseProxyModule : AbstractModule(){

    override fun configure() {
        bind(ProxyActionsRegistry::class.java).to(entryRegistryClass())

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

    open fun entryRegistryClass(): Class<out ProxyActionsRegistry> = JsonConfigProxyActionsRegistry::class.java

    companion object {
        @JvmStatic fun proxyHandler() : ProxyHandler = ProxyHandler()
    }

}