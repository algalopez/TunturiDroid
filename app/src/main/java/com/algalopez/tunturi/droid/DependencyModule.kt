package com.algalopez.tunturi.droid

import com.algalopez.tunturi.droid.echo.core.EchoActor
import com.algalopez.tunturi.droid.echo.presentation.EchoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dependencyModuleList = module {

    single { EchoActor() }

    viewModel { EchoViewModel(get()) }
}
