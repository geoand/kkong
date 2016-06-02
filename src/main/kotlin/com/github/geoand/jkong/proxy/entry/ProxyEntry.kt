package com.github.geoand.jkong.proxy.entry

import com.google.common.base.Strings

/**
 * Created by gandrianakis on 2/6/2016.
 */
data class ProxyEntry(val requestHost: String? = null, val requestPath: String? = null,
                      val type: ProxyTargetType = ProxyTargetType.UPSTREAM_URL,
                      val targetValue: String) {

    init {
        require(!Strings.isNullOrEmpty(requestHost) || !Strings.isNullOrEmpty(requestPath)) {
            "One of requestHost,requestPath must be non-empty"
        }
    }
}