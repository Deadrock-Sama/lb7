package lb.project.lb6_server.client;
import lb.project.lb6_server.lib.senders.*;
import lb.project.lb6_server.lib.ui.ConsoleController;
import lb.project.lb6_server.lib.ui.UIController;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

@Configuration
@ComponentScan
public class ClientConfig {

    @Bean("ConsoleController")
    public UIController getConsoleController() {
        return new ConsoleController();
    }

    @Bean("ClientChannel")
    public ClientExchangeChannel getChannel() {

        String cPort = System.getenv("CLIENT_PORT");
        if (cPort == null) {
            System.out.println("Не указан CLIENT_PORT.");
            System.exit(998);
        }
        int clientPort = Integer.valueOf(cPort.trim());

        String sPort = System.getenv("SERVER_PORT");
        if (cPort == null) {
            System.out.println("Не указан SERVER_PORT.");
            System.exit(997);
        }
        int serverPort = Integer.valueOf(sPort.trim());
        String address = "127.0.0.1";

        SocketAddress clientAddress =  new InetSocketAddress(address, clientPort);
        SocketAddress serverAddress =  new InetSocketAddress(address, serverPort);

        return new ClientExchangeChannel(serverAddress, clientAddress);

    }

    @Bean
    public DataSource getDataSource() {
     //атстаньen
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://pg:5432/studs")
                .username("s409677")
                .password("iZIywMzhnKf4Bd3L")
                //.url("jdbc:postgresql://localhost:5432/lb7?currentSchema=s409677")
                //.username("postgres")
                //.password("postgres")
                .build();


//                .driverClassName("org.postgresql.Driver")
//                .url("jdbc:postgresql://pg:5432/studs")
//                .username("s409677")
//                .password("iZIywMzhnKf4Bd3L")
//                .build();
    }


}
