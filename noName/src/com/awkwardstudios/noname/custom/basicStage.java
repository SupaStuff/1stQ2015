package com.awkwardstudios.noname.custom;

import com.awkwardstudios.noname.noName;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class basicStage extends basicScreen {

	 SpriteBatch spriteBatch;
	 Texture bottomTexture, topTexture, midTexture;
	 Sprite bottom, mid, top;
	 float scrollTimer;
	
    // constructor to keep a reference to the main Game class
     public basicStage(noName g){
             super(g);
    		 groupname = "scroll";
    	     spriteBatch = new SpriteBatch();
    	     scrollTimer = 0.0f;
     }

     @Override
     public void render(float delta) {
    	 scrollTimer+=Gdx.graphics.getDeltaTime()*-1;
	     if(scrollTimer>1.0f)
	         scrollTimer = 0.0f;
	     
	     bottom.setV(scrollTimer);
    	 bottom.setV2(scrollTimer+1);
    	 
    	 top.setV(scrollTimer/3);
    	 top.setV2(scrollTimer/3 + 1);
	     
    	 mid.setRotation(scrollTimer*89);
	     
	     spriteBatch.begin();
	     	bottom.draw(spriteBatch);
	     	mid.draw(spriteBatch);
	     	top.draw(spriteBatch);
	     spriteBatch.end();
     }


     @Override
     public void show() {
          // called when this screen is set as the screen with game.setScreen();
    	 bottomTexture = game.asset.get("data/bg_under.png", Texture.class);
	     bottomTexture.setWrap(TextureWrap.Repeat,TextureWrap.Repeat);
	     bottom = new Sprite(bottomTexture, 0, 0, 480, 800);
	     bottom.setSize(480, 800);
	     
	     topTexture = game.asset.get("data/bg_over.png", Texture.class);
	     topTexture.setWrap(TextureWrap.Repeat,TextureWrap.Repeat);
	     top = new Sprite(topTexture, 0 , 0, 480, 800);
	     top.setSize(480, 800);
	     
	     midTexture = game.asset.get("data/bg_middle2.png", Texture.class);
	     mid = new Sprite(midTexture, 0 , 0, 480, 359);
	     mid.setSize(480, 359);
	     mid.setPosition(0, 220);
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
    
    @Override
    public void resize(int width, int height) {
    }
}