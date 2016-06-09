package com.github.geoand.kkong.proxy.entry

import com.github.geoand.kkong.extensions.withBothIfMissing
import com.github.geoand.kkong.extensions.withLeadingIfMissing
import com.github.geoand.kkong.proxy.RequestHostAndPath
import com.google.common.base.Strings

/**
 * Created by gandrianakis on 2/6/2016.
 *
 * TODO This needs to be revisited so support various global and type specific options
 */
data class ProxyEntry(val requestHost: String? = null, val requestPath: String? = null,
                      val type: ProxyTargetType = ProxyTargetType.UPSTREAM_URL,
                      val stripPath: Boolean = true,
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
            return  request.path.withBothIfMissing(SLASH).startsWith(requestPath?.withBothIfMissing(SLASH) ?: "", true)
        }
        else {
            return requestHost.withLeadingIfMissing(HTTP_SCHEME).equals(request.host.withLeadingIfMissing(HTTP_SCHEME), true)
        }
    }


}