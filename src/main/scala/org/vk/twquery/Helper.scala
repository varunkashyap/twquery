package org.vk.twquery

import twitter4j.{RateLimitStatus, PagableResponseList, User}

import scala.collection.mutable.ListBuffer

object Helper {
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
}
