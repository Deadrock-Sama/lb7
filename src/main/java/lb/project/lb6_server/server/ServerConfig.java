package lb.project.lb6_server.server;

import lb.project.lb6_server.lib.senders.*;
import lb.project.lb6_server.lib.ui.ConsoleController;
import lb.project.lb6_server.lib.ui.UIController;
import lb.project.lb6_server.server.data.IWorkersRepository;
import lb.project.lb6_server.server.data.savers.ISaver;
import lb.project.lb6_server.server.data.savers.json.JsonSaver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

@Configuration
@ComponentScan({"lb.project.lb6_server.server", "lb.project.lb6_server.lib"})
public class ServerConfig {

    @Bean("CurrentKeeper")
    public ISaver getJsonKeeper() {
        keeper = new JsonSaver();
        return keeper;
    }

    @Bean("WorkersHashTable")
    @DependsOn("CurrentKeeper")
    public IWorkersRepository getWorkersRepository() {
        return keeper.load();
    }

    @Bean("ConsoleController")
    public UIController getConsoleController() {
        return new ConsoleController();
    }

    @Bean("ServerChannel")
    public ExchangeChannel getChannel() {

        String port = System.getenv("SERVER_PORT");
        if (port==null) {
            System.out.println("Не указан порт.");
            System.exit(999);
        }
        int serverPort = Integer.valueOf(port);
        String address = "127.0.0.1";

        SocketAddress serverAddress =  new InetSocketAddress(address, serverPort);

        return new ExchangeChannel(serverAddress);

    }

    private ISaver keeper;



}
