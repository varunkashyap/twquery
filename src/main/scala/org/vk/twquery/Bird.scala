package org.vk.twquery

import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

object Bird {
  val cb = new ConfigurationBuilder()
  cb.setDebugEnabled(true)
    .setOAuthConsumerKey("0IFowyQR31ht5OlRiuZBXA")
    .setOAuthConsumerSecret("Lr1N0BCSbHStnB5nfsXOSXMYIh4f9D8RvUGV7Q5A")
    .setOAuthAccessToken("14842616-umSnSgjrhzibRH1MjcMatb6P6X8D9OEMvlDnWm9sE")
    .setOAuthAccessTokenSecret("PwdREGM6bQkPgPEiHy90E1jVQocLLTojX4wcBUBohc")

  private val twitter = new TwitterFactory(cb.build).getInstance()

  def getInstance = twitter
}
