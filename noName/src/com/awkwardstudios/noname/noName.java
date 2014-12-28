package com.awkwardstudios.noname;

import com.awkwardstudios.noname.custom.Assets;
import com.awkwardstudios.noname.custom.basicStage;
import com.awkwardstudios.noname.custom.stuff;
import com.awkwardstudios.noname.screens.creditsScreen;
import com.awkwardstudios.noname.screens.endlessScreen;
import com.awkwardstudios.noname.screens.gameOverScreen;
import com.awkwardstudios.noname.screens.loadingScreen;
import com.awkwardstudios.noname.screens.optionsScreen;
import com.awkwardstudios.noname.screens.splashScreen;
import com.awkwardstudios.noname.screens.stage1Screen;
import com.awkwardstudios.noname.screens.titleScreen;
import com.awkwardstudios.noname.test.rainNbucket;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;

public class noName extends Game {
	public splashScreen splash;
	public titleScreen tit;
	optionsScreen options;
	gameOverScreen gameOver;
	stage1Screen stage1;
	endlessScreen endless;
	creditsScreen credits;
	
	rainNbucket rain;
	//testScroll scroll;
	
	loadingScreen load;
	public Assets asset;
	
   @Override
    public void create() {
	   stuff.printf("nothing to see\n");

       Texture.setEnforcePotImages(false);
	   asset = new Assets("data/assets.xml");
	   asset.showJson();
	   //rain = new rainNbucket(this);
	   //scroll = new testScroll(this);
	   //splash = new splashScreen(this); //load loading screen assets while displaying this
	   load = new loadingScreen(this);
	   //tit = new titleScreen(this);
	   //options = new optionsScreen(this);
	   //gameOver = new gameOverScreen(this);
	   //stage1 = new stage1Screen(this);
	   //endless = new endlessScreen(this);
	   //credits = new creditsScreen(this);
	   stuff.printf("about to load\n");
	   //load.load(scroll); //here
	   load.load(new basicStage(this));
	   //stuff.printf("load was queued");
	   //setScreen(scroll); //causes error
    }
}