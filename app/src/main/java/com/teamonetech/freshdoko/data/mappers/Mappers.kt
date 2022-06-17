package com.teamonetech.freshdoko.data.mappers

import com.teamonetech.freshdoko.GetTokenMutation
import com.teamonetech.freshdoko.ProductCollectionQuery
import com.teamonetech.freshdoko.domain.models.ProductsModel
import com.teamonetech.freshdoko.domain.models.UserModel


//
fun ProductCollectionQuery.Products.mapToDomainModel() = edges.map { ProductsModel(it.node.fragments.productCardFragment.id,it.node.fragments.productCardFragment.name,
    (it.node.fragments.productCardFragment.description ?:"") as String,it.node.fragments.productCardFragment.thumbnail?.fragments?.imageFragment?.url ?:"",price= it.node.fragments.productCardFragment.pricing?.priceRange?.start?.gross?.fragments?.priceFragment?.amount ?:0.0, currency = it.node?.fragments?.productCardFragment?.pricing?.priceRange?.start?.gross?.fragments?.priceFragment?.currency ?:"",
attribute = it.node.fragments.productCardFragment.attributes.first().attribute.fragments.attributeFragment.name ?:"",
minValue = it.node.fragments.productCardFragment.metadata.filter { it.fragments.metaDataFragment.key =="min_value" }.firstOrNull()?.fragments?.metaDataFragment?.value?.toInt() ?:8)}


fun GetTokenMutation.TokenCreate.mapToDomainModel() = UserModel(token ?:"",refreshToken ?:"",csrfToken?:"",user?.email ?:"")
//
//fun GetCharactersQuery.Episode.mapToDomainModel() = EpisodeModel(id ?: "", name ?: "")
//L
//fun GetCharactersQuery.Result.mapToDomainModel() = SingleCharacterModel(
//    id ?: "",
//    name ?: "",
//    image ?: "",
//    episode?.map { it!!.mapToDomainModel() } ?: emptyList()
//)
//
//fun GetCharactersQuery.Characters.mapToDomainModel() = CharactersModel(
//    info?.mapToDomainModel() ?: InfoModel(),
//    results?.map { it!!.mapToDomainModel() } ?: emptyList()
//)