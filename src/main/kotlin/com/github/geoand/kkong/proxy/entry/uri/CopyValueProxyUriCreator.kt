package com.github.geoand.kkong.proxy.entry.uri

import com.github.geoand.kkong.proxy.entry.ProxyEntry
import ratpack.http.Request
import java.net.URI
import javax.inject.Singleton

/**
 * TODO this is the dirtiest/stupidest implementation possible. It needs to be totally revised
 */

@Singleton
class CopyValueProxyUriCreator : ProxyUriCreator {

    override fun create(request: Request, proxyEntry: ProxyEntry): URI {
        val requestUri = URI(request.rawUri)

        val targetValueUri = URI(proxyEntry.targetValue)

        val proxyUri = URI(
                targetValueUri.scheme,
                targetValueUri.userInfo,
                targetValueUri.host,
                targetValueUri.port,
                if(proxyEntry.stripPath && (null != proxyEntry.requestPath)) requestUri.path.replace(proxyEntry.requestPath, "") else requestUri.path,
                requestUri.query,
                requestUri.fragment
        )

        return proxyUri
    }
}