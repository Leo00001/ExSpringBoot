package com.baiyuas.boot.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WindowsCmdCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return getOsName(context).contains("Windows");
    }

    private String getOsName(ConditionContext context) {
        return context.getEnvironment().getProperty("os.name") == null ? "" : context.getEnvironment().getProperty("os.name");
    }
}
