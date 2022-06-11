package com.example.pmannotionhandler.pm

import com.example.pmannotionhandler.pm.PmSymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class PmSymbolProcessorProvider: SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return PmSymbolProcessor()
    }
}