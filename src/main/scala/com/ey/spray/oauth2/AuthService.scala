package com.ey.spray.oauth2

import spray.routing._
import spray.http.StatusCodes._
import spray.http.HttpCookie
import scala.Some
import com.ey.spray.oauth2.utils.Config


trait AuthService extends HttpService with SessionStore {

  def service:OAuth2Provider

  //TODO : Always redirect to REDIRECT_ROUTE when there is no session
  val securedDirective: Directive1[Identity] = {
    cookie(Config.SESSION_NAME).flatMap {
      case cookie: HttpCookie => getSession(cookie.content) match {
        case Some(user) => provide(user)
        case _ => {
          redirect(Config.REDIRECT_ROUTE, Found)
        }
      }

    }
  }



  val oauth2Routes =
    (path(Config.CALLBACK_ROUTE) & parameters('code)) { code =>
      val identity = service.requestAccessToken(code)
      val sessionId = getRandomSessionId
      addSession(sessionId, identity)
       setCookie(HttpCookie(Config.SESSION_NAME, sessionId, None, identity.oAuth2Info.get.expiresIn )){
        redirect(Config.ON_LOGIN_GO_TO, Found)
      }
    } ~
    (path(Config.REDIRECT_ROUTE)) {
      redirect(service.getAuthorizationCodeRequestUrl(), Found)
    }
}

