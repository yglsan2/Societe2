package com.benja2.utilitaires;

import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.text.SimpleDateFormat;

public class FormatterLog extends Formatter {
    @Override
    public String format(LogRecord record) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return format.format(new Date()) +
                "Level:" +
                record.getLevel() +
                "/Message:" +
                record.getMessage() +
                "/Classe:" +
                record.getSourceClassName() +
                "/Méthode:" +
                record.getSourceMethodName() +
                "\n";
    }
}




