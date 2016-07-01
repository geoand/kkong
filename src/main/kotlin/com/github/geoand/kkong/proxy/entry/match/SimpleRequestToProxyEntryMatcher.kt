package com.github.geoand.kkong.proxy.entry.match

import com.github.geoand.kkong.proxy.RequestHostAndPath
import com.github.geoand.kkong.proxy.entry.ProxyEntry
import com.github.geoand.kkong.proxy.registry.ProxyEntryRegistry
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SimpleRequestToProxyEntryMatcher @Inject constructor(val proxyEntryRegistry: ProxyEntryRegistry) : RequestToProxyEntryMatcher {

    private val log = LoggerFactory.getLogger(SimpleRequestToProxyEntryMatcher::class.java)

    override fun match(request: RequestHostAndPath): ProxyEntry? {
        log.trace("Looking up matching proxy entry for request: $request")

        val allEntries = proxyEntryRegistry.all()

        val matchingEntry = allEntries.firstOrNull { it.match(request) }
        if(null == matchingEntry) {
            log.debug("No matching proxy entry found for request: $request")
        }
        else {
            log.debug("Request: $request check proxy entry $matchingEntry")
        }

        return matchingEntry
    }
}