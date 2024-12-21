package com.company;

import java.util.logging.*;

public class FormattLog extends Formatter {
    @Override
    public String format(LogRecord record) {
        return record.getMessage() + "\n";
    }
}
