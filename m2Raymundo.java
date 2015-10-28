
//globals are pool table and 3 balls
float west, east, north, south;
float middle;
int counter = 0;
int r;
int g;
int b;

String title = "Pool Project";
String name = "Gershom Raymundo";
String yeswall = "Press W to raise the wall";
String restart = "Press R to restart";
String buttonReset = " R ";
String buttonWall = " W ";
String buttonPinkTable = " P ";

float gullX, gullY, gullDX, gullDY;
float ghostX, ghostY, ghostDX, ghostDY;
float rainX, rainY, rainDX, rainDY;

boolean wall;



void setup(){
  size( 700, 500);
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
  
  r = 0;
  g = 250;
  b = 48;
}

void draw(){
  background(249, 250, 35);
  rectMode(CORNERS);
  table();                          ///where all collisions and bounce take place 
  bounce();                         ///bouncing off walls
  collisions();                     ///bouncong off other balls
  show();                           //show the balls and the messages
  buttons();                        // show buttons
  middleWall();                     //middle wall applied 
  messages();

  
}

void table(){
  fill(121, 55, 13);
  stroke(0);
  strokeWeight(0);
  rect(west - 10, north - 10, east + 10, south + 10);
  fill( r, g, b);   //green pool table
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
    stroke(28,37,185);
    strokeWeight(20);
    line(middle, north + 10, middle, south - 10);
    
  //bouncing off the right side
  gullX += gullDX;                                          // red ball movement
    if ( gullX < middle || gullX > east) gullDX *= -1;
  gullY += gullDY;
    if ( gullY < north || gullY > south ) gullDY *= -1;
    
  ghostX += ghostDX;                                          // blue ball movement
    if ( ghostX < middle || ghostX > east) ghostDX *= -1;
  ghostY += ghostDY;
    if ( ghostY < north || ghostY > south ) ghostDY *= -1;
    
  rainX += rainDX;                                          // pink ball movement
    if ( rainX < middle || rainX > east) rainDX *= -1;
  rainY += rainDY;
    if ( rainY < north || rainY > south ) rainDY *= -1;
    }
}

void buttons(){
  float buttonr = 178;
  float buttong = 28;
  float buttonb = 5;
  //reset
  stroke(0);
  fill( buttonr,buttong,buttonb);
  rectMode(CORNER);
  rect( 5, 20, 25, 100);
  
  //remove the wall
  stroke(0);
  fill( buttonr, buttong, buttonb);
  rectMode(CORNER);
  rect(5, 130, 25,100);
  
  //change table color to pink
  stroke(0);
  fill( buttonr, buttong, buttonb);
  rectMode(CORNER);
  rect(5, 240, 25,100);
  
  //animate mouse move across the screen
  stroke(0);
  fill( buttonr, buttong, buttonb);
  rectMode(CORNER);
  rect(5,350,25,100);
  
}

void messages(){
  fill(0);
  text(title, width / 4, 10 );
  text(name, width / 2, 10 );
  text(yeswall, width / 4, 40);
  text(restart, width / 4, 500);
  text(buttonReset , 11, 70);
  text(buttonWall , 11, 185);
  text(buttonPinkTable, 11, 300);
}


//Key presses
void keyPressed(){
  if ( key == 'r') { reset(); }
  if ( key == 'q') { exit(); }
  if ( key == 'w') {
    counter = counter + 1;
    if(counter % 2 > 0){
      wall = true;
    }
    else{
      wall = false;
    }
  }
  if( key == '1') {   
    gullX = random ( middle, east);  gullY = random ( north, south);
  }
  if ( key == '2'){
    ghostX = random ( middle, east);  ghostY= random (north , south);
  }
  if ( key == '3'){
    rainX = random ( middle, east);  rainY= random ( north, south);
  }
}

//mouse press
void mousePressed(){
  if( mouseX > 5 && mouseX < 30 && mouseY > 20 && mouseY < 120){
    reset();
  }
  
  if ( mouseX > 5 && mouseX < 30 && mouseY > 130 && mouseY < 230){
    if( wall ) {
      wall = false;
    }
    
  }
  
  if( mouseX > 5 && mouseX < 30 && mouseY > 240 && mouseY <340){
      counter=  counter+1;
    if (counter % 2 > 0) {
      r=  204;
      g=  118;
      b=  209;
    } else {
      r = 0;
      g = 250;
      b = 48;
    }
  }
  
  //reset balls with mouse clicks
  
  if ( dist(mouseX, mouseY, gullX, gullY) < 15){
    gullX = random ( middle, east);  gullY = random ( north, south);
  }
  if ( dist(mouseX, mouseY, ghostX, ghostY) < 15){
    ghostX = random ( middle, east);  ghostY = random ( north, south);
  }
    if ( dist(mouseX, mouseY, rainX, rainY) < 15 ){
    rainX = random ( middle, east);  rainY = random ( north, south);
  }
}
