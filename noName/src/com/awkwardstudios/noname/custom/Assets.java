package com.awkwardstudios.noname.custom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class Assets extends AssetManager{
	

    private static final String TAG = "Assets";
    private Logger logger;
    private ObjectMap<String, Array<Asset>> groups;
	
	public Assets(String assetFile)
	{
		super();

        logger = new Logger(TAG, Logger.INFO);
		loadGroups(assetFile);
	}

	public void loadGroup(String groupName) {
        logger.info("loading group " + groupName);
        
        Array<Asset> assets = groups.get(groupName, null);
        logger.info("Array gotten\n");
        if (assets != null) {
        	logger.info("shit isn't null\n");
                for (Asset asset : assets) {
                	logger.info(asset.path + " type: " + asset.type.toString());
                        this.load(asset.path, asset.type);
                        logger.info("got it");
                }
        }
        else {
                logger.error("error loading group " + groupName + ", not found");
        }
        logger.info("No Problems here, so wtf?!");
}

public void unloadGroup(String groupName) {
        logger.info("unloading group " + groupName);
        
        Array<Asset> assets = groups.get(groupName, null);
        
        if (assets != null) {
                for (Asset asset : assets) {
                        if (this.isLoaded(asset.path, asset.type)) {
                                this.unload(asset.path);
                        }
                }
        }
        else {
                logger.error("error unloading group " + groupName + ", not found");
        }
}

public String showJson()
{
	Json json = new Json();
	return json.prettyPrint(groups);
}
	
	private void loadGroups(String assetFile) {
		logger.info("Loading");
		FileHandle filename = Gdx.files.internal(assetFile);
		if(filename.extension().equalsIgnoreCase("json"))
		{
			logger.info("JSON");
			Json json = new Json();
			//ObjectMap maps = json.fromJson(ObjectMap.class, filename);
			//groups = new ObjectMap<String, Array<Asset>>();
			//groups.putAll(map);
			//json.setElementType(groups.getClass(), "group", String.class);
			//json.setElementType(groups.getClass(), "assets", Asset.class);
			groups = json.fromJson(groups.getClass(), filename);
			
			logger.info("moved from json to groups");
			//logger.info(groups.keys().toString());

	        //Array<Asset> assets = groups.get("rain", null);
            //for (Asset asset : assets) {
            	//logger.info(asset.path + " type: " + asset.type.toString());
            //}
		}
		else
		{

			logger.info("XML");
	        groups = new ObjectMap<String, Array<Asset>>();
	        
	        logger.info("loading file " + assetFile);
	        
	        try {
	                XmlReader reader = new XmlReader();
	                Element root = reader.parse(filename);
	
	                for (Element groupElement : root.getChildrenByName("group")) {
	                        String groupName = groupElement.getAttribute("name", "base");
	                        if (groups.containsKey(groupName)) {
	                                logger.error("group " + groupName + " already exists, skipping");
	                                continue;
	                        }
	                        
	                        logger.info("registering group " + groupName);
	                        
	                        Array<Asset> assets = new Array<Asset>();
	                        
	                        for (Element assetElement : groupElement.getChildrenByName("asset")) {
	                                assets.add(new Asset(assetElement.getAttribute("type", ""),
	                                                     assetElement.getAttribute("path", ""),
	                                                     assetElement.getAttribute("name", "")));
	                        }
	                        
	                        groups.put(groupName, assets);
	                }
	        }
	        catch (Exception e) {
	                logger.error("error loading file " + assetFile + " " + e.getMessage());
	        }
		}
}

private class Asset {
        public Class<?> type;
        public String path;
        @SuppressWarnings("unused") public String name;
        
        public Asset(String type, String path, String name) {
                try {
                        this.type = Class.forName(type);
                        this.path = path;
                        this.name = name;
                } catch (ClassNotFoundException e) {
                        logger.error("asset type " + type + " not found");
                }
        }
}


}
