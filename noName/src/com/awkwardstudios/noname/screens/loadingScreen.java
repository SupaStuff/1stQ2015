package com.awkwardstudios.noname.screens;

import com.awkwardstudios.noname.noName;
import com.awkwardstudios.noname.custom.basicScreen;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Logger;



public class loadingScreen extends basicScreen {
	 

    basicScreen screen;
    Logger logger;

    
    public void load(basicScreen s)
    {
    	logger.info("READY...");
    	screen = s;
    	game.asset.loadGroup(screen.getAssetGroup());
        //screen.game.asset.load("data/bucket.png", Texture.class);
    	//screen.game.asset.load("data/drop.wav", Sound.class);
    	//screen.game.asset.load("data/drop.wav", Sound.class);
    	//screen.game.asset.load("data/rain.mp3", Music.class);
        logger.info("done queuing");
        game.setScreen(this);
    }
    // constructor to keep a reference to the main Game class
     public loadingScreen(noName g){
    	super(g);
    	logger = new Logger("loading", Logger.INFO);
     }
     
     @Override
     public void render(float delta) {
    	 logger.info("RENDERING");
    	 if(game.asset.update())
    		 {
    		 	logger.info("setting screen");
    		 	game.setScreen(screen);
    		 	logger.info("screen set");
    		 }
    	 
    	 logger.info(Float.toString(game.asset.getProgress()));
    	 logger.info("next");
     }


    @Override
     public void resize(int width, int height) {
     }


    @Override
     public void show() {
          // called when this screen is set as the screen with game.setScreen();
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