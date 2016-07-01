package com.github.geoand.kkong.proxy

import com.github.geoand.kkong.proxy.request.RequestMatcher
import com.github.geoand.kkong.proxy.request.RequestMatcherResult
import ratpack.http.Request
import rx.Observable
import java.net.URI

abstract class AbstractDelegatingProxyAPIActions(private val name: String,
                                                 private val options: Options,
                                                 private val requestMatcher: RequestMatcher) : ProxyAPIActions {

    override fun name() = name

    override fun requestMatcher() = requestMatcher

    override fun options() = options

    override fun convert(request: Request): Observable<URI> {
        val matchesResult = requestMatcher.check(request.toRequestHostAndPath(), options())
        if(!matchesResult.matches) {
            return Observable.empty()
        }

        return doConvert(request, matchesResult)
    }

    abstract fun doConvert(request: Request, matchesResult: RequestMatcherResult): Observable<URI>

    fun Request.toRequestHostAndPath() = RequestHostAndPath(this.headers.get("Host"), this.path)


}

