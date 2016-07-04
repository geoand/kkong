package com.github.geoand.kkong.proxy

import com.github.geoand.kkong.proxy.request.RequestMatcher
import ratpack.http.Request
import rx.Observable
import java.net.URI

/**
 * Created by gandrianakis on 13/6/2016.
 */
interface ProxyAPIActions {

    fun name(): String

    fun requestMatcher(): RequestMatcher

    fun options(): Options

    fun convert(request: Request) : Observable<URI>

    fun supports(request: Request) = requestMatcher().check(request.toRequestHostAndPath(), options()).matches
}

fun Request.toRequestHostAndPath() = RequestHostAndPath(this.headers.get("Host"), this.path)