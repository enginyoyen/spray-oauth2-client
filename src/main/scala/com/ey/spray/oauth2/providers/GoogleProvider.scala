package com.ey.spray.oauth2.providers

import com.ey.spray.oauth2.{OAuth2Settings, OAuth2Provider}
/**
 * Created by enginyoyen on 15/06/14.
 */
class GoogleProvider(providerId :String = GoogleProvider.Google) extends OAuth2Provider(providerId) {


}


object GoogleProvider {
  val Google = "google"
}
