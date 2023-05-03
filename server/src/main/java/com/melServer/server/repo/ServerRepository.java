package com.melServer.server.repo;

import com.melServer.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Melissa H
 * @since 5/3/2023
 */


 //manipulate data in database
public interface ServerRepository extends JpaRepository<Server, Long> {
    // Manual method
    Server findByIpAddress(String ipAddress);
}
