

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Input
import com.teamonetech.freshdoko.GetCharactersQuery
import com.teamonetech.freshdoko.GetTokenMutation
import com.teamonetech.freshdoko.ProductCollectionQuery
import com.teamonetech.freshdoko.data.commun.BASE_URL
import com.teamonetech.freshdoko.type.LanguageCodeEnum
import com.teamonetech.freshdoko.type.ProductFilterInput

object GraphQlApolloClient {

    private fun apolloClient(): ApolloClient =
        ApolloClient.builder().serverUrl(BASE_URL).build()

    fun getToken(email:String,password:String) = apolloClient().mutation(GetTokenMutation(email,password))



    fun getProducts() =
        apolloClient().query(ProductCollectionQuery(
            channel = "nrs", locale = LanguageCodeEnum.EN_US))


}
