package com.company;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LoggerService {
    public static Logger logger = Logger.getLogger(Game.class.getName());

    public void createLogger(String algoName, int level,  Map<String, Integer> result, long time, long memory) throws IOException {

        FileHandler fileHandler = new FileHandler("./logs/" +algoName+"-level-"+level +".log");
        fileHandler.setFormatter(new FormattLog());
        logger.addHandler(fileHandler);
        logger.setLevel(Level.ALL);
        logger.log(Level.FINE, "Algorithm: " + algoName);
        logger.log(Level.FINE, "Level: " + level);
        logger.log(Level.FINE, "Visited States: " + result.get("visited"));
        logger.log(Level.FINE, "Path States: " + result.get("path"));
        logger.log(Level.FINE, "Time: " + time + " milliseconds");
        logger.log(Level.FINE, "Memory Used: " +  memory + " Kb");

        fileHandler.close();
    }
}
