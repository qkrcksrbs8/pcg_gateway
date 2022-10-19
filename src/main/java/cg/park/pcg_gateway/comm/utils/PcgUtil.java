package cg.park.pcg_gateway.comm.utils;

import cg.park.pcg_gateway.comm.controllers.CommFilterController;
import net.bytebuddy.implementation.bind.annotation.Super;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class PcgUtil {

    private static final Logger log = LogManager.getLogger();

    public void requestLog(boolean check, ServerHttpRequest request) {
        if (check) log.info("Start: " + request);
    }
    public void responseLog(boolean check, ServerHttpResponse response) {
        if (check) log.info("End: " + response);
    }

}
