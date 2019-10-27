package com.algalopez.tunturi.droid

import com.algalopez.tunturi.droid.echo.core.EchoActor
import org.koin.dsl.module

val dependencyModuleList = module {

    single { EchoActor() }
}
