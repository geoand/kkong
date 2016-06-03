package com.github.geoand.jkong.proxy.basic

import com.github.geoand.jkong.modules.BaseProxyModule
import com.github.geoand.jkong.proxy.registry.ProxyEntryRegistry

/**
 * Created by gandrianakis on 3/6/2016.
 */
class EmbeddedProxyModule extends BaseProxyModule {

    @Override
    Class<ProxyEntryRegistry> entryRegistryClass() {
        return EmbeddedProxyEntryRegistry
    }
}
