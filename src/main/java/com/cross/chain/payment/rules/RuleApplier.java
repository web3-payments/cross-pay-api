package com.cross.chain.payment.rules;

public interface RuleApplier<T> {

    default void process(T t) {
        if(!applies(t)){
            return;
        }
        executeProcess(t);
    }
    default boolean applies(T t) {
        return true;
    }
    void executeProcess(T t);
}
