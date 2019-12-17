package game.renderer;

import game.game_objects.GameObject;

import java.awt.*;

public abstract class Renderder {
    abstract public void render(Graphics g, GameObject master);
}
