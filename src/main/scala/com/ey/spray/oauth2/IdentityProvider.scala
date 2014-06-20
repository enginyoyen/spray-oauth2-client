package com.ey.spray.oauth2

import com.typesafe.config.ConfigFactory
import com.google.api.client.json.webtoken.JsonWebToken.Payload

/**
 * Created by enginyoyen on 15/06/14.
 */

trait IdentityProvider{
  def getAuthorizationCodeRequestUrl() :String
  def requestAccessToken(code: String) : Identity
}

