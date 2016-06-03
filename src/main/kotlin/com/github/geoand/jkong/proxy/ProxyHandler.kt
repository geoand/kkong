package com.github.geoand.jkong.proxy

import com.github.geoand.jkong.proxy.entry.match.RequestToProxyEntryMatcher
import com.github.geoand.jkong.proxy.entry.uri.ProxyUriCreator
import org.slf4j.LoggerFactory
import ratpack.handling.Context
import ratpack.handling.InjectionHandler
import ratpack.http.client.HttpClient
import ratpack.http.client.RequestSpec
import ratpack.http.client.StreamedResponse

/**
 * Created by gandrianakis on 3/6/2016.
 */
class ProxyHandler : InjectionHandler() {

    private val log = LoggerFactory.getLogger(ProxyHandler::class.java)

    fun handle(context: Context?, requestToProxyEntryMatcher: RequestToProxyEntryMatcher, proxyUriCreator: ProxyUriCreator) {
        if(null == context) {
            return //this never happens. it's only there to satisfy Kotlin's compiler so that we can use ctx as Context instead of Context?
        }

        val request = context.request
        val hostAndPath = fromRatpackRequest(request)
        val matchingProxyEntry = requestToProxyEntryMatcher.match(RequestHostAndPath(request.headers.get("Host"), request.path))
        if(null == matchingProxyEntry) {
            log.info("Request $hostAndPath does match any proxy entry")
            context.next()
            return
        }

        val httpClient = context.get(HttpClient::class.java)
        val proxyUri = proxyUriCreator.create(request, matchingProxyEntry)

        httpClient.requestStream(proxyUri) { spec: RequestSpec ->
            spec.headers.copy(request.headers)
        }.then { responseStream: StreamedResponse ->
            responseStream.forwardTo(context.response)
        }

    }
}