package com.github.geoand.kkong.proxy.request

import com.github.geoand.kkong.extensions.withBothIfMissing
import com.github.geoand.kkong.extensions.withLeadingIfMissing
import com.github.geoand.kkong.proxy.RequestHostAndPath

class RequestPathMatcher(private val path: String): RequestMatcher {

    private val SLASH = "/"

    override fun matches(request: RequestHostAndPath): Boolean {
        return  request.path.addSlashes().startsWith(path.withBothIfMissing(SLASH), true)

    }

    private fun String.addSlashes() = if(this.contains('?')) this.withLeadingIfMissing(SLASH).replace("?", "/?") else this.withBothIfMissing(SLASH)
}