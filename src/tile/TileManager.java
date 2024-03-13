package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    Tile[] tile;

    int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp  = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage(){

        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/greenLand.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/snowLand.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/snowTree3.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/landTree1.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col < gp.maxScreenCol && row < gp.maxScreenRow){

                String line = br.readLine();

                while(col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch (Exception e){

        }


    }
    public void draw(Graphics2D g2){

        //yeşilik için
//        for (int i = 0; i <= gp.maxScreenRow ; i++) {
//            for (int j = 0; j <= gp.maxScreenCol /2; j++) {
//                g2.drawImage(tile[0].image, i * gp.originalTileSize * gp.scale, j * gp.originalTileSize * gp.scale, gp.tileSize, gp.tileSize, null);
//            }
//        }
//
//        //kar için
//        for (int i = 15; i < gp.maxScreenCol ; i++) {
//            for (int j = 0; j < gp.maxScreenRow; j++) {
//                g2.drawImage(tile[1].image, i * gp.originalTileSize * gp.scale, j* gp.originalTileSize * gp.scale, gp.tileSize, gp.tileSize, null);
//            }
//        }

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){

            int tileNum = mapTileNum[col][row];

//            int worldX = col * gp.tileSize;
//            int worldY = row * gp.tileSize;

            g2.drawImage( tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol){
                col = 0;
                x=0;
                row++;
                y += gp.tileSize;
            }
        }

    }

}
