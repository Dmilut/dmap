package ru.dz.vita2d.init;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author dmilut
 */

@Configuration
@ComponentScan("ru.dz.vita2d")
@Import(value = { PersistenceJPAConfig.class })
public class AppConfig {

}
