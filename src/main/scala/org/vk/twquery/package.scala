package org.vk

import twitter4j.User

package object twquery {

  /**
   * Helps throttle operations on any seq of twitter4j.users to meet twitter's
   * rate limitations.
   * @param users
   */
  implicit class UserUtils(users: Seq[User]) {
    def throttledEach[U] (f: User => U, limit: Int, interval: Int) = {
      var these = users
      val sleepTime: Int = interval / limit
      while(!these.isEmpty) {
        f(these.head)
        Thread.sleep(sleepTime)
        these = these.tail
      }

    def throttledMap[U] (f: User => U, limit: Int, interval: Int) = {
      var these = users
      val sleepTime: Int = interval / limit
      while(!these.isEmpty) {
        f(these.head)
        Thread.sleep(sleepTime)
        these = these.tail
      }
    }}
  }
}
