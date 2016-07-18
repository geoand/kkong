package com.github.geoand.kkong.proxy.registry

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.geoand.kkong.proxy.Options
import com.github.geoand.kkong.proxy.ProxyAPIActions
import com.github.geoand.kkong.proxy.StaticTargetDelegatingProxyAPIActions
import com.github.geoand.kkong.proxy.request.RequestPathMatcher
import com.google.inject.name.Named
import org.slf4j.LoggerFactory
import java.net.URI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JsonConfigProxyActionsRegistry @Inject constructor(private val mapper: ObjectMapper, @Named("JsonConfigContent") private val jsonContents: String) : ProxyActionsRegistry {

    private val log = LoggerFactory.getLogger(JsonConfigProxyActionsRegistry::class.java)

    private val proxyEntries: List<ProxyAPIActions> by lazy {

        val configMap = getContents()

        @Suppress("UNCHECKED_CAST")
        val configMapEntries = (configMap["entries"] ?: listOf<Map<String, String>>()) as List<Map<String, String>>

        //for the time being we have one type -> static
        val staticEntries = configMapEntries.filter {
            it.getOrElse("type", {"static"}) == "static"
        }.map { entry ->
            StaticTargetDelegatingProxyAPIActions(
                    entry["name"] ?: throw IllegalArgumentException("Each proxy entry must provide a name"),
                    Options(true, false), //hard-coded for now
                    RequestPathMatcher(entry["path"] ?: throw IllegalArgumentException("All path matching entries must provide a valid path")),
                    URI(entry["value"] ?: throw IllegalArgumentException("All static proxy entries must supply a value"))
            )
        }

        staticEntries
    }

    private fun getContents(): Map<*, *> {
        try {
            return mapper.readValue(jsonContents, Map::class.java)
        } catch(e: Exception) {
            log.error("Json contents not valid")
            throw e
        }
    }

    override fun all(): List<ProxyAPIActions> {
        return proxyEntries
    }
}