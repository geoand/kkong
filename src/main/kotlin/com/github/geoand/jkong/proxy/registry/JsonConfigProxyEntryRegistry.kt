package com.github.geoand.jkong.proxy.registry

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.geoand.jkong.proxy.entry.ProxyEntry
import com.google.inject.name.Named
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JsonConfigProxyEntryRegistry @Inject constructor(private val mapper: ObjectMapper, @Named("JsonConfigContent") private val jsonContents: String) : ProxyEntryRegistry {

    private val log = LoggerFactory.getLogger(JsonConfigProxyEntryRegistry::class.java)

    private val proxyEntries: List<ProxyEntry> by lazy {
        try {
            val configMap = mapper.readValue(jsonContents, Map::class.java)

            val configMapEntries = (configMap["entries"] ?: listOf<Map<String, String>>()) as List<Map<String, String>>

            configMapEntries.map { entry ->
                ProxyEntry(
                        requestHost = entry["host"],
                        requestPath = entry["path"],
                        targetValue = entry["value"] ?: throw IllegalArgumentException("Missing value")
                )
            }
        } catch(e: Exception) {
            log.error("Json configuration not valid", e)
            listOf<ProxyEntry>()
        }
    }

    override fun all(): List<ProxyEntry> {
        return proxyEntries
    }
}