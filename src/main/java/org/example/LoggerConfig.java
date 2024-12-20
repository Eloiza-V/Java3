package org.example;
import java.io.IOException;
import java.util.logging.*;

public class LoggerConfig {

    //Статичный метод создания логгера. Способен выбрасывать исключения IOException. Принимает аргументом имя класса
    public static Logger createLogger(Class<?> className) throws IOException {
        //Создаем стандартный хэндлер для логгирования в файл "logger.log"
        Handler fileHandler = new FileHandler("logger.log", true);
        //Устанавливаем формат лога (человекочитаемое форматирование) для хэндлера
        fileHandler.setFormatter(new SimpleFormatter());
        // Создаем логгер для поступившего имени класса
        Logger logger = Logger.getLogger(className.getName());
        //Добавляем хэндлер в логгер
        logger.addHandler(fileHandler);
        //Устанавливаем уровень логгирования FINE
        //FINE - логгирование ошибок, предупреждений, исключений и успешных операций
        logger.setLevel(Level.FINE);
        //Отключаем логгирование в консоль, наследующееся по умолчанию от родителя
        logger.setUseParentHandlers(false);
        //Возвращаем логгер с установленной конфигурацией
        return logger;

    }



}
