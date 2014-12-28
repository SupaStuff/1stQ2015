package com.awkwardstudios.noname.custom;

import com.awkwardstudios.noname.noName;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class basicScreen implements Screen {
	 

    protected noName game;
    protected String groupname;

    public String getAssetGroup(){return groupname;}
    
    // constructor to keep a reference to the main Game class
     public basicScreen(noName g){
             this.game = g;
             this.groupname = "load";
     }

     @Override
     public void render(float delta) {
         // update and draw stuff
    	 Gdx.gl.glClearColor(0, 0, 1, 1);
         Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    	 if(Gdx.input.isTouched())game.setScreen(game.splash);;
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
