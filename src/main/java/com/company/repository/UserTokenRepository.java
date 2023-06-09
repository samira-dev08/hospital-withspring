package com.company.repository;

import com.company.entity.User;
import com.company.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
UserToken findUserTokenByUserAndTokenAndActive(User user, String token, Integer active);

}
