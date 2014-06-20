package com.ey.spray.oauth2

import java.util

/**
 * Created by enginyoyen on 15/06/14.
 */
/**
 * The settings for OAuth2 providers.
 */
case class OAuth2Settings(authorizationUrl: String, accessTokenUrl: String, clientId: String,
                          clientSecret: String, scope: util.List[String] )


object OAuth2Settings {
  val AuthorizationUrl = "authorizationUrl"
  val AccessTokenUrl = "accessTokenUrl"
  val ClientId = "clientId"
  val ClientSecret = "clientSecret"
  val Scope = "scopes"

  /**
   * Helper method to create an OAuth2Settings instance from the properties file.
   *
   * @param id the provider id
   * @return an OAuth2Settings instance
   */
  def forProvider(id: String): OAuth2Settings = {
    import com.ey.spray.oauth2.utils.Config._

    val authorizationUrl = loadProperty(id, OAuth2Settings.AuthorizationUrl) ;
    val accessTokenUrl = loadProperty(id, OAuth2Settings.AccessTokenUrl) ;
    val clientId = loadProperty(id, OAuth2Settings.ClientId) ;
    val clientSecret = loadProperty(id, OAuth2Settings.ClientSecret)
    val scopes = loadPropertyOfScope(id, OAuth2Settings.Scope)

    //TODO add error handling
    OAuth2Settings(authorizationUrl.toString, accessTokenUrl.toString, clientId.toString, clientSecret.toString, scopes)
  }
}


case class OAuth2Info(accessToken: String,
                      tokenType: Option[String] = None,
                      expiresIn: Option[Long] = None,
                      IdToken:Option[String] = None,
                      refreshToken:Option[String] = None)






object OAuth2Constants {
  val ClientId = "client_id"
  val ClientSecret = "client_secret"
  val RedirectUri = "redirect_uri"
  val Scope = "scope"
  val ResponseType = "response_type"
  val State = "state"
  val GrantType = "grant_type"
  val AuthorizationCode = "authorization_code"
  val AccessToken = "access_token"
  val Error = "error"
  val Code = "code"
  val TokenType = "token_type"
  val ExpiresIn = "expires_in"
  val RefreshToken = "refresh_token"
  val AccessDenied = "access_denied"
  val IdToken = "id_token"
}