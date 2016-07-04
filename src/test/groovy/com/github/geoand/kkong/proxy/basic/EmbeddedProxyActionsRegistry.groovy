package com.github.geoand.kkong.proxy.basic

import com.github.geoand.kkong.proxy.Options
import com.github.geoand.kkong.proxy.ProxyAPIActions
import com.github.geoand.kkong.proxy.StaticTargetDelegatingProxyAPIActions
import com.github.geoand.kkong.proxy.registry.ProxyActionsRegistry
import com.github.geoand.kkong.proxy.request.RequestPathMatcher

import static com.github.geoand.kkong.proxy.basic.EmbeddedProxyConsts.*

/**
 * Created by gandrianakis on 3/6/2016.
 */
class EmbeddedProxyActionsRegistry implements ProxyActionsRegistry {

    @Override
    List<ProxyAPIActions> all() {
        return [
                new StaticTargetDelegatingProxyAPIActions(
                    "name",
                    new Options(false, false),
                    new RequestPathMatcher(NON_STRIPPED_PROXY_PATH),
                    new URI("http://localhost:${EMBEDDED_APP_PORT}")
                ),

                new StaticTargetDelegatingProxyAPIActions(
                        "name",
                        new Options(true, false),
                        new RequestPathMatcher(STRIPPED_PROXY_PATH),
                        new URI("http://localhost:${EMBEDDED_APP_PORT}")
                )
        ]
    }
}
