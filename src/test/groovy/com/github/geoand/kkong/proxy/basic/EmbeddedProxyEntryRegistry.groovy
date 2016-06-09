package com.github.geoand.kkong.proxy.basic

import com.github.geoand.kkong.proxy.entry.ProxyEntry
import com.github.geoand.kkong.proxy.entry.ProxyTargetType
import com.github.geoand.kkong.proxy.registry.ProxyEntryRegistry

import static com.github.geoand.kkong.proxy.basic.EmbeddedProxyConsts.EMBEDDED_APP_PORT
import static com.github.geoand.kkong.proxy.basic.EmbeddedProxyConsts.PROXY_PATH

/**
 * Created by gandrianakis on 3/6/2016.
 */
class EmbeddedProxyEntryRegistry implements ProxyEntryRegistry {

    @Override
    List<ProxyEntry> all() {
        return [new ProxyEntry(null, PROXY_PATH, ProxyTargetType.UPSTREAM_URL, false,  "http://localhost:${EMBEDDED_APP_PORT}")]
    }
}
