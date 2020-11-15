package com.dais.scrum.estimate.repository;

import com.dais.scrum.estimate.entity.Participant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParticipantRepository extends CrudRepository<Participant, UUID> {
}
