package com.github.geoand.kkong.proxy.request

import com.github.geoand.kkong.extensions.withLeadingIfMissing
import com.github.geoand.kkong.proxy.Options
import com.github.geoand.kkong.proxy.RequestHostAndPath

class RequestHostMatcher(private val host: String) : RequestMatcher {

    private val HTTP_SCHEME = "http://"

    override fun check(request: RequestHostAndPath, options: Options): RequestMatcherResult {
        val matches =  host.withLeadingIfMissing(HTTP_SCHEME).equals(request.host.withLeadingIfMissing(HTTP_SCHEME), true)
        return RequestMatcherResult(matches, request.path)
    }
}