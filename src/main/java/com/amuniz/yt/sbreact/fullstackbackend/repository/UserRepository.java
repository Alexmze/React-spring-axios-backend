package com.amuniz.yt.sbreact.fullstackbackend.repository;

import com.amuniz.yt.sbreact.fullstackbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
