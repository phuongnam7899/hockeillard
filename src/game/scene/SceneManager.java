package game.scene;

public class SceneManager {
    public static Scene currentScene;

    public static void signNewScene(Scene newScene) {
        if(currentScene != null) {
            currentScene.clear();
//          currentScene.sound.stop();
        }
        newScene.init();
        currentScene = newScene;
    }
}