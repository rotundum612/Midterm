
//globals are pool table and 3 balls
float west, east, north, south;
float middle;

String title = "Pool Project";
String name = "Gershom Raymundo";

float gullX, gullY, gullDX, gullDY;
float ghostX, ghostY, ghostDX, ghostDY;
float rainX, rainY, rainDX, rainDY;

boolean wall;



void setup(){
  size( 640, 480);
  west = 50;
  east = width - 50;
  north = 25;
  south = height - 25;
  middle = west + east / 2;
 // 
  reset();
}

void reset(){
  
  //balls location
  gullX = random ( middle, east);  gullY = random ( north, south);            //red ball
  ghostX = random ( middle, east);  ghostY= random (north , south);           //blue ball
  rainX = random ( middle, east);  rainY= random ( north, south);             //pink ball
   
  //speed of balls (random)
  gullDX = random ( 1, 3);  gullDY = random ( 1, 3);
  ghostDX = random ( 1, 3);  ghostDY = random ( 1, 3);
  rainDX = random ( 1, 3);  rainDY = random ( 1, 3);
  
}

void draw(){
  background(249, 250, 35);
  rectMode(CORNERS);
  table();                          ///where all collisions and bounce take place 
  bounce();                         ///bouncing off walls
  collisions();                     ///bouncong off other balls
  show();                           //show the balls and the messages
  messages();                       //directions
  middleWall();
  
}

void table(){
  fill(121, 55, 13);
  stroke(0);
  strokeWeight(0);
  rect(west - 10, north - 10, east + 10, south + 10);
  fill( 0, 250, 48);   //green pool table
  stroke(0);
  strokeWeight(0);
  rectMode(CORNERS);
  rect( west, north, east, south);

}

void bounce(){
  gullX += gullDX;                                          // red ball movement
  if ( gullX < west || gullX > east) gullDX *= -1;
  gullY += gullDY;
  if ( gullY < north || gullY > south ) gullDY *= -1;
  
  ghostX += ghostDX;                                          // blue ball movement
  if ( ghostX < west || ghostX > east) ghostDX *= -1;
  ghostY += ghostDY;
  if ( ghostY < north || ghostY > south ) ghostDY *= -1;
  
  rainX += rainDX;                                          // pink ball movement
  if ( rainX < west || rainX > east) rainDX *= -1;
  rainY += rainDY;
  if ( rainY < north || rainY > south ) rainDY *= -1;
}

void collisions(){
  float tmp;
  
  if ( dist ( gullX , gullY, ghostX, ghostY) < 30){
    tmp = ghostDX;  ghostDX = gullDX;  gullDX = tmp;
    tmp = ghostDY;  ghostDY = gullDY;  gullDY = tmp;
  }
  
  if ( dist ( gullX , gullY, rainX, rainY) < 30){
    tmp = rainDX;  rainDX = gullDX;  gullDX = tmp;
    tmp = rainDY;  rainDY = gullDY;  gullDY = tmp;
  }
  
  if ( dist ( rainX , rainY, ghostX, ghostY) < 30){
    tmp = ghostDX;  ghostDX = rainDX;  rainDX = tmp;
    tmp = ghostDY;  ghostDY = rainDY;  rainDY = tmp;
  }
}

 void show(){
   fill( 255, 0, 0);                  // red ball
   ellipse( gullX, gullY, 30, 30);
   
   fill( 0, 0, 255);                  // blue ball
   ellipse( ghostX, ghostY, 30, 30);
   
   fill( 227, 21, 255);               // pink ball
   ellipse( rainX, rainY, 30, 30);
   
   
}

void middleWall(){
    if (wall) { 
    float middle = (east + west) / 2;
    stroke(10);
    fill( 188, 180, 28);
    line(middle, north + 10, middle, south - 10);
  }
}

void messages(){
  fill(0);
  text(title, width / 4, 40 );
  text(name, width / 4, 50 );
}

//Key presses
void keyPressed(){
  if ( key == 'r') { reset(); }
  if ( key == 'q') { exit(); }
  if ( key == 'w') { wall = true; }
  if ( key == 'e') { wall = false; }
}
