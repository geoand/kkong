package com.github.geoand.jkong.proxy.entry

import com.github.geoand.jkong.extensions.withBothIfMissing
import com.github.geoand.jkong.extensions.withLeadingIfMissing
import com.github.geoand.jkong.proxy.RequestHostAndPath
import com.google.common.base.Strings

/**
 * Created by gandrianakis on 2/6/2016.
 *
 * TODO This needs to be revisited so support various global and type specific options
 */
data class ProxyEntry(val requestHost: String? = null, val requestPath: String? = null,
                      val type: ProxyTargetType = ProxyTargetType.UPSTREAM_URL,
                      val targetValue: String) {

    private val SLASH = "/"
    private val HTTP_SCHEME = "http://"

    init {
        require(!Strings.isNullOrEmpty(requestHost) || !Strings.isNullOrEmpty(requestPath)) {
            "One of requestHost,requestPath must be non-empty"
        }
    }

    fun match(request: RequestHostAndPath): Boolean {
        if(null == requestHost) {
            return requestPath?.withBothIfMissing(SLASH) == request.path.withBothIfMissing(SLASH)
        }
        else {
            return requestHost.withLeadingIfMissing(HTTP_SCHEME) == request.host.withLeadingIfMissing(HTTP_SCHEME)
        }
    }


}