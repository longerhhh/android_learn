package com.example.customize.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class CustomPlugin implements Plugin<Project>{
    @Override
    void apply(Project project) {
        println "in custom plugin groovy......................................"
        // groovy cannot handle kotlin class
//        def ext = project.extensions.create("customConfig", ExtConfig.class)
        def ext = project.extensions.create("customConfig", ExtConfigG)
        project.afterEvaluate{
            println "name is ${ext.name}"
        }
    }
}