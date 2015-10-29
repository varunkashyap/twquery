package org.vk.twquery

import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

object Bird {
  val cb = new ConfigurationBuilder()

  // -- initialization redacted --

  private val twitter = new TwitterFactory(cb.build).getInstance()


  def getInstance = twitter
}
