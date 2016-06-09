package com.github.geoand.kkong.config

import com.github.geoand.kkong.extensions.combine
import com.google.common.io.Resources
import ratpack.config.ConfigDataBuilder
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Created by gandrianakis on 3/6/2016.
 */
object FileConfigLoader {

    @JvmStatic fun load(configDataBuilder: ConfigDataBuilder): ConfigDataBuilder {
        val names = listOf("application.properties", "application.yml", "application.json")
        val directories = listOf("", "config/")

        val possibleFileNames = directories.combine(names, {directory, name -> "$directory$name"})

        possibleFileNames.forEach { //first load the classpath resources
            loadClasspathConfiguration(configDataBuilder, it)
        }

        possibleFileNames.forEach { //then read the file system resources and override properties using them
            loadFileSystemConfiguration(configDataBuilder, it)
        }

        return configDataBuilder
    }

    private fun loadFileSystemConfiguration(configDataBuilder: ConfigDataBuilder, configurationFilename: String) {
        val configurationPath = Paths.get(configurationFilename)
        if(!Files.exists(configurationPath)){
            return
        }

        when {
            configurationFilename.endsWith(".yml") -> configDataBuilder.yaml(configurationPath)
            configurationFilename.endsWith(".json") -> configDataBuilder.json(configurationPath)
            configurationFilename.endsWith(".properties") -> configDataBuilder.props(configurationPath)
        }
    }

    private fun loadClasspathConfiguration(configDataBuilder: ConfigDataBuilder, configurationFilename: String) {
        try {
            val configurationResource = Resources.getResource(configurationFilename)

            when {
                configurationFilename.endsWith(".yml") -> configDataBuilder.yaml(configurationResource)
                configurationFilename.endsWith(".json") -> configDataBuilder.json(configurationResource)
                configurationFilename.endsWith(".properties") -> configDataBuilder.props(configurationResource)
            }
        } catch(ignored: Exception) {
            //the Resource does not exist
        }
    }
}