package com.edu.knoldus

import akka.actor.{Actor, Props}
import akka.event.Logging

class ValidationActor extends Actor{

  val log = Logging(context.system,this)
  var piecesAvailable = 1000

  override def receive = {

    case x : BuyerInfo => if(piecesAvailable>0) {piecesAvailable -= 1; log.info(s"phone booked for ${x.name}, " +
                                                s"REMAINING phones are : $piecesAvailable")}
                          else log.info(s"sorry ${x.name},phone out of sale")

  }

}
object ValidationActor{

  def prop:Props = Props[ValidationActor]


}

