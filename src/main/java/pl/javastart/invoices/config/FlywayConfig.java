package pl.javastart.invoices.config;


import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.flywaydb.core.Flyway;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class FlywayConfig implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            System.out.println("🔄 [Flyway] Migration starting...");

            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("application.properties"));

            String jdbcUrl = props.getProperty("flyway.url");
            String dbUser = props.getProperty("flyway.user");
            String dbPassword = props.getProperty("flyway.password");

            if (jdbcUrl == null || dbUser == null || dbPassword == null) {
                throw new IllegalStateException("❌ Missing database credentials in application.properties");
            }

            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/invoices");

            Flyway flyway = Flyway.configure()
                    .dataSource(jdbcUrl, dbUser, dbPassword)
                    .locations("classpath:db/migration")
                    .baselineOnMigrate(true)
                    .baselineVersion("1")
                    .load();

            flyway.migrate();

            System.out.println("✅ [Flyway] Migration complete!");

        } catch (IOException e) {
            System.err.println("❌ Failed to load application.properties: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ [Flyway] Error while migration: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("🛑 [Flyway] Cleaning up...");
    }
}
