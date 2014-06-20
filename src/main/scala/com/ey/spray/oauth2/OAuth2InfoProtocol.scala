package com.ey.spray.oauth2

import spray.json._
import scala.Some

/**
 * Created by enginyoyen on 20/06/14.
 */
object OAuth2InfoProtocol extends DefaultJsonProtocol {
  implicit object OAuth2InfoJsonFormat extends RootJsonFormat[OAuth2Info] {

    def write(a: OAuth2Info) = ???

    def read(value: JsValue) = {
      value.asJsObject.getFields(OAuth2Constants.AccessToken, OAuth2Constants.TokenType, OAuth2Constants.ExpiresIn, OAuth2Constants.IdToken) match {
        case Seq(JsString(accessToken), JsString(tokenType), JsNumber(expiresIn), JsString(id_token)) =>
          new OAuth2Info(accessToken, Some(tokenType), Some(expiresIn.toLong), Some(id_token))
        case _ => throw new DeserializationException("OAuth2Info expected")
      }
    }
  }
}


object JsonProtocol extends DefaultJsonProtocol {
  implicit val oauth2InfoFormat = jsonFormat5(OAuth2Info)
}