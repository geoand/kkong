package com.github.geoand.kkong.proxy.request

import com.github.geoand.kkong.proxy.Options
import com.github.geoand.kkong.proxy.RequestHostAndPath

/**
 * Created by gandrianakis on 13/6/2016.
 */
interface RequestMatcher {

    fun check(request: RequestHostAndPath, options: Options): RequestMatcherResult
}