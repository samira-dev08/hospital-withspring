package com.company.util;

import com.company.dto.request.ReqToken;
import com.company.entity.User;
import com.company.entity.UserToken;
import com.company.enums.EnumAvailableStatus;
import com.company.exception.ExceptionConstants;
import com.company.exception.HospitalException;
import com.company.repository.UserRepository;
import com.company.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Utility {
    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;

    public UserToken checkToken(ReqToken reqToken) {
        Long userId = reqToken.getUserId();
        String token = reqToken.getToken();
        if (userId == null || token == null) {
            throw new HospitalException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
        }
        User user = userRepository.findUserByIdAndActive(userId, EnumAvailableStatus.ACTIVE.value);
        if (user == null) {
            throw new HospitalException(ExceptionConstants.USER_NOT_FOUND, "User not found");
        }
        UserToken userToken = userTokenRepository.findUserTokenByUserAndTokenAndActive(user, token, EnumAvailableStatus.ACTIVE.value);
        if (userToken == null) {
            throw new HospitalException(ExceptionConstants.INVALID_TOKEN, "Invalid token");
        }
        return userToken;
    }
}
