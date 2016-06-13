package com.github.geoand.kkong.proxy.host

import rx.Observable

class SimpleHostResolver(private val host: String) : HostResolver {

    override fun resolve(): Observable<String> = Observable.just(host)
}