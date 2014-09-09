/**
 * Description : some words
 * Author: chris
 * Date: 2014/8/28
 */

import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.actor.ActorRef;

public class HelloWorld extends UntypedActor {

    @Override
    public void preStart() {
        // create the greeter actor
        final ActorRef greeter =
                getContext().actorOf(Props.create(Greeter.class), "greeter");//创建greeter actor实例
        // tell it to perform the greeting
        greeter.tell(Greeter.Msg.GREET, getSelf());//通过tell方法给greeter actor 发送一条消息
    }

    @Override
    public void onReceive(Object msg) {
        if (msg == Greeter.Msg.DONE) {
            // when the greeter is done, stop this actor and with it the application
            getContext().stop(getSelf());
        } else unhandled(msg);
    }
}
