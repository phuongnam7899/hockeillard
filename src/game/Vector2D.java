package game;

import java.util.Vector;

public class Vector2D {
    public float x;
    public float y;

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }
    public Vector2D(float x,float y){
        this.x = x;
        this.y = y;
    }
    public Vector2D set(float x,float y){
        this.x = x;
        this.y = y;
        return this;
    }
    public Vector2D set(Vector2D otherVector) {return this.set(otherVector.x,otherVector.y);}
    public Vector2D add(float otherX,float otherY){
        this.x += otherX;
        this.y += otherY;
        return this;
    }
    public boolean isEqual(Vector2D other){
        return (this.x == other.x && this.y == other.y);
    }
    public Vector2D add(Vector2D otherVector ){
        return this.add(otherVector.x,otherVector.y);
    }
    public Vector2D subtract(float newX,float newY){
        return this.add(-newX, -newY);
    }
    public Vector2D subtract(Vector2D otherVector ){
        return this.subtract(otherVector.x,otherVector.y);
    }
    public Vector2D scale(float rate){
        this.x *= rate;
        this.y *= rate;
        return this;
    }
    public Vector2D clone() {
        return new Vector2D(this.x,this.y);
    }
    public float getLength(){
        return (float ) Math.sqrt(this.x * this.x + this.y * this.y);
    }
    public Vector2D setLength(float newLength){
        if (newLength != 0){
            float rate = newLength/this.getLength();
            this.scale(rate);
            return this;
        }
        return this;
    }
    public float getAngle(){
        return (float)Math.atan(this.y/this.x);
    }
    public Vector2D setAngle(float angle){
        float length = this.getLength();
        this.x = (float)(length * Math.cos(angle));
        this.y = (float)(length * Math.sin(angle));
        return this;
    }
    public void print(){
        System.out.println(this.x + "-" + this.y);
    }
    public Vector2D rotate(float angleRad){
        float old = this.getAngle();
        return this.setAngle(old + angleRad);
    }
    public Vector2D rotate(int angleDeg){
        float old = this.getAngle();
        return this.setAngle(old + (float) Math.toRadians(angleDeg));
    }
    public Vector2D symmetryX(){
        Vector2D old = this;
        return this.set(old.x,-old.y);
    }
    public Vector2D symmetryY(){
        Vector2D old = this;
        return this.set(-old.x,old.y);
    }
    public Vector2D reverse(){
        Vector2D old = this;
        return this.set(-old.x,-old.y);
    }
}
