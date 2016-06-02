package com.github.geoand.jkong.proxy.registry

import com.github.geoand.jkong.proxy.entry.ProxyEntry

/**
 * Created by gandrianakis on 2/6/2016.
 */
interface ProxyEntryRegistry {

    fun all(): List<ProxyEntry>
}