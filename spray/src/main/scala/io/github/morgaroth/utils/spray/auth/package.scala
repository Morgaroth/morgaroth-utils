package io.github.morgaroth.utils.spray

import io.github.morgaroth.utils.spray.auth.mongoimpl._

package object auth {

  object mongo {
    object user {
      trait MongoUserWithSelfUser
        extends UsersDBConfig
        with DefaultMongoUserDAO
    }

    object session {
      trait MongoSession[UserType <: IdAble]
        extends SessionsDBConfig
        with DefaultMongoSessionDAO[UserType]

      trait MongoSessionWithSelfUser
        extends MongoSession[DefaultMongoUser]
    }
  }

  trait AuthService[UserType <: IdAble, SessionType]
    extends UserDAO[UserType]
    with SessionDAO[UserType, SessionType]
    with DefaultTokenGenerator
    with AuthServiceAuthentication[UserType, SessionType]
    with AuthServiceAPI[UserType, SessionType]

  trait MongoAuthService[UserType <: IdAble]
    extends mongo.session.MongoSessionWithSelfUser
    with AuthService[UserType, DefaultMongoSession]

  trait MongoAuthSelfService
    extends mongo.user.MongoUserWithSelfUser
    with mongo.session.MongoSessionWithSelfUser
    with MongoAuthService[DefaultMongoUser]

}
