package com.example

import akka.actor.Actor
import spray.routing._
import com.ey.spray.oauth2.providers.GoogleProvider
import com.ey.spray.oauth2.{Identity, AuthService, OAuth2Provider}

class MyServiceActor extends Actor with MyService {
  def actorRefFactory = context
  def receive = runRoute(myRoute ~ oauth2Routes)
  def service:OAuth2Provider = new GoogleProvider()
}

trait MyService extends HttpService with AuthService     {

  val myRoute =
    path(""){
      getFromResource("default.html")
    } ~
    path("no-auth"){
        complete {
          "This is a public content"
        }
    }~
    path("uid"){
      (get & securedDirective) {  identity =>
        complete {
            val userIdentity = identity.asInstanceOf[Identity]
            s"My UID is : ${userIdentity.uid}"
          }
      }
    }

}