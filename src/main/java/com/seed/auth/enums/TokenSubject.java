package com.seed.auth.enums;

/**
 * Auth模块Token类型枚举
 *
 * @author lishenggui
 * @since 2018/12/7
 */
public enum TokenSubject {
    ACCESS("access"), REFRESH("refresh");

    private final String type;

    TokenSubject(String type) {
        this.type = type;
    }

    public String type() {
        return type;
    }
}
