package com.windows.spark;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ClasspathLoader;
import com.mitchellbosecke.pebble.loader.Loader;
import spark.ModelAndView;
import spark.template.pebble.PebbleTemplateEngine;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {

    private static final Path TEMPLATES = Paths.get("templates");
    private static final Loader<String> LOADER = new ClasspathLoader() {
        {
            setPrefix(TEMPLATES.toString());
        }
    };
    private static final PebbleEngine.Builder PEBBLE_ENGINE_BUILDER = new PebbleEngine.Builder().loader(
            LOADER).templateCache(null);

    public static void main(String[] args) {
        staticFileLocation(TEMPLATES.toString());
        externalStaticFileLocation(".");

        get("/", (request, response) -> {
            Map<String, Object> variables = new HashMap<>();
            variables.put("title", "Hello Spark");
            variables.put("mountain", copyPath().resolve("mountain.png"));
            variables.put("canyon", copyPath().resolve("canyon.png"));
            return new ModelAndView(variables, "images.html");
        }, templateEngine());

    }

    public static Path copyPath() {
        return Paths.get("copy");
    }

    public static Path templatePath() {
        return Paths.get("src/main/resources/templates");
    }

    private static PebbleTemplateEngine templateEngine() {
        return new PebbleTemplateEngine(PEBBLE_ENGINE_BUILDER.build());
    }

}
