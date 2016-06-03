package com.github.geoand.jkong.proxy.entry.match

import com.github.geoand.jkong.proxy.RequestHostAndPath
import com.github.geoand.jkong.proxy.entry.ProxyEntry

/**
 * Created by gandrianakis on 3/6/2016.
 */
interface RequestToProxyEntryMatcher {

    fun match(request: RequestHostAndPath): ProxyEntry?
}