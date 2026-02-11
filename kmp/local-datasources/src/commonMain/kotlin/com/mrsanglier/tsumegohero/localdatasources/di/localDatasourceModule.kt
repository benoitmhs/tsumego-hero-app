package com.mrsanglier.tsumegohero.localdatasources.di

import com.mrsanglier.tsumegohero.localdatasources.datasource.LocalTsumegoDatasource
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val localDatasourceModule: Module = module {
    singleOf(::LocalTsumegoDatasource)
}
