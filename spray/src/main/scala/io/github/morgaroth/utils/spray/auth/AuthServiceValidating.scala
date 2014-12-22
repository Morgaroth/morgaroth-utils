package io.github.morgaroth.utils.spray.auth

import spray.routing._
import spray.routing.directives.MethodDirectives

/**
 * Responsibility:
 * 1. defining directive method
 * 2. be requirement for validating directives extension
 */
trait AuthServiceValidating[UserType] {
  def validateDirective: Directive1[UserType]
}

trait AuthServiceSecureMethods[UserType] extends MethodDirectives {
  this: AuthServiceValidating[UserType] =>

  def secureHead = head & validateDirective
  def secureGet = get & validateDirective
  def securePost = post & validateDirective
  def securePatch = patch & validateDirective
  def secureDelete = delete & validateDirective

  def shead = secureHead
  def sget = secureGet
  def spost = securePost
  def spatch = securePatch
  def sdelete = secureDelete

}