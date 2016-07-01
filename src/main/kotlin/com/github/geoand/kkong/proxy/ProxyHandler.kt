package com.github.geoand.kkong.proxy

import com.github.geoand.kkong.proxy.registry.ProxyActionsRegistry
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

    fun handle(context: Context?, proxyActionsRegistry: ProxyActionsRegistry) {
        if(null == context) {
            return //this never happens. it's only there to satisfy Kotlin's compiler so that we can use ctx as Context instead of Context?
        }

        val request = context.request

        val allActions = proxyActionsRegistry.all()

        /**
         * TODO
         *
         * 1. Find first observable that is not empty
         * 2. Subscribe to it
         * 3. Call the proxy code inside the subscriber
         */

        context.next()
//
//
//        if(null == matchingProxyEntry) {
//            log.info("Request $hostAndPath does match any proxy entry")
//            context.next()
//            return
//        }
//
//        val httpClient = context.get(HttpClient::class.java)
//
//        httpClient.requestStream(proxyUri) { spec: RequestSpec ->
//            spec.headers.copy(request.headers)
//        }.then { responseStream: StreamedResponse ->
//            responseStream.forwardTo(context.response)
//        }

    }
}