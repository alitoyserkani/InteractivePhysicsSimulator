package interactivephysicssimulator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Simulator implements MouseListener {
    
    JPanel drawingPanel; //screen area to draw simulation
    
    double xMouse, yMouse; //current mouse positions
    double clickedX, clickedY; //mouse event click placing
    boolean isObjPlaced = true; //has the object selected been placed?
    int collisionIndex1, collisionIndex2; //which indeces of the arrays are colliding
    
    boolean isLineInit = true; //has the line been start
    boolean isLinePlaced = true; // has the second vertice been placed
    double lineV1x, lineV1y, lineV2x, lineV2y; //line vertice 1 and 2
    
    
    Circle addingCirc; //ball that is to be added to the simulation
    
    ArrayList<Circle> balls = new ArrayList(); //arrays for ball a line
    ArrayList<Line> lines = new ArrayList();
    
    ArrayList<Vector> ballVels = new ArrayList();
    
    Vector acceleration = SimulatorGUI.acceleration; //acceleration (gravity) of the simulation
    
    
    
    
     
    //contructor
    public Simulator(JPanel j) {
        this.drawingPanel = j;
        
    }

    
    //runs when a ball is being added to the screen
    public void addingBall(Circle ball) {
        
        addingCirc = ball; //sets ball as ball created on BallGUI
        //Circle c = new Circle(addingCirc.mass, (int) addingCirc.radius, addingCirc.color, addingCirc.velocity, addingCirc.xCentre, addingCirc.yCentre);
        isObjPlaced = false; 
        while (isObjPlaced == false) { //until object is placed
            checkMouseLoc(); //update xMouse and yMouse
            addingCirc.xCentre = xMouse; //set the mouse location to the centre of the circle
            addingCirc.yCentre = yMouse;  
            this.drawingPanel.addMouseListener(this); //initialize listener for click location     
            draw(); //draw the paused simulation and object to be added

        }
        //Circle c = new Circle(addingCirc.mass, (int) addingCirc.radius, addingCirc.color, addingCirc.velocity, addingCirc.xCentre, addingCirc.yCentre);
        
        balls.add(addingCirc);//once object is placed, add it to the array of balls
        ballVels.add(addingCirc.velocity);

    }
    //runs when a line is being added
    public void addingLine() {
        
       // this.drawingPanel.addMouseListener(this);
        isLineInit = false; 

        
        
        while (isLineInit == false) { //while first vertice has not been placed
            checkMouseLoc();
            lineV1x = xMouse; //update first vertice with mouse locations
            lineV1y = yMouse;
            this.drawingPanel.addMouseListener(this); //listens until click is made
            draw(); //draws small circle until click
        }
        
        isLinePlaced = false;
        
        while (isLinePlaced == false) { //while second vertice has not been placed
            checkMouseLoc();
            lineV2x = xMouse; //draw line to form verticle 1 to mouse location
            lineV2y = yMouse;
            this.drawingPanel.addMouseListener(this); //listens for a click
            draw(); //drws line until click
        }
        
        
        Line l = new Line(lineV1x,lineV1y,lineV2x,lineV2y);
        lines.add(l); //ads finished lines to array of lines
        
    }
    //finds the current location of the mouse on the screen by using mouse info
    public void checkMouseLoc() {
        xMouse = MouseInfo.getPointerInfo().getLocation().x-5;
        yMouse = MouseInfo.getPointerInfo().getLocation().y-50;
       
    }
    
    //draws the simulation by getting a buffered image
    public void draw() {
        
        Image img;
        
        img = drawSimulation();
              
        Graphics g = this.drawingPanel.getGraphics();
        
        g.drawImage(img, 0, 0, this.drawingPanel);
        
    }
    

    public Image drawSimulation() {
        BufferedImage b = new BufferedImage(this.drawingPanel.getWidth(),this.drawingPanel.getHeight(),BufferedImage.TYPE_INT_RGB);        
        Graphics2D gr = (Graphics2D) b.getGraphics();
        
        gr.setColor(Color.gray); //sets background and covers up previous frames
        gr.fillRect(0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE); 
        
        double xC, yC, r; 
        for (int i=0; i<balls.size(); i++) { //draws all the balls in the ball array onto the screen
            gr.setColor(balls.get(i).color);
            
            xC = balls.get(i).xCentre;
            yC = balls.get(i).yCentre;
            r = balls.get(i).radius;
            gr.fillOval((int)(xC-r), (int)(yC-r), (int)(2*r), (int)(2*r));
        }
        
        gr.setColor(Color.WHITE);
        
        double xV1, yV1, xV2, yV2;
        for (int i=0;i<lines.size();i++) { //draws all the lines in the line array onto the scrren
            
            xV1 = lines.get(i).xVertice1;
            yV1 = lines.get(i).yVertice1;
            xV2 = lines.get(i).xVertice2;
            yV2 = lines.get(i).yVertice2;
            gr.setStroke(new BasicStroke(3));
            gr.drawLine((int)xV1, (int)yV1, (int)xV2, (int)yV2);
        }
        
        if (isObjPlaced == false) { //if there is a ball being placed draw that ball being added
            gr.setColor(addingCirc.color);
            
            xC = addingCirc.xCentre;
            yC = addingCirc.yCentre;
            r = addingCirc.radius;
            gr.fillOval((int)(xC-r), (int)(yC-r), (int)(2*r), (int) (2*r));
        }
        
        if (isLineInit == false) { //draw a small white ball to indicate a line is being placed
            gr.setColor(Color.WHITE);
            gr.fillOval((int)lineV1x-10, (int)lineV1y-10, 20, 20);
            
        }
        
        if (isLinePlaced == false) { // draws lines to the second vertice
            gr.setColor(Color.WHITE);
            gr.setStroke(new BasicStroke(3));
            gr.drawLine((int)lineV1x,(int)lineV1y, (int)lineV2x, (int)lineV2y);
        }
    
        return b; //return the buffered image
    }
    
    
    public boolean hasCollidedWithBall() { //check for a ball to ball collision
        
        double currDistBetween;
        double xCompSum, yCompSum;
        double shortestDistToCollision;
        
        if (balls.size() <= 1) { //doesn't run if there is only 1 or no balls
            return false;
        } else {
            //checkk distances between points and radius sums comparison
            for (int i=0; i<balls.size(); i++) {
                
                for (int j=i+1; j<balls.size();j++) { 
                    
                    xCompSum = Math.pow(balls.get(i).xCentre-balls.get(j).xCentre, 2);
                    yCompSum = Math.pow(balls.get(i).yCentre-balls.get(j).yCentre, 2);
                    currDistBetween = Math.sqrt(xCompSum+yCompSum);
                    
                    shortestDistToCollision = balls.get(i).radius+balls.get(j).radius;
                    if (currDistBetween < shortestDistToCollision) {                        
                        collisionIndex1 = i; collisionIndex2 = j; //saves colliding ball indeces                       
                        return true;
                    }             
                }
            }
            return false;
        }
        
        
        
    }
    
    public boolean hasCollidedWithLine() { //checks for ball to line collisions
        double closestDist;
        double shortestDistToCollision;
        double perpSlope;
        Vector a,b;
        
        //System.out.println(balls.size() + " " + lines.size());
        
        if (balls.size() == 0 || lines.size() < 1 ) {
            return false;
        }
        //finds shortest distance from ball centre to line with comparison to radius
        for (int i=0; i<balls.size(); i++) {
            for (int j=0; j<lines.size();j++) {
                
                a = new Vector(lines.get(j).xVertice1-balls.get(i).xCentre,lines.get(j).yVertice1-balls.get(i).yCentre);
                b = new Vector(lines.get(j).xVertice1-lines.get(j).xVertice2,lines.get(j).yVertice1-lines.get(j).yVertice2);
                //System.out.println("X " + a.xComponent);
                closestDist = a.getShortestDist(b);
          
                shortestDistToCollision = balls.get(i).radius;
                
                
                if (closestDist < shortestDistToCollision) {
                    collisionIndex1 = i; collisionIndex2 = j; //saves colliding indeces
                    return true; //ADD index points as well
                }
                
            }
        }
        return false;

        
    }
    

    

    
    public void changeComponents() { //changes velocites after each frame
        
       
      
            for (int i=0; i< balls.size(); i++) {

                

                balls.get(i).xCentre += balls.get(i).velocity.xComponent;
                balls.get(i).yCentre += balls.get(i).velocity.yComponent;
    
                balls.get(i).velocity.xComponent = ((balls.get(i).velocity).add(acceleration)).xComponent;
                balls.get(i).velocity.yComponent = ((balls.get(i).velocity).add(acceleration)).yComponent;
                
                double xC = balls.get(i).velocity.xComponent;
                double yC = balls.get(i).velocity.yComponent;
                balls.get(i).velocity.magnitude = Math.sqrt(xC*xC+yC*yC);

          
            }
            
            
        }
        
    
    

    
    
    
    
    
    
    public void simulate() { //calls collision checkers and velocity changers
        
        //System.out.println(balls.size());
        
        if (hasCollidedWithBall() == true) {

            balls.get(collisionIndex1).changeBalltoBall(balls.get(collisionIndex2));
            
        } else if (hasCollidedWithLine() == true) {

            balls.get(collisionIndex1).changeLinetoBall(lines.get(collisionIndex2));
        }
        
        changeComponents();
        
        draw();
        
    }
    
    
    

    @Override
    public void mouseClicked(MouseEvent e) { //updates cariables when mouse is clicked
        clickedX = e.getPoint().x;
        clickedY = e.getPoint().y;
        
        isObjPlaced = true;
        isLineInit = true;
        isLinePlaced = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}


