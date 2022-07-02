package com.example.hellocompiler

import com.example.hello.annotation.Hello
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.ksp.writeTo

class HelloProcessor(private val environment: SymbolProcessorEnvironment): SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        resolver.getSymbolsWithAnnotation(Hello::class.java.name).forEach {
            if (it is KSFunctionDeclaration && it.containingFile != null) {
                FileSpec.builder(packageName = it.packageName.asString(), "Hello.kt")
                    .addType(TypeSpec.classBuilder("Hello").build())
                    .addFunction(FunSpec.builder("hello")
                        .addModifiers(KModifier.PRIVATE)
                        .addStatement("println(\"hello\")")
                        .build()
                    )
                    .build()
                    .writeTo(environment.codeGenerator, Dependencies(false, it.containingFile!!))
            }
        }
        return emptyList()
    }
}