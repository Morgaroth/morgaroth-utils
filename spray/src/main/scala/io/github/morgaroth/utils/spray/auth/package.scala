package io.github.morgaroth.utils.spray

import io.github.morgaroth.utils.spray.auth.mongoimpl._

package object auth {

  object mongo {
    trait MongoUserWithSelfUser extends UsersDBConfig with DefaultMongoUserDAO
    trait MongoSession[UserType <: IdAble] extends SessionsDBConfig with DefaultMongoSessionDAO[UserType]
    trait MongoSessionWithSelfUser extends MongoSession[DefaultMongoUser]
  }


}
