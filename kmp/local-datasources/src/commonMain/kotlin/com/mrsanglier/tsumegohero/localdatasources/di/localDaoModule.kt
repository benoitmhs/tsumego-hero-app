package com.mrsanglier.tsumegohero.localdatasources.di

import com.mrsanglier.tsumegohero.localdatasources.room.AppDatabase
import com.mrsanglier.tsumegohero.localdatasources.room.dao.TsumegoDao
import org.koin.core.module.Module
import org.koin.dsl.module

val localDaoModule: Module = module {
    single<TsumegoDao> { get<AppDatabase>().tsumegoDao() }
}