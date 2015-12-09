package io.vertx.example.jgroups;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * @author <a href="http://escoffier.me">Clement Escoffier</a>
 */
public class ConsumerApp {

  public static void main(String[] args) {
    System.setProperty("jgroups.bind_addr", "192.168.1.29");
    Vertx.clusteredVertx(new VertxOptions().setClustered(true).setClusterHost("192.168.1.29"), ar -> {
      if (ar.failed()) {
        System.err.println("Cannot create vert.x instance : " + ar.cause());
      } else {
        Vertx vertx = ar.result();
        System.out.println("Registering consumer");
        vertx.eventBus().consumer("news", message -> {
          System.out.println(">> " + message.body());
        });
      }
    });
  }

}
