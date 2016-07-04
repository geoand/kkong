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
            return //this never happens. It's only there to satisfy Kotlin's compiler so that we can use ctx as Context instead of Context?
        }

        val request = context.request

        val allActions = proxyActionsRegistry.all()

        val matching = allActions.firstOrNull() {it.supports(context.request)}

        if(null == matching) {
            log.info("Request $request does match any proxy entry")
            context.next()
        }
        else {
            //for now the simplest thing to do is just use the first result
            matching.convert(request).first().subscribe({ proxyUri ->
                val httpClient = context.get(HttpClient::class.java)

                httpClient.requestStream(proxyUri) { spec: RequestSpec ->
                    spec.headers.copy(request.headers)
                }.then { responseStream: StreamedResponse ->
                    responseStream.forwardTo(context.response)
                }
            })
        }

    }
}