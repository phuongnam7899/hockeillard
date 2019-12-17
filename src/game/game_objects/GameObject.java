package game.game_objects;

import game.FrameCounter;
import game.box_colider.BoxColider;
import game.renderer.Renderder;
import game.Vector2D;
import interfaces.Physics;

import java.awt.*;
import java.util.ArrayList;

public class GameObject {
    //attribute
    public Renderder renderer;
    public Vector2D position;
    public Vector2D velocity;
    public Vector2D anchor;
    public FrameCounter reTeleportableCounter = new FrameCounter(120);
    public boolean isTeleportable = true;
    public boolean isActive = true;
    FrameCounter frictionCounter = new FrameCounter(180);

    //static
    public static ArrayList<GameObject> gameObjects = new ArrayList<>();

    public static ArrayList<GameObject> botLayer = new ArrayList<>();
    public static ArrayList<GameObject> midLayer = new ArrayList<>();
    public static ArrayList<GameObject> topLayer = new ArrayList<>();

    public static void addNew(GameObject object) {
        gameObjects.add(object);
    }

    public static <E extends GameObject> E recycle(Class<E> cls) {
        E object = findInactive(cls);
        if (object != null){
            object.reactive();
            return object;
        }
        else {
            try {
                return cls.getConstructor().newInstance();
            } catch (Exception ex){
                return null;
            }
//
        }
    }
    public static <E extends GameObject> E findInactive(Class<E> cls){
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject object = gameObjects.get(i);
            if(!object.isActive && cls.isAssignableFrom(object.getClass())){
                return (E)object;
            }
        }
        return null;
    }
    public static <E extends GameObject> ArrayList<E> findObject(Class<E> cls){
        ArrayList<E> objectList = new ArrayList<>();
        for(int i = 0; i< gameObjects.size();i++){
            GameObject object = gameObjects.get(i);
            if(cls.isAssignableFrom(object.getClass()) && object.isActive){
                objectList.add((E)object);
            }
        }
        return objectList;
    }
    public static void clearAll() {
        gameObjects.clear();
        topLayer.clear();
        botLayer.clear();
        midLayer.clear();
    }
    public static void deActiveAll(){
        for (int i = 0; i < gameObjects.size() ; i++) {
            GameObject object = gameObjects.get(i);
            object.deactive();
        }
    }

    public static void runAll() {
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject object = gameObjects.get(i);
            if ( object.isActive){
                object.run();
            }
        }
    }

    public static void renderAll(Graphics g) {
        for (int i = 0; i < botLayer.size(); i++) {
            GameObject object = botLayer.get(i);
            if (object.isActive){
                object.render(g);
            }
        }
        for (int i = 0; i < midLayer.size(); i++) {
            GameObject object = midLayer.get(i);
            if (object.isActive){
                object.render(g);
            }
        }
        for (int i = 0; i < topLayer.size(); i++) {
            GameObject object = topLayer.get(i);
            if (object.isActive){
                object.render(g);
            }
        }
    }

    //constructor
    public GameObject() {
        this.position = new Vector2D();
        this.velocity = new Vector2D();
        this.isActive = true;
        addNew(this);
        this.anchor = new Vector2D(0.5f,0.5f);
    }
    public void run() {
        this.position.add(this.velocity);
        this.limitPosition();
        this.reTeleportable();
    }
    public void render(Graphics g) {
        if(this.renderer != null) {
            this.renderer.render(g,this);
        }
    }
    public static  <E extends GameObject> E findIntersected(Class<E> cls, BoxColider boxColider) {
        for (int i = 0; i < gameObjects.size() ; i++) {
            GameObject object = gameObjects.get(i);
            if(cls.isAssignableFrom(object.getClass())
            && object instanceof Physics
            && object.isActive
            && ((Physics)object).getBoxColider().intersected(boxColider)){
                return (E)object;
            }
        }
        return null;
    }
   public void deactive() {
        this.isActive = false;
    }
    public void reactive() {
        this.isActive = true;
    }
    public void reTeleportable(){
        if( !this.isTeleportable){
            if(this.reTeleportableCounter.run()){
                this.isTeleportable = true;
                this.reTeleportableCounter.reset();

            }
        }else {
            this.reTeleportableCounter.reset();
        }
    }
    public void limitPosition() {

    }
    public void getFriction(float k){
        if(this.frictionCounter.run()){
            float oldVelocity = this.velocity.getLength();
            float newVelocity = (oldVelocity * (1 - k));
            this.velocity.setLength(newVelocity);
        }
    }
}
