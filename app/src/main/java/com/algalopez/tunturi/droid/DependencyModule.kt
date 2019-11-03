package com.algalopez.tunturi.droid

import com.algalopez.tunturi.droid.echo.core.EchoActor
import com.algalopez.tunturi.droid.echo.core.IEchoRepository
import com.algalopez.tunturi.droid.echo.core.IEchoServer
import com.algalopez.tunturi.droid.echo.presentation.EchoViewModel
import com.algalopez.tunturi.droid.echo.repository.EchoRepositoryAdapter
import com.algalopez.tunturi.droid.echo.server.EchoServerAdapter
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val dependencyModuleList = module {

    single { EchoRepositoryAdapter() } bind IEchoRepository::class
    single { EchoServerAdapter() } bind IEchoServer::class
    single { EchoActor(get(), get()) }

    viewModel { EchoViewModel(get()) }
}
