package proxy.impl;

import proxy.IUserApi;

public class UserApi implements IUserApi {
    @Override
    public String queryUserInfo() {
        return "IUserApi.queryUserInfo() method called";
    }
}
