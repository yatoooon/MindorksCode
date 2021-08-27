package com.common.inject_example.dagger2

import dagger.Binds
import dagger.Module
import javax.inject.Inject

@Module
abstract class AdvanceMoudle {
    @Binds
    abstract fun bindAdvance(advanceImpl: AdvanceImpl): Advance
}


interface Advance {
    fun printName()
}

class AdvanceImpl : Advance {
    @Inject
    constructor() {

    }
    override fun printName() {
        println("Advanceimpl")
    }
}