package com.github.geoand.kkong.proxy

import ratpack.http.Request

/**
 * Created by gandrianakis on 3/6/2016.
 */
data class RequestHostAndPath(val host: String, val path: String)

fun fromRatpackRequest(request: Request) : RequestHostAndPath {
    return RequestHostAndPath(request.headers.get("Host"), request.path)
}