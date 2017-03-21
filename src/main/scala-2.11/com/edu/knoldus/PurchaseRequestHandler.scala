package com.edu.knoldus

import akka.actor.{Actor, ActorRef, Props}
import akka.event.Logging
import scala.collection.mutable.ListBuffer

class PurchaseRequestHandler extends Actor {

  val log = Logging(context.system,this)
  val usersPhoneNumber: ListBuffer[String] = ListBuffer[String]()
  val validatorRef: ActorRef = context.actorOf(ValidationActor.prop)

  override def receive = {

    case x : BuyerInfo => if(usersPhoneNumber.contains(x.mobile)) log.info(s"${x.name} has already booked a phone <==========")
                          else {
                                usersPhoneNumber += x.mobile
                                validatorRef ! x
                          }
    }


  }

object PurchaseRequestHandler{

  def prop:Props = Props[PurchaseRequestHandler]

}
