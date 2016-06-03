package com.github.geoand.jkong.proxy.registry

import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.jackson.Jackson.json
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by gandrianakis on 2/6/2016.
 */
@Singleton
class ProxyEntryRegistryHandler @Inject constructor(val proxyEntryRegistry: ProxyEntryRegistry) : Handler {

    override fun handle(ctx: Context?) {
        ctx?.render(json(proxyEntryRegistry.all()))
    }
}