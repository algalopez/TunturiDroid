package com.algalopez.tunturi.droid.echo.repository

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper
interface EchoMapper {

//     INSTANCE: EchoMapper = Mappers.getMapper( EchoMapper.class )

    @Mappings(
        Mapping(target = "id", source = "id"),
        Mapping(target = "message", source = "message"),
        Mapping(target = "dateTime", source = "dateTime")
    )
    fun modelToData(echoMessage: com.algalopez.tunturi.droid.echo.core.model.EchoMessage): EchoMessage

    @Mappings(
        Mapping(target = "id", source = "id"),
        Mapping(target = "message", source = "message"),
        Mapping(target = "dateTime", source = "dateTime")
    )
    fun dataToModel(echoMessage: EchoMessage?): com.algalopez.tunturi.droid.echo.core.model.EchoMessage?

}
