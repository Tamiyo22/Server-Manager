package com.melServer.server.resource;

import com.melServer.server.enumeration.Status;
import com.melServer.server.model.Response;
import com.melServer.server.model.Server;
import com.melServer.server.service.implementation.ServerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.SSLEngineResult;
import java.io.IOException;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static javax.security.auth.callback.ConfirmationCallback.OK;

/**
 * @author Melissa Hargis
 * 5/4/2023
 */

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
    private final ServerServiceImpl serverService;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
                Response.builder().timeStamp(now())
                        .data(Map.of("servers", serverService.list(30)))
                        .message("Servers retrieved")
                        .status(HttpStatus.valueOf(OK))
                        .statusCode(OK)
                        .build()
        );
    }
        @GetMapping("/ping/{ipAddress}")
        public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
            Server server = serverService.ping(ipAddress);

            return ResponseEntity.ok(
                    Response.builder().timeStamp(now())
                            .data(Map.of("server", server))
                            .message(server.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping Failed")
                            .status(HttpStatus.valueOf(OK))
                            .statusCode(OK)
                            .build()
            );
        }

  }

