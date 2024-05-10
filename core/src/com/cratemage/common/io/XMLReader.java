package com.cratemage.common.io;

import com.cratemage.view.TileSet;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;


public class XMLReader {
    private static XMLReader instance;
    private XMLReader() {}
    public static XMLReader getInstance() {
        XMLReader XMLReader;
        if(instance == null){
            instance = new XMLReader();
        }
        return instance;
    }
    public ArrayList<TileSet> readTileSets(String gameMapPath) throws ParserConfigurationException, IOException, SAXException {
        ArrayList<TileSet> tilesets = new ArrayList<TileSet>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(gameMapPath);
        NodeList tilesetData = doc.getElementsByTagName("tileset");
        for(int i = 0; i < tilesetData.getLength(); i++){
            Node node = tilesetData.item(i);
            Node imageNode = node.getChildNodes().item(1);
            TileSet tilesetToAdd = new TileSet();
            tilesetToAdd.setImagePath(imageNode.getAttributes().getNamedItem("source").getNodeValue());
            tilesetToAdd.setFirstGid(Integer.parseInt(node.getAttributes().getNamedItem("firstgid").getNodeValue()));
            tilesetToAdd.setColNum(Integer.parseInt(imageNode.getAttributes().getNamedItem("width").getNodeValue()) / tilesetToAdd.getTileSize());
            tilesetToAdd.setRowNum(Integer.parseInt(imageNode.getAttributes().getNamedItem("height").getNodeValue()) / tilesetToAdd.getTileSize());
            tilesets.add(tilesetToAdd);
        }
        return tilesets;
    }
    public ArrayList<String> readMap(String gameMapPath) throws ParserConfigurationException, IOException, SAXException {
        ArrayList<TileSet>  tileSets = readTileSets(gameMapPath);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(gameMapPath);
        NodeList layerDatas = doc.getElementsByTagName("data");

        ArrayList<String> layers = new ArrayList<String>();
        for(int i = 0; i < layerDatas.getLength(); i++){
            String layer = layerDatas.item(i).getTextContent();
            layers.add(layer);
        }
        return layers;
    }

}