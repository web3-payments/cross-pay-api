package com.cross.chain.payment.rules;

/**************
 * @param <T> => Object Source
 * @param <X> => Object Target
 ***************/
public interface ComplexRuleApplier<T, X> {

    default void process(T t, X x) {
        if(!applies(t, x)){
            return;
        }
        executeProcess(t, x);
    }
    boolean applies(T t, X x);
    void executeProcess(T t, X x);
}
