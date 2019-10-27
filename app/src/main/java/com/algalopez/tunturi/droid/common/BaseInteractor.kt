package com.algalopez.tunturi.droid.common

abstract class BaseInteractor<Request, Response> {

    abstract fun run(request: Request): Response
}
