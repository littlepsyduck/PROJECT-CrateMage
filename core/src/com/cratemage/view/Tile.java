package com.cratemage.view;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tile {
    TileSet tileSet;
    private TextureRegion textureRegion;
    int val;
    int tileSize = 16;
    public Tile(){
        tileSize = 16;
    }

    public TileSet getTileSet() {
        return tileSet;
    }

    public void setTileSet(TileSet tileSet) {
        this.tileSet = tileSet;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public int getTileSize() {
        return tileSize;
    }

}
