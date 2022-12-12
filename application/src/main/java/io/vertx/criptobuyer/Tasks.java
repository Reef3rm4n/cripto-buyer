package io.vertx.criptobuyer;

import io.activej.inject.annotation.Inject;
import io.activej.inject.annotation.Provides;
import io.vertx.criptobuyer.handlers.DefaultPriceProvider;
import io.vertx.criptobuyer.handlers.OrderGeneratorTask;
import io.vertx.criptobuyer.handlers.PriceProviderTask;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.web.client.WebClient;
import io.vertx.skeleton.utils.VertxComponent;

public class Tasks extends VertxComponent {

  @Provides
  @Inject
  PriceProviderTask providerTask(
    Vertx vertx,
    WebClient webClient
  ) {
    return new PriceProviderTask(vertx, new DefaultPriceProvider(webClient));
  }

  @Provides
  @Inject
  WebClient webClient(Vertx vertx) {
    return WebClient.create(vertx, new WebClientOptions()
      .setPipelining(false)
      .setName("price-provider")
      .setShared(true)
    );
  }


//  @Provides
//  @Inject
//  OrderGeneratorTask orderGenerator(WebClient webClient) {
//    return new OrderGeneratorTask(webClient);
//  }

}