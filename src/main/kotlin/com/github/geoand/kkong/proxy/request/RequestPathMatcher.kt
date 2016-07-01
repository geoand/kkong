package com.github.geoand.kkong.proxy.request

import com.github.geoand.kkong.extensions.withBothIfMissing
import com.github.geoand.kkong.extensions.withLeadingIfMissing
import com.github.geoand.kkong.proxy.Options
import com.github.geoand.kkong.proxy.RequestHostAndPath

class RequestPathMatcher(private val matchPath: String): RequestMatcher {

    private val SLASH = "/"

    override fun check(request: RequestHostAndPath, options: Options): RequestMatcherResult {
        val matches = request.path.addSlashes().startsWith(matchPath.withBothIfMissing(SLASH), true)

        return RequestMatcherResult(matches, if(options.stripPath) request.path.replace(matchPath, "") else request.path )
    }

    private fun String.addSlashes() = if(this.contains('?')) this.withLeadingIfMissing(SLASH).replace("?", "/?") else this.withBothIfMissing(SLASH)
}