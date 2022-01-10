package com.lixing.lixingdemo.FunctionalInterface;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2021-11-29 15:00
 */
@FunctionalInterface
public interface BranchHandler {
    public abstract void branchSelect(Runnable trueBranch, Runnable falseBranch);
}
