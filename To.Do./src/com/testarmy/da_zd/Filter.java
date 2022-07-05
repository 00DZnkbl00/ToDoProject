package com.testarmy.da_zd;

@FunctionalInterface
public interface Filter {
    boolean check(Task value);
}
