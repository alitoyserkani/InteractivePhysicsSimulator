package interactivephysicssimulator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alitoyserkani
 */
public class Line {
    //fields
    double xVertice1, yVertice1;
    double xVertice2, yVertice2;
    Vector slope;
    
    //constuctor
    public Line(double xV1, double yV1, double xV2, double yV2) {
        this.xVertice1 = xV1;
        this.yVertice1 = yV1;
        this.xVertice2 = xV2;
        this.yVertice2 = yV2;
        
        
        if (xV2-xV1 == 0) this.slope = new Vector(0.000001,yV2-yV1);
        
        else this.slope = new Vector(xV2-xV1,yV2-yV1);
        
      
    }
    
    public Vector getNormal() { //gets the normal vector of the line  
        //System.out.println("XN " + -this.slope.yComponent/this.slope.magnitude);
        //System.out.println("YN " + this.slope.xComponent/this.slope.magnitude);
        return new Vector(-this.slope.yComponent/this.slope.magnitude, this.slope.xComponent/this.slope.magnitude);
    }
}
