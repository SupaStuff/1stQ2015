package com.awkwardstudios.noname.test;


import java.util.Iterator;

import com.awkwardstudios.noname.noName;
import com.awkwardstudios.noname.custom.basicScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.TimeUtils;

public class rainNbucket extends basicScreen {
	 

	OrthographicCamera camera;
	SpriteBatch batch;
	Texture dropImage;
	Texture bucketImage;
	Rectangle bucket;
	Sound dropSound;
	Music rainMusic;
	Vector3 touchPos;
	Array<Rectangle> raindrops;
	Logger logger;
	long lastDropTime;
	

    // constructor to keep a reference to the main Game class
     public rainNbucket(noName g){
             super(g);
             this.groupname = "rain";
             logger = new Logger("RAINING", Logger.INFO);

     		camera = new OrthographicCamera();
     		camera.setToOrtho(false, 800, 480);

     		batch = new SpriteBatch();
     		bucket = new Rectangle();
     		 bucket.x = 480 / 2 - 64 / 2;
     		 bucket.y = 20;
     		 bucket.width = 64;
     		 bucket.height = 64;
     		 
     		 raindrops = new Array<Rectangle>();
     }
     
     @Override
     public void render(float delta) {
    	 logger.info("Guess who's rendering");
         // update and draw stuff
    	 Gdx.gl.glClearColor(0, 0, 0.2f, 1);
 		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
 		
camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bucketImage, bucket.x, bucket.y);
		 for(Rectangle raindrop: raindrops) {
			    batch.draw(dropImage, raindrop.x, raindrop.y);
			 }
		batch.end();
		
		 if(Gdx.input.isTouched()) {
			    touchPos = new Vector3();
			    touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			    camera.unproject(touchPos);
			    bucket.x = touchPos.x - 64 / 2;
			 }
		 if(Gdx.input.isKeyPressed(Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		 if(Gdx.input.isKeyPressed(Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();
		 if(bucket.x < 0) bucket.x = 0;
		 if(bucket.x > 480 - 64) bucket.x = 480 - 64;
		 if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();
		 Iterator<Rectangle> iter = raindrops.iterator();
		 while(iter.hasNext()) {
		    Rectangle raindrop = iter.next();
		    raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
		    if(raindrop.y + 64 < 0) iter.remove();

			 if(raindrop.overlaps(bucket)) {
			       dropSound.play();
			       iter.remove();
			    }
		 }

     }
     private void spawnRaindrop() {
 	    Rectangle raindrop = new Rectangle();
 	    raindrop.x = MathUtils.random(0, 480-64);
 	    raindrop.y = 800;
 	    raindrop.width = 64;
 	    raindrop.height = 64;
 	    raindrops.add(raindrop);
 	    lastDropTime = TimeUtils.nanoTime();
 	 }

    @Override
     public void resize(int width, int height) {
     }


    @Override
     public void show() {
          // called when this screen is set as the screen with game.setScreen();
    	

		 spawnRaindrop();
		 dropImage = game.asset.get("data/droplet.png", Texture.class);
			bucketImage = game.asset.get("data/bucket.png", Texture.class);
			dropSound = game.asset.get("data/drop.wav", Sound.class);
			rainMusic = game.asset.get("data/rain.mp3", Music.class);
	      // start the playback of the background music immediately
	      rainMusic.setLooping(true);
	      rainMusic.play();
    	
    	/*
    	dropImage = game.asset.get("data/droplet.png", Texture.class);//This
      	 logger.info("dropImage");
	      bucketImage = game.asset.get("data/bucket.png", Texture.class);
	    	 logger.info("bucket");
	      
	      // load the drop sound effect and the rain background "music"
	      dropSound = game.asset.get("data/drop.wav", Sound.class);
	    	 logger.info("dropSound");
	      rainMusic = game.asset.get("data/rain.mp3", Music.class);
	    	 logger.info("rainMusic");
	      
	      // start the playback of the background music immediately
	      rainMusic.setLooping(true);
	      rainMusic.play();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		batch = new SpriteBatch();
		bucket = new Rectangle();
		 bucket.x = 480 / 2 - 64 / 2;
		 bucket.y = 20;
		 bucket.width = 64;
		 bucket.height = 64;
		 
		 raindrops = new Array<Rectangle>();
		 spawnRaindrop();
		 */
     }


    @Override
     public void hide() {
          // called when current screen changes from this to a different screen
     }


    @Override
     public void pause() {
     }


    @Override
     public void resume() {
     }


    @Override
     public void dispose() {
             // never called automatically
     }
}