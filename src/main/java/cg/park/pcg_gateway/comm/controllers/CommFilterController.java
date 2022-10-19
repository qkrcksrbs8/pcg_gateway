package cg.park.pcg_gateway.comm.controllers;

import cg.park.pcg_gateway.comm.utils.PcgUtil;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


@Component
public class CommFilterController extends AbstractGatewayFilterFactory<CommFilterController.Config> {
    private static final Logger log = LogManager.getLogger(CommFilterController.class);
    @Autowired
    PcgUtil util;

    public CommFilterController() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            log.info("CommFilter baseMessage:" + config.getBaseMessage());
            util.requestLog(config.isPreLogger(), exchange.getRequest());
            return chain.filter(exchange).then(Mono.fromRunnable(()-> {
                util.responseLog(config.isPostLogger(), exchange.getResponse());
            }));
        });
    }

    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
