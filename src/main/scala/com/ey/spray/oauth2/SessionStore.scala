package com.ey.spray.oauth2


import java.util.UUID
import scala.Predef._




/**
 * Created by enginyoyen on 16/06/14.
 */
trait SessionStore {

  var sessionStore: Map[String, Identity] = Map()

  def addSession(sessionId: String, identity: Identity) =
    sessionStore += sessionId -> identity

  def getSession(sessionId: String): Option[Identity] =
    sessionStore.get(sessionId)


  def removeSession(sessionId: String) =
    sessionStore -= sessionId

  def getRandomSessionId:String = UUID.randomUUID().toString

}



