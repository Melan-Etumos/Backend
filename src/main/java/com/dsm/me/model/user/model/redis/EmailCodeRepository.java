package com.dsm.me.model.user.model.redis;

import org.springframework.data.repository.CrudRepository;

public interface EmailCodeRepository extends CrudRepository<Code, String> {
}
