package com.zhurawell.base.data.model.user;

import java.math.BigInteger;

public interface UserLightView {

    BigInteger getId();

    String getEmail();

    String getLogin();

    String getFirstName();

    String getLastName();

}
