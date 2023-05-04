package com.melServer.server.service.implementation;

import com.melServer.server.enumeration.Status;
import com.melServer.server.model.Server;
import com.melServer.server.repo.ServerRepository;
import com.melServer.server.service.ServerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j /* log */
public class ServerServiceImpl implements ServerService {
    private final ServerRepository serverRepository; /* lombok dependency injection @RequiredArgsConstructor */
    @Override
    public Server create(Server server) {
        log.info("Saving new server:{}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP:{}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(1000) ? Status.SERVER_UP:Status.SERVER_DOWN);
        serverRepository.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers");
        return serverRepository.findAll(of(0,limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id:{}", id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by id:{}", id);
        serverRepository.deleteById(id);
        return Boolean.TRUE;
    }

    /* TODO */
    private String setServerImageUrl(){
        return null;
    }
}
