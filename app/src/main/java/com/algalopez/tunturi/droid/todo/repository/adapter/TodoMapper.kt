package com.algalopez.tunturi.droid.todo.repository.adapter

import com.algalopez.tunturi.droid.todo.repository.Item
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

private typealias RepositoryItem = Item
private typealias DomainItem = com.algalopez.tunturi.droid.todo.core.model.Item

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface TodoMapper {

    @Mappings(
        Mapping(target = "id", source = "id"),
        Mapping(target = "name", source = "name"),
        Mapping(target = "color", source = "color")
    )
    fun fromDomain(item: DomainItem?): RepositoryItem

    @Mappings(
        Mapping(target = "id", source = "id"),
        Mapping(target = "name", source = "name"),
        Mapping(target = "color", source = "color")
    )
    fun toDomain(item: RepositoryItem?): DomainItem

}
