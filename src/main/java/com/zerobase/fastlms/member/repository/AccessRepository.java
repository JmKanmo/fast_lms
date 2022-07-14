package com.zerobase.fastlms.member.repository;

import com.zerobase.fastlms.member.entity.Access;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessRepository extends JpaRepository<Access, Long> {
}
