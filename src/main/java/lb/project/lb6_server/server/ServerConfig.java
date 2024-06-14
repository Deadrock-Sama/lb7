package lb.project.lb6_server.server;

import lb.project.lb6_server.lib.senders.*;
import lb.project.lb6_server.lib.ui.ConsoleController;
import lb.project.lb6_server.lib.ui.UIController;
import lb.project.lb6_server.server.data.savers.ISaver;
import lb.project.lb6_server.server.data.savers.json.JsonSaver;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

@Configuration
@ComponentScan({"lb.project.lb6_server.server", "lb.project.lb6_server.lib.*"})

@EnableJpaRepositories(basePackages =
        "lb.project.lb6_server.server.data.savers.db")
@EntityScan("lb.project.lb6_server.lib.entities")
public class ServerConfig {

    @Bean("ConsoleController")
    public UIController getConsoleController() {
        return new ConsoleController();
    }

    @Bean("ServerChannel")
    public ServerExchangeChannel getChannel() {

        String port = System.getenv("SERVER_PORT");
        if (port==null) {
            System.out.println("Не указан порт.");
            System.exit(999);
        }
        int serverPort = Integer.valueOf(port);
        String address = "127.0.0.1";

        SocketAddress serverAddress =  new InetSocketAddress(address, serverPort);

        return new ServerExchangeChannel(serverAddress);

    }

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                //.url("jdbc:postgresql://pg:5432/studs?currentSchema=s409677")
                //.username("s409677")
                //.password("iZIywMzhnKf4Bd3L")
                .url("jdbc:postgresql://localhost:5432/lb7?currentSchema=s409677")
               .username("postgres")
               .password("postgres")
        .build();


//                .driverClassName("org.postgresql.Driver")
//                .url("jdbc:postgresql://pg:5432/studs")
//                .username("s409677")
//                .password("iZIywMzhnKf4Bd3L")
//                .build();
    }

}
