package di;

import appcontainer.AppComponentsContainerImpl;
import api.AppComponentsContainer;
import config.AppConfig;
import services.GameProcessor;

public class App {

    public static void main(String[] args) throws Exception {
            AppComponentsContainer container = new AppComponentsContainerImpl(AppConfig.class);

            GameProcessor gameProcessor = container.getAppComponent(GameProcessor.class);

            gameProcessor.startGame();
    }
}
