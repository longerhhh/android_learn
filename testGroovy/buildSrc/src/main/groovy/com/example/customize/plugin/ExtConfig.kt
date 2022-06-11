package com.example.customize.plugin

/**
 * FileName: ExtConfig
 * create date: 2022/5/22
 *
 * @author: longer
 * @description:
 */
/*
gradle cannot handle final class, eg. data class

An exception occurred applying plugin request [id: 'plugin.test1']
> Failed to apply plugin 'plugin.test1'.
   > Could not create an instance of type plugin.ExtConfig.
      > Class ExtConfig is final.
 */
open class ExtConfig(
 val name: String?
)