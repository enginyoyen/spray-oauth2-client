package com.ey.spray.oauth2

/**
 * Created by enginyoyen on 16/06/14.
 *
 * @uid : An identifier for the user. Use this value within your application as the unique-identifier key for the user. This is the sub value in an id_token payload.
 *
 */
case class Identity (uid:String,
                     firstName: Option[String],
                     lastName: Option[String],
                     fullName: Option[String],
                     email: Option[String],
                    avatarUrl: Option[String],
                    oAuth2Info: Option[OAuth2Info] = None)
