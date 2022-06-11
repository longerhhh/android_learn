package com.example.pmannotionhandler.pm

import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.squareup.kotlinpoet.*

class PmSymbolProcessor : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        // 主逻辑在此实现
        resolver.getSymbolsWithAnnotation(annotationName).asSequence()
            .filterIsInstance<KSFunctionDeclaration>().forEach {
                FileSpec.builder("", "genfile").addType(
                    TypeSpec.classBuilder("Test").primaryConstructor(
                        FunSpec.constructorBuilder().addParameter("index", Int::class).build()
                    ).addProperties(
                        listOf(PropertySpec.builder("index", Int::class).build())
                    ).addFunction(
                        FunSpec.builder("newFun").addParameter("param", String::class).build()
                    ).build()
                ).addFunction(FunSpec.builder("classFun").build()).build()
            }
        return emptyList()
    }
}

const val annotationName = "RequestPermission"
