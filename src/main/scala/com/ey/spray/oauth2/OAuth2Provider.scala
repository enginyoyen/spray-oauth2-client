package com.ey.spray.oauth2


import com.google.api.client.auth.oauth2.{ClientParametersAuthentication, AuthorizationCodeTokenRequest, AuthorizationCodeRequestUrl}
import scala.collection.JavaConverters._
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.http.GenericUrl
import spray.json._
import com.google.api.client.json.webtoken.JsonWebSignature


import spray.json.{DefaultJsonProtocol, JsonParser}
import DefaultJsonProtocol._
import com.ey.spray.oauth2.utils.Config

/**
 * Created by enginyoyen on 15/06/14.
 */


class OAuth2Provider(providerId:String)  extends IdentityProvider{

  val settings:OAuth2Settings = OAuth2Settings.forProvider(providerId)


  def getAuthorizationCodeRequestUrl() :String = {
    val url : AuthorizationCodeRequestUrl =
      new AuthorizationCodeRequestUrl(settings.authorizationUrl,settings.clientId)
        .setRedirectUri(Config.CALLBACK_URL)
        .setScopes(settings.scope)
    url.build()
  }


  def requestAccessToken(code: String) : Identity  = {
    import JsonProtocol._
    import OAuth2InfoProtocol._

    val authorizationCodeTokenRequest =  new AuthorizationCodeTokenRequest(
      new NetHttpTransport(),
      new JacksonFactory(),
      new GenericUrl(settings.accessTokenUrl),
      code)
      .setRedirectUri(Config.CALLBACK_URL)
      .setScopes(settings.scope)
      .setClientAuthentication(new ClientParametersAuthentication(settings.clientId, settings.clientSecret))


    val tokenResponse = authorizationCodeTokenRequest.execute()
    val oauth2Info = JsonParser(tokenResponse.toString).convertTo[OAuth2Info]

    val jws:JsonWebSignature = JsonWebSignature.parse(new JacksonFactory(), oauth2Info.IdToken.get);

    Identity(jws.getPayload.getSubject,None,None,None,None,None,Some(oauth2Info))
  }




}


