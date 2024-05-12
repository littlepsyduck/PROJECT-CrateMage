package com.cratemage.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.cratemage.common.io.XMLReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;


public class GameMap {
    private ArrayList<TileSet> tileSets;
    private ArrayList<ArrayList<ArrayList<Tile>>> tiles;

    private int maxCol = 64;
    private int maxRow = 45;
    private int tileSize = 16;
    public GameMap(){
        maxCol = 64;
        maxRow = 45;
        tileSize = 16;
        tiles = new ArrayList<ArrayList<ArrayList<Tile>>>();
    }

    public void loadMap(String gameMapPath) throws ParserConfigurationException, IOException, SAXException {
        tileSets = XMLReader.getInstance().readTileSets(gameMapPath);
        for(int i = 0; i < tileSets.size(); i++){
            System.out.println(tileSets.get(i).getFirstGid());
        }
        ArrayList<String> layers = XMLReader.getInstance().readMap(gameMapPath);
        for(String layerData : layers){
            ArrayList<ArrayList<Tile>> layerToAdd = new ArrayList<ArrayList<Tile>>();
            String rowData[] = layerData.split("\n");
            for(int i = 1; i < rowData.length; i++){
                String tileData[] = rowData[i].split(",");
                ArrayList<Tile> rowToAdd = new ArrayList<Tile>();
                for(int j = 0; j < tileData.length; j++){
                    rowToAdd.add(loadTile(tileData[j]));
                }
                layerToAdd.add(rowToAdd);

            }
            tiles.add(layerToAdd);
        }
    }
    public void drawMap(SpriteBatch spriteBatch){
        for (ArrayList<ArrayList<Tile>> tile : tiles) {
            for (int x = 0; x < tile.size(); x++) {

                for (int y = 0; y < tile.get(x).size(); y++) {
                    System.out.println(tile.get(x).size());
                    if (tile.get(x).get(y) != null) {
                        spriteBatch.draw(tile.get(x).get(y).getTextureRegion(), y * tileSize, (maxRow - 1 - x) * tileSize);
                    }
                }
            }
        }
    }
    public Tile loadTile(String data){
        int val = Integer.parseInt(data);
        if(val == 0) return null;
        Tile tile = new Tile();
        for(int i = tileSets.size() - 1; i >= 0; i--){
            if(val >= tileSets.get(i).getFirstGid()){
                tile.setTileSet(tileSets.get(i));
                break;
            }
        }
        int y = (val - tile.getTileSet().getFirstGid()) / tile.getTileSet().getColNum();
        int x = (val - tile.getTileSet().getFirstGid()) % tile.getTileSet().getColNum();
        tile.setTextureRegion(new TextureRegion(tile.getTileSet().loadImage(), x * tileSize, y * tileSize, tileSize, tileSize));

        return tile;
    }

}