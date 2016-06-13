package com.github.geoand.kkong.proxy.host

import rx.Observable

/**
 * Created by gandrianakis on 13/6/2016.
 */
interface HostResolver {

    fun resolve(): Observable<String>
}