package com.algalopez.tunturi.droid.common

import kotlinx.coroutines.flow.Flow

abstract class BaseInteractor<Request, Response> {

    abstract suspend fun run(request: Request): Flow<Response>
}
