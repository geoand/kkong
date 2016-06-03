package com.github.geoand.jkong.proxy.registry

import com.github.geoand.jkong.proxy.entry.ProxyEntry

class HardCodedProxyEntryRegistry : ProxyEntryRegistry {

    override fun all(): List<ProxyEntry> {
        return listOf(ProxyEntry(requestPath = "/payments", targetValue = "http://172.29.126.178:11337"))
    }
}