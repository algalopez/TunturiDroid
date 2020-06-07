package com.algalopez.tunturi.droid.echo.repository

import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface EchoMapper {

    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "message", source = "message"),
        Mapping(target = "dateTime", source = "dateTime")
    )
    fun modelToData(echoMessage: com.algalopez.tunturi.droid.echo.core.model.EchoMessage): EchoMessage

    @Mappings(
        Mapping(target = "message", source = "message"),
        Mapping(target = "dateTime", source = "dateTime")
    )
    fun dataToModel(echoMessage: EchoMessage?): com.algalopez.tunturi.droid.echo.core.model.EchoMessage?

}
