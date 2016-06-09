package com.github.geoand.kkong.proxy.entry.match

import com.github.geoand.kkong.proxy.RequestHostAndPath
import com.github.geoand.kkong.proxy.entry.ProxyEntry

/**
 * Created by gandrianakis on 3/6/2016.
 */
interface RequestToProxyEntryMatcher {

    fun match(request: RequestHostAndPath): ProxyEntry?
}