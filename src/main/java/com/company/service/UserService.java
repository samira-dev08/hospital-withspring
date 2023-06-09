package com.company.service;

import com.company.dto.request.ReqLogin;
import com.company.dto.request.ReqToken;
import com.company.dto.response.RespUser;
import com.company.dto.response.Response;

public interface UserService {
    Response<RespUser> login(ReqLogin reqLogin);

    Response logout(ReqToken reqToken);
}
