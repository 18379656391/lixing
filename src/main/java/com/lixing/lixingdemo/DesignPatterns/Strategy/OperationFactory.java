package com.lixing.lixingdemo.DesignPatterns.Strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/12/3
 */
public class OperationFactory {

    public static Map<String, Operation> operationMap = new HashMap<>();

    static {
        operationMap.put("add", new AddOperation());
        operationMap.put("sub", new SubOperation());
        operationMap.put("multi", new MultiOperation());
        operationMap.put("div", new DivOperation());
    }

    public static Optional<Operation> getOperation(String operationName) {
        return Optional.ofNullable(operationMap.get(operationName));
    }
}
