package com.github.geoand.kkong.proxy

import com.github.geoand.kkong.proxy.request.RequestMatcher
import com.github.geoand.kkong.proxy.request.RequestMatcherResult
import ratpack.http.Request
import rx.Observable
import java.net.URI

/**
 * Created by gandrianakis on 1/7/2016.
 */
class StaticTargetDelegatingProxyAPIActions(name: String,
                                            options: Options,
                                            requestMatcher: RequestMatcher,
                                            private val targetURI: URI): AbstractDelegatingProxyAPIActions(name, options, requestMatcher) {

    override fun doConvert(request: Request, matchesResult: RequestMatcherResult): Observable<URI> {
        val requestUri = URI(request.rawUri)

        val proxyUri = URI(
                targetURI.scheme,
                targetURI.userInfo,
                targetURI.host,
                targetURI.port,
                matchesResult.effectivePath,
                requestUri.query,
                requestUri.fragment
        )

        return Observable.just(proxyUri)
    }
}