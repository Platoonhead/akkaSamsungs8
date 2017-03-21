package com.edu.knoldus

import akka.actor.ActorSystem
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory

object Initializer extends App{

  val people = ConfigFactory.parseString(
    """
      |akka.actor.deployment {
      | /poolRouter {
      |   router = balancing-pool
      |   nr-of-instances = 500
      | }
      |}
    """.stripMargin
  )

  val system = ActorSystem("FlashSale",people)
  val RequestHandlerRef  = system.actorOf(PurchaseRequestHandler.prop)
  val buyer = system.actorOf(FromConfig.props(PurchaseActor.prop(RequestHandlerRef)),"poolRouter")


  for (i  <- 1 to 1100) {

    buyer ! BuyerInfo("name"+i,"address"+i,"mobileNumber"+i,i)
  }

    buyer ! BuyerInfo("name10","address10","mobileNumber10",10) //users with same phone number cant buy a phone

}
