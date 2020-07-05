package com.algalopez.tunturi.droid.echo.repository

import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

typealias RepositoryEchoMessage = EchoMessage
typealias DomainEchoMessage = com.algalopez.tunturi.droid.echo.core.model.EchoMessage

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface EchoMapper {

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "message", source = "message"),
        Mapping(target = "dateTime", source = "dateTime")
    )
    fun fromDomain(echoMessage: DomainEchoMessage?): RepositoryEchoMessage

    @Mappings(
        Mapping(target = "message", source = "message"),
        Mapping(target = "dateTime", source = "dateTime")
    )
    fun toDomain(echoMessage: RepositoryEchoMessage?): DomainEchoMessage?

}
