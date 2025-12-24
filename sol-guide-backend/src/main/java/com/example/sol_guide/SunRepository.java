package com.example.sol_guide;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SunRepository extends JpaRepository <Restaurant, Long> {
}
