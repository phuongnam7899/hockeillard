package game.box_colider;

import game.Vector2D;
import game.game_objects.GameObject;

public class BoxColider {
    Vector2D position;
    int radius;
    Vector2D anchor;

    public BoxColider(GameObject master, int radius) {
        this.position = master.position;
        this.anchor = master.anchor;
        this.radius = radius;
    }
    public Vector2D center(){
        float x = this.position.x + radius;
        float y = this.position.y + radius;
        return new Vector2D(x,y);
    }
    public boolean intersected(BoxColider other) {
        float centerDistance = (float) (Math.sqrt(Math.pow(this.center().x - other.center().x,2) + Math.pow(this.center().y - other.center().y,2)));
        return centerDistance < this.radius + other.radius;
    }
}
