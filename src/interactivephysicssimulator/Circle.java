package interactivephysicssimulator;

import java.awt.Color;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alitoyserkani
 */
public class Circle {
    
    double radius; //circle fields
    int mass;
    Color color;
    
    Vector velocity;
    Vector momentum;
    
    double xCentre, yCentre;
    
    
    public Circle(double r, int m, Color c, Vector v, double x, double y) { //constructor
        this.mass = m;
        this.radius = r;
        this.color = c;
        this.velocity = v;
        this.momentum = (this.velocity).scalarMultiplyBy(m);
        this.xCentre = x;
        this.yCentre = y;
        
    }
    
    public double getYVel() {
        return (this.velocity.yComponent);
    }
    // needs recoding, not working at the moment
    
    public void changeBalltoBall(Circle other) {
        
        double deltaX = other.xCentre-this.xCentre; //gets xDistance from centres
     
        double v1x = this.velocity.xComponent; //gets horizantal and vertical velocities for balls
        double v2x = other.velocity.xComponent;
        double v1y = this.velocity.yComponent;
        double v2y = other.velocity.yComponent;
                     
        double m1 = this.mass; //masses for colliding balls
        double m2 = other.mass;
        
        //CALCULATING the prime velocities (after velocited following collision), using
        // algebreacially determined derivation by hand
        // this part is sketch ..................
        double a = m2*m2 + m1*m2; 
        double b1 = -2*(m1*v1x+m2*v2x)*m2;
        double b2 = -2*(m1*v1y+m2*v2y)*m2;
        double c1 = m1*m1*v1x*v1x + m1*m2*v2x*v2x - Math.pow(m1*v1x+m2*v2x, 2);
        double c2 = m1*m1*v1y*v1y + m1*m2*v2y*v2y - Math.pow(m1*v1y+m2*v2y, 2);
        
        double v1Px, v1Py, v2Px, v2Py;
        
        if (deltaX >= 0) {
            v2Px = (-b1-Math.sqrt(b1*b1-4*a*c1))/(2*a);
            v2Py = (-b2-Math.sqrt(b2*b2-4*a*c2))/(2*a);
        } else {
            v2Px = (-b1+Math.sqrt(b1*b1-4*a*c1))/(2*a);
            v2Py = (-b2+Math.sqrt(b2*b2-4*a*c2))/(2*a);            
        }

        
        v1Px = (m1*v1x + m2*v2x - m2*v2Px)/m1;
        v1Py = (m1*v1y + m2*v2y - m2*v2Py)/m1;
    
        System.out.println(v1Px + " " + v1x);
        
        //changes velocity compenents of respective balls after collision
        this.velocity.xComponent = v1Px; 
        this.velocity.yComponent = v1Py;
        other.velocity.xComponent = v2Px;
        other.velocity.yComponent = v2Py;
        
    }
    
    public void changeLinetoBall(Line line) { //accounts for ball to line collision 
        
        Vector vD = line.getNormal(); //gets reduced normal vector of line

        
        Vector v = new Vector(this.xCentre-line.xVertice1,this.yCentre-line.yVertice1);
        Vector vP = v.projectedOnto(line.slope);
        double deltaX = this.xCentre - (line.xVertice1+vP.xComponent); //gets xDistance from ballCentre
        double deltaY = this.yCentre - (line.yVertice1+vP.yComponent);

 
        if  (deltaY == 0) {
            
        } else if (deltaY < 0)  {
            
        } else if (deltaY > 0) {
 
            vD.xComponent = -vD.xComponent;
            vD.yComponent = -vD.yComponent;
            //flips normal vector compents depepnding on above or below collision point

  
        
        this.velocity = vD.scalarMultiplyBy(Math.max(0, this.velocity.magnitude-1));
        //basic accountation for loss in energy, doesnt this allow the direction to change

    }
    
}
}
