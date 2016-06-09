package com.github.geoand.kkong.proxy.basic

import com.github.geoand.kkong.modules.BaseProxyModule
import com.github.geoand.kkong.proxy.registry.ProxyEntryRegistry

/**
 * Created by gandrianakis on 3/6/2016.
 */
class EmbeddedProxyModule extends BaseProxyModule {

    @Override
    Class<ProxyEntryRegistry> entryRegistryClass() {
        return EmbeddedProxyEntryRegistry
    }
}
