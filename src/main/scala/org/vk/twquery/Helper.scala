package org.vk.twquery

import twitter4j.{RateLimitStatus, PagableResponseList, User}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object Helper {
  val bird = Bird.getInstance

  def cursorWalker(func: (Long, Long) => PagableResponseList[User], id: Long) = {
    println ("Cursor walker function called")
    var cursor = -1L
    var numCalls = 0
    val users = ListBuffer[User]()
    while (cursor != 0) {
      println ("cursor: " + cursor)
      println ("numCalls: " + numCalls)
      val userBatch = func(id, cursor)
      numCalls += 1
      val userBatchIterator = userBatch.iterator
      while (userBatchIterator.hasNext) {
        var user = userBatchIterator.next
        println("User: " + user.getScreenName)
        users += user
      }
      cursor = userBatch.getNextCursor
    }

    users
  }

  def showRateLimits() = {
    import scala.collection.JavaConverters._

    val rateLimits = Bird.getInstance.getRateLimitStatus
    rateLimits.asScala.foreach( kv => println(kv._1 + ": " + kv._2))
  }

  /**
   * Get user statistics from screen name or id
   *
   * @param Either username or password
   * @return List of Twitter4j User objects corresponding to supplied usernames
   */
  def userStatistics(users: List[String]): List[User] = {
    users.map { user =>
      bird.showUser(user)
    }
  }

  def userStatistics(user: String): List[User] = {
    userStatistics(List(user))
  }

  /**
   * A naive select operation on Java Objects
   * TODO: Implement support for recursive property fetch from nested objects
   *
   * @param cols - A list of properties to be plucked from the each of targets object
   * @param targets - The data objects from which the columns/properties are selected from
   * @return Seq of Maps for each value of target, each of whose key corresponds to elements in cols
   *         and value is the value of the property from target object
   */
  def select(cols: List[String], targets: Seq[Any]): List[Map[String, String]] = {
    targets.map { target =>
      cols.map { field =>
        val declaredField = target.getClass.getDeclaredField(field)
        declaredField.setAccessible(true)
        (field, declaredField.get(target).toString)
      }.toMap
    }.toList
  }
}
