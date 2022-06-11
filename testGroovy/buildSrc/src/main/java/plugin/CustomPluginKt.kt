package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * FileName: CustomPluginKt
 * create date: 2022/5/22
 *
 * @author: longer
 * @description:
 */
class CustomPluginKt: Plugin<Project> {
    override fun apply(p0: Project) {
        println("in custom plugin kotlin....................................")
        val extConfigKt = p0.extensions.create("customConfigKt", ExtConfigKt::class.java)
        p0.afterEvaluate {
            println("name in kotlin plugin is ${extConfigKt.name}")
        }
    }
}