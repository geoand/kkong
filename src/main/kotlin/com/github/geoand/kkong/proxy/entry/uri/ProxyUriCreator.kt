package com.github.geoand.kkong.proxy.entry.uri

import com.github.geoand.kkong.proxy.entry.ProxyEntry
import ratpack.http.Request
import java.net.URI

/**
 * Created by gandrianakis on 3/6/2016.
 */
interface ProxyUriCreator {

    fun create(request: Request, proxyEntry: ProxyEntry) : URI
}