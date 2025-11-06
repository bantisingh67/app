package com.app.repository;

import com.app.evaluation.Agent;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface AgentRepository extends Repository<Agent, Long> {
    Optional<Agent> findById(int agentId);
}