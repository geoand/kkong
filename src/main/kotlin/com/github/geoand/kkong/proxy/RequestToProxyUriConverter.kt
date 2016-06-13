package com.github.geoand.kkong.proxy

import rx.Observable
import java.net.URI

/**
 * Created by gandrianakis on 13/6/2016.
 */
interface RequestToProxyUriConverter {

    fun convert(request: RequestHostAndPath) : Observable<URI>
}