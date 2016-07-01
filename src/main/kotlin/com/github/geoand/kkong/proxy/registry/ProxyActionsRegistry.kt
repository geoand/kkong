package com.github.geoand.kkong.proxy.registry

import com.github.geoand.kkong.proxy.ProxyAPIActions

/**
 * Created by gandrianakis on 2/6/2016.
 */
interface ProxyActionsRegistry {

    fun all(): List<ProxyAPIActions>
}