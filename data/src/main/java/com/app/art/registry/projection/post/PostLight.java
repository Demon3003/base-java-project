package com.app.art.registry.projection.post;

import java.math.BigInteger;

public interface PostLight {

    BigInteger getId();

    String getTitle();

    String getText();
}