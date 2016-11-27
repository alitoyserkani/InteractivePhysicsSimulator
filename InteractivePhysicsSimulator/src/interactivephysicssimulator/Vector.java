package interactivephysicssimulator;


public class Vector {
    
    //field
    double xComponent;
    double yComponent;
    double magnitude;
    
    //contructor
    public Vector(double xC, double yC) {
        this.xComponent = xC;
        this.yComponent = yC;
        this.magnitude = Math.sqrt(this.xComponent*this.xComponent+this.yComponent*this.yComponent);
       
    }
    //subtracts two vectors together
    public Vector subtract(Vector other) {
        return new Vector(this.xComponent-other.xComponent,this.yComponent-other.yComponent);
    }
    //adds to vectors together
    public Vector add(Vector other) {
        return new Vector(this.xComponent+other.xComponent,this.yComponent+other.yComponent);
    }
    //takes the dotproduct of two vectors
    public double dotProduct(Vector other) {
        return (this.xComponent * other.xComponent) + (this.yComponent * other.yComponent);
    }
    //shortest distance from point to line essentially 
    public Vector projectedOnto(Vector other) {
        Vector v = other.scalarMultiplyBy((this.dotProduct(other))/(Math.pow(other.magnitude, 2)));
        return new Vector(v.xComponent,v.yComponent);
    }
    //scalar projection instead of vector projection
    public double getShortestDist(Vector other) {
        double angle = this.getAngle(other);
        return this.magnitude*Math.sin(angle);
    }
    
    //gets angle between two vectors
    public double getAngle(Vector other) {
        double numerator = this.dotProduct(other);
        double denom = this.magnitude*other.magnitude;
        return Math.acos(numerator/denom);
    }
    //multiplies a vector by a scalar
    public Vector scalarMultiplyBy(double x) {
        return new Vector(this.xComponent*x,this.yComponent*x);
    }
    
    
}
