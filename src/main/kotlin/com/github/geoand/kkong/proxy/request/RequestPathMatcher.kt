package com.github.geoand.kkong.proxy.request

import com.github.geoand.kkong.extensions.withBothIfMissing
import com.github.geoand.kkong.extensions.withLeadingIfMissing
import com.github.geoand.kkong.proxy.Options
import com.github.geoand.kkong.proxy.RequestHostAndPath

class RequestPathMatcher(private val matchPath: String): RequestMatcher {

    override fun check(request: RequestHostAndPath, options: Options): RequestMatcherResult {
        val requestPathWithSlashes = request.path.addSlashes()
        val matches = requestPathWithSlashes.startsWith(matchPath.withBothIfMissing("/"), true)

        return RequestMatcherResult(matches, createEffectivePath(options, request, requestPathWithSlashes))
    }

    private fun createEffectivePath(options: Options, request: RequestHostAndPath, requestPathWithSlashes: String): String {
        val pathNoSlashes =
                (if (options.stripPath)
                    "${requestPathWithSlashes.replaceFirst(matchPath, "")}"
                 else
                    request.path
                ).trim('/')

        return if(pathNoSlashes.isEmpty()) "" else "/$pathNoSlashes"
    }

    private fun String.addSlashes() = if(this.contains('?')) this.withLeadingIfMissing("/").replace("?", "/?") else this.withBothIfMissing("/")
}